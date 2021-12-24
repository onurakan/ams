package com.onur.akan.ams.controllers.model;

import com.onur.akan.ams.domain.AssetEntity;
import com.onur.akan.ams.domain.SpecificationEntity;

import java.util.ArrayList;

/**
 * @Author Onur Akan
 */
public class ModelMapper {

    public static Asset toAsset(AssetEntity assetEntity) {
        if (assetEntity == null) return null;
        Asset asset = Asset.builder().assetId(assetEntity.getId())
                                     .status(assetEntity.getStatus())
                                     .classification(assetEntity.getClassification())
                                     .description(assetEntity.getDescription())
                                     .assetTag(assetEntity.getAssetTag())
                                     .build();

        if (assetEntity.getSpecificationList() != null) {
            asset.setSpecificationList(new ArrayList<>());

            for (SpecificationEntity specificationEntity : assetEntity.getSpecificationList()) {
                asset.getSpecificationList().add(toSpecification(specificationEntity));
            }
        }
        return asset;
    }

    public static Specification toSpecification(SpecificationEntity specificationEntity) {
        return Specification.builder()
                .id(specificationEntity.getId())
                .attribute(specificationEntity.getAttribute())
                .attributeDescription(specificationEntity.getAttributeDescription())
                .dataType(specificationEntity.getDataType())
                .alphanumericValue(specificationEntity.getNumericValue())
                .alphanumericDescription(specificationEntity.getAlphanumericDescription())
                .numericValue(specificationEntity.getNumericValue())
                .numericDescription(specificationEntity.getNumericDescription())
                .unitOfMeasure(specificationEntity.getUnitOfMeasure())
                .tableValue(specificationEntity.getTableValue())
                .build();
    }

    public static AssetEntity toAssetEntitiy(Asset asset) {
        AssetEntity assetEntity = AssetEntity.builder()
                                            .id(asset.getAssetId())
                                            .status(asset.getStatus())
                                            .description(asset.getDescription())
                                            .classification(asset.getClassification())
                                            .assetTag(asset.getAssetTag())
                                            .build();

        if (asset.getSpecificationList() != null) {
            assetEntity.setSpecificationList(new ArrayList<>());
            for (Specification specification : asset.getSpecificationList()) {
                assetEntity.getSpecificationList().add(toSpecificationEntity(specification));
            }
        }
        return assetEntity;
    }

    public static SpecificationEntity toSpecificationEntity(Specification specification) {
        SpecificationEntity specificationEntity = SpecificationEntity.builder()
                                                                        .id(specification.getId())
                                                                        .attribute(specification.getAttribute())
                                                                        .attributeDescription(specification.getAttributeDescription())
                                                                        .dataType(specification.getDataType())
                                                                        .alphnumericValue(specification.getAlphanumericValue())
                                                                        .alphanumericDescription(specification.getAlphanumericDescription())
                                                                        .numericValue(specification.getNumericValue())
                                                                        .numericDescription(specification.getNumericDescription())
                                                                        .unitOfMeasure(specification.getUnitOfMeasure())
                                                                        .tableValue(specification.getTableValue())
                                                                        .build();
        return specificationEntity;
    }
}
