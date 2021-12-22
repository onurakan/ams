package com.onur.akan.ams.business.api;

import com.onur.akan.ams.business.api.mocks.AmsSpecificationRepositoryMock;
import com.onur.akan.ams.business.specification.AmsSpecification;
import com.onur.akan.ams.business.specification.AmsSpecificationBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * @Author Onur Akan
 */
public class AmsSpecificationReadTest {
    
    @Test
    public void readSpecificationBySpecificationId_with_null_parameters_throwsException() {
        assertThat("amsRequest cannot be null", is(Assertions.assertThrows(AmsRequestException.class, () ->
                new AmsSpecificationRead(new AmsSpecificationRepositoryMock()).read(null)).getMessage()));

        final AmsSpecification amsSpecification1 = null;
        assertThat("amsRequest.amsSpecification cannot be null", is(Assertions.assertThrows(AmsRequestException.class, () ->
                new AmsSpecificationRead(new AmsSpecificationRepositoryMock()).read(new AmsRequest(amsSpecification1))).getMessage()));

        final AmsSpecification amsSpecification2 = new AmsSpecification();
        assertThat("amsRequest.amsSpecification.id cannot be null", is(Assertions.assertThrows(AmsRequestException.class, () ->
                new AmsSpecificationRead(new AmsSpecificationRepositoryMock()).read(new AmsRequest(amsSpecification2))).getMessage()));
    }

    @Test
    public void readAllAmsSpecification_success() throws AmsRequestException {
        AmsSpecification amsSpecification = AmsSpecificationBuilder.aAmsSpecification().withId(1L).build();
        AmsResponse amsResponse = new AmsSpecificationRead(new AmsSpecificationRepositoryMock()).read(new AmsRequest(amsSpecification));

        assertThat(amsResponse, is(notNullValue()));
        assertThat(amsResponse.getAmsSpecificationList(), is(notNullValue()));
        assertThat(amsResponse.getAmsSpecificationList(), is(not(Collections.emptyList())));
        assertThat(amsResponse.getAmsSpecificationList().size(), is(1));
    }

    @Test
    public void readSpecificationById_success() throws AmsRequestException{

        AmsSpecification amsSpecification = AmsSpecificationBuilder.aAmsSpecification().withId(1L).build();
        AmsResponse amsResponse = new AmsSpecificationRead(new AmsSpecificationRepositoryMock()).read(new AmsRequest(amsSpecification));

        assertThat(amsResponse, is(notNullValue()));
        assertThat(amsResponse.getAmsSpecificationList(), is(notNullValue()));
        assertThat(amsResponse.getAmsSpecificationList(), is(not(Collections.emptyList())));
        assertThat(amsResponse.getAmsSpecificationList().size(), is(1));
        assertThat(amsResponse.getAmsSpecificationList(), hasItem(amsSpecification));
        assertThat(amsResponse.getAmsSpecificationList(), not(hasItem(AmsSpecificationBuilder.aAmsSpecification().withId(2l).build())));
    }
}
