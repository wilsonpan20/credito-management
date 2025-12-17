package com.wildev.creditoapimanagement.api.controller;

import com.wildev.creditoapimanagement.api.model.dto.response.CreditoResponseDto;
import com.wildev.creditoapimanagement.domain.model.Credito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping ("/api/creditos")
public interface CreditoController {

    @GetMapping ("/{numeroNfse}")
    ResponseEntity<List<CreditoResponseDto>> buscarPorNfse(@PathVariable final String numeroNfse);

    @GetMapping ("/credito/{numeroCredito}")
    ResponseEntity<CreditoResponseDto> buscarPorNumeroCredito(@PathVariable final String numeroCredito);
}
