package com.wildev.creditoapimanagement.domain.service.impl;

import com.wildev.creditoapimanagement.domain.model.Credito;
import com.wildev.creditoapimanagement.domain.repository.CreditoRepository;
import com.wildev.creditoapimanagement.domain.service.CreditoService;
import com.wildev.creditoapimanagement.mapper.CreditoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreditoServiceImpl implements CreditoService {

    private final CreditoRepository repository;
    private final CreditoMapper creditoMapper;

    @Override
    public List<Credito> buscarPorNfse(String numeroNfse) {
        return repository.findByNumeroNfse(numeroNfse) ;
    }

    @Override
    public Credito buscarPorNumeroCredito(String numeroCredito) {
        return repository.findByNumeroCredito(numeroCredito).orElseThrow(null);
    }
}
