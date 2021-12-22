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

    // Baska tipte repository entegrasyonunda onun ipmlementation'i ile degistirilebilir.
    public static final String AMS_ASSET_REPOSITORY = "amsAssetRepositoryImpl";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        amsAssetRepository         = applicationContext.getBean(AMS_ASSET_REPOSITORY, AmsAssetRepository.class);
        amsSpecificationRepository = applicationContext.getBean(AMS_ASSET_REPOSITORY, AmsSpecificationRepository.class);
    }

    private static AmsAssetRepository amsAssetRepository;
    private static AmsSpecificationRepository amsSpecificationRepository;
    private static AmsAssetRead amsAssetRead;
    private static AmsAssetWrite amsAssetWrite;
    private static AmsSpecificationRead amsSpecificationRead;
    private static AmsSpecificationWrite amsSpecificationWrite;

    public synchronized static AmsAssetRead getAmsAssetRead () {
        return (amsAssetRead == null) ? amsAssetRead = new AmsAssetRead(amsAssetRepository) : amsAssetRead;
    }

    public synchronized static AmsAssetWrite getAmsAssetWrite() {
        return (amsAssetWrite == null) ? amsAssetWrite = new AmsAssetWrite(amsAssetRepository) : amsAssetWrite;
    }

    public synchronized static AmsSpecificationRead getAmsSpecificationRead() {
        return (amsSpecificationRead == null) ? amsSpecificationRead = new AmsSpecificationRead(amsSpecificationRepository) : amsSpecificationRead;
    }

    public synchronized static AmsSpecificationWrite getAmsSpecificationWrite() {
        return (amsSpecificationWrite == null) ? amsSpecificationWrite = new AmsSpecificationWrite(amsSpecificationRepository) : amsSpecificationWrite;
    }

    public static void setAmsAssetRepository(AmsAssetRepository amsAssetRepository) {
        AmsBusinessApiFactory.amsAssetRepository = amsAssetRepository;
    }
}
