package com.onur.akan.ams.services;

import com.onur.akan.ams.domain.SpecificationEntity;

import java.util.List;
import java.util.UUID;

public interface SpecificationService extends CRUDService<SpecificationEntity>{
    
    List<SpecificationEntity> findByAssetId(UUID assetId);

    SpecificationEntity findBySpecificationId(UUID specificationId);
}
