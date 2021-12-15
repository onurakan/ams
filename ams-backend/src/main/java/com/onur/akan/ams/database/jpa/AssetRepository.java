package com.onur.akan.ams.database.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author Onur Akan
 */
public interface AssetRepository extends CrudRepository<AssetEntity, Long> {

	List<AssetEntity> findByAssetTag(String lastName);

	AssetEntity findById(long id);
}
