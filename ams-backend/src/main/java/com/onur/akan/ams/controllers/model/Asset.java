package com.onur.akan.ams.controllers.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private UUID assetId;
    private Integer status;
    private String classification;
    private String description;
    private String assetTag;
    private List<Specification> specificationList;
}
