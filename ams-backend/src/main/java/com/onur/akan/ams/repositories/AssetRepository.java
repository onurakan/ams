package com.onur.akan.ams.repositories;

import com.onur.akan.ams.domain.AssetEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author Onur Akan
 */
public interface AssetRepository extends CrudRepository<AssetEntity, Long> {
}
