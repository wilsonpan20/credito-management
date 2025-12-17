package com.wildev.creditoapimanagement.domain.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConsultaCreditoProducerImplTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private ConsultaCreditoProducerImpl producer;

    @Test
    void quandoEnviarMensagem_deveChamarKafkaTemplateSend() {
        // Cenário
        String topicName = "meu-topico-de-teste";
        String mensagem = "Olá, Kafka!";
        ReflectionTestUtils.setField(producer, "topicName", topicName); // Injeta o valor do @Value

        // Ação
        producer.enviar(mensagem);

        // Verificação
        verify(kafkaTemplate).send(topicName, mensagem);
    }
}
