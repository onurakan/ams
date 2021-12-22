package com.onur.akan.ams.business.specification;

import java.util.List;

/**
 * @Author Onur Akan
 */
public interface AmsSpecificationRepository {
    List<AmsSpecification> readSpecification();
    List<AmsSpecification> readSpecification(AmsSpecification amsSpecification);
    AmsSpecification createSpecification(AmsSpecification amsSpecification);
    AmsSpecification updateSpecification(AmsSpecification amsSpecification);
    void deleteSpecification(AmsSpecification amsSpecification);
}
