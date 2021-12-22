package com.onur.akan.ams.web;

import com.onur.akan.ams.AmsBusinessApiFactory;
import com.onur.akan.ams.business.api.AmsRequest;
import com.onur.akan.ams.business.api.AmsRequestException;
import com.onur.akan.ams.business.api.AmsResponse;
import com.onur.akan.ams.business.asset.AmsAsset;
import com.onur.akan.ams.web.model.Asset;
import com.onur.akan.ams.web.model.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Onur Akan
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AssetReadController {

    private static final Logger log = LoggerFactory.getLogger(AssetReadController.class);

    @GetMapping("/assets")
    public ResponseEntity<List<Asset>> getAllAssets() {
        try {
            return new ResponseEntity<>(getAssets(null), HttpStatus.OK);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping({"/assets/{id}"})
    public ResponseEntity<Asset> getAssetById(@PathVariable(value = "id") Long id) {
        try {
            List<Asset> assets = getAssets(id);

            if (assets != null && !assets.isEmpty()) {
                return new ResponseEntity<>(assets.get(0), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<Asset> getAssets(Long id) throws AmsRequestException {
        AmsRequest amsRequest = new AmsRequest(new AmsAsset());
        amsRequest.getAmsAsset().setAssetId(id);
        AmsResponse amsResponse = AmsBusinessApiFactory.getAmsAssetRead().read(amsRequest);

        List<Asset> assets = null;
        if (amsResponse != null && amsResponse.getAmsAssetList() != null && !amsResponse.getAmsAssetList().isEmpty()) {
            assets = new ArrayList<>();
            for (AmsAsset amsAsset : amsResponse.getAmsAssetList()) {
                assets.add(ModelMapper.toAsset(amsAsset));
            }
        }
        return assets;
    }
}