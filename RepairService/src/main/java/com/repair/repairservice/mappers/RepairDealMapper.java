package com.repair.repairservice.mappers;

import com.repair.repairservice.dto.ClientDto;
import com.repair.repairservice.entities.RepairDeal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

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


}
