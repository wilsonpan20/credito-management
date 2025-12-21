package com.wildev.creditoapimanagement.infrastructure.configuration;

import com.wildev.creditoapimanagement.domain.port.in.CreditoUseCase;
import com.wildev.creditoapimanagement.domain.port.out.CreditoRepositoryPort;
import com.wildev.creditoapimanagement.domain.port.out.NotificacaoPort;
import com.wildev.creditoapimanagement.domain.service.CreditoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public CreditoUseCase creditoUseCase(
            CreditoRepositoryPort creditoRepositoryPort,
            NotificacaoPort notificacaoPort) {
        return new CreditoServiceImpl(creditoRepositoryPort, notificacaoPort);
    }
}
