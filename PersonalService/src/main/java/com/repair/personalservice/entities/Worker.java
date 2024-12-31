package com.repair.personalservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

/**
 * Класс-сущность, представляющий работника. Этот класс отображается на таблицу базы данных и
 * содержит информацию о работнике.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Worker {

  /** Уникальный идентификатор работника, генерируется автоматически. */
  @Id UUID uuid = UUID.randomUUID();

  /** Имя работника. */
  String workerName;

  /** Количество сделок, обработанных работником. */
  Long workerDeals;
}
