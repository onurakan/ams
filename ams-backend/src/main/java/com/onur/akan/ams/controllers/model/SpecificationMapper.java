package com.onur.akan.ams.controllers.model;

import com.onur.akan.ams.domain.SpecificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpecificationMapper {

    SpecificationMapper INSTANCE = Mappers.getMapper(SpecificationMapper.class);

    @Mapping(target="assetEntity.assetId", source="assetId")
    SpecificationEntity specificationToSpecificationEntity(Specification specification);
    @Mapping(target="assetId", source="assetEntity.assetId")
    Specification specificationEntityToSpecification(SpecificationEntity specificationEntity);
}