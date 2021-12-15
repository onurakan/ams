package com.onur.akan.ams.business.asset;

import com.onur.akan.ams.business.specification.AmsSpecification;

import java.util.List;

/**
 * @Author Onur Akan
 */
public class AmsAsset {

    private Long assetId;
    private Integer status;
    private String classification;
    private String description;
    private String assetTag;
    private List<AmsSpecification> amsSpecificationList;

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public List<AmsSpecification> getAmsSpecificationList() {
        return amsSpecificationList;
    }

    public void setAmsSpecificationList(List<AmsSpecification> amsSpecificationList) {
        this.amsSpecificationList = amsSpecificationList;
    }

    @Override
    public String toString() {
        return "AmsAsset{" +
                "assetId=" + assetId +
                ", status=" + status +
                ", classification='" + classification + '\'' +
                ", description='" + description + '\'' +
                ", assetTag='" + assetTag + '\'' +
                ", amsSpecificationList=" + amsSpecificationList +
                '}';
    }
}
