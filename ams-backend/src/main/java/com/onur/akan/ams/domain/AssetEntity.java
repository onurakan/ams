package com.onur.akan.ams.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @Author Onur Akan
 */
@Entity
@Table(name = "tasset")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AssetEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name = "nstatus")
    private Integer status;
    @Column(name = "vclassification")
    private String classification;
    @Column(name = "vdescription")
    private String description;
    @Column(name = "vasset_tag")
    private String assetTag;
    @OneToMany( targetEntity= SpecificationEntity.class, cascade = CascadeType.ALL)
    private List<SpecificationEntity> specificationList;
}
