package com.onur.akan.ams.business.api;

import com.onur.akan.ams.business.asset.AmsAsset;
import com.onur.akan.ams.business.asset.AmsAssetRepository;

import java.util.List;

public class AmsAssetRepositoryMock implements AmsAssetRepository {

    @Override
    public List<AmsAsset> readAssetAll() {
        return null;
    }

    @Override
    public List<AmsAsset> readAsset(AmsAsset amsAsset) {
        return null;
    }

    @Override
    public AmsAsset createAsset(AmsAsset amsAsset) {
        return null;
    }

    @Override
    public AmsAsset updateAsset(AmsAsset amsAsset) {
        return null;
    }

    @Override
    public void deleteAsset(AmsAsset amsAsset) {

    }
}