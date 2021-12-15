package com.onur.akan.ams.business.api;

import com.onur.akan.ams.business.asset.AmsAsset;
import com.onur.akan.ams.business.specification.AmsSpecification;

/**
 * @Author Onur Akan
 */
public class AmsRequest {

    private AmsAsset amsAsset;
    private AmsSpecification amsSpecification;

    public AmsRequest(AmsAsset amsAsset) {
        this.amsAsset = amsAsset;
    }

    public AmsRequest(AmsSpecification amsSpecification) {
        this.amsSpecification = amsSpecification;
    }

    public AmsAsset getAmsAsset() {
        return amsAsset;
    }

    public void setAmsAsset(AmsAsset amsAsset) {
        this.amsAsset = amsAsset;
    }

    public AmsSpecification getAmsSpecification() {
        return amsSpecification;
    }

    public void setAmsSpecification(AmsSpecification amsSpecification) {
        this.amsSpecification = amsSpecification;
    }
}
