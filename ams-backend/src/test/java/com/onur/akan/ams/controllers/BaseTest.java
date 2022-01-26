package com.onur.akan.ams.controllers;

import com.onur.akan.ams.domain.AmsEntityStatus;
import com.onur.akan.ams.domain.AssetEntity;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.key;

public class BaseTest {

    protected AssetEntity aAsset(UUID assetId) {
        return AssetEntity.builder()
                .id(1L)
                .assetId(assetId)
                .status(AmsEntityStatus.ACTIVE.toString())
                .assetTag("assetTag1")
                .classification("classification1")
                .description("description1")
                .price(BigDecimal.valueOf(12.90))
                .createDate(Timestamp.valueOf(LocalDateTime.parse("2022-01-25T22:08:00+03:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME)))
                //.lastModifiedDate(Timestamp.valueOf(LocalDateTime.parse("2022-01-25T22:08:00+03:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME)))
                .build();
    }

    protected AssetEntity newAsset(UUID assetId) {
        return AssetEntity.builder()
                .assetTag("assetTag1")
                .classification("classification1")
                .description("description1")
                .price(BigDecimal.valueOf(12.90))
                .build();
    }

    protected static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        protected FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }

}
