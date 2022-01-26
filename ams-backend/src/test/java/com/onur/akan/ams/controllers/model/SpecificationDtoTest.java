package com.onur.akan.ams.controllers.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onur.akan.ams.domain.AmsEntityStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@JsonTest
@AutoConfigureDataJpa
class SpecificationDtoTest {

    @Autowired
    private ObjectMapper objectMapper;

    /*
    SpecificationJson: {"specificationId":"56baaa40-e610-4973-889d-3e05a9052517","status":"ACTIVE","attribute":"attribute1","attributeDescription":"attributeDescription1","dataType":"dataType1","alphanumericValue":"alphanumericValue1","alphanumericDescription":"alphanumericDescription1","numericValue":"numericValue1","numericDescription":"numericDescription1","unitOfMeasure":"unitOfMeasure1","tableValue":"tableValue1","assetId":"d06bda4c-f04c-473a-98e0-71f5151760d4","createDate":"2022-01-25T19:49:58.865+03:00","lastModifiedDate":"2022-01-25T19:49:58.865+03:00"}
     */
    @Test
    public void testSerializeDto() throws JsonProcessingException {
        SpecificationDto specificationDto = getDto();
        String jsonString = objectMapper.writeValueAsString(specificationDto);

        System.out.println("SpecificationJson: " + jsonString);
    }

    /*
    SpecificationDto: SpecificationDto(specificationId=e923d26e-1760-429d-92fd-df2cb8ee7ca4, status=ACTIVE, attribute=attribute1, attributeDescription=attributeDescription1, dataType=dataType1, alphanumericValue=alphanumericValue1, alphanumericDescription=alphanumericDescription1, numericValue=numericValue1, numericDescription=numericDescription1, unitOfMeasure=unitOfMeasure1, tableValue=tableValue1, assetId=16bdc43f-4618-4d01-a25f-fe0cc1e7ac84, createDate=2022-01-25T16:47:41.016Z, lastModifiedDate=2022-01-25T16:47:41.017Z)
     */
    @Test
    public void testDeserializeDto() throws JsonProcessingException {
        String jsonString = "{\"specificationId\":\"e923d26e-1760-429d-92fd-df2cb8ee7ca4\",\"status\":\"ACTIVE\",\"attribute\":\"attribute1\",\"attributeDescription\":\"attributeDescription1\",\"dataType\":\"dataType1\",\"alphanumericValue\":\"alphanumericValue1\",\"alphanumericDescription\":\"alphanumericDescription1\",\"numericValue\":\"numericValue1\",\"numericDescription\":\"numericDescription1\",\"unitOfMeasure\":\"unitOfMeasure1\",\"tableValue\":\"tableValue1\",\"assetId\":\"16bdc43f-4618-4d01-a25f-fe0cc1e7ac84\",\"createDate\":\"2022-01-25T19:47:41.016+03:00\",\"lastModifiedDate\":\"2022-01-25T19:47:41.017+03:00\"}\n";
        SpecificationDto specificationDto = objectMapper.readValue(jsonString, SpecificationDto.class);

        System.out.println("SpecificationDto: " + specificationDto);
    }

    SpecificationDto getDto() {
        return SpecificationDto.builder()
                .specificationId(UUID.randomUUID())
                .alphanumericDescription("alphanumericDescription1")
                .alphanumericValue("alphanumericValue1")
                .attribute("attribute1")
                .attributeDescription("attributeDescription1")
                .createDate(OffsetDateTime.now())
                .dataType("dataType1")
                .lastModifiedDate(OffsetDateTime.now())
                .numericDescription("numericDescription1")
                .numericValue("numericValue1")
                .status(AmsEntityStatus.ACTIVE)
                .tableValue("tableValue1")
                .unitOfMeasure("unitOfMeasure1")
                .assetId(UUID.randomUUID())
                .build();
    }

}