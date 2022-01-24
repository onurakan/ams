package com.onur.akan.ams.services.implementations;

import com.onur.akan.ams.controllers.exception.AmsRequestException;
import com.onur.akan.ams.domain.AssetEntity;
import com.onur.akan.ams.domain.SpecificationEntity;
import com.onur.akan.ams.repositories.AssetRepository;
import com.onur.akan.ams.repositories.SpecificationRepository;
import com.onur.akan.ams.services.SpecificationService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Setter
@RequiredArgsConstructor
@Slf4j
public class SpecificationServiceImpl implements SpecificationService {

    private final AssetRepository assetRepository;
    private final SpecificationRepository specificationRepository;

    @Override
    public List<?> listAll() {
        return specificationRepository.findAll();
    }

    @Override
    public List<SpecificationEntity> findByAssetId(UUID assetId) {
        return specificationRepository.findByAssetId(assetId);
    }

    @Override
    public SpecificationEntity findBySpecificationId(UUID specificationId) {
        return specificationRepository.findBySpecificationId(specificationId).get();
    }


    @Override
    public SpecificationEntity getById(Long id) {
        return specificationRepository.findById(id).get();
    }

    @Override
    @Transactional
    public SpecificationEntity save(SpecificationEntity specificationEntity) throws AmsRequestException {
        //TODO implement business rules here
        if (specificationEntity == null) throw new AmsRequestException("specification cannot be null");
        if (specificationEntity.getId() != null) throw new AmsRequestException("Update is not allowed");
        if (specificationEntity.getAssetEntity() == null) throw new AmsRequestException("specification.asset cannot be null");
        if (specificationEntity.getAssetEntity().getAssetId() == null) throw new AmsRequestException("specification.asset.assetId cannot be null");

        AssetEntity assetEntity = assetRepository.readByAssetId(specificationEntity.getAssetEntity().getAssetId()).get();
        if (assetEntity == null) throw new NoSuchElementException(String.format("asset.id=%s not found!", specificationEntity.getAssetEntity().getId()));

        specificationEntity.setAssetEntity(assetEntity);
        assetEntity.addSpecification(specificationEntity);

        SpecificationEntity newSpecificationEntity = specificationRepository.save(specificationEntity);
        log.info("Created specification with id=" + newSpecificationEntity.getId());
        return newSpecificationEntity;
    }

    @Override
    public SpecificationEntity update(SpecificationEntity specificationEntity) throws AmsRequestException {
        //TODO implement business rules here
        if (specificationEntity == null) throw new AmsRequestException("specification cannot be null");
        if (specificationEntity.getId() == null) throw new AmsRequestException("specification.id cannot be null");
        return specificationRepository.save(specificationEntity);
    }

    @Override
    public void delete(Long id) {
        specificationRepository.deleteById(id);
    }
}
