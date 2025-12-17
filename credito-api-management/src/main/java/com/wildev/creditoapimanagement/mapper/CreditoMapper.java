package com.wildev.creditoapimanagement.mapper;

import com.wildev.creditoapimanagement.api.model.dto.response.CreditoResponseDto;
import com.wildev.creditoapimanagement.domain.model.Credito;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper (componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS

)
public interface CreditoMapper {

    CreditoResponseDto toCreditoResponseDto(final Credito credito);

    List<CreditoResponseDto> toCreditoResponseDto(final List<Credito> creditos);
}
