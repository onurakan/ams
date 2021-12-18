package com.onur.akan.ams.database.jpa;

import com.onur.akan.ams.business.asset.AmsAsset;
import com.onur.akan.ams.business.specification.AmsSpecification;

import java.util.ArrayList;

/**
 * @Author Onur Akan
 */
public class ModelMapper {
    public static AssetEntity toAssetEntitiy(AmsAsset amsAsset) {
        AssetEntity assetEntity = new AssetEntity();
        assetEntity.setId(amsAsset.getAssetId());
        assetEntity.setStatus(amsAsset.getStatus());
        assetEntity.setDescription(amsAsset.getDescription());
        assetEntity.setClassification(amsAsset.getClassification());
        assetEntity.setAssetTag(amsAsset.getAssetTag());
        if (amsAsset.getAmsSpecificationList() != null) {
            assetEntity.setSpecificationList(new ArrayList<>());
            for (AmsSpecification amsSpecification : amsAsset.getAmsSpecificationList()) {
                assetEntity.getSpecificationList().add(toSpecificationEntity(amsSpecification));
            }
        }
        return assetEntity;
    }

    public static AmsAsset toAmsAsset(AssetEntity assetEntity) {
        AmsAsset amsAsset = new AmsAsset();
        amsAsset.setAssetId(assetEntity.getId());
        amsAsset.setStatus(assetEntity.getStatus());
        amsAsset.setClassification(assetEntity.getClassification());
        amsAsset.setDescription(assetEntity.getDescription());
        amsAsset.setAssetTag(assetEntity.getAssetTag());

        if (assetEntity.getSpecificationList() != null) {
            amsAsset.setAmsSpecificationList(new ArrayList<>());
            for (SpecificationEntity specificationEntity : assetEntity.getSpecificationList()) {
                amsAsset.getAmsSpecificationList().add(toAmsSpecification(specificationEntity));
            }
        }

        return amsAsset;
    }

    public static AmsSpecification toAmsSpecification(SpecificationEntity specificationEntity) {
        AmsSpecification amsSpecification = new AmsSpecification();
        amsSpecification.setId(specificationEntity.getId());
        amsSpecification.setAttribute(specificationEntity.getAttribute());
        amsSpecification.setAttributeDescription(specificationEntity.getAttributeDescription());
        amsSpecification.setDataType(specificationEntity.getDataType());
        amsSpecification.setAlphnumericValue(specificationEntity.getAlphnumericValue());
        amsSpecification.setAlphanumericDescription(specificationEntity.getAlphanumericDescription());
        amsSpecification.setNumericValue(specificationEntity.getNumericValue());
        amsSpecification.setNumericDescription(specificationEntity.getNumericDescription());
        amsSpecification.setUnitOfMeasure(specificationEntity.getUnitOfMeasure());
        amsSpecification.setTableValue(specificationEntity.getTableValue());
        return amsSpecification;
    }

    public static SpecificationEntity toSpecificationEntity(AmsSpecification amsSpecification) {
        SpecificationEntity specificationEntity = new SpecificationEntity();
        specificationEntity.setId(amsSpecification.getId());
        specificationEntity.setAttribute(amsSpecification.getAttribute());
        specificationEntity.setAttributeDescription(amsSpecification.getAttributeDescription());
        specificationEntity.setDataType(amsSpecification.getDataType());
        specificationEntity.setAlphnumericValue(amsSpecification.getAlphnumericValue());
        specificationEntity.setAlphanumericDescription(amsSpecification.getAlphanumericDescription());
        specificationEntity.setNumericValue(amsSpecification.getNumericValue());
        specificationEntity.setNumericDescription(amsSpecification.getNumericDescription());
        specificationEntity.setUnitOfMeasure(amsSpecification.getUnitOfMeasure());
        specificationEntity.setTableValue(amsSpecification.getTableValue());
        return specificationEntity;
    }
}
