package com.onur.akan.ams.services;

import com.onur.akan.ams.domain.SpecificationEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SpecificationService extends CRUDService<SpecificationEntity>{
    
    List<SpecificationEntity> findByAssetId(Long assetId);
}
