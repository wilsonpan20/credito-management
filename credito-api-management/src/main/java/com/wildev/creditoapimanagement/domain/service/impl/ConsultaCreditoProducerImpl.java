package com.wildev.creditoapimanagement.domain.service.impl;

import com.wildev.creditoapimanagement.domain.service.ConsultaCreditoProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ConsultaCreditoProducerImpl implements ConsultaCreditoProducer {

    @Value("${topic.consulta-credito.name}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void enviar(String mensagem) {
        log.info("Enviando evento para o tópico: {}", topicName);
        kafkaTemplate.send(topicName, mensagem);
    }
}
