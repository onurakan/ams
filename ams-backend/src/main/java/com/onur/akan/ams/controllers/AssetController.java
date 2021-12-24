package com.onur.akan.ams.controllers;

import com.onur.akan.ams.controllers.model.Asset;
import com.onur.akan.ams.controllers.model.ModelMapper;
import com.onur.akan.ams.domain.AssetEntity;
import com.onur.akan.ams.services.AssetService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:8081") //TODO onur
@Slf4j
@Setter
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @GetMapping("/assets")
    public ResponseEntity<List<Asset>> listAssets() {
        try {
            List<Asset> assets = assetService.listAll().stream()
                                                        .map(ae -> ModelMapper.toAsset((AssetEntity) ae))
                                                        .collect(Collectors.toList());

            return new ResponseEntity<>(assets, HttpStatus.OK);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/assets/{id}")
    public ResponseEntity<Asset>  getAssetById(@PathVariable Long id) {
        try {
            AssetEntity assetEntity = assetService.getById(id);
            if (assetEntity != null) {
                return new ResponseEntity<>(ModelMapper.toAsset(assetEntity), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/assets")
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {

        try {
            AssetEntity newAssetEntity = assetService.save(ModelMapper.toAssetEntitiy(asset));
            return new ResponseEntity<>(ModelMapper.toAsset(newAssetEntity), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/assets/{id}")
    public ResponseEntity<Long> updateAsset(@RequestBody Asset asset) {
        try {
            AssetEntity updatedAssetEntity = assetService.update(ModelMapper.toAssetEntitiy(asset));
            if (updatedAssetEntity != null) {
                return new ResponseEntity<>(updatedAssetEntity.getId(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/assets/delete/{id}")
    public ResponseEntity<AssetEntity> delete(@PathVariable Long id) {
        try {
            AssetEntity assetEntity = assetService.getById(id);

            if (assetEntity == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            assetService.delete(id);

            return new ResponseEntity<>(assetEntity, HttpStatus.OK);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}