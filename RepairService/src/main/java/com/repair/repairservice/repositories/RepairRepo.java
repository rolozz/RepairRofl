package com.repair.repairservice.repositories;

import com.repair.repairservice.entities.RepairDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepairRepo extends JpaRepository<RepairDeal, UUID> {
}
