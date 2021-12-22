package com.onur.akan.ams.business.api.mocks;

import com.onur.akan.ams.business.asset.AmsAsset;
import com.onur.akan.ams.business.asset.AmsAssetBuilder;
import com.onur.akan.ams.business.asset.AmsAssetRepository;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Onur Akan
 */
public class AmsAssetRepositoryMock implements AmsAssetRepository {

    @Override
    public List<AmsAsset> readAssetAll() {

        return Arrays.asList(
                AmsAssetBuilder.aAmsAsset().withAssetId(1L).build(),
                AmsAssetBuilder.aAmsAsset().withAssetId(2L).build(),
                AmsAssetBuilder.aAmsAsset().withAssetId(3L).build(),
                AmsAssetBuilder.aAmsAsset().withAssetId(4L).build(),
                AmsAssetBuilder.aAmsAsset().withAssetId(5L).build()
        );
    }

    @Override
    public List<AmsAsset> readAsset(AmsAsset amsAsset) {
        return Arrays.asList(amsAsset);
    }

    @Override
    public AmsAsset createAsset(AmsAsset amsAsset) {
        return amsAsset;
    }

    @Override
    public AmsAsset updateAsset(AmsAsset amsAsset) {
        return amsAsset;
    }

    @Override
    public void deleteAsset(AmsAsset amsAsset) {
    }
}