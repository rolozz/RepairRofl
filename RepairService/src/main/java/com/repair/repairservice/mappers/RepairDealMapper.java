package com.repair.repairservice.mappers;

import com.repair.repairservice.dto.ActiveDto;
import com.repair.repairservice.dto.ClientDto;
import com.repair.repairservice.dto.WorkerDto;
import com.repair.repairservice.entities.RepairDeal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RepairDealMapper {

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "clientUUID", source = "uuid")
    @Mapping(target = "clientName", source = "name")
    @Mapping(target = "workerUUID", ignore = true)
    @Mapping(target = "workerName", ignore = true)
    RepairDeal toCreatedEntity(ClientDto clientDto);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "clientUUID", ignore = true)
    @Mapping(target = "clientName", ignore = true)
    @Mapping(target = "workerUUID", source = "uuid")
    @Mapping(target = "workerName", source = "workerName")
    RepairDeal mergeToEntity(WorkerDto workerDto, @MappingTarget RepairDeal repairDeal);

    @Mapping(target = "dealUUID", source = "uuid")
    @Mapping(target = "clientUUID", source = "clientUUID")
    @Mapping(target = "workerUUID", source = "workerUUID")
    ActiveDto toActive(RepairDeal repairDeal);

}
