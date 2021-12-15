package com.onur.akan.ams.web;

import com.onur.akan.ams.business.api.AmsAssetRead;
import com.onur.akan.ams.business.api.AmsRequest;
import com.onur.akan.ams.business.api.AmsResponse;
import com.onur.akan.ams.business.asset.AmsAsset;
import com.onur.akan.ams.business.asset.AmsAssetRepository;
import com.onur.akan.ams.web.model.Asset;
import com.onur.akan.ams.web.model.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @Autowired
    private AmsAssetRepository amsAssetRepository;

    @GetMapping("/assets")
    public ResponseEntity<List<Asset>> getAllAssets(@RequestParam(required = false) Long id) {
        try {
            AmsRequest amsRequest = new AmsRequest(new AmsAsset());
            amsRequest.getAmsAsset().setAssetId(id);
            AmsResponse amsResponse = new AmsAssetRead(amsAssetRepository).read(amsRequest);

            if (amsResponse == null || amsResponse.getAmsAssetList() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<Asset> assets = new ArrayList<Asset>();
            for (AmsAsset amsAsset : amsResponse.getAmsAssetList()) {
                assets.add(ModelMapper.toAsset(amsAsset));
            }

            return new ResponseEntity<>(assets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/assets/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable("id") Long id) {

        AmsRequest amsRequest = new AmsRequest(new AmsAsset());
        amsRequest.getAmsAsset().setAssetId(id);
        AmsResponse amsResponse = new AmsAssetRead(amsAssetRepository).read(amsRequest);

        if (amsResponse != null && amsResponse.getAmsAssetList() != null && !amsResponse.getAmsAssetList().isEmpty()) {
            return new ResponseEntity<>(ModelMapper.toAsset(amsResponse.getAmsAssetList().get(0)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}