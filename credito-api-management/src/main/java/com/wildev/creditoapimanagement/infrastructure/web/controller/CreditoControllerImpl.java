package com.wildev.creditoapimanagement.infrastructure.web.controller;

import com.wildev.creditoapimanagement.api.controller.CreditoController;
import com.wildev.creditoapimanagement.api.model.dto.response.CreditoResponseDto;
import com.wildev.creditoapimanagement.domain.port.in.CreditoUseCase;
import com.wildev.creditoapimanagement.mapper.CreditoApiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CreditoControllerImpl implements CreditoController {

    private final CreditoUseCase creditoUseCase;
    private final CreditoApiMapper creditoApiMapper;

    @Override
    public ResponseEntity<List<CreditoResponseDto>> buscarPorNfse(String numeroNfse) {
        return ResponseEntity.ok(creditoApiMapper.toResponseDtoList(creditoUseCase.buscarPorNfse(numeroNfse)));
    }

    @Override
    public ResponseEntity<CreditoResponseDto> buscarPorNumeroCredito(String numeroCredito) {
        return ResponseEntity.ok(creditoApiMapper.toResponseDto(creditoUseCase.buscarPorNumeroCredito(numeroCredito)));
    }
}
