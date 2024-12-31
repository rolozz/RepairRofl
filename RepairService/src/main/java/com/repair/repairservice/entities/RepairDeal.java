package com.repair.repairservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
 * Класс "RepairDeal" представляет сделку по ремонту и используется для хранения
 * информации о клиенте, рабочем и текущем состоянии сделки.
 *
 * <p>Ключевые данные включают уникальный идентификатор сделки, описание, данные
 * клиента и рабочего, а также статус сделки, который может быть "создан",
 * "активен" или "завершен".</p>
 *
 * <p>Аннотации JPA и Lombok используются для упрощения работы с сущностью:</p>
 * <ul>
 *     <li>{@link Entity} - делает класс сущностью JPA.</li>
 *     <li>{@link Id} - указывает поле первичного ключа.</li>
 *     <li>{@link Column} - задает параметры маппинга для полей базы данных.</li>
 *     <li>{@link Getter}, {@link Setter}, {@link ToString}, {@link NoArgsConstructor},
 *         {@link AllArgsConstructor}, {@link FieldDefaults} - генерируют стандартные методы
 *         и облегчают разработку.</li>
 * </ul>
 */
@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RepairDeal {

    /**
     * Уникальный идентификатор сделки.
     * Генерируется автоматически при создании.
     */
    @Id
    UUID uuid = UUID.randomUUID();

    /**
     * Описание сделки, предоставляющее краткую информацию о её содержании.
     */
    String description;

    /**
     * Уникальный идентификатор клиента, участвующего в сделке.
     */
    @Column(name = "client_uuid")
    UUID clientUUID;

    /**
     * Уникальный идентификатор рабочего, участвующего в сделке.
     */
    @Column(name = "worker_uuid")
    UUID workerUUID;

    /**
     * Имя клиента, участвующего в сделке.
     */
    @Column(name = "client_name")
    String clientName;

    /**
     * Имя рабочего, участвующего в сделке.
     */
    @Column(name = "worker_name")
    String workerName;

    /**
     * Текущий статус сделки.
     * Может принимать одно из значений перечисления {@link Status}.
     */
    @Enumerated(EnumType.STRING)
    Status status;

    /**
     * Перечисление, представляющее возможные статусы сделки:
     * <ul>
     *     <li>{@link #CREATED} - сделка создана, но не активирована.</li>
     *     <li>{@link #ACTIVE} - сделка находится в процессе выполнения.</li>
     *     <li>{@link #DONE} - сделка завершена.</li>
     * </ul>
     */
    public enum Status {
        CREATED,
        ACTIVE,
        DONE
    }
}
