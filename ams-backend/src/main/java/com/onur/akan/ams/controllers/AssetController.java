package com.onur.akan.ams.controllers;

import com.onur.akan.ams.controllers.exception.AmsRequestException;
import com.onur.akan.ams.controllers.mapper.AssetMapper;
import com.onur.akan.ams.controllers.model.Asset;
import com.onur.akan.ams.controllers.model.OnCreate;
import com.onur.akan.ams.controllers.model.OnUpdate;
import com.onur.akan.ams.controllers.model.Page;
import com.onur.akan.ams.domain.AssetEntity;
import com.onur.akan.ams.services.AssetService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping(AssetController.API_V_1_ASSET)
@RestController
@Setter
@CrossOrigin(origins = "http://localhost:8081") //TODO onur
@RequiredArgsConstructor
public class AssetController {

    public static final String API_V_1_ASSET = "/api/v1/asset";

    private final AssetService assetService;
    private final AssetMapper assetMapper;

    @GetMapping
    public ResponseEntity<List<Asset>> readAssets() {
        val assets = assetService.listAll().stream()
                                                    .map(ae -> assetMapper.assetEntityToAssetIgnoreSpecificationList((AssetEntity) ae))
                                                    .collect(Collectors.toList());

        return ResponseEntity.ok(assets);
    }

    @GetMapping("/{assetId}")
    public ResponseEntity<Asset>  getAssetByAssetId(@PathVariable UUID assetId) {
        val assetEntity = assetService.findByAssetId(assetId);
        return assetEntity != null ? ResponseEntity.ok(assetMapper.assetEntityToAssetIgnoreSpecificationList(assetEntity)) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{currentPageNumber}/{pageSize}")
    public ResponseEntity<Page<Asset>> getAssetsByFilter(@PathVariable("currentPageNumber") @Min(value = 1, message = "Page can be minimum = 1")  int currentPageNumber,
                                                         @PathVariable("pageSize") @Min(value = 1, message = "Size can be min=1, max=100") int pageSize,
                                                         @RequestBody Asset asset) {

        val assetEntityPage = assetService.findByExampleMatcher(assetMapper.assetToAssetEntity(asset), currentPageNumber, pageSize);

        if (assetEntityPage == null) new NoSuchElementException();

        val assets = assetEntityPage.getContent().stream()
                                    .map(ae -> assetMapper.assetEntityToAssetIgnoreSpecificationList((AssetEntity) ae))
                                    .collect(Collectors.toList());

        String previousPage = assetEntityPage.hasPrevious()? "/asset/" + (currentPageNumber -1) + "/" +  pageSize : null;//TODO put host address in URL
        String nextPage     = assetEntityPage.hasNext() ?    "/asset/" + (currentPageNumber + 1) + "/" +  pageSize : null;//TODO put host address in URL

        return ResponseEntity.ok(Page.<Asset>builder().data(assets).previousPage(previousPage).nextPage(nextPage).build());
    }

    @PostMapping
    public ResponseEntity<Asset> createAsset(@Validated(OnCreate.class) @RequestBody Asset asset) throws AmsRequestException {
        val assetEntity = assetMapper.assetToAssetEntity(asset);
        val newAssetEntity = assetService.save(assetEntity);
        val newAsset = assetMapper.assetEntityToAsset(newAssetEntity);

        return ResponseEntity.created(URI.create(API_V_1_ASSET + "/" + newAssetEntity.getAssetId())).body(newAsset);//TODO put full url (host+contextPath+resourcePath)
    }

    @PutMapping(value = "/{assetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAsset(@PathVariable UUID assetId, @Validated(OnUpdate.class) @RequestBody Asset asset) throws AmsRequestException {
        val assetEntity = assetService.findByAssetId(assetId);
        if (assetEntity == null) new NoSuchElementException();

        val in_assetEntity = assetMapper.assetToAssetEntity(asset);
        assetEntity.setAssetTag(in_assetEntity.getAssetTag());
        assetEntity.setStatus(in_assetEntity.getStatus());
        assetEntity.setClassification(in_assetEntity.getClassification());
        assetEntity.setDescription(in_assetEntity.getDescription());

       assetService.update(assetEntity);
    }

    @DeleteMapping("/{assetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAsset(@PathVariable UUID assetId) {
        val assetEntity = assetService.findByAssetId(assetId);

        if (assetEntity == null) new NoSuchElementException();

        assetService.delete(assetEntity.getId());
    }
}