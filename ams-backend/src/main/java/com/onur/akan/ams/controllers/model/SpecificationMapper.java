package com.onur.akan.ams.controllers.model;

import com.onur.akan.ams.domain.SpecificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpecificationMapper {

    SpecificationMapper INSTANCE = Mappers.getMapper(SpecificationMapper.class);

    @Mapping(source="assetId", target="assetEntity.id")
    SpecificationEntity specificationToSpecificationEntity(Specification specification);
    @Mapping(source="assetEntity.id", target="assetId")
    Specification specificationEntityToSpecification(SpecificationEntity specificationEntity);
}