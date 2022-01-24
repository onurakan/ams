package com.onur.akan.ams.controllers.mapper;

import com.onur.akan.ams.controllers.model.Specification;
import com.onur.akan.ams.domain.SpecificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class})
public interface SpecificationMapper {

    @Mapping(target="assetEntity.assetId", source="assetId")
    SpecificationEntity specificationToSpecificationEntity(Specification specification);
    @Mapping(target="assetId", source="assetEntity.assetId")
    Specification specificationEntityToSpecification(SpecificationEntity specificationEntity);
}