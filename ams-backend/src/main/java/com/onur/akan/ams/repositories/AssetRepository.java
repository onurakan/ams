package com.onur.akan.ams.repositories;

import com.onur.akan.ams.domain.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

/**
 * @Author Onur Akan
 */
public interface AssetRepository extends JpaRepository<AssetEntity, Long> {

    @Query("select a from AssetEntity a Where a.assetId = ?1")
    Optional<AssetEntity> readByAssetId(UUID assetId);
}
