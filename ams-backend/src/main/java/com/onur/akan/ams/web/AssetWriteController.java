package com.onur.akan.ams.web;

import com.onur.akan.ams.AmsBusinessApiFactory;
import com.onur.akan.ams.business.api.AmsRequest;
import com.onur.akan.ams.business.api.AmsResponse;
import com.onur.akan.ams.business.asset.AmsAsset;
import com.onur.akan.ams.business.asset.AmsAssetBuilder;
import com.onur.akan.ams.web.model.Asset;
import com.onur.akan.ams.web.model.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Onur Akan
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AssetWriteController {

    private static final Logger log = LoggerFactory.getLogger(AssetWriteController.class);

    @PostMapping("/assets")
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        try {
            AmsRequest amsRequest = ModelMapper.toAmsRequest(asset);
            AmsResponse amsResponse = AmsBusinessApiFactory.getAmsAssetWrite().create(amsRequest);

            if (amsResponse != null && amsResponse.getAmsAssetList() != null && !amsResponse.getAmsAssetList().isEmpty()) {
                return new ResponseEntity<>(ModelMapper.toAsset(amsResponse.getAmsAssetList().get(0)), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/assets/{id}")
    public ResponseEntity<Long> deleteAsset(@PathVariable("id") Long assetId) {
        try {
            AmsAsset amsAsset = AmsAssetBuilder.aAmsAsset().withAssetId(assetId).build();
            boolean isDeleted = AmsBusinessApiFactory.getAmsAssetWrite().delete(new AmsRequest(amsAsset));

            return (isDeleted) ? new ResponseEntity<>(assetId, HttpStatus.OK) :  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/assets/{id}")
    public ResponseEntity<Long> updateAsset(@RequestBody Asset asset) {
        try {
            boolean isUpdated = AmsBusinessApiFactory.getAmsAssetWrite().update(new AmsRequest(ModelMapper.toAmsAsset(asset)));

            return (isUpdated) ? new ResponseEntity<>(asset.getAssetId(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
