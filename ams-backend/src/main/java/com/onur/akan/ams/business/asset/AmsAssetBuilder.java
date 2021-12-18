package com.onur.akan.ams.business.asset;

import com.onur.akan.ams.business.specification.AmsSpecification;

import java.util.List;


public class AmsAssetBuilder {

    private Long assetId;
    private Integer status;
    private String classification;
    private String description;
    private String assetTag;
    private List<AmsSpecification> amsSpecificationList;

    public static AmsAssetBuilder aAmsAsset() {
        return new AmsAssetBuilder();
    }

    private AmsAssetBuilder() {}

    public AmsAssetBuilder withAssetId(Long assetId) {
        this.assetId = assetId;
        return this;
    }

    public AmsAssetBuilder withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public AmsAssetBuilder withClassification(String classification) {
        this.classification = classification;
        return this;
    }

    public AmsAssetBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public AmsAssetBuilder withAssetTag(String assetTag) {
        this.assetTag = assetTag;
        return this;
    }

    public AmsAssetBuilder withAmsSpecificationList(List<AmsSpecification> amsSpecificationList) {
        this.amsSpecificationList = amsSpecificationList;
        return this;
    }

    public AmsAsset build() {
        final AmsAsset amsAsset = new AmsAsset();
        amsAsset.setAssetId(assetId);
        amsAsset.setStatus(status);
        amsAsset.setClassification(classification);
        amsAsset.setDescription(description);
        amsAsset.setAssetTag(assetTag);
        amsAsset.setAmsSpecificationList(amsSpecificationList);
        return amsAsset;
    }
}