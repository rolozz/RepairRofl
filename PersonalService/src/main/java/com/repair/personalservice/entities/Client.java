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
 * Класс-сущность, представляющий клиента. Этот класс отображается на таблицу базы данных и содержит
 * информацию о клиенте.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Client {

  /** Уникальный идентификатор клиента, генерируется автоматически. */
  @Id UUID uuid = UUID.randomUUID();

  /** Имя клиента. */
  String name;

  /** Количество сделок, связанных с клиентом. */
  Long deals;
}
