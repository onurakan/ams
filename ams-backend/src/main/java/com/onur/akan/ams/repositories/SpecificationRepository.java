package com.onur.akan.ams.repositories;


import com.onur.akan.ams.domain.SpecificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @Author Onur Akan
 */
public interface SpecificationRepository  extends JpaRepository<SpecificationEntity, Long> {

    @Query("select s from SpecificationEntity s Where s.assetEntity.assetId = ?1")
    List<SpecificationEntity> findByAssetId(UUID assetId);

    @Query("select s from SpecificationEntity s Where s.specificationId = ?1")
    Optional<SpecificationEntity> findBySpecificationId(UUID specificationId);
}
