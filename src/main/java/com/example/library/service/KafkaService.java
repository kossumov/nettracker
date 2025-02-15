package com.example.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
@AllArgsConstructor
public class KafkaService {
  private final KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String topic, String message) {
    kafkaTemplate.send(topic, message);
  }
}
