package com.onur.akan.ams.database;

import com.onur.akan.ams.business.asset.AmsAsset;
import com.onur.akan.ams.business.asset.AmsAssetRepository;
import com.onur.akan.ams.business.specification.AmsSpecification;
import com.onur.akan.ams.business.specification.AmsSpecificationRepository;
import com.onur.akan.ams.database.jpa.AssetEntity;
import com.onur.akan.ams.database.jpa.AssetRepository;
import com.onur.akan.ams.database.jpa.ModelMapper;
import com.onur.akan.ams.database.jpa.SpecificationEntity;
import com.onur.akan.ams.database.jpa.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Onur Akan
 */
@Service
public class AmsAssetRepositoryImpl implements AmsAssetRepository, AmsSpecificationRepository {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private SpecificationRepository specificationRepository;

    @Override
    public List<AmsAsset> readAssetAll() {
        List<AmsAsset> result = new ArrayList<>();
        for (AssetEntity assetEntity : assetRepository.findAll()) {
            result.add(ModelMapper.toAmsAsset(assetEntity));
        }
        return result;
    }

    @Override
    public List<AmsAsset> readAsset(AmsAsset amsAsset) {
        if (amsAsset.getAssetId() != null) {
            AssetEntity assetEntity = assetRepository.findById(amsAsset.getAssetId().longValue());
            if (assetEntity != null) {
                return Arrays.asList(ModelMapper.toAmsAsset(assetEntity));
            }
        } else if (amsAsset.getAssetTag() != null) {
            assetRepository.findByAssetTag(amsAsset.getAssetTag());
        }

        return null;
    }


    @Override
    public AmsAsset createAsset(AmsAsset amsAsset) {
        AssetEntity assetEntity = assetRepository.save(ModelMapper.toAssetEntitiy(amsAsset));
        return ModelMapper.toAmsAsset(assetEntity);
    }

    @Override
    public AmsAsset updateAsset(AmsAsset amsAsset) {
        return createAsset(amsAsset);
    }

    @Override
    public void deleteAsset(AmsAsset amsAsset) {
        assetRepository.delete(ModelMapper.toAssetEntitiy(amsAsset));
    }

    @Override
    public List<AmsSpecification> readSpecification() {
        return null;
    }

    @Override
    public List<AmsSpecification> readSpecification(AmsSpecification amsSpecification) {
        if (amsSpecification != null) {
            SpecificationEntity specificationEntity = specificationRepository.findById(amsSpecification.getId().longValue());
            if (specificationEntity != null) {
                return Arrays.asList(ModelMapper.toAmsSpecification(specificationEntity));
            }
        }
        return null;
    }

    @Override
    public AmsSpecification createSpecification(AmsSpecification amsSpecification) {
        SpecificationEntity specificationEntity = specificationRepository.save(ModelMapper.toSpecificationEntity(amsSpecification));
        return ModelMapper.toAmsSpecification(specificationEntity);
    }

    @Override
    public AmsSpecification updateSpecification(AmsSpecification amsSpecification) {
        return createSpecification(amsSpecification);
    }

    @Override
    public void deleteSpecification(AmsSpecification amsSpecification) {

    }
}
