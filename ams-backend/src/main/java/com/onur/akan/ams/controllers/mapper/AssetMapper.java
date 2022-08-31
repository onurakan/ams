package com.onur.akan.ams.controllers.mapper;

import com.onur.akan.ams.controllers.model.AssetDto;
import com.onur.akan.ams.domain.AssetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {DateMapper.class})
public interface AssetMapper {

    @Mappings({
            @Mapping(target="specificationList", source= "assetDto.specificationList"),
            @Mapping(target="id", ignore = true)
    })
    AssetEntity assetToAssetEntity(AssetDto assetDto);

    @Mappings({
            @Mapping(target="specificationList", source="assetEntity.specificationList")
    })
    AssetDto assetEntityToAsset(AssetEntity assetEntity);

    @Mappings({
            //@Mapping(target = "assetId", source = "assetId"),
            @Mapping(target="specificationList", source="assetEntity.specificationList", ignore = true)
    })
    AssetDto assetEntityToAssetIgnoreSpecificationList(AssetEntity assetEntity);
}