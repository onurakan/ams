package com.onur.akan.ams.business.specification;

import java.util.List;

/**
 * @Author Onur Akan
 */
public interface AmsSpecificationRepository {
    List<AmsSpecification> readSpecification();
    List<AmsSpecification> readSpecification(AmsSpecification amsAsset);
    AmsSpecification createSpecification(AmsSpecification amsSpecification);
    void updateSpecification(AmsSpecification amsSpecification);
    void deleteSpecification(AmsSpecification amsSpecification);
}
