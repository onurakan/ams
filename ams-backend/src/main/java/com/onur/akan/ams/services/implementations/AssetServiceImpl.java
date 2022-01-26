package com.onur.akan.ams.services.implementations;

import com.onur.akan.ams.controllers.exception.AmsRequestException;
import com.onur.akan.ams.domain.AssetEntity;
import com.onur.akan.ams.domain.SpecificationEntity;
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
import javax.validation.Valid;
import java.math.BigDecimal;
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
        return assetRepository.findAll();
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
    public AssetEntity save(@Valid AssetEntity assetEntity) throws AmsRequestException {
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
        if (assetEntity.getAssetId() == null) throw new AmsRequestException("assetId cannot be null");

        return assetRepository.save(assetEntity);
    }

    @Override
    public void delete(Long id) {
        assetRepository.deleteById(id);
    }


    @Override
    public void insertInitialAssets(Integer assetCount, Integer specificationCount) throws AmsRequestException {
        long count = assetRepository.count();
        if (count == 0) {
            log.info(String.format("No asset in repository. Initializing repo with %s asset, %s specification", assetCount, specificationCount));

            for (int i = 0; i < assetCount; i++) {
                save(asset(i, specificationCount));
            }
        } else {
            log.info(String.format("There are %s assets in repo. Will not initialize more!", count));
        }
    }

    private AssetEntity asset(Integer i, Integer specificationCount) {
        AssetEntity assetEntity = AssetEntity.builder()
                .classification("classification" + i)
                .description("description" + i)
                .assetTag("tag" + i)
                .price(BigDecimal.valueOf(12.90))
                .build();

        assetEntity.setSpecificationList(new ArrayList<>(specificationCount));
        for (int j = 0; j < specificationCount; j++) {
            assetEntity.getSpecificationList().add(specificationList(j, assetEntity));
        }

        return assetEntity;
    }

    private SpecificationEntity specificationList(Integer i, AssetEntity assetEntity) {
        SpecificationEntity specificationEntity = SpecificationEntity.builder()
                .alphanumericDescription("alphanumericDescription" + i)
                .attribute("attribute" + i)
                .attributeDescription("attributeDescription" +i)
                .tableValue("tableValue" + i)
                .unitOfMeasure("unitOfMeasure" + i)
                .numericDescription("numericDescription" +i)
                .numericValue("numericValue" +i)
                .dataType("dataType" + i)
                .alphanumericValue("alphanumericValue" + i)
                .assetEntity(assetEntity)
                .build();

        return specificationEntity;
    }
}