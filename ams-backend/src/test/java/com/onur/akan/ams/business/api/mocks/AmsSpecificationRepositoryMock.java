package com.onur.akan.ams.business.api.mocks;

import com.onur.akan.ams.business.specification.AmsSpecification;
import com.onur.akan.ams.business.specification.AmsSpecificationBuilder;
import com.onur.akan.ams.business.specification.AmsSpecificationRepository;

import java.util.Arrays;
import java.util.List;

public class AmsSpecificationRepositoryMock implements AmsSpecificationRepository {
    
    @Override
    public List<AmsSpecification> readSpecification() {
        return null;
    }

    @Override
    public List<AmsSpecification> readSpecification(AmsSpecification amsSpecification) {
        return Arrays.asList(AmsSpecificationBuilder.aAmsSpecification().withId(1L).build());
    }

    @Override
    public AmsSpecification createSpecification(AmsSpecification amsSpecification) {
        return amsSpecification;
    }

    @Override
    public AmsSpecification updateSpecification(AmsSpecification amsSpecification) {
        return amsSpecification;
    }

    @Override
    public void deleteSpecification(AmsSpecification amsSpecification) {

    }
}
