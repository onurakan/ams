package com.onur.akan.ams.web.model;

import java.util.List;

/**
 * @Author Onur Akan
 */
public class Asset {

    private Long assetId;
    private Integer status;
    private String classification;
    private String description;
    private String assetTag;
    private List<Specification> specificationList;

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public int getStatus() {
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

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Specification> getSpecificationList() {
        return specificationList;
    }

    public void setSpecificationList(List<Specification> specificationList) {
        this.specificationList = specificationList;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "assetId=" + assetId +
                ", status=" + status +
                ", classification='" + classification + '\'' +
                ", description='" + description + '\'' +
                ", assetTag='" + assetTag + '\'' +
                ", specificationList=" + specificationList +
                '}';
    }
}
