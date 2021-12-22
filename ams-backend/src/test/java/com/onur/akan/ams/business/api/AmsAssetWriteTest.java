package com.onur.akan.ams.business.api;

import com.onur.akan.ams.business.api.mocks.AmsAssetRepositoryMock;
import com.onur.akan.ams.business.asset.AmsAsset;
import com.onur.akan.ams.business.asset.AmsAssetBuilder;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * @Author Onur Akan
 */
public class AmsAssetWriteTest {

    @Test
    public void amsAssetWrite_create_success() throws AmsRequestException {
        AmsRequest amsRequest = new AmsRequest(new AmsAsset());
        AmsResponse amsResponse = new AmsAssetWrite(new AmsAssetRepositoryMock()).create(amsRequest);

        assertThat(amsResponse, is(notNullValue()));
        assertThat(amsResponse.getAmsAssetList(), is(notNullValue()));
        assertThat(amsResponse.getAmsAssetList(), is(not(Collections.emptyList())));
        assertThat(amsResponse.getAmsAssetList().size(), is(1));
        assertThat(amsResponse.getAmsAssetList(), hasItem(amsRequest.getAmsAsset()));
        assertThat(amsResponse.getAmsAssetList(), not(hasItem(AmsAssetBuilder.aAmsAsset().withAssetId(2l).build())));
    }

    @Test
    public void amsAssetWrite_update_success() throws AmsRequestException {
        AmsRequest amsRequest = new AmsRequest(new AmsAsset());
        amsRequest.getAmsAsset().setAssetId(1L);
        boolean isUpdated = new AmsAssetWrite(new AmsAssetRepositoryMock()).update(amsRequest);

        assertThat(isUpdated, is(Boolean.TRUE));
    }
}
