package com.repair.personalservice.controllers;

import com.repair.personalservice.dto.ClientDto;
import com.repair.personalservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/get")
    public ResponseEntity<ClientDto> getSomeRandom() {
        return ResponseEntity.ok(clientService.getRandomClient());
    }

}
