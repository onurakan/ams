package com.onur.akan.ams.repositories;

import com.onur.akan.ams.domain.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Onur Akan
 */
public interface AssetRepository extends JpaRepository<AssetEntity, Long> {
}
