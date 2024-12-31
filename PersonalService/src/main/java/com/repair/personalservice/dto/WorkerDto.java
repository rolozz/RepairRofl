package com.repair.personalservice.dto;

import com.repair.personalservice.entities.Worker;
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
 * Класс DTO (Data Transfer Object) для работника. Этот класс используется для передачи данных между
 * различными слоями приложения. Связан с сущностью {@link Worker}.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkerDto implements Serializable {

  /** Уникальный идентификатор работника. */
  UUID uuid;

  /** Имя работника. */
  String workerName;

  /** Количество сделок, обработанных работником. */
  Long workerDeals;
}
