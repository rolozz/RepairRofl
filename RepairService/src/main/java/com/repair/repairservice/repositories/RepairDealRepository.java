package com.repair.repairservice.repositories;

import com.repair.repairservice.entities.RepairDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepairDealRepository extends JpaRepository<RepairDeal, UUID> {

    @Query("FROM RepairDeal r WHERE r.status = :status")
    List<RepairDeal> findAllCreated(@Param("status") RepairDeal.Status status);

}
