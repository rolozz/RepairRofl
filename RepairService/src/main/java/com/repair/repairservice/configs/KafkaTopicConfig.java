package com.repair.repairservice.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic toFinalize(){
        return new NewTopic("to-finalize",1,(short) 1);
    }

    @Bean
    public NewTopic fromFinalize(){
        return new NewTopic("from-finalize",1,(short) 1);
    }

}
