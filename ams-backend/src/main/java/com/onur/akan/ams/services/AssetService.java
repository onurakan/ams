package com.onur.akan.ams.services;

import com.onur.akan.ams.controllers.exception.AmsRequestException;
import com.onur.akan.ams.domain.AssetEntity;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AssetService extends CRUDService<AssetEntity>{
    Page<AssetEntity> findByExampleMatcher(AssetEntity assetEntity, Integer currentPageNumber, Integer pageSize);

    AssetEntity findByAssetId(UUID assetId);//TODO Optional donsun

    void insertInitialAssets(Integer assetCount, Integer specificationCount) throws AmsRequestException;
}
