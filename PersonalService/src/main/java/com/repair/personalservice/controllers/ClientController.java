package com.repair.personalservice.controllers;

import com.repair.personalservice.dto.ClientDto;
import com.repair.personalservice.entities.Client;
import com.repair.personalservice.services.ClientService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с сущностями {@link Client}.
 *
 * <p>Предоставляет REST-эндпоинт для получения случайного клиента.
 */
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientController {

  ClientService clientService;

  /**
   * Возвращает случайного клиента.
   *
   * <p>Этот метод вызывает сервисный слой для получения случайного {@link Client}, преобразованного
   * в {@link ClientDto}, и возвращает его в виде HTTP-ответа.
   *
   * @return {@link ResponseEntity}, содержащий случайного {@link ClientDto}.
   */
  @GetMapping("/client")
  public ResponseEntity<ClientDto> getSomeRandomC() {
    return ResponseEntity.ok(clientService.getRandomClient());
  }
}
