package com.onur.akan.ams.business.api;

import com.onur.akan.ams.business.asset.AmsAsset;
import com.onur.akan.ams.business.asset.AmsAssetRepository;

import java.util.List;

/**
 * @Author Onur Akan
 */
public class AmsAssetRead {

    private AmsAssetRepository amsAssetRepository;

    public AmsAssetRead (AmsAssetRepository amsAssetRepository) {
        this.amsAssetRepository = amsAssetRepository;
    }

    public AmsResponse read(AmsRequest amsRequest) {
        //TODO implement business rules here
        if (amsRequest == null) throw new AmsRequestException("amsRequest cannot be null");
        if (amsRequest.getAmsAsset() == null) throw new AmsRequestException("amsRequest.amsAsset cannot be null");

        if (amsRequest.getAmsAsset().getAssetId() == null) {
            return readAll();
        }

        List<AmsAsset> amsAssetList = amsAssetRepository.readAsset(amsRequest.getAmsAsset());
        AmsResponse amsResponse = new AmsResponse();
        amsResponse.setAmsAssetList(amsAssetList);
        return amsResponse;
    }

    private AmsResponse readAll(){
        List<AmsAsset> amsAssetList = amsAssetRepository.readAssetAll();
        AmsResponse amsResponse = new AmsResponse();
        amsResponse.setAmsAssetList(amsAssetList);
        return amsResponse;
    }
}
