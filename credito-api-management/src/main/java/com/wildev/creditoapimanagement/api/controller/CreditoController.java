package com.wildev.creditoapimanagement.api.controller;

import com.wildev.creditoapimanagement.api.model.dto.response.CreditoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag (name = "Crédito", description = "Endpoints para consulta de créditos")
@RequestMapping ("/api/creditos")
public interface CreditoController {

    @Operation (summary = "Busca uma lista de créditos por número da NFS-e")
    @ApiResponses (value = {@ApiResponse (responseCode = "200", description = "Operação bem-sucedida, retorna a lista de créditos", content = @Content (mediaType = "application/json", schema = @Schema (implementation = CreditoResponseDto.class))), @ApiResponse (responseCode = "404", description = "Nenhum crédito encontrado para o número da NFS-e informado", content = @Content)})
    @GetMapping ("/{numeroNfse}")
    ResponseEntity<List<CreditoResponseDto>> buscarPorNfse(@Parameter (description = "Número da Nota Fiscal de Serviço Eletrônica", required = true, example = "7891011") @PathVariable final String numeroNfse);

    @Operation (summary = "Busca um crédito específico pelo seu número único")
    @ApiResponses (value = {@ApiResponse (responseCode = "200", description = "Crédito encontrado com sucesso", content = @Content (mediaType = "application/json", schema = @Schema (implementation = CreditoResponseDto.class))), @ApiResponse (responseCode = "404", description = "Crédito não encontrado com o número informado", content = @Content)})
    @GetMapping ("/credito/{numeroCredito}")
    ResponseEntity<CreditoResponseDto> buscarPorNumeroCredito(@Parameter (description = "Número único do crédito gerado pelo sistema", required = true, example = "123456") @PathVariable final String numeroCredito);
}
