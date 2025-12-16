package com.wildev.creditoapimanagement.domain.repository;

import com.wildev.creditoapimanagement.domain.model.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {

    List<Credito> findByNumeroNfse(String numeroNfse);
    Optional<Credito> findByNumeroCredito(String numeroCredito);
}
