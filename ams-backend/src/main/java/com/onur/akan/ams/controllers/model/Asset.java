package com.onur.akan.ams.controllers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author Onur Akan
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Asset {

    private Long assetId;
    private Integer status;
    private String classification;
    private String description;
    private String assetTag;
    private List<Specification> specificationList;
}
