package com.onur.akan.ams.business.api;

import com.onur.akan.ams.business.asset.AmsAsset;
import org.junit.Test;

public class AmsAssetWriteTest {

    @Test
    public void nothing() {
        AmsRequest amsRequest = new AmsRequest(new AmsAsset());
        amsRequest.getAmsAsset().setAssetId(1L);
        AmsResponse amsResponse = new AmsAssetWrite(new AmsAssetRepositoryMock()).create(amsRequest);
    }
}
