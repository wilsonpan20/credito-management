package com.wildev.creditoapimanagement.domain.port.out;

import com.wildev.creditoapimanagement.domain.model.Credito;

import java.util.List;
import java.util.Optional;

public interface CreditoRepositoryPort {
    List<Credito> findByNumeroNfse(String numeroNfse);
    Optional<Credito> findByNumeroCredito(String numeroCredito);
}
