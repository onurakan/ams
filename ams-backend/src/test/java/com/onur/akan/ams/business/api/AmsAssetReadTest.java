package com.onur.akan.ams.business.api;

import com.onur.akan.ams.business.asset.AmsAsset;
import com.onur.akan.ams.business.asset.AmsAssetBuilder;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @Author Onur Akan
 *
 * Reference: https://dzone.com/articles/test-doubles-mockito
 * https://dev.to/kirekov/spring-boot-jpa-clear-tests-45he
 *  https://martinfowler.com/bliki/ObjectMother.html
 * https://www.toptal.com/java/a-guide-to-everyday-mockito
 */
public class AmsAssetReadTest {

    @Test
    public void readAssetByAssetId_success() {

        AmsAsset amsAsset = AmsAssetBuilder.aAmsAsset().withAssetId(1L).build();
        AmsResponse amsResponse = new AmsAssetRead(new AmsAssetRepositoryMock()).read(new AmsRequest(amsAsset));

        assertThat(amsResponse, is(notNullValue()));
        assertThat(amsResponse.getAmsAssetList(), is(notNullValue()));
        assertThat(amsResponse.getAmsAssetList(), is(not(Collections.emptyList())));
        assertThat(amsResponse.getAmsAssetList().size(), is(5));
        assertThat(amsResponse.getAmsAssetList(), hasItem(amsAsset));
        assertThat(amsResponse.getAmsAssetList(), not(hasItem(AmsAssetBuilder.aAmsAsset().withAssetId(2l).build())));
    }

    @Test
    public void readAllAmsAsset_success() {
        AmsResponse amsResponse = new AmsAssetRead(new AmsAssetRepositoryMock()).read(new AmsRequest(new AmsAsset()));

        assertThat(amsResponse, is(notNullValue()));
        assertThat(amsResponse.getAmsAssetList(), is(notNullValue()));
        assertThat(amsResponse.getAmsAssetList(), is(not(Collections.emptyList())));
        assertThat(amsResponse.getAmsAssetList().size(), is(5));
    }
}
