package com.repair.personalservice.mappers;

import com.repair.personalservice.dto.WorkerDto;
import com.repair.personalservice.entities.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WorkerMapper {

    @Mapping(target = "id", ignore = true)
    Worker toEntity(WorkerDto workerDto);

    WorkerDto toDto(Worker worker);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "workerDeals", source = "workerDeals")
    Worker mergeToEntity(WorkerDto workerDto, @MappingTarget Worker worker);

}
