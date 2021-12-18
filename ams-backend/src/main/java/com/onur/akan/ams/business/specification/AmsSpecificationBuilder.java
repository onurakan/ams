package com.onur.akan.ams.business.specification;

public class AmsSpecificationBuilder {

    private Long id;
    private String attribute;
    private String attributeDescription;
    private String dataType;
    private String alphanumericValue;
    private String alphanumericDescription;
    private String numericValue;
    private String numericDescription;
    private String unitOfMeasure;
    private String tableValue;

    public static AmsSpecificationBuilder aAmsSpecification() {
        return new AmsSpecificationBuilder();
    }

    private AmsSpecificationBuilder() {}

    public AmsSpecificationBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AmsSpecificationBuilder withAttribute(String attribute) {
        this.attribute = attribute;
        return this;
    }

    public AmsSpecificationBuilder withAttributeDescription(String attributeDescription) {
        this.attributeDescription = attributeDescription;
        return this;
    }

    public AmsSpecificationBuilder withDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public AmsSpecificationBuilder withAlphanumericValue(String alphanumericValue) {
        this.alphanumericValue = alphanumericValue;
        return this;
    }

    public AmsSpecificationBuilder withAlphanumericDescription(String alphanumericDescription) {
        this.alphanumericDescription = alphanumericDescription;
        return this;
    }

    public AmsSpecificationBuilder withNumericValue(String numericValue) {
        this.numericValue = numericValue;
        return this;
    }

    public AmsSpecificationBuilder withNumericDescription(String numericDescription) {
        this.numericDescription = numericDescription;
        return this;
    }

    public AmsSpecificationBuilder withUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
        return this;
    }

    public AmsSpecificationBuilder withTableValue(String tableValue) {
        this.tableValue = tableValue;
        return this;
    }


    public AmsSpecification build() {
        final AmsSpecification amsSpecification = new AmsSpecification();
        amsSpecification.setId(id);
        amsSpecification.setAttribute(attribute);
        amsSpecification.setAttributeDescription(attributeDescription);
        amsSpecification.setDataType(dataType);
        amsSpecification.setAlphnumericValue(alphanumericValue);
        amsSpecification.setAlphanumericDescription(alphanumericDescription);
        amsSpecification.setNumericValue(numericValue);
        amsSpecification.setNumericDescription(numericDescription);
        amsSpecification.setUnitOfMeasure(unitOfMeasure);
        amsSpecification.setTableValue(tableValue);
        return amsSpecification;
    }
}
