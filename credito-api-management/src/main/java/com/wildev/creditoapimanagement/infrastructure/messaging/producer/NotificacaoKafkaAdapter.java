package com.wildev.creditoapimanagement.infrastructure.messaging.producer;

import com.wildev.creditoapimanagement.domain.port.out.NotificacaoPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class NotificacaoKafkaAdapter implements NotificacaoPort {

    @Value("${topic.consulta-credito.name}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void enviar(String mensagem) {
        log.info("Enviando evento para o tópico: {}", topicName);
        kafkaTemplate.send(topicName, mensagem);
    }
}
