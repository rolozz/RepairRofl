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

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RepairDeal {

    @Id
    UUID uuid = UUID.randomUUID();
    String description;
    @Column(name = "client_uuid")
    UUID clientUUID;
    @Column(name = "worker_uuid")
    UUID workerUUID;
    @Column(name = "client_name")
    String clientName;
    @Column(name = "worker_name")
    String workerName;
    @Enumerated(EnumType.STRING)
    Status status;

    public enum Status{
        CREATED,
        ACTIVE,
        DONE
    }
}
