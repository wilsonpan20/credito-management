package com.wildev.creditoapimanagement.infrastructure.persistence.repository;

import com.wildev.creditoapimanagement.domain.model.Credito;
import com.wildev.creditoapimanagement.domain.port.out.CreditoRepositoryPort;
import com.wildev.creditoapimanagement.infrastructure.persistence.mapper.CreditoPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreditoRepositoryAdapter implements CreditoRepositoryPort {

    private final CreditoJpaRepository jpaRepository;
    private final CreditoPersistenceMapper mapper;

    @Override
    public List<Credito> findByNumeroNfse(String numeroNfse) {
        return mapper.toDomainList(jpaRepository.findByNumeroNfse(numeroNfse));
    }

    @Override
    public Optional<Credito> findByNumeroCredito(String numeroCredito) {
        return jpaRepository.findByNumeroCredito(numeroCredito).map(mapper::toDomain);
    }
}
