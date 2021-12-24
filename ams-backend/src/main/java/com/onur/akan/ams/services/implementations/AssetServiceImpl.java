package com.onur.akan.ams.services.implementations;

import com.onur.akan.ams.AmsRequestException;
import com.onur.akan.ams.domain.AssetEntity;
import com.onur.akan.ams.repositories.AssetRepository;
import com.onur.akan.ams.services.AssetService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Setter
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    @Override
    public List<?> listAll() {
        List<AssetEntity> entities = new ArrayList<>();
        assetRepository.findAll().forEach(entities::add);
        return entities;
    }

    @Override
    public AssetEntity getById(Long id) {
        return assetRepository.findById(id).get();
    }

    @Override
    public AssetEntity save(AssetEntity assetEntity) throws AmsRequestException {
        //TODO implement business rules here
        if (assetEntity == null) throw new AmsRequestException("asset cannot be null");
        if (assetEntity.getId() != null) throw new AmsRequestException("Update is not allowed");

        return assetRepository.save(assetEntity);
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