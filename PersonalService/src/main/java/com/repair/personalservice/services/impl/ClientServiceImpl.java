package com.repair.personalservice.services.impl;

import com.repair.personalservice.dto.ClientDto;
import com.repair.personalservice.entities.Client;
import com.repair.personalservice.mappers.ClientMapper;
import com.repair.personalservice.repositories.ClientRepo;
import com.repair.personalservice.services.ClientService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Реализация сервиса для работы с сущностями {@link Client}.
 *
 * <p>Этот сервис предоставляет метод для получения случайного {@link Client} из репозитория.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientServiceImpl implements ClientService {

  ClientMapper clientMapper;
  ClientRepo clientRepo;
  Random random = new Random();

  /**
   * Получает случайного {@link Client} из репозитория.
   *
   * <p>Сначала выполняется подсчет всех клиентов в базе данных. Затем выбирается случайное
   * смещение, чтобы получить случайного клиента из репозитория.
   *
   * @return {@link ClientDto} случайного {@link Client}.
   * @throws RuntimeException если клиент не найден.
   */
  @Transactional(readOnly = true)
  @Override
  public ClientDto getRandomClient() {
    final var totalClients = clientRepo.count();

    final var randomOffset = random.nextInt((int) totalClients);

    return clientMapper.toDto(
        clientRepo.getClientByOffset(randomOffset).orElseThrow(() -> new RuntimeException("oops")));
  }
}
