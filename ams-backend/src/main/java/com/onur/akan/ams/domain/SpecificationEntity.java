package com.onur.akan.ams.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author Onur Akan
 */
@Entity
@Table(name = "tspecification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SpecificationEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "vattribute")
    private String attribute;
    @Column(name = "vattributeDescription")
    private String attributeDescription;
    @Column(name = "vdataType")
    private String dataType;
    @Column(name = "valphnumericValue")
    private String alphnumericValue;
    @Column(name = "valphanumericDescription")
    private String alphanumericDescription;
    @Column(name = "vnumericValue")
    private String numericValue;
    @Column(name = "vnumericDescription")
    private String numericDescription;
    @Column(name = "vunitOfMeasure")
    private String unitOfMeasure;
    @Column(name = "vtableValue")
    private String tableValue;
}
