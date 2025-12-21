package com.wildev.creditoapimanagement.infrastructure.persistence.repository;

import com.wildev.creditoapimanagement.infrastructure.persistence.entity.CreditoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditoJpaRepository extends JpaRepository<CreditoEntity, Long> {
    List<CreditoEntity> findByNumeroNfse(String numeroNfse);
    Optional<CreditoEntity> findByNumeroCredito(String numeroCredito);
}
