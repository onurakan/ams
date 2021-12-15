package com.onur.akan.ams.business.asset;

import java.util.List;

/**
 * @Author Onur Akan
 */
public interface AmsAssetRepository {
    List<AmsAsset> readAssetAll();
    List<AmsAsset> readAsset(AmsAsset amsAsset);
    AmsAsset createAsset(AmsAsset amsAsset);
    AmsAsset updateAsset(AmsAsset amsAsset);
    void deleteAsset(AmsAsset amsAsset);
}
