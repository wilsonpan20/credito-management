package com.wildev.creditoapimanagement.domain.exception;

public class CreditoNaoEncontradoException extends RecursoNaoEncontradoException {

    private static final long serialVersionUID = 1L;

    public CreditoNaoEncontradoException(String numeroCredito) {
        super(String.format("Não existe um cadastro de crédito com código %s", numeroCredito));
    }
}
