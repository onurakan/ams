package com.onur.akan.ams.controllers.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.math.BigDecimal;
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
public class AssetDto {

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

    @JsonFormat(shape=JsonFormat.Shape.STRING)
    @NotNull
    private BigDecimal price;

    private List<SpecificationDto> specificationList;

    //@JsonSerialize(using = CustomLocalDateSerializer.class)
    //@JsonDeserialize(using = CustomLocalDateDeSerializer.class)
    //@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Null(groups = {OnCreate.class})
    @NotNull(groups = {OnUpdate.class})
    private OffsetDateTime createDate;

    //@JsonSerialize(using = CustomLocalDateSerializer.class)
    //@JsonDeserialize(using = CustomLocalDateDeserializer.class)
    //@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Null(groups = {OnCreate.class})
    @NotNull(groups = {OnUpdate.class})
    private OffsetDateTime lastModifiedDate;
}
