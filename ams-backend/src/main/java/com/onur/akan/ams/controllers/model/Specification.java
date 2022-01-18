package com.onur.akan.ams.controllers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author Onur Akan
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Specification {

    private Long id;
    private Integer status;
    private String attribute;
    private String attributeDescription;
    private String dataType;
    private String alphanumericValue;
    private String alphanumericDescription;
    private String numericValue;
    private String numericDescription;
    private String unitOfMeasure;
    private String tableValue;
    private Long assetId;
}
