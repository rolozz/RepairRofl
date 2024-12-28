package com.repair.repairservice.services.impl;

import com.repair.repairservice.dto.ActiveDto;
import com.repair.repairservice.dto.ClientDto;
import com.repair.repairservice.dto.WorkerDto;
import com.repair.repairservice.entities.RepairDeal;
import com.repair.repairservice.mappers.RepairDealMapper;
import com.repair.repairservice.repositories.RepairDealRepository;
import com.repair.repairservice.messaging.KafkaProducer;
import com.repair.repairservice.services.RepairDealService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Random;

@Service
public class RepairDealServiceImpl implements RepairDealService {

    private final KafkaProducer kafkaProducer;
    private final RepairDealRepository repairDealRepository;
    private final RepairDealMapper repairDealMapper;
    private final Random random = new Random();
    private final WebClient webClient;
    private final RepairDealService self;

    @Autowired
    public RepairDealServiceImpl(
            KafkaProducer kafkaProducer,
            RepairDealRepository repairDealRepository,
            RepairDealMapper repairDealMapper,
            WebClient webClient,
            @Lazy RepairDealService self
    ) {
        this.kafkaProducer = kafkaProducer;
        this.repairDealRepository = repairDealRepository;
        this.repairDealMapper = repairDealMapper;
        this.webClient = webClient;
        this.self = self;
    }

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
        final var works = self.getAll();
        final var work = works.get(random.nextInt(works.size()));
        final var updatedWork = repairDealMapper.mergeToEntity(worker, work);
        updatedWork.setStatus(RepairDeal.Status.ACTIVE);
        final var activeDto = repairDealMapper.toActive(updatedWork);
        kafkaProducer.sendMessage("to-finalize", activeDto);
        return activeDto;
    }



    @Override
    public RepairDeal getClient(){
        return repairDealMapper.toCreatedEntity(
                webClient.get()
                        .uri("/client")
                        .retrieve()
                        .bodyToMono(ClientDto.class)
                        .block()
        );
    }

    @Override
    public WorkerDto getWorker(){
        return webClient.get()
                .uri("/worker")
                .retrieve()
                .bodyToMono(WorkerDto.class)
                .block();
    }

    @Transactional
    @Override
    public List<RepairDeal> getAll(){
        return repairDealRepository.findAllCreated(RepairDeal.Status.CREATED);
    }

    @SuppressWarnings("unused")
    public String fallbackClient(Throwable throwable) {
        return "fail";
    }

    @SuppressWarnings("unused")
    public ActiveDto fallbackWorker(Throwable throwable){
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
