package com.repair.repairservice.messaging.impl;

import com.repair.repairservice.dto.ActiveDto;
import com.repair.repairservice.messaging.KafkaProducer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KafkaProducerImpl implements KafkaProducer {

    KafkaTemplate<String, ActiveDto> kafkaTemplate;

    @Override
    public void sendMessage(String topic, ActiveDto activeDto) {
        kafkaTemplate.send(topic, activeDto);
    }
}
