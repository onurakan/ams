package com.onur.akan.ams.business.api;

import com.onur.akan.ams.business.api.mocks.AmsSpecificationRepositoryMock;
import com.onur.akan.ams.business.specification.AmsSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AmsSpecificationWriteTest {

    @Test
    public void readSpecificationBySpecificationId_with_null_parameters_throwsException() {

        assertThat("amsRequest cannot be null", is(Assertions.assertThrows(AmsRequestException.class, () ->
                new AmsSpecificationRead(new AmsSpecificationRepositoryMock()).read(null)).getMessage()));
    }
    
    @Test
    public void amsSpecificationWrite_update() throws AmsRequestException {
        AmsRequest amsRequest = new AmsRequest(new AmsSpecification());
        amsRequest.getAmsSpecification().setId(1L);
        boolean isUpdated = new AmsSpecificationWrite(new AmsSpecificationRepositoryMock()).update(amsRequest);

        assertThat(isUpdated, is(Boolean.TRUE));
    }
}
