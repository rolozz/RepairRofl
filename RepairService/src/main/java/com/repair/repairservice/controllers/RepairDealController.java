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
        repairDealService.created();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Well Done");
    }
}
