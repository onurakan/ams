package com.onur.akan.ams.services;

import com.onur.akan.ams.domain.AssetEntity;
import org.springframework.data.domain.Page;

public interface AssetService extends CRUDService<AssetEntity>{
    Page<AssetEntity> findByExampleMatcher(AssetEntity assetEntity, Integer currentPageNumber, Integer pageSize);
}
