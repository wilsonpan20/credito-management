package com.wildev.creditoapimanagement.infrastructure.messaging.producer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.verify;

@ExtendWith (MockitoExtension.class)
class NotificacaoKafkaAdapterTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private NotificacaoKafkaAdapter producer;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(producer, "topicName", "topico-teste");
    }

    @Test
    void deveEnviarMensagemParaOTopicoCorreto() {

        String mensagem = "Mensagem de teste";

        producer.enviar(mensagem);

        verify(kafkaTemplate).send("topico-teste", mensagem);
    }
}
