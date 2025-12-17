package com.wildev.creditoapimanagement.api.exceptionhandler;

import com.wildev.creditoapimanagement.domain.exception.RecursoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.time.LocalDateTime.now;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler (RecursoNaoEncontradoException.class)
    public ResponseEntity<StandardError> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex, final HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StandardError.builder().timestamp(now()).status(HttpStatus.NOT_FOUND.value()).error(HttpStatus.NOT_FOUND.getReasonPhrase()).message(ex.getMessage()).path(request.getRequestURI()).build());
    }

}
