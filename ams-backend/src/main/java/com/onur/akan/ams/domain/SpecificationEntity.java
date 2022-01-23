package com.onur.akan.ams.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @Author Onur Akan
 */
@Entity
@Table(name = "tspecification")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE tspecification SET status = 'PASSIVE' WHERE id = ? ")
public class SpecificationEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotNull
    private Long id;

    @Column(name = "nspecification_id", length = 16, updatable = false, nullable = false)//columnDefinition ="varchar",
    @NotNull
    private UUID specificationId;

    @Column(name = "nstatus", nullable = false)
    @NotNull
    private AmsEntityStatus status;

    @Column(name = "vattribute", nullable = false)
    @NotBlank
    private String attribute;

    @Column(name = "vattributeDescription", nullable = false)
    @NotBlank
    private String attributeDescription;

    @Column(name = "vdataType")
    @NotBlank
    private String dataType;

    @Column(name = "valphnumericValue")
    @NotBlank
    private String alphanumericValue;

    @Column(name = "valphanumericDescription")
    @NotBlank
    private String alphanumericDescription;

    @Column(name = "vnumericValue")
    @NotBlank
    private String numericValue;

    @Column(name = "vnumericDescription")
    @NotBlank
    private String numericDescription;

    @Column(name = "vunitOfMeasure")
    @NotBlank
    private String unitOfMeasure;

    @Column(name = "vtableValue")
    @NotBlank
    private String tableValue;

    @JoinColumn(name = "asset_id")
    @ManyToOne(optional = false)
    @NotNull
    private AssetEntity assetEntity;

    @CreationTimestamp
    @Column(updatable = false)
    private OffsetDateTime createDate;
    @UpdateTimestamp
    private OffsetDateTime lastModifiedDate;

    @PrePersist
    public void autofill() {
        this.setStatus(AmsEntityStatus.ACTIVE);
        this.setSpecificationId(UUID.randomUUID());
    }
}
