package com.onur.akan.ams.controllers.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.onur.akan.ams.domain.AmsEntityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @Author Onur Akan
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Specification {

    private UUID specificationId;
    private AmsEntityStatus status;
    private String attribute;
    private String attributeDescription;
    private String dataType;
    private String alphanumericValue;
    private String alphanumericDescription;
    private String numericValue;
    private String numericDescription;
    private String unitOfMeasure;
    private String tableValue;
    private UUID assetId;

    private OffsetDateTime createDate;
    private OffsetDateTime lastModifiedDate;
}
