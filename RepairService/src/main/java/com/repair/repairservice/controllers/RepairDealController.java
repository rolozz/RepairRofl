package com.repair.repairservice.controllers;

import com.repair.repairservice.services.RepairDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepairDealController {

    private final RepairDealService repairDealService;

    @Autowired
    public RepairDealController(RepairDealService repairDealService) {
        this.repairDealService = repairDealService;
    }

    @GetMapping("/created")
    public ResponseEntity<String> getCreated() {
        final var income = repairDealService.created();
        if ("fail".equals(income)) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Сервис временно недоступен");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Well Done");
        }
    }

    @GetMapping("/active")
    public ResponseEntity<Object> getActivated() {
        final var income = repairDealService.activated();
        if (income.getDealUUID() == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Сервис временно недоступен");
        } else {
            return ResponseEntity.ok(income);
        }
    }
}
