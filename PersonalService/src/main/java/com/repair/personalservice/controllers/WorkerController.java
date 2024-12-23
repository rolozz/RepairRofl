package com.repair.personalservice.controllers;

import com.repair.personalservice.dto.WorkerDto;
import com.repair.personalservice.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkerController {

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/worker")
    public ResponseEntity<WorkerDto> getSomeRandomW(){
        return ResponseEntity.ok(workerService.getRandomWorker());
    }
}
