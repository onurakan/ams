package com.onur.akan.ams.business.asset;

import com.onur.akan.ams.business.specification.AmsSpecification;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmsAsset amsAsset = (AmsAsset) o;
        return Objects.equals(assetId, amsAsset.assetId) && Objects.equals(status, amsAsset.status) && Objects.equals(classification, amsAsset.classification) && Objects.equals(description, amsAsset.description) && Objects.equals(assetTag, amsAsset.assetTag) && Objects.equals(amsSpecificationList, amsAsset.amsSpecificationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetId, status, classification, description, assetTag, amsSpecificationList);
    }
}
