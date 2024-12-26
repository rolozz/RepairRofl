package com.repair.personalservice.mappers;

import com.repair.personalservice.dto.ClientDto;
import com.repair.personalservice.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    ClientDto toDto(Client client);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "deals", source = "deals")
    Client mergeToEntity(ClientDto clientDto, @MappingTarget Client client);
}
