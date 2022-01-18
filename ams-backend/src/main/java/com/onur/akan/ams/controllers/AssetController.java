package com.onur.akan.ams.controllers;

import com.onur.akan.ams.controllers.exception.AmsRequestException;
import com.onur.akan.ams.controllers.model.Asset;
import com.onur.akan.ams.controllers.model.AssetMapper;
import com.onur.akan.ams.controllers.model.Page;
import com.onur.akan.ams.domain.AssetEntity;
import com.onur.akan.ams.services.AssetService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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

import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
@Setter
@CrossOrigin(origins = "http://localhost:8081") //TODO onur
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @GetMapping("/asset")
    public ResponseEntity<List<Asset>> readAssets() {
        List<Asset> assets = assetService.listAll().stream()
                                                    .map(ae -> AssetMapper.INSTANCE.assetEntityToAssetIgnoreSpecificationList((AssetEntity) ae))
                                                    .collect(Collectors.toList());

        return ResponseEntity.ok(assets);
    }

    @PostMapping("/asset/{currentPageNumber}/{pageSize}")
    public ResponseEntity<Page<Asset>> getAssetsByFilter(@PathVariable("currentPageNumber") @Min(value = 1, message = "Page can be min = 1")  int currentPageNumber,
                                                         @PathVariable("pageSize") @Min(value = 1, message = "Size can be min=1, max=100") int pageSize,
                                                         @RequestBody Asset asset) {

        org.springframework.data.domain.Page<AssetEntity> assetEntityPage = assetService.findByExampleMatcher(AssetMapper.INSTANCE.assetToAssetEntity(asset), currentPageNumber, pageSize);

        if (assetEntityPage == null) new NoSuchElementException();

        List<Asset> assets = assetEntityPage.getContent().stream()
                .map(ae -> AssetMapper.INSTANCE.assetEntityToAssetIgnoreSpecificationList((AssetEntity) ae))
                .collect(Collectors.toList());

        String previousPage = assetEntityPage.hasPrevious()? "/api/asset/" + (currentPageNumber -1) + "/" +  pageSize : null;
        String nextPage     = assetEntityPage.hasNext() ?    "/api/asset/" + (currentPageNumber + 1) + "/" +  pageSize : null;

        return ResponseEntity.ok(Page.<Asset>builder().data(assets).previousPage(previousPage).nextPage(nextPage).build());
    }

    @GetMapping("/asset/{id}")
    public ResponseEntity<Asset>  getAssetById(@PathVariable Long id) {
        AssetEntity assetEntity = assetService.getById(id);
        return ResponseEntity.ok(AssetMapper.INSTANCE.assetEntityToAssetIgnoreSpecificationList(assetEntity));
    }

    @PostMapping("/asset")
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) throws AmsRequestException {
        AssetEntity newAssetEntity = assetService.save(AssetMapper.INSTANCE.assetToAssetEntity(asset));
        Asset newAsset = AssetMapper.INSTANCE.assetEntityToAsset(newAssetEntity);

        return ResponseEntity.created(URI.create("/asset/"+newAssetEntity.getId())).body(newAsset);
    }

    @PutMapping(value = "/asset/{id}")
    public ResponseEntity<Long> updateAsset(@RequestBody Asset asset) throws AmsRequestException {
        AssetEntity updatedAssetEntity = assetService.update(AssetMapper.INSTANCE.assetToAssetEntity(asset));
        if (updatedAssetEntity == null) new NoSuchElementException();

        return ResponseEntity.ok(updatedAssetEntity.getId());
    }

    @DeleteMapping("/asset/{id}")
    public ResponseEntity<AssetEntity> delete(@PathVariable Long id) {
        AssetEntity assetEntity = assetService.getById(id);

        if (assetEntity == null) new NoSuchElementException();

        assetService.delete(id);

        return ResponseEntity.ok(assetEntity);
    }
}