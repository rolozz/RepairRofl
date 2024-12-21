package com.repair.personalservice.repositories;

import com.repair.personalservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepo extends JpaRepository<Client, UUID> {

    @Query(value = "SELECT * FROM client OFFSET :offset LIMIT 1", nativeQuery = true)
    Optional<Client> getClientByOffset(@Param("offset") int offset);

}
