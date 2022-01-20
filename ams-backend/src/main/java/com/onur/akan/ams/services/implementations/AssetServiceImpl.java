package com.onur.akan.ams.services.implementations;

import com.onur.akan.ams.controllers.exception.AmsRequestException;
import com.onur.akan.ams.domain.AssetEntity;
import com.onur.akan.ams.repositories.AssetRepository;
import com.onur.akan.ams.services.AssetService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Setter
@RequiredArgsConstructor
@Slf4j
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    @Override
    public AssetEntity getById(Long id) {
        return assetRepository.findById(id).get();
    }

    @Override
    public List<?> listAll() {
        List<AssetEntity> entities = new ArrayList<>();
        assetRepository.findAll().forEach(entities::add);
        return entities;
    }

    public static ExampleMatcher CUSTOM_EXAMPLE_MATCHER = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase();

    @Override
    public Page<AssetEntity> findByExampleMatcher(AssetEntity assetEntity, @NonNull Integer currentPageNumber, @NonNull Integer pageSize) {

        Example<AssetEntity> assetExample = Example.of(assetEntity, CUSTOM_EXAMPLE_MATCHER);

        return assetRepository.findAll(assetExample, PageRequest.of(currentPageNumber - 1, pageSize, Sort.by("id").ascending()));
    }

    @Override
    public AssetEntity findByAssetId(UUID assetId) {
        return assetRepository.readByAssetId(assetId).get();
    }

    @Override
    @Transactional
    public AssetEntity save(AssetEntity assetEntity) throws AmsRequestException {
        //TODO implement business rules here
        if (assetEntity == null) throw new AmsRequestException("asset cannot be null");
        if (assetEntity.getId() != null) throw new AmsRequestException("Update is not allowed");

        AssetEntity newAssetEntity = assetRepository.save(assetEntity);
        log.info("Created asset with id=" + newAssetEntity.getId());
        return newAssetEntity;
    }

    @Override
    public AssetEntity update(AssetEntity assetEntity) throws AmsRequestException {
        //TODO implement business rules here
        if (assetEntity == null) throw new AmsRequestException("asset cannot be null");
        if (assetEntity.getId() == null) throw new AmsRequestException("asset.id cannot be null");

        return assetRepository.save(assetEntity);
    }

    @Override
    public void delete(Long id) {
        assetRepository.deleteById(id);
    }
}