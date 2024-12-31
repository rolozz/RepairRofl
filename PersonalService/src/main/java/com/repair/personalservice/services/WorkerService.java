package com.repair.personalservice.services;

import com.repair.personalservice.dto.WorkerDto;
import com.repair.personalservice.entities.Worker;

/**
 * Сервис для работы с сущностями {@link Worker}.
 *
 * <p>Этот интерфейс определяет метод для получения случайного {@link Worker} из репозитория.
 */
public interface WorkerService {

  /**
   * Получает случайного {@link Worker} из репозитория.
   *
   * @return {@link WorkerDto} случайного {@link Worker}.
   */
  WorkerDto getRandomWorker();
}
