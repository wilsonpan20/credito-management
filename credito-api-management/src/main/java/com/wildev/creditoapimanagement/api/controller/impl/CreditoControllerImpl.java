package com.wildev.creditoapimanagement.api.controller.impl;

import com.wildev.creditoapimanagement.api.controller.CreditoController;
import com.wildev.creditoapimanagement.api.model.dto.response.CreditoResponseDto;
import com.wildev.creditoapimanagement.domain.model.Credito;
import com.wildev.creditoapimanagement.domain.service.CreditoService;
import com.wildev.creditoapimanagement.mapper.CreditoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CreditoControllerImpl implements CreditoController {

    private final CreditoService service;
    private final CreditoMapper creditoMapper;


    @Override
    public ResponseEntity<List<CreditoResponseDto>> buscarPorNfse(String numeroNfse) {
        return ResponseEntity.ok(creditoMapper.toCreditoResponseDto(service.buscarPorNfse(numeroNfse)));
    }

    @Override
    public ResponseEntity<CreditoResponseDto> buscarPorNumeroCredito(String numeroCredito) {
        return ResponseEntity.ok(creditoMapper.toCreditoResponseDto(service.buscarPorNumeroCredito(numeroCredito)));
    }
}
