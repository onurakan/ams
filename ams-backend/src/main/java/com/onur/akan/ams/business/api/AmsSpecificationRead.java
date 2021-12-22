package com.onur.akan.ams.business.api;

import com.onur.akan.ams.business.specification.AmsSpecification;
import com.onur.akan.ams.business.specification.AmsSpecificationRepository;

import java.util.List;

public class AmsSpecificationRead {

    private AmsSpecificationRepository amsSpecificationRepository;

    public AmsSpecificationRead (AmsSpecificationRepository amsSpecificationRepository) {
        this.amsSpecificationRepository = amsSpecificationRepository;
    }

    public AmsResponse read(AmsRequest amsRequest) throws AmsRequestException {
        //TODO implement business rules here
        if (amsRequest == null) throw new AmsRequestException("amsRequest cannot be null");
        if (amsRequest.getAmsSpecification() == null) throw new AmsRequestException("amsRequest.amsSpecification cannot be null");
        if (amsRequest.getAmsSpecification().getId() == null) throw new AmsRequestException("amsRequest.amsSpecification.id cannot be null");

        List<AmsSpecification> amsSpecificationList = amsSpecificationRepository.readSpecification((amsRequest.getAmsSpecification()));
        AmsResponse amsResponse = new AmsResponse();
        amsResponse.setAmsSpecificationList(amsSpecificationList);
        return amsResponse;
    }
}
