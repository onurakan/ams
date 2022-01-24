package com.onur.akan.ams.controllers.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.onur.akan.ams.domain.AmsEntityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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

    @Null(groups = {OnCreate.class})
    @NotNull(groups = {OnUpdate.class})
    private UUID specificationId;

    @Null(groups = {OnCreate.class})
    @NotNull(groups = {OnUpdate.class})
    private AmsEntityStatus status;

    @NotNull
    @NotBlank
    private String attribute;
    @NotNull
    @NotBlank
    private String attributeDescription;
    @NotNull
    @NotBlank
    private String dataType;
    @NotNull
    @NotBlank
    private String alphanumericValue;
    @NotNull
    @NotBlank
    private String alphanumericDescription;
    @NotNull
    @NotBlank
    private String numericValue;
    @NotNull
    @NotBlank
    private String numericDescription;
    @NotNull
    @NotBlank
    private String unitOfMeasure;
    @NotNull
    @NotBlank
    private String tableValue;
    @NotNull
    private UUID assetId;

    @Null(groups = {OnCreate.class})
    @NotNull(groups = {OnUpdate.class})
    private OffsetDateTime createDate;

    @Null(groups = {OnCreate.class})
    @NotNull(groups = {OnUpdate.class})
    private OffsetDateTime lastModifiedDate;
}
