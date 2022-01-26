package com.onur.akan.ams.controllers.mapper;

import com.onur.akan.ams.controllers.model.SpecificationDto;
import com.onur.akan.ams.domain.SpecificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class})
public interface SpecificationMapper {

    @Mapping(target="assetEntity.assetId", source="assetId")
    SpecificationEntity specificationToSpecificationEntity(SpecificationDto specificationDto);
    @Mapping(target="assetId", source="assetEntity.assetId")
    SpecificationDto specificationEntityToSpecification(SpecificationEntity specificationEntity);
}