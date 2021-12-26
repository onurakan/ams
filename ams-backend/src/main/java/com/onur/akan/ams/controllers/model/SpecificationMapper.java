package com.onur.akan.ams.controllers.model;

import com.onur.akan.ams.domain.SpecificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpecificationMapper {

    SpecificationMapper INSTANCE = Mappers.getMapper(SpecificationMapper.class);

    SpecificationEntity specificationToSpecificationEntity(Specification specification);
    Specification specificationEntityToSpecification(SpecificationEntity specificationEntity);
}