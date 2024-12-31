package com.repair.repairservice.services;

import com.repair.repairservice.dto.ActiveDto;
import com.repair.repairservice.dto.WorkerDto;
import com.repair.repairservice.entities.RepairDeal;

import java.util.List;

public interface RepairDealService {

    String created();

    ActiveDto activated();

    RepairDeal getClient();

    WorkerDto getWorker();

}
