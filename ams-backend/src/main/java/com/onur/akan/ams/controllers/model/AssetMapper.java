package com.onur.akan.ams.controllers.model;

import com.onur.akan.ams.domain.AssetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AssetMapper {

    AssetMapper INSTANCE = Mappers.getMapper(AssetMapper.class);

    @Mappings({
            @Mapping(target="id", source="asset.assetId"),
            @Mapping(target="specificationList", source="asset.specificationList")
    })
    AssetEntity assetToAssetEntity(Asset asset);

    @Mappings({
            @Mapping(target="assetId", source="assetEntity.id"),
            @Mapping(target="specificationList", source="assetEntity.specificationList")
    })
    Asset assetEntityToAsset(AssetEntity assetEntity);

    @Mappings({
            @Mapping(target="assetId", source="assetEntity.id"),
            @Mapping(target="specificationList", source="assetEntity.specificationList", ignore = true)
    })
    Asset assetEntityToAssetIgnoreSpecificationList(AssetEntity assetEntity);
}