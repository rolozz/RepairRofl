package com.repair.repairservice.messaging.impl;

import com.repair.repairservice.dto.ActiveDto;
import com.repair.repairservice.messaging.KafkaProducer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KafkaProducerImpl implements KafkaProducer {

    KafkaTemplate<String, ActiveDto> kafkaTemplate;
    RetryTemplate retryTemplate;

    @Override
    public void sendMessage(String topic, ActiveDto activeDto) {
        retryTemplate.execute(context -> {
            CompletableFuture<SendResult<String, ActiveDto>> future = kafkaTemplate.send(topic, activeDto);
            future.whenComplete((result, ex) -> {
                if (ex != null) {
                    // логика отправки в еластик серч
                    log.error("Ошибка отправки: {}", ex.getMessage());
                } else {
                    // логика отправки в еластик серч
                    log.info("Сообщение отправлено: {}", result.getRecordMetadata());
                }
            });
            return null;
        });
    }
}
