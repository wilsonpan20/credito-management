package com.wildev.creditoapimanagement.mapper;

import com.wildev.creditoapimanagement.api.model.dto.response.CreditoResponseDto;
import com.wildev.creditoapimanagement.domain.model.Credito;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditoApiMapper {
    CreditoResponseDto toResponseDto(Credito credito);
    List<CreditoResponseDto> toResponseDtoList(List<Credito> creditos);
}
