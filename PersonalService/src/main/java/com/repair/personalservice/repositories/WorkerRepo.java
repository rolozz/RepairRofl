package com.repair.personalservice.repositories;

import com.repair.personalservice.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с сущностями {@link Worker} в базе данных.
 *
 * <p>Этот интерфейс расширяет {@link JpaRepository} для предоставления CRUD-операций и
 * пользовательских методов запросов для {@link Worker}.
 */
@Repository
public interface WorkerRepo extends JpaRepository<Worker, UUID> {

  /**
   * Получает {@link Worker} по смещению.
   *
   * @param offset Смещение, с которого будет выполнен запрос.
   * @return {@link Optional} с найденным {@link Worker}.
   */
  @Query(value = "SELECT * FROM worker OFFSET :offset LIMIT 1", nativeQuery = true)
  Optional<Worker> getWorkerByOffset(@Param("offset") int offset);
}
