package com.onur.akan.ams.web.model;

import com.onur.akan.ams.business.api.AmsRequest;
import com.onur.akan.ams.business.asset.AmsAsset;
import com.onur.akan.ams.business.asset.AmsAssetBuilder;
import com.onur.akan.ams.business.specification.AmsSpecification;
import com.onur.akan.ams.business.specification.AmsSpecificationBuilder;

import java.util.ArrayList;

/**
 * @Author Onur Akan
 */
public class ModelMapper {

    public static Asset toAsset(AmsAsset amsAsset) {
        if (amsAsset == null) return null;
        Asset asset = new Asset();
        asset.setAssetId(amsAsset.getAssetId());
        asset.setStatus(amsAsset.getStatus());
        asset.setClassification(amsAsset.getClassification());
        asset.setDescription(amsAsset.getDescription());
        asset.setAssetTag(amsAsset.getAssetTag());
        if (amsAsset.getAmsSpecificationList() != null) {
            asset.setSpecificationList(new ArrayList<>());

            for (AmsSpecification amsSpecification : amsAsset.getAmsSpecificationList()) {
                asset.getSpecificationList().add(toSpecification(amsSpecification));
            }
        }
        return asset;
    }

    public static AmsAsset toAmsAsset(Asset asset) {
        AmsAsset amsAsset = AmsAssetBuilder.aAmsAsset()
                                            .withAssetId(asset.getAssetId())
                                            .withStatus(asset.getStatus())
                                            .withClassification(asset.getClassification())
                                            .withDescription(asset.getDescription())
                                            .withAssetTag(asset.getAssetTag())
                                            .build();

        if (asset.getSpecificationList() != null) {
            amsAsset.setAmsSpecificationList(new ArrayList<>());
            for (Specification specification : asset.getSpecificationList()) {
                amsAsset.getAmsSpecificationList().add(toAmsSpecification(specification));
            }
        }
        return amsAsset;
    }

    public static AmsRequest toAmsRequest(Asset asset) {
        AmsRequest amsRequest = new AmsRequest(new AmsAsset());
        amsRequest.getAmsAsset().setAssetId(asset.getAssetId());
        amsRequest.getAmsAsset().setStatus(asset.getStatus());
        amsRequest.getAmsAsset().setClassification(asset.getClassification());
        amsRequest.getAmsAsset().setDescription(asset.getDescription());
        amsRequest.getAmsAsset().setAssetTag(asset.getAssetTag());
        amsRequest.getAmsAsset().setAmsSpecificationList(new ArrayList<>());
        if (asset.getSpecificationList() != null) {
            for (Specification specification : asset.getSpecificationList()) {
                AmsSpecification amsSpecification = new AmsSpecification();
                amsSpecification.setId(amsSpecification.getId());
                amsSpecification.setAttribute(specification.getAttribute());
                amsSpecification.setAttributeDescription(specification.getAttributeDescription());
                amsSpecification.setDataType(specification.getDataType());
                amsSpecification.setAlphnumericValue(specification.getAlphnumericValue());
                amsSpecification.setAlphanumericDescription(specification.getAlphanumericDescription());
                amsSpecification.setNumericValue(specification.getNumericValue());
                amsSpecification.setNumericDescription(specification.getNumericDescription());
                amsSpecification.setUnitOfMeasure(specification.getUnitOfMeasure());
                amsSpecification.setTableValue(specification.getTableValue());
                amsRequest.getAmsAsset().getAmsSpecificationList().add(amsSpecification);
            }
        }
        return amsRequest;
    }

    public static Specification toSpecification(AmsSpecification amsSpecification) {
        Specification specification = new Specification();
        specification.setId(amsSpecification.getId());
        specification.setAttribute(amsSpecification.getAttribute());
        specification.setAttributeDescription(amsSpecification.getAttributeDescription());
        specification.setAlphanumericDescription(amsSpecification.getAttributeDescription());
        specification.setDataType(amsSpecification.getDataType());
        specification.setAlphnumericValue(amsSpecification.getAlphnumericValue());
        specification.setAlphanumericDescription(amsSpecification.getAlphanumericDescription());
        specification.setNumericValue(amsSpecification.getNumericValue());
        specification.setNumericDescription(amsSpecification.getNumericDescription());
        specification.setUnitOfMeasure(amsSpecification.getUnitOfMeasure());
        specification.setTableValue(amsSpecification.getTableValue());
        return specification;
    }

    public static AmsSpecification toAmsSpecification(Specification specification) {
        return AmsSpecificationBuilder.aAmsSpecification()
                                .withId(specification.getId())
                                .withAttribute(specification.getAttribute())
                                .withAttributeDescription(specification.getAttributeDescription())
                                .withDataType(specification.getDataType())
                                .withAlphanumericValue(specification.getAlphnumericValue())
                                .withAlphanumericDescription(specification.getAlphanumericDescription())
                                .withNumericValue(specification.getNumericValue())
                                .withNumericDescription(specification.getNumericDescription())
                                .withUnitOfMeasure(specification.getUnitOfMeasure())
                                .withTableValue(specification.getTableValue())
                                .build();
    }
}
