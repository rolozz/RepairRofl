package com.repair.personalservice.dto;

import com.repair.personalservice.entities.Client;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.UUID;

/**
 * Класс DTO (Data Transfer Object) для клиента.
 * Этот класс используется для передачи данных между различными слоями приложения.
 * Связан с сущностью {@link Client}.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientDto implements Serializable {

    /**
     * Уникальный идентификатор клиента.
     */
    UUID uuid;

    /**
     * Имя клиента.
     */
    String name;

    /**
     * Количество сделок, связанных с клиентом.
     */
    Long deals;
}
