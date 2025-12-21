package com.wildev.creditoapimanagement.infrastructure.persistence.mapper;

import com.wildev.creditoapimanagement.domain.model.Credito;
import com.wildev.creditoapimanagement.infrastructure.persistence.entity.CreditoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditoPersistenceMapper {
    Credito toDomain(CreditoEntity entity);
    CreditoEntity toEntity(Credito domain);
    List<Credito> toDomainList(List<CreditoEntity> entityList);
}
