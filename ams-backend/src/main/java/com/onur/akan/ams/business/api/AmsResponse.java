package com.onur.akan.ams.business.api;

import com.onur.akan.ams.business.asset.AmsAsset;
import com.onur.akan.ams.business.specification.AmsSpecification;

import java.util.List;

/**
 * @Author Onur Akan
 */
public class AmsResponse {

    private List<AmsAsset> amsAssetList;
    private List<AmsSpecification> amsSpecificationList;

    public List<AmsAsset> getAmsAssetList() {
        return amsAssetList;
    }

    public void setAmsAssetList(List<AmsAsset> amsAssetList) {
        this.amsAssetList = amsAssetList;
    }

    public List<AmsSpecification> getAmsSpecificationList() {
        return amsSpecificationList;
    }

    public void setAmsSpecificationList(List<AmsSpecification> amsSpecificationList) {
        this.amsSpecificationList = amsSpecificationList;
    }
}
