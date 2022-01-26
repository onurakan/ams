package com.onur.akan.ams.controllers;

import com.onur.akan.ams.controllers.exception.AmsRequestException;
import com.onur.akan.ams.controllers.mapper.SpecificationMapper;
import com.onur.akan.ams.controllers.model.OnCreate;
import com.onur.akan.ams.controllers.model.OnUpdate;
import com.onur.akan.ams.controllers.model.SpecificationDto;
import com.onur.akan.ams.domain.SpecificationEntity;
import com.onur.akan.ams.services.SpecificationService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping(SpecificationController.API_V_1_SPECIFICATION)
@RestController
@Setter
@CrossOrigin(origins = "http://localhost:8081") //TODO onur
@RequiredArgsConstructor
public class SpecificationController {

    public static final String API_V_1_SPECIFICATION = "/api/v1/specification";

    private final SpecificationService specificationService;
    private final SpecificationMapper specificationMapper;

    @GetMapping
    public ResponseEntity<List<SpecificationDto>> readSpecifications(@RequestParam(required = false) UUID assetId) {

        List<SpecificationDto> specifications = null;
        if (assetId == null) {
            specifications = specificationService.listAll().stream()
                                                    .map(se -> specificationMapper.specificationEntityToSpecification((SpecificationEntity) se))
                                                    .collect(Collectors.toList());
        } else {
            specifications = specificationService.findByAssetId(assetId).stream()
                                                    .map(se -> specificationMapper.specificationEntityToSpecification((SpecificationEntity) se))
                                                    .collect(Collectors.toList());
        }


        return ResponseEntity.ok(specifications);
    }

    @GetMapping("/{specificationId}")
    public ResponseEntity<SpecificationDto>  getSpecificationById(@PathVariable UUID specificationId) {
        val specificationEntity = specificationService.findBySpecificationId(specificationId);
        val specification           = specificationMapper.specificationEntityToSpecification(specificationEntity);

        return specificationEntity != null ? ResponseEntity.ok(specification) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<SpecificationDto> createSpecification(@Validated(OnCreate.class) @RequestBody SpecificationDto specificationDto) throws AmsRequestException {
        val newSpecificationEntity = specificationService.save(specificationMapper.specificationToSpecificationEntity(specificationDto));
        val newSpecification = specificationMapper.specificationEntityToSpecification(newSpecificationEntity);

        return ResponseEntity.created(URI.create(API_V_1_SPECIFICATION + "/" + newSpecificationEntity.getSpecificationId())).body(newSpecification);//TODO put full url (host+contextPath+resourcePath)
    }

    @PutMapping(value = "/{specificationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSpecification(@PathVariable UUID specificationId, @Validated(OnUpdate.class) @RequestBody SpecificationDto specificationDto) throws AmsRequestException {
        val specificationEntity = specificationService.findBySpecificationId(specificationId);
        if (specificationEntity == null) new NoSuchElementException();

        val in_specificationEntity = specificationMapper.specificationToSpecificationEntity(specificationDto);
        specificationEntity.setStatus(in_specificationEntity.getStatus());
        specificationEntity.setAttribute(in_specificationEntity.getAttribute());
        specificationEntity.setAlphanumericDescription(in_specificationEntity.getAlphanumericDescription());
        specificationEntity.setAlphanumericValue(in_specificationEntity.getAlphanumericValue());
        specificationEntity.setAttributeDescription(in_specificationEntity.getAttributeDescription());
        specificationEntity.setDataType(in_specificationEntity.getDataType());
        specificationEntity.setNumericDescription(in_specificationEntity.getNumericDescription());
        specificationEntity.setNumericValue(in_specificationEntity.getNumericValue());
        specificationEntity.setTableValue(in_specificationEntity.getTableValue());
        specificationEntity.setUnitOfMeasure(in_specificationEntity.getUnitOfMeasure());

        specificationService.update(specificationEntity);
    }

    @DeleteMapping("/{specificationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSpecification (@PathVariable UUID specificationId) {
        val specificationEntity = specificationService.findBySpecificationId(specificationId);
        if (specificationEntity == null) new NoSuchElementException();

        specificationService.delete(specificationEntity.getId());
    }
}
