package com.repair.repairservice.dto;

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
 * DTO (Data Transfer Object) для представления данных активной сделки.
 * Используется для передачи информации между сервисами и слоями приложения.
 *
 * <p>Содержит следующие поля:</p>
 * <ul>
 *     <li><b>dealUUID</b> - Уникальный идентификатор сделки.</li>
 *     <li><b>clientUUID</b> - Уникальный идентификатор клиента.</li>
 *     <li><b>workerUUID</b> - Уникальный идентификатор рабочего.</li>
 * </ul>
 *
 * <p>Аннотации Lombok используются для автоматической генерации геттеров, сеттеров,
 * конструктора, метода {@code toString}, а также для задания уровня доступа к полям.</p>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActiveDto implements Serializable {

    /**
     * Уникальный идентификатор сделки.
     */
    UUID dealUUID;

    /**
     * Уникальный идентификатор клиента.
     */
    UUID clientUUID;

    /**
     * Уникальный идентификатор рабочего.
     */
    UUID workerUUID;
}
