package com.repair.personalservice.services.impl;

import com.repair.personalservice.dto.ClientDto;
import com.repair.personalservice.mappers.ClientMapper;
import com.repair.personalservice.repositories.ClientRepo;
import com.repair.personalservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientMapper clientMapper;
    private final ClientRepo clientRepo;
    private final Random random = new Random();

    @Autowired
    public ClientServiceImpl(ClientMapper clientMapper, ClientRepo clientRepo) {
        this.clientMapper = clientMapper;
        this.clientRepo = clientRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public ClientDto getRandomClient() {
        final var totalClients = clientRepo.count();

        final var randomOffset = random.nextInt((int) totalClients);

        return clientMapper.toDto(
                clientRepo.getClientByOffset(randomOffset).orElseThrow(()-> new RuntimeException("oops"))
        );
    }
}
