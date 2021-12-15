package com.onur.akan.ams.database.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author Onur Akan
 */
@Entity
public class SpecificationEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "vattribute")
    private String attribute;
    @Column(name = "vattribureDescription")
    private String attribureDescription;
    @Column(name = "vdataType")
    private String dataType;
    @Column(name = "valphnumericValue")
    private String alphnumericValue;
    @Column(name = "valphanumericDescription")
    private String alphanumericDescription;
    @Column(name = "vnumericValue")
    private String numericValue;
    @Column(name = "vnumericDescription")
    private String numericDescription;
    @Column(name = "vunitOfMeasure")
    private String unitOfMeasure;
    @Column(name = "vtableValue")
    private String tableValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribureDescription() {
        return attribureDescription;
    }

    public void setAttribureDescription(String attribureDescription) {
        this.attribureDescription = attribureDescription;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getAlphnumericValue() {
        return alphnumericValue;
    }

    public void setAlphnumericValue(String alphnumericValue) {
        this.alphnumericValue = alphnumericValue;
    }

    public String getAlphanumericDescription() {
        return alphanumericDescription;
    }

    public void setAlphanumericDescription(String alphanumericDescription) {
        this.alphanumericDescription = alphanumericDescription;
    }

    public String getNumericValue() {
        return numericValue;
    }

    public void setNumericValue(String numericValue) {
        this.numericValue = numericValue;
    }

    public String getNumericDescription() {
        return numericDescription;
    }

    public void setNumericDescription(String numericDescription) {
        this.numericDescription = numericDescription;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getTableValue() {
        return tableValue;
    }

    public void setTableValue(String tableValue) {
        this.tableValue = tableValue;
    }

    @Override
    public String toString() {
        return "SpecificationEntity{" +
                "id=" + id +
                ", attribute='" + attribute + '\'' +
                ", attribureDescription='" + attribureDescription + '\'' +
                ", dataType='" + dataType + '\'' +
                ", alphnumericValue='" + alphnumericValue + '\'' +
                ", alphanumericDescription='" + alphanumericDescription + '\'' +
                ", numericValue='" + numericValue + '\'' +
                ", numericDescription='" + numericDescription + '\'' +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", tableValue='" + tableValue + '\'' +
                '}';
    }
}
