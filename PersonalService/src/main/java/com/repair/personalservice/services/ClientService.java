package com.repair.personalservice.services;

import com.repair.personalservice.dto.ClientDto;
import com.repair.personalservice.entities.Client;

/**
 * Сервис для работы с сущностями {@link Client}.
 *
 * <p>Этот интерфейс определяет метод для получения случайного {@link Client} из репозитория.
 */
public interface ClientService {

  /**
   * Получает случайного {@link Client} из репозитория.
   *
   * @return {@link ClientDto} случайного {@link Client}.
   */
  ClientDto getRandomClient();
}
