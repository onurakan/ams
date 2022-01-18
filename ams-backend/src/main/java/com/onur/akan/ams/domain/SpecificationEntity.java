package com.onur.akan.ams.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @Author Onur Akan
 */
@Entity
@Table(name = "tspecification")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@SQLDelete(sql = "UPDATE tspecification SET status = -1 WHERE id = ? ")
public class SpecificationEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "nstatus", nullable = false)
    private Integer status;
    @Column(name = "vattribute", nullable = false)
    private String attribute;
    @Column(name = "vattributeDescription", nullable = false)
    private String attributeDescription;
    @Column(name = "vdataType")
    private String dataType;
    @Column(name = "valphnumericValue")
    private String alphanumericValue;
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
    @JoinColumn(name = "asset_id")
    @ManyToOne(optional = false)
    private AssetEntity assetEntity;
}
