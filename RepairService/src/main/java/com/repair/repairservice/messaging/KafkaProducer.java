package com.repair.repairservice.messaging;

import com.repair.repairservice.dto.ActiveDto;

public interface KafkaProducer {

    void sendMessage(String topic, ActiveDto activeDto);

}
