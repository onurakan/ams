package com.onur.akan.ams.repositories;


import com.onur.akan.ams.domain.SpecificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author Onur Akan
 */
public interface SpecificationRepository  extends JpaRepository<SpecificationEntity, Long> {

    @Query("select s from SpecificationEntity s Where s.assetEntity.id = ?1")
    List<SpecificationEntity> findByAssetId(Long assetId);
}
