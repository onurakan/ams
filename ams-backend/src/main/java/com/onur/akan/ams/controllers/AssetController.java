package com.onur.akan.ams.controllers;

import com.onur.akan.ams.controllers.model.Asset;
import com.onur.akan.ams.controllers.model.AssetMapper;
import com.onur.akan.ams.controllers.model.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
@Setter
@CrossOrigin(origins = "http://localhost:8081") //TODO onur
@Slf4j
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @GetMapping("/asset/read")
    public ResponseEntity<List<Asset>> readAssets() {
        try {
            List<Asset> assets = assetService.listAll().stream()
                    .map(ae -> AssetMapper.INSTANCE.assetEntityToAsset((AssetEntity) ae))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(assets, HttpStatus.OK);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/asset/read/filter/{currentPageNumber}/{pageSize}")
    public ResponseEntity<Page<Asset>> getAssetsByFilter(@PathVariable("currentPageNumber") @Min(value = 1, message = "Page can be min = 1")  int currentPageNumber,
                                                         @PathVariable("pageSize") @Min(value = 1, message = "Size can be min=1, max=100") int pageSize,
                                                         @RequestBody Asset asset) {

        try {
            org.springframework.data.domain.Page<AssetEntity> assetEntityPage = assetService.findByExampleMatcher(AssetMapper.INSTANCE.assetToAssetEntity(asset), currentPageNumber, pageSize);

            if (assetEntityPage == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            List<Asset> assets = assetEntityPage.getContent().stream()
                                                .map(ae -> AssetMapper.INSTANCE.assetEntityToAsset((AssetEntity) ae))
                                                .collect(Collectors.toList());

            String previousPage = assetEntityPage.hasPrevious()? "/api/asset/read/filter/" + (currentPageNumber -1) + "/" +  pageSize : null;
            String nextPage     = assetEntityPage.hasNext() ?    "/api/asset/read/filter/" + (currentPageNumber + 1) + "/" +  pageSize : null;

            return new ResponseEntity<>(Page.<Asset>builder().data(assets).previousPage(previousPage).nextPage(nextPage).build(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/asset/read/{id}")
    public ResponseEntity<Asset>  getAssetById(@PathVariable Long id) {
        try {
            AssetEntity assetEntity = assetService.getById(id);
            return new ResponseEntity<>(AssetMapper.INSTANCE.assetEntityToAsset(assetEntity), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/asset/create")
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {

        try {
            AssetEntity newAssetEntity = assetService.save(AssetMapper.INSTANCE.assetToAssetEntity(asset));
            return new ResponseEntity<>(AssetMapper.INSTANCE.assetEntityToAsset(newAssetEntity), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/asset/update/{id}")
    public ResponseEntity<Long> updateAsset(@RequestBody Asset asset) {
        try {
            AssetEntity updatedAssetEntity = assetService.update(AssetMapper.INSTANCE.assetToAssetEntity(asset));
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

    @DeleteMapping("/asset/delete/{id}")
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