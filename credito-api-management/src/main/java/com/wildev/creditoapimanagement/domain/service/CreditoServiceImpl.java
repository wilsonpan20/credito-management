package com.wildev.creditoapimanagement.domain.service;

import com.wildev.creditoapimanagement.domain.exception.CreditoNaoEncontradoException;
import com.wildev.creditoapimanagement.domain.model.Credito;
import com.wildev.creditoapimanagement.domain.port.in.CreditoUseCase;
import com.wildev.creditoapimanagement.domain.port.out.CreditoRepositoryPort;
import com.wildev.creditoapimanagement.domain.port.out.NotificacaoPort;

import java.time.LocalDateTime;
import java.util.List;

public class CreditoServiceImpl implements CreditoUseCase {

    private final CreditoRepositoryPort creditoRepositoryPort;
    private final NotificacaoPort notificacaoPort;

    public CreditoServiceImpl(CreditoRepositoryPort creditoRepositoryPort, NotificacaoPort notificacaoPort) {
        this.creditoRepositoryPort = creditoRepositoryPort;
        this.notificacaoPort = notificacaoPort;
    }

    @Override
    public List<Credito> buscarPorNfse(String numeroNfse) {
        notificacaoPort.enviar("Consulta realizada por NFS-e: " + numeroNfse + " em " + LocalDateTime.now());
        return creditoRepositoryPort.findByNumeroNfse(numeroNfse);
    }

    @Override
    public Credito buscarPorNumeroCredito(String numeroCredito) {
        notificacaoPort.enviar("Consulta realizada por Número do Crédito: " + numeroCredito + " em " + LocalDateTime.now());
        return creditoRepositoryPort.findByNumeroCredito(numeroCredito)
                .orElseThrow(() -> new CreditoNaoEncontradoException(numeroCredito));
    }
}
