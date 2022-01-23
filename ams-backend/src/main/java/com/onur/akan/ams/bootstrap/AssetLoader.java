package com.onur.akan.ams.bootstrap;

import com.onur.akan.ams.controllers.exception.AmsRequestException;
import com.onur.akan.ams.domain.AssetEntity;
import com.onur.akan.ams.domain.SpecificationEntity;
import com.onur.akan.ams.repositories.AssetRepository;
import com.onur.akan.ams.services.AssetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Slf4j
public class AssetLoader implements CommandLineRunner {
    private final AssetRepository assetRepository;
    private final AssetService assetService;
    @Override
    public void run(String... args) throws Exception {

        /*Executors.newCachedThreadPool().submit(() -> {
            loadAssets(50, 10);
            return null;
        });*/

        insertInitialAssets(50, 10);
    }

    private void insertInitialAssets(Integer assetCount, Integer specificationCount) throws AmsRequestException {
        if (assetRepository.count() == 0) {
            log.info("No asset in repository. Initializing some asset");

            for (int i = 0; i < assetCount; i++) {
                assetService.save(asset(i, specificationCount));
            }
        }
    }

    private AssetEntity asset(Integer i, Integer specificationCount) {
        AssetEntity assetEntity = AssetEntity.builder()
                .classification("classification" + i)
                .description("description" + i)
                .assetTag("tag" + i)
                .build();

        assetEntity.setSpecificationList(new ArrayList<>());
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
