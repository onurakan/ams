package com.onur.akan.ams.controllers;

import com.onur.akan.ams.controllers.model.Specification;
import com.onur.akan.ams.controllers.model.SpecificationMapper;
import com.onur.akan.ams.domain.SpecificationEntity;
import com.onur.akan.ams.controllers.exception.AmsRequestException;
import com.onur.akan.ams.services.SpecificationService;
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
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    public ResponseEntity<List<Specification>> readSpecifications(@RequestParam(required = false) UUID assetId) {

        List<Specification> specifications = null;
        if (assetId == null) {
            specifications = specificationService.listAll().stream()
                                                    .map(se -> SpecificationMapper.INSTANCE.specificationEntityToSpecification((SpecificationEntity) se))
                                                    .collect(Collectors.toList());
        } else {
            specifications = specificationService.findByAssetId(assetId).stream()
                                                    .map(se -> SpecificationMapper.INSTANCE.specificationEntityToSpecification((SpecificationEntity) se))
                                                    .collect(Collectors.toList());
        }


        return ResponseEntity.ok(specifications);
    }

    @GetMapping("/{specificationId}")
    public ResponseEntity<Specification>  getSpecificationById(@PathVariable UUID specificationId) {
        SpecificationEntity specificationEntity = specificationService.findBySpecificationId(specificationId);
        Specification specification = SpecificationMapper.INSTANCE.specificationEntityToSpecification(specificationEntity);

        return specificationEntity != null ? ResponseEntity.ok(specification) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Specification> createSpecification(@RequestBody Specification specification) throws AmsRequestException {
        SpecificationEntity newSpecificationEntity = specificationService.save(SpecificationMapper.INSTANCE.specificationToSpecificationEntity(specification));
        Specification newSpecification = SpecificationMapper.INSTANCE.specificationEntityToSpecification(newSpecificationEntity);

        return ResponseEntity.created(URI.create(API_V_1_SPECIFICATION + "/" + newSpecificationEntity.getSpecificationId())).body(newSpecification);//TODO put full url
    }

    @PutMapping(value = "/{specificationId}")
    public ResponseEntity<UUID> updateSpecification(@PathVariable UUID specificationId, @RequestBody Specification specification) throws AmsRequestException {
        SpecificationEntity updatedSpecificationEntity = specificationService.update(SpecificationMapper.INSTANCE.specificationToSpecificationEntity(specification));
        if (updatedSpecificationEntity == null) new NoSuchElementException();

        return ResponseEntity.ok(updatedSpecificationEntity.getSpecificationId());
    }

    @DeleteMapping("/{specificationId}")
    public ResponseEntity<Specification> deleteSpecification (@PathVariable UUID specificationId) {
        SpecificationEntity specificationEntity = specificationService.findBySpecificationId(specificationId);
        if (specificationEntity == null) new NoSuchElementException();

        specificationService.delete(specificationEntity.getId());

        return ResponseEntity.ok(SpecificationMapper.INSTANCE.specificationEntityToSpecification(specificationEntity));
    }
}
