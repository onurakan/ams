package com.onur.akan.ams.business.specification;

import java.util.Objects;

/**
 * @Author Onur Akan
 */
public class AmsSpecification {

    private Long id;
    private String attribute;
    private String attributeDescription;
    private String dataType;
    private String alphnumericValue;
    private String alphanumericDescription;
    private String numericValue;
    private String numericDescription;
    private String unitOfMeasure;
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

    public String getAttributeDescription() {
        return attributeDescription;
    }

    public void setAttributeDescription(String attributeDescription) {
        this.attributeDescription = attributeDescription;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmsSpecification that = (AmsSpecification) o;
        return id.equals(that.id) && Objects.equals(attribute, that.attribute) && Objects.equals(attributeDescription, that.attributeDescription) && Objects.equals(dataType, that.dataType) && Objects.equals(alphnumericValue, that.alphnumericValue) && Objects.equals(alphanumericDescription, that.alphanumericDescription) && Objects.equals(numericValue, that.numericValue) && Objects.equals(numericDescription, that.numericDescription) && Objects.equals(unitOfMeasure, that.unitOfMeasure) && Objects.equals(tableValue, that.tableValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attribute, attributeDescription, dataType, alphnumericValue, alphanumericDescription, numericValue, numericDescription, unitOfMeasure, tableValue);
    }
}
