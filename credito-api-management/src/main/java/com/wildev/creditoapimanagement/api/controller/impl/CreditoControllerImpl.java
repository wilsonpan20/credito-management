package com.wildev.creditoapimanagement.api.controller.impl;

import com.wildev.creditoapimanagement.api.controller.CreditoController;
import com.wildev.creditoapimanagement.domain.model.Credito;
import com.wildev.creditoapimanagement.domain.service.CreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CreditoControllerImpl implements CreditoController {

    private final CreditoService service;


    @Override
    public ResponseEntity<List<Credito>> buscarPorNfse(String numeroNfse) {
        return ResponseEntity.ok(service.buscarPorNfse(numeroNfse));
    }

    @Override
    public ResponseEntity<Credito> buscarPorNumeroCredito(String numeroCredito) {
        return ResponseEntity.ok(service.buscarPorNumeroCredito(numeroCredito));
    }
}
