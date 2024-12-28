package com.repair.personalservice.mappers;

import com.repair.personalservice.dto.WorkerDto;
import com.repair.personalservice.entities.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Маппер для преобразования данных работника.
 * Используется для преобразования между {@link Worker} и {@link WorkerDto}.
 */
@Mapper(componentModel = "spring")
public interface WorkerMapper {

    /**
     * Преобразует сущность {@link Worker} в DTO {@link WorkerDto}.
     *
     * @param worker объект сущности работника
     * @return объект DTO работника
     */
    WorkerDto toDto(Worker worker);

    /**
     * Объединяет данные из DTO {@link WorkerDto} с существующей сущностью {@link Worker}.
     *
     * @param workerDto объект DTO работника
     * @param worker объект сущности работника, который нужно обновить
     * @return обновленный объект сущности работника
     */
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "workerDeals", source = "workerDeals")
    @Mapping(target = "workerName", ignore = true)
    Worker mergeToEntity(WorkerDto workerDto, @MappingTarget Worker worker);
}
