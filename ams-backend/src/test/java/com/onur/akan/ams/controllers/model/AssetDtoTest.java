package com.onur.akan.ams.controllers.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onur.akan.ams.domain.AmsEntityStatus;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@JsonTest
@AutoConfigureDataJpa
@RequiredArgsConstructor
class AssetDtoTest {

    @Autowired
    private ObjectMapper objectMapper;

    /*
    AssetJson: {"assetId":"0d7aeccc-1473-486d-80ca-0e161a74c785","status":"ACTIVE","classification":"classification1","description":"description1","assetTag":"assetTag1","createDate":"2022-01-25T19:40:47.979+03:00","lastModifiedDate":"2022-01-25T19:40:47.979+03:00"}
     */
    @Test
    public void testSerializeDto() throws JsonProcessingException {
        AssetDto assetDto = getDto();
        String jsonString = objectMapper.writeValueAsString(assetDto);
        System.out.println("AssetJson: "+ jsonString);
    }
    /*
    AssetDto: AssetDto(assetId=4088ede6-9195-4801-9802-1ca51529b2a0, status=ACTIVE, classification=classification1, description=description1, assetTag=assetTag1, specificationList=null, createDate=2022-01-25T16:36:06.790Z, lastModifiedDate=2022-01-25T16:36:06.790Z)
     */
    @Test
    public void testDeserializeDto() throws JsonProcessingException {
        String jsonString = "{\"assetId\":\"4088ede6-9195-4801-9802-1ca51529b2a0\",\"status\":\"ACTIVE\",\"classification\":\"classification1\",\"description\":\"description1\",\"assetTag\":\"assetTag1\",\"createDate\":\"2022-01-25T19:36:06.79+03:00\",\"lastModifiedDate\":\"2022-01-25T19:36:06.79+03:00\"}\n";
        AssetDto assetDto = objectMapper.readValue(jsonString, AssetDto.class);
        System.out.println("AssetDto: " + assetDto);
    }

    AssetDto getDto() {
        return AssetDto.builder()
                .assetId(UUID.randomUUID())
                .assetTag("assetTag1")
                .classification("classification1")
                .description("description1")
                .lastModifiedDate(OffsetDateTime.now())
                .createDate(OffsetDateTime.parse("2022-01-25T22:08:00Z", DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .lastModifiedDate(OffsetDateTime.parse("2022-01-25T22:08:00Z", DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .status(AmsEntityStatus.ACTIVE)
                .build();
    }
}