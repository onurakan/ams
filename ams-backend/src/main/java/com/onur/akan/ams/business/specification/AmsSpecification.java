package com.onur.akan.ams.business.specification;

/**
 * @Author Onur Akan
 */
public class AmsSpecification {

    private Long id;
    private String attribute;
    private String attribureDescription;
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
}
