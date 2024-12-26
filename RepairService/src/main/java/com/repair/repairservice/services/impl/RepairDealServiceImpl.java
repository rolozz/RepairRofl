package com.repair.repairservice.services.impl;

import com.repair.repairservice.dto.ActiveDto;
import com.repair.repairservice.dto.ClientDto;
import com.repair.repairservice.dto.WorkerDto;
import com.repair.repairservice.entities.RepairDeal;
import com.repair.repairservice.mappers.RepairDealMapper;
import com.repair.repairservice.repositories.RepairDealRepository;
import com.repair.repairservice.services.RepairDealService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Random;

@Service
public class RepairDealServiceImpl implements RepairDealService {

    private final RepairDealRepository repairDealRepository;
    private final RepairDealMapper repairDealMapper;
    private final Random random = new Random();
    private final WebClient webClient;

    @Autowired
    public RepairDealServiceImpl(RepairDealRepository repairDealRepository, RepairDealMapper repairDealMapper, WebClient webClient) {
        this.repairDealRepository = repairDealRepository;
        this.repairDealMapper = repairDealMapper;
        this.webClient = webClient;
    }

    @Transactional
    @Override
    @CircuitBreaker(name = "PersonalService", fallbackMethod = "fallbackClient")
    public String created() {
        final var created = repairDealMapper.toCreatedEntity(
                webClient.get()
                        .uri("/client")
                        .retrieve()
                        .bodyToMono(ClientDto.class)
                        .block()
        );
        created.setStatus(RepairDeal.Status.CREATED);
        created.setDescription(generateRandomString());
        repairDealRepository.save(created);
        return "Success";
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "PersonalService", fallbackMethod = "fallbackWorker")
    public ActiveDto activated() {
        final var worker = webClient.get()
                .uri("/worker")
                .retrieve()
                .bodyToMono(WorkerDto.class)
                .block();
        final var works = repairDealRepository.findAllCreated(RepairDeal.Status.CREATED);
        final var work = works.get(random.nextInt(works.size()));
        final var updatedWork = repairDealMapper.mergeToEntity(worker, work);
        updatedWork.setStatus(RepairDeal.Status.ACTIVE);
        return repairDealMapper.toActive(updatedWork);
    }

    private String generateRandomString() {
        var sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            final var randomChar = (char) ('A' + random.nextInt(26));
            sb.append(randomChar);
        }
        return sb.toString();
    }

    @SuppressWarnings("unused")
    public String fallbackClient(Throwable throwable) {
        return "fail";
    }

    @SuppressWarnings("unused")
    public ActiveDto fallbackWorker(Throwable throwable){
        return new ActiveDto();
    }

}
