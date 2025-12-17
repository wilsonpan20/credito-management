package com.wildev.creditoapimanagement.domain.service.impl;

import com.wildev.creditoapimanagement.domain.exception.CreditoNaoEncontradoException;
import com.wildev.creditoapimanagement.domain.model.Credito;
import com.wildev.creditoapimanagement.domain.repository.CreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith (MockitoExtension.class)
class CreditoServiceImplTest {

    @Mock
    private CreditoRepository repository;

    @InjectMocks
    private CreditoServiceImpl service;

    private Credito credito;

    @BeforeEach
    void setUp() {
        credito = new Credito();
        credito.setId(1L);
        credito.setNumeroCredito("CRE-123");
        credito.setNumeroNfse("NFSE-456");
    }

    @Test
    void quandoBuscarPorNfse_eEncontrar_deveRetornarListaDeCreditos() {

        when(repository.findByNumeroNfse("NFSE-456")).thenReturn(List.of(credito));

        List<Credito> resultado = service.buscarPorNfse("NFSE-456");

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("CRE-123", resultado.get(0).getNumeroCredito());
        verify(repository).findByNumeroNfse("NFSE-456");
    }

    @Test
    void quandoBuscarPorNfse_eNaoEncontrar_deveRetornarListaVazia() {

        when(repository.findByNumeroNfse(anyString())).thenReturn(Collections.emptyList());

        List<Credito> resultado = service.buscarPorNfse("NFSE-INEXISTENTE");

        assertTrue(resultado.isEmpty());
        verify(repository).findByNumeroNfse("NFSE-INEXISTENTE");
    }

    @Test
    void quandoBuscarPorNumeroCredito_eEncontrar_deveRetornarCredito() {

        when(repository.findByNumeroCredito("CRE-123")).thenReturn(Optional.of(credito));

        Credito resultado = service.buscarPorNumeroCredito("CRE-123");

        assertNotNull(resultado);
        assertEquals("NFSE-456", resultado.getNumeroNfse());
        verify(repository).findByNumeroCredito("CRE-123");
    }

    @Test
    void quandoBuscarPorNumeroCredito_eNaoEncontrar_deveLancarExcecao() {

        when(repository.findByNumeroCredito(anyString())).thenReturn(Optional.empty());


        assertThrows(CreditoNaoEncontradoException.class, () -> {
            service.buscarPorNumeroCredito("CRE-INEXISTENTE");
        });
        verify(repository).findByNumeroCredito("CRE-INEXISTENTE");
    }
}
