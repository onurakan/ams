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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
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
    @NotNull
    private Long id;

    @Column(name = "nasset_id", length = 16, updatable = false, nullable = false)//columnDefinition ="varchar",
    @NotNull
    private UUID assetId;

    @Column(name = "nstatus", nullable = false)
    @NotNull
    private AmsEntityStatus status;

    @Column(name = "vclassification", nullable = false)
    @NotNull
    @NotBlank
    private String classification;

    @Column(name = "vdescription", nullable = false)
    @NotNull
    @NotBlank
    private String description;

    @Column(name = "vasset_tag")
    @NotNull
    @NotBlank
    private String assetTag;

    @OneToMany(mappedBy = "assetEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SpecificationEntity> specificationList;

    @CreationTimestamp
    @Column(updatable = false)
    private OffsetDateTime createDate;
    @UpdateTimestamp
    private OffsetDateTime lastModifiedDate;

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
        this.setStatus(AmsEntityStatus.ACTIVE);
        this.setAssetId(UUID.randomUUID());
    }
}