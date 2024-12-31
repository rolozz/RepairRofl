package com.repair.personalservice.services.impl;

import com.repair.personalservice.dto.WorkerDto;
import com.repair.personalservice.entities.Worker;
import com.repair.personalservice.mappers.WorkerMapper;
import com.repair.personalservice.repositories.WorkerRepo;
import com.repair.personalservice.services.WorkerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Реализация сервиса для работы с сущностями {@link Worker}.
 *
 * <p>Этот сервис предоставляет метод для получения случайного {@link Worker} из репозитория.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkerServiceImpl implements WorkerService {

  WorkerMapper workerMapper;
  WorkerRepo workerRepo;
  Random random = new Random();

  /**
   * Получает случайного {@link Worker} из репозитория.
   *
   * <p>Сначала выполняется подсчет всех работников в базе данных. Затем выбирается случайное
   * смещение, чтобы получить случайного работника из репозитория.
   *
   * @return {@link WorkerDto} случайного {@link Worker}.
   * @throws RuntimeException если работник не найден.
   */
  @Transactional(readOnly = true)
  @Override
  public WorkerDto getRandomWorker() {
    final var totalWorkers = workerRepo.count();

    final var randomOffset = random.nextInt((int) totalWorkers);

    return workerMapper.toDto(
        workerRepo.getWorkerByOffset(randomOffset).orElseThrow(() -> new RuntimeException("oops")));
  }
}
