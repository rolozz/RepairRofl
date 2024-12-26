package com.repair.repairservice.services.impl;

import com.repair.repairservice.dto.ClientDto;
import com.repair.repairservice.entities.RepairDeal;
import com.repair.repairservice.mappers.RepairDealMapper;
import com.repair.repairservice.repositories.RepairRepo;
import com.repair.repairservice.services.RepairDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Random;

@Service
public class RepairDealServiceImpl implements RepairDealService {

    private final RepairRepo repairRepo;
    private final RepairDealMapper repairDealMapper;
    private final Random random = new Random();
    private final WebClient webClient;

    @Autowired
    public RepairDealServiceImpl(RepairRepo repairRepo, RepairDealMapper repairDealMapper, WebClient webClient) {
        this.repairRepo = repairRepo;
        this.repairDealMapper = repairDealMapper;
        this.webClient = webClient;
    }

    @Transactional
    @Override
    public void created() {
        var created = repairDealMapper.toCreatedEntity(
                webClient.get()
                        .uri("/client")
                        .retrieve()
                        .bodyToMono(ClientDto.class)
                        .block()
        );
        created.setStatus(RepairDeal.Status.CREATED);
        created.setDescription(generateRandomString());
        repairRepo.save(created);
    }

    private String generateRandomString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            char randomChar = (char) ('A' + random.nextInt(26));
            sb.append(randomChar);
        }
        return sb.toString();
    }

}
