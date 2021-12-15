package com.onur.akan.ams.database.jpa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @Author Onur Akan
 */
@Entity
@Table(name = "tasset")
public class AssetEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name = "nstatus")
    private Integer status;
    @Column(name = "vclassification")
    private String classification;
    @Column(name = "vdescription")
    private String description;
    @Column(name = "vasset_tag")
    private String assetTag;
    @OneToMany( targetEntity= SpecificationEntity.class, cascade = CascadeType.ALL)
    private List<SpecificationEntity> specificationList;


    public AssetEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public List<SpecificationEntity> getSpecificationList() {
        return specificationList;
    }

    public void setSpecificationList(List<SpecificationEntity> specificationList) {
        this.specificationList = specificationList;
    }

    @Override
    public String toString() {
        return "AssetEntity{" +
                "id=" + id +
                ", status=" + status +
                ", classification='" + classification + '\'' +
                ", description='" + description + '\'' +
                ", assetTag='" + assetTag + '\'' +
                ", specificationList=" + specificationList +
                '}';
    }
}
