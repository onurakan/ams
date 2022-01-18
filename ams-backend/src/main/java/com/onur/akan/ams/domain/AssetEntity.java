package com.onur.akan.ams.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@SQLDelete(sql = "UPDATE tasset SET status = -1 WHERE id = ? ")
public class AssetEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name = "nstatus", nullable = false)
    private Integer status;
    @Column(name = "vclassification", nullable = false)
    private String classification;
    @Column(name = "vdescription", nullable = false)
    private String description;
    @Column(name = "vasset_tag")
    private String assetTag;
    @OneToMany(mappedBy = "assetEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SpecificationEntity> specificationList;

    //@Transient
    public void addSpecification(SpecificationEntity specificationEntity) {
        specificationList.add(specificationEntity);
    }

    //@Transient
    public void removeSpecification(SpecificationEntity specificationEntity) {
        specificationList.remove(specificationEntity);
    }
}