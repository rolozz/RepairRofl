package com.repair.personalservice.repositories;

import com.repair.personalservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с сущностями {@link Client} в базе данных.
 *
 * <p>Этот интерфейс расширяет {@link JpaRepository} для предоставления CRUD-операций и
 * пользовательских методов запросов для {@link Client}.
 */
@Repository
public interface ClientRepo extends JpaRepository<Client, UUID> {

  /**
   * Получает {@link Client} по смещению.
   *
   * @param offset Смещение, с которого будет выполнен запрос.
   * @return {@link Optional} с найденным {@link Client}.
   */
  @Query(value = "SELECT * FROM client OFFSET :offset LIMIT 1", nativeQuery = true)
  Optional<Client> getClientByOffset(@Param("offset") int offset);
}
