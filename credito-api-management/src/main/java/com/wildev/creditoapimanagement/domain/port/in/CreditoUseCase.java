package com.wildev.creditoapimanagement.domain.port.in;

import com.wildev.creditoapimanagement.domain.model.Credito;

import java.util.List;

public interface CreditoUseCase {
    List<Credito> buscarPorNfse(String numeroNfse);
    Credito buscarPorNumeroCredito(String numeroCredito);
}
