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
import java.sql.Timestamp;
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
    private Long id;

    @Column(name = "nspecification_id", length = 16, updatable = false, nullable = false)//columnDefinition ="varchar",
    private UUID specificationId;

    @Column(name = "nstatus", nullable = false)
    private String status;

    @Column(name = "vattribute", nullable = false)
    private String attribute;

    @Column(name = "vattributeDescription", nullable = false)
    private String attributeDescription;

    @Column(name = "vdataType")
    private String dataType;

    @Column(name = "valphanumericValue")
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

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    @PrePersist
    public void autofill() {
        this.setStatus(AmsEntityStatus.ACTIVE.toString());
        this.setSpecificationId(UUID.randomUUID());
    }
}
