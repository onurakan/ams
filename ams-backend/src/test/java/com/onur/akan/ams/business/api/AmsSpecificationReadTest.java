package com.onur.akan.ams.business.api;

import com.onur.akan.ams.business.specification.AmsSpecification;
import org.junit.Test;

public class AmsSpecificationReadTest {

    @Test
    public void nothing() {
        AmsRequest amsRequest = new AmsRequest(new AmsSpecification());
        amsRequest.getAmsSpecification().setId(1L);
        AmsResponse amsResponse = new AmsAssetWrite(new AmsAssetRepositoryMock()).create(amsRequest);
    }
}
