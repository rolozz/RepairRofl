package com.repair.personalservice.mappers;

import com.repair.personalservice.dto.ClientDto;
import com.repair.personalservice.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

/**
 * Маппер для преобразования данных клиента. Используется для преобразования между {@link Client} и
 * {@link ClientDto}.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

  /**
   * Преобразует сущность {@link Client} в DTO {@link ClientDto}.
   *
   * @param client объект сущности клиента
   * @return объект DTO клиента
   */
  ClientDto toDto(Client client);

  /**
   * Объединяет данные из DTO {@link ClientDto} с существующей сущностью {@link Client}.
   *
   * @param clientDto объект DTO клиента
   * @param client объект сущности клиента, который нужно обновить
   * @return обновленный объект сущности клиента
   */
  @Mapping(target = "uuid", ignore = true)
  @Mapping(target = "name", ignore = true)
  @Mapping(target = "deals", source = "deals")
  Client mergeToEntity(ClientDto clientDto, @MappingTarget Client client);
}
