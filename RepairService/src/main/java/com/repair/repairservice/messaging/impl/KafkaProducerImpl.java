package com.repair.repairservice.messaging.impl;

import com.repair.repairservice.dto.ActiveDto;
import com.repair.repairservice.messaging.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerImpl implements KafkaProducer {

    private final KafkaTemplate<String, ActiveDto> kafkaTemplate;

    @Autowired
    public KafkaProducerImpl(KafkaTemplate<String, ActiveDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String topic, ActiveDto activeDto) {
         kafkaTemplate.send(topic, activeDto);
    }
}
