package com.repair.personalservice.controllers;

import com.repair.personalservice.dto.WorkerDto;
import com.repair.personalservice.entities.Worker;
import com.repair.personalservice.services.WorkerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с сущностями {@link Worker}.
 *
 * <p>Предоставляет REST-эндпоинт для получения случайного работника.
 */
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkerController {

  WorkerService workerService;

  /**
   * Возвращает случайного работника.
   *
   * <p>Этот метод вызывает сервисный слой для получения случайного {@link Worker}, преобразованного
   * в {@link WorkerDto}, и возвращает его в виде HTTP-ответа.
   *
   * @return {@link ResponseEntity}, содержащий случайного {@link WorkerDto}.
   */
  @GetMapping("/worker")
  public ResponseEntity<WorkerDto> getSomeRandomW() {
    return ResponseEntity.ok(workerService.getRandomWorker());
  }
}
