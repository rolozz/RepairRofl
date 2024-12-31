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
 * DTO (Data Transfer Object) для представления данных о рабочем.
 * Используется для передачи информации между сервисами и слоями приложения.
 *
 * <p>Содержит следующие поля:</p>
 * <ul>
 *     <li><b>uuid</b> - Уникальный идентификатор рабочего.</li>
 *     <li><b>workerName</b> - Имя рабочего.</li>
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
public class WorkerDto implements Serializable {

    /**
     * Уникальный идентификатор рабочего.
     */
    UUID uuid;

    /**
     * Имя рабочего.
     */
    String workerName;
}
