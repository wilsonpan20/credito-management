package com.wildev.creditoapimanagement.domain.service.impl;

import com.wildev.creditoapimanagement.domain.exception.CreditoNaoEncontradoException;
import com.wildev.creditoapimanagement.domain.model.Credito;
import com.wildev.creditoapimanagement.domain.repository.CreditoRepository;
import com.wildev.creditoapimanagement.domain.service.ConsultaCreditoProducer;
import com.wildev.creditoapimanagement.domain.service.CreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CreditoServiceImpl implements CreditoService {

    private final CreditoRepository repository;
    private final ConsultaCreditoProducer producer;

    @Override
    public List<Credito> buscarPorNfse(String numeroNfse) {
        producer.enviar("Consulta realizada por NFS-e: " + numeroNfse + " em " + LocalDateTime.now());
        return repository.findByNumeroNfse(numeroNfse);
    }

    @Override
    public Credito buscarPorNumeroCredito(String numeroCredito) {
        producer.enviar("Consulta realizada por Número do Crédito: " + numeroCredito + " em " + LocalDateTime.now());
        return repository.findByNumeroCredito(numeroCredito)
                .orElseThrow(() -> new CreditoNaoEncontradoException(numeroCredito));
    }
}
