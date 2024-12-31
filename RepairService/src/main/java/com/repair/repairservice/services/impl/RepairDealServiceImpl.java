package com.repair.repairservice.services.impl;

import com.repair.repairservice.dto.ActiveDto;
import com.repair.repairservice.dto.ClientDto;
import com.repair.repairservice.dto.WorkerDto;
import com.repair.repairservice.entities.RepairDeal;
import com.repair.repairservice.mappers.RepairDealMapper;
import com.repair.repairservice.messaging.KafkaProducer;
import com.repair.repairservice.repositories.RepairDealRepository;
import com.repair.repairservice.services.RepairDealService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Random;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RepairDealServiceImpl implements RepairDealService {

    KafkaProducer kafkaProducer;
    RepairDealRepository repairDealRepository;
    RepairDealMapper repairDealMapper;
    Random random = new Random();
    WebClient webClient;

    @Transactional
    @Override
    @CircuitBreaker(name = "PersonalService", fallbackMethod = "fallbackClient")
    public String created() {
        final var created = getClient();
        created.setStatus(RepairDeal.Status.CREATED);
        created.setDescription(generateRandomString());
        repairDealRepository.save(created);
        return "Success";
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "PersonalService", fallbackMethod = "fallbackWorker")
    public ActiveDto activated() {
        final var worker = getWorker();
        final var works = repairDealRepository.findAllCreated(RepairDeal.Status.CREATED);
        final var work = works.get(random.nextInt(works.size()));
        final var updatedWork = repairDealMapper.mergeToEntity(worker, work);
        updatedWork.setStatus(RepairDeal.Status.ACTIVE);
        final var activeDto = repairDealMapper.toActive(updatedWork);
        kafkaProducer.sendMessage("to-finalize", activeDto);
        return activeDto;
    }

    @Override
    public RepairDeal getClient() {
        return repairDealMapper.toCreatedEntity(
                webClient.get()
                        .uri("/client")
                        .retrieve()
                        .bodyToMono(ClientDto.class)
                        .block()
        );
    }

    @Override
    public WorkerDto getWorker() {
        return webClient.get()
                .uri("/worker")
                .retrieve()
                .bodyToMono(WorkerDto.class)
                .block();
    }

    @SuppressWarnings("unused")
    public String fallbackClient(Throwable throwable) {
        return "fail";
    }

    @SuppressWarnings("unused")
    public ActiveDto fallbackWorker(Throwable throwable) {
        return new ActiveDto();
    }

    private String generateRandomString() {
        var sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            final var randomChar = (char) ('A' + random.nextInt(26));
            sb.append(randomChar);
        }
        return sb.toString();
    }


}
