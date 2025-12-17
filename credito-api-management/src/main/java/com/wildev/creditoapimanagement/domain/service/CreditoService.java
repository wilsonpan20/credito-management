package com.wildev.creditoapimanagement.domain.service;

import com.wildev.creditoapimanagement.domain.model.Credito;

import java.util.List;

public interface CreditoService {
    List<Credito> buscarPorNfse(String numeroNfse);

    Credito buscarPorNumeroCredito(String numeroCredito);
}
