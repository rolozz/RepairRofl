package com.repair.personalservice.repositories;

import com.repair.personalservice.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkerRepo extends JpaRepository<Worker, UUID> {

    @Query(value = "SELECT * FROM worker OFFSET :offset LIMIT 1", nativeQuery = true)
    Optional<Worker> getWorkerByOffset(@Param("offset") int offset);

}
