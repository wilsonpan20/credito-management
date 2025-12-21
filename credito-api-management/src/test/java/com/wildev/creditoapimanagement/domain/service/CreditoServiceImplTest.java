package com.wildev.creditoapimanagement.domain.service;

import com.wildev.creditoapimanagement.domain.exception.CreditoNaoEncontradoException;
import com.wildev.creditoapimanagement.domain.model.Credito;
import com.wildev.creditoapimanagement.domain.port.out.CreditoRepositoryPort;
import com.wildev.creditoapimanagement.domain.port.out.NotificacaoPort;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditoServiceImplTest {

    @Mock
    private CreditoRepositoryPort repositoryPort;

    @Mock
    private NotificacaoPort notificacaoPort;

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
        // Cenário
        when(repositoryPort.findByNumeroNfse("NFSE-456")).thenReturn(List.of(credito));

        // Ação
        List<Credito> resultado = service.buscarPorNfse("NFSE-456");

        // Verificação
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("CRE-123", resultado.get(0).getNumeroCredito());
        verify(repositoryPort).findByNumeroNfse("NFSE-456");
        verify(notificacaoPort).enviar(anyString());
    }

    @Test
    void quandoBuscarPorNfse_eNaoEncontrar_deveRetornarListaVazia() {
        // Cenário
        when(repositoryPort.findByNumeroNfse(anyString())).thenReturn(Collections.emptyList());

        // Ação
        List<Credito> resultado = service.buscarPorNfse("NFSE-INEXISTENTE");

        // Verificação
        assertTrue(resultado.isEmpty());
        verify(repositoryPort).findByNumeroNfse("NFSE-INEXISTENTE");
        verify(notificacaoPort).enviar(anyString());
    }

    @Test
    void quandoBuscarPorNumeroCredito_eEncontrar_deveRetornarCredito() {
        // Cenário
        when(repositoryPort.findByNumeroCredito("CRE-123")).thenReturn(Optional.of(credito));

        // Ação
        Credito resultado = service.buscarPorNumeroCredito("CRE-123");

        // Verificação
        assertNotNull(resultado);
        assertEquals("NFSE-456", resultado.getNumeroNfse());
        verify(repositoryPort).findByNumeroCredito("CRE-123");
        verify(notificacaoPort).enviar(anyString());
    }

    @Test
    void quandoBuscarPorNumeroCredito_eNaoEncontrar_deveLancarExcecao() {
        // Cenário
        when(repositoryPort.findByNumeroCredito(anyString())).thenReturn(Optional.empty());

        // Ação e Verificação
        assertThrows(CreditoNaoEncontradoException.class, () -> {
            service.buscarPorNumeroCredito("CRE-INEXISTENTE");
        });
        verify(repositoryPort).findByNumeroCredito("CRE-INEXISTENTE");
        verify(notificacaoPort).enviar(anyString());
    }
}
