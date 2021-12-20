package com.onur.akan.ams;

import com.onur.akan.ams.business.api.AmsAssetRead;
import com.onur.akan.ams.business.api.AmsAssetWrite;
import com.onur.akan.ams.business.api.AmsSpecificationRead;
import com.onur.akan.ams.business.api.AmsSpecificationWrite;
import com.onur.akan.ams.business.asset.AmsAssetRepository;
import com.onur.akan.ams.business.specification.AmsSpecificationRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author Onur Akan
 */
@Component
public class AmsBusinessApiFactory implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static final String AMS_ASSET_REPOSITORY = "amsAssetRepositoryImpl"; //Baska bir db entegrasyonunda onun ipmlementation'i ile degistirilebilir.

    @Override
    public void setApplicationContext(ApplicationContext _applicationContext) throws BeansException {
        applicationContext = _applicationContext;
    }

    public static AmsAssetRead getAmsAssetRead () {
        AmsAssetRepository amsAssetRepository = applicationContext.getBean(AMS_ASSET_REPOSITORY, AmsAssetRepository.class);
        return new AmsAssetRead(amsAssetRepository);
    }

    public static AmsAssetWrite getAmsAssetWrite() {
        AmsAssetRepository amsAssetRepository = applicationContext.getBean(AMS_ASSET_REPOSITORY, AmsAssetRepository.class);
        return new AmsAssetWrite(amsAssetRepository);
    }

    public static AmsSpecificationRead getAmsSpecificationRead() {
        AmsSpecificationRepository amsSpecificationRepository = applicationContext.getBean(AMS_ASSET_REPOSITORY, AmsSpecificationRepository.class);
        return new AmsSpecificationRead(amsSpecificationRepository);
    }

    public static AmsSpecificationWrite getAmsSpecificationWrite() {
        AmsSpecificationRepository amsSpecificationRepository = applicationContext.getBean(AMS_ASSET_REPOSITORY, AmsSpecificationRepository.class);
        return new AmsSpecificationWrite(amsSpecificationRepository);
    }
}
