package com.onur.akan.ams.business.api;

import com.onur.akan.ams.business.asset.AmsAsset;
import com.onur.akan.ams.business.asset.AmsAssetBuilder;
import com.onur.akan.ams.business.asset.AmsAssetRepository;
import com.onur.akan.ams.business.specification.AmsSpecification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Onur Akan
 *
 * Crud operations on AmsAsset AmsSpecification.
 */
public class AmsAssetWrite {
    private AmsAssetRepository amsAssetRepository;

    public AmsAssetWrite (AmsAssetRepository amsAssetRepository) {
        this.amsAssetRepository = amsAssetRepository;
    }

    public AmsResponse create(AmsRequest amsRequest) {
        //TODO implement business rules here
        if (amsRequest == null) throw new AmsRequestException("amsRequest cannot be null");
        if (amsRequest.getAmsAsset() == null) throw new AmsRequestException("amsRequest.amsAsset cannot be null");
        if (amsRequest.getAmsAsset().getAssetId() != null) throw new AmsRequestException("Update is not allowed");

        AmsAsset amsAsset = amsAssetRepository.createAsset(amsRequest.getAmsAsset());
        AmsResponse amsResponse = new AmsResponse();
        amsResponse.setAmsAssetList(new ArrayList<>());
        amsResponse.getAmsAssetList().add(amsAsset);
        return amsResponse;
    }
    public boolean update(AmsRequest amsRequest) {
        if (amsRequest == null) throw new AmsRequestException("amsRequest cannot be null");
        if (amsRequest.getAmsAsset().getAssetId() == null) throw new AmsRequestException("amsRequest.amsAsset.assetId cannot be null");

        AmsAsset amsAsset = readAssetById(amsRequest.getAmsAsset().getAssetId());
        if (amsAsset != null) {
            setChangedField(amsAsset, amsRequest.getAmsAsset());
            return amsAssetRepository.updateAsset(amsAsset) != null;
        }
        return false;
    }

    private void setChangedField(AmsAsset amsAssetTo, AmsAsset amsAssetFrom) {
        if (amsAssetFrom.getClassification() != null) {
            amsAssetTo.setClassification(amsAssetFrom.getClassification());
        }
        if (amsAssetFrom.getDescription() != null) {
            amsAssetTo.setDescription(amsAssetFrom.getDescription());
        }
        if (amsAssetFrom.getStatus() != null) {
            amsAssetTo.setStatus(amsAssetFrom.getStatus());
        }
        if (amsAssetFrom.getAssetTag() != null) {
            amsAssetTo.setAssetTag(amsAssetFrom.getAssetTag());
        }
        if (amsAssetTo.getAmsSpecificationList() != null && amsAssetFrom.getAmsSpecificationList() != null) {
            Map<Long, AmsSpecification> specKeyValueTo = list2Map(amsAssetTo.getAmsSpecificationList());
            Map<Long, AmsSpecification> specKeyValueFrom = list2Map(amsAssetFrom.getAmsSpecificationList());
            for (Long specKeyFrom : specKeyValueFrom.keySet()) {
                if (specKeyValueTo.containsKey(specKeyFrom)) {
                    specKeyValueTo.get(specKeyFrom).setAttribute(specKeyValueFrom.get(specKeyFrom).getAttribute());
                    specKeyValueTo.get(specKeyFrom).setAttribureDescription(specKeyValueFrom.get(specKeyFrom).getAttribureDescription());
                    specKeyValueTo.get(specKeyFrom).setAlphanumericDescription(specKeyValueFrom.get(specKeyFrom).getAlphanumericDescription());
                    specKeyValueTo.get(specKeyFrom).setDataType(specKeyValueFrom.get(specKeyFrom).getDataType());
                    specKeyValueTo.get(specKeyFrom).setAlphnumericValue(specKeyValueFrom.get(specKeyFrom).getAlphnumericValue());
                    specKeyValueTo.get(specKeyFrom).setNumericValue(specKeyValueFrom.get(specKeyFrom).getNumericValue());
                    specKeyValueTo.get(specKeyFrom).setNumericDescription(specKeyValueFrom.get(specKeyFrom).getNumericDescription());
                    specKeyValueTo.get(specKeyFrom).setUnitOfMeasure(specKeyValueFrom.get(specKeyFrom).getUnitOfMeasure());
                    specKeyValueTo.get(specKeyFrom).setTableValue(specKeyValueFrom.get(specKeyFrom).getTableValue());
                } else {
                    //TODO requestten cikartilan spec, cikartilan database'den silinsin mi?
                }
            }
        }
    }

    private HashMap<Long, AmsSpecification> list2Map(List<AmsSpecification> amsSpecificationList) {
        HashMap<Long, AmsSpecification> result = new HashMap<>();
        for (AmsSpecification amsSpecification : amsSpecificationList) {
            result.put(amsSpecification.getId(), amsSpecification);
        }
        return result;
    }

    public boolean delete(AmsRequest amsRequest) {
        if (amsRequest == null) throw new AmsRequestException("amsRequest cannot be null");
        if (amsRequest.getAmsAsset() == null) throw new AmsRequestException("amsRequest cannot be null");
        if (amsRequest.getAmsAsset().getAssetId() == null) throw new AmsRequestException("amsRequest.amsAsset.assetID cannot be null");

        //TODO implement business rules here
        if (readAssetById(amsRequest.getAmsAsset().getAssetId()) != null) {
            amsAssetRepository.deleteAsset(amsRequest.getAmsAsset());
            return true;
        }
        return false;
    }

    private AmsAsset readAssetById(Long assetId) {
        AmsAsset amsAsset = AmsAssetBuilder.aAmsAsset().withAssetId(assetId).build();
        AmsResponse amsResponse = new AmsAssetRead(amsAssetRepository).read(new AmsRequest(amsAsset));
        if (amsResponse != null && amsResponse.getAmsAssetList() != null && !amsResponse.getAmsAssetList().isEmpty()) {
            return amsResponse.getAmsAssetList().get(0);
        }
        return null;
    }
}
