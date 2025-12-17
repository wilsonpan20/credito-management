package com.wildev.creditoapimanagement.api.controller.impl;

import com.wildev.creditoapimanagement.api.model.dto.response.CreditoResponseDto;
import com.wildev.creditoapimanagement.domain.model.Credito;
import com.wildev.creditoapimanagement.domain.service.CreditoService;
import com.wildev.creditoapimanagement.mapper.CreditoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest (CreditoControllerImpl.class)
class CreditoControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreditoService service;

    @MockitoBean
    private CreditoMapper mapper;

    private Credito credito;
    private CreditoResponseDto creditoResponseDto;

    @BeforeEach
    void setUp() {
        credito = new Credito();
        credito.setNumeroCredito("CRE-123");
        credito.setNumeroNfse("NFSE-456");

        creditoResponseDto = new CreditoResponseDto();
        creditoResponseDto.setNumeroCredito("CRE-123");
        creditoResponseDto.setNumeroNfse("NFSE-456");
    }

    @Test
    void quandoBuscarPorNfse_deveRetornarOk_eListaDeCreditos() throws Exception {

        when(service.buscarPorNfse("NFSE-456")).thenReturn(List.of(credito));
        when(mapper.toCreditoResponseDto(any(List.class))).thenReturn(List.of(creditoResponseDto));

        mockMvc.perform(get("/api/creditos/{numeroNfse}", "NFSE-456")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numeroCredito").value("CRE-123"));
    }

    @Test
    void quandoBuscarPorNumeroCredito_deveRetornarOk_eCredito() throws Exception {

        when(service.buscarPorNumeroCredito("CRE-123")).thenReturn(credito);
        when(mapper.toCreditoResponseDto(any(Credito.class))).thenReturn(creditoResponseDto);

        mockMvc.perform(get("/api/creditos/credito/{numeroCredito}", "CRE-123")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroNfse").value("NFSE-456"));
    }
}
