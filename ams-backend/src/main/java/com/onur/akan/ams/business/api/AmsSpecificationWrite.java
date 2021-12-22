package com.onur.akan.ams.business.api;

import com.onur.akan.ams.business.specification.AmsSpecification;
import com.onur.akan.ams.business.specification.AmsSpecificationBuilder;
import com.onur.akan.ams.business.specification.AmsSpecificationRepository;

public class AmsSpecificationWrite {

    private AmsSpecificationRepository amsSpecificationRepository;

    public AmsSpecificationWrite (AmsSpecificationRepository amsSpecificationRepository) {
        this.amsSpecificationRepository = amsSpecificationRepository;
    }

    public boolean update(AmsRequest amsRequest) throws AmsRequestException {
        if (amsRequest == null) throw new AmsRequestException("amsRequest cannot be null");
        if (amsRequest.getAmsSpecification() == null) throw new AmsRequestException("amsRequest.amsAsset.amsSpecification cannot be null");

        AmsSpecification amsSpecification = readSpecificationById(amsRequest.getAmsSpecification().getId());
        if (amsSpecification != null) {
            setChangedField(amsSpecification, amsRequest.getAmsSpecification());
            return amsSpecificationRepository.updateSpecification(amsSpecification) != null;
        }
        return false;
    }

    private void setChangedField(AmsSpecification amsSpecificationTo, AmsSpecification amsSpecificationFrom) {
        if (amsSpecificationFrom.getAttribute() != null) {
            amsSpecificationTo.setAttribute(amsSpecificationFrom.getAttribute());
        }
        if (amsSpecificationFrom.getAttributeDescription() != null) {
            amsSpecificationTo.setAttributeDescription(amsSpecificationFrom.getAttributeDescription());
        }
        if (amsSpecificationFrom.getDataType() != null) {
            amsSpecificationTo.setDataType(amsSpecificationFrom.getDataType());
        }
        if (amsSpecificationFrom.getAlphnumericValue() != null) {
            amsSpecificationTo.setAlphnumericValue(amsSpecificationFrom.getAlphnumericValue());
        }
        if (amsSpecificationFrom.getAlphanumericDescription() != null) {
            amsSpecificationTo.setAlphanumericDescription(amsSpecificationFrom.getAlphanumericDescription());
        }
        if (amsSpecificationFrom.getNumericValue() != null) {
            amsSpecificationTo.setNumericValue(amsSpecificationFrom.getNumericValue());
        }
        if (amsSpecificationFrom.getNumericDescription() != null) {
            amsSpecificationTo.setNumericDescription(amsSpecificationFrom.getNumericDescription());
        }
        if (amsSpecificationFrom.getUnitOfMeasure() != null) {
            amsSpecificationTo.setUnitOfMeasure(amsSpecificationFrom.getUnitOfMeasure());
        }
        if (amsSpecificationFrom.getTableValue() != null) {
            amsSpecificationTo.setTableValue(amsSpecificationFrom.getTableValue());
        }
    }

    private AmsSpecification readSpecificationById(Long specificationId) throws AmsRequestException {
        AmsSpecification amsSpecification = AmsSpecificationBuilder.aAmsSpecification().withId(specificationId).build();
        AmsResponse amsResponse = new AmsSpecificationRead(amsSpecificationRepository).read(new AmsRequest(amsSpecification));
        if (amsResponse != null && amsResponse.getAmsSpecificationList() != null && !amsResponse.getAmsSpecificationList().isEmpty()) {
            return amsResponse.getAmsSpecificationList().get(0);
        }
        return null;
    }
}
