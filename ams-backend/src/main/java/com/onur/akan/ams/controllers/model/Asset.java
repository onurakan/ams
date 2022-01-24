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
import java.util.List;
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
public class Asset {

    //@JsonIgnore
    @Null(groups = {OnCreate.class})
    @NotNull(groups = {OnUpdate.class})
    private UUID assetId;

    @Null(groups = {OnCreate.class})
    @NotNull(groups = {OnUpdate.class})
    private AmsEntityStatus status;
    @NotNull
    @NotBlank
    private String classification;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private String assetTag;
    private List<Specification> specificationList;

    @Null(groups = {OnCreate.class})
    @NotNull(groups = {OnUpdate.class})
    private OffsetDateTime createDate;

    @Null(groups = {OnCreate.class})
    @NotNull(groups = {OnUpdate.class})
    private OffsetDateTime lastModifiedDate;
}
