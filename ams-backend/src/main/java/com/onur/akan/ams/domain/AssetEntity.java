package com.onur.akan.ams.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * @Author Onur Akan
 */
@Entity
@Table(name = "tasset")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE tasset SET status = 'PASSIVE' WHERE id = ? ")
public class AssetEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "nasset_id", length = 16, updatable = false, nullable = false)//columnDefinition ="varchar",
    private UUID assetId;

    @Column(name = "nstatus", nullable = false)
    private String status;

    @Column(name = "vclassification", nullable = false)
    private String classification;

    @Column(name = "vdescription", nullable = false)
    private String description;

    @Column(name = "vasset_tag")
    private String assetTag;

    @OneToMany(mappedBy = "assetEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SpecificationEntity> specificationList;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    //@Transient
    public void addSpecification(SpecificationEntity specificationEntity) {
        specificationList.add(specificationEntity);
    }

    //@Transient
    public void removeSpecification(SpecificationEntity specificationEntity) {
        specificationList.remove(specificationEntity);
    }

    @PrePersist
    public void autofill() {
        this.setStatus(AmsEntityStatus.ACTIVE.toString());
        this.setAssetId(UUID.randomUUID());
    }
}