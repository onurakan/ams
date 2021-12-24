package com.onur.akan.ams.controllers;

import com.onur.akan.ams.controllers.model.ModelMapper;
import com.onur.akan.ams.controllers.model.Specification;
import com.onur.akan.ams.domain.SpecificationEntity;
import com.onur.akan.ams.services.SpecificationService;
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
@Setter
@CrossOrigin(origins = "http://localhost:8081") //TODO onur
@Slf4j
public class SpecificationController {

    private SpecificationService specificationService;

    @GetMapping("/specifications")
    public ResponseEntity<List<Specification>> listAssets() {
        try {
            List<Specification> specifications = specificationService.listAll().stream()
                                                                                .map(se -> ModelMapper.toSpecification((SpecificationEntity) se))
                                                                                .collect(Collectors.toList());
            
            return new ResponseEntity<>(specifications, HttpStatus.OK);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/specifications/{id}")
    public ResponseEntity<Specification>  getSpecificationById(@PathVariable Long id) {
        try {
            SpecificationEntity SpecificationEntity = specificationService.getById(id);
            if (SpecificationEntity != null) {
                return new ResponseEntity<>(ModelMapper.toSpecification(SpecificationEntity), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/specifications")
    public ResponseEntity<Specification> createSpecification(@RequestBody Specification specification) {

        try {
            SpecificationEntity newSpecificationEntity = specificationService.save(ModelMapper.toSpecificationEntity(specification));
            return new ResponseEntity<>(ModelMapper.toSpecification(newSpecificationEntity), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/specifications/{id}")
    public ResponseEntity<Long> updateSpecification(@RequestBody Specification specification) {
        try {
            SpecificationEntity updatedSpecificationEntity = specificationService.update(ModelMapper.toSpecificationEntity(specification));
            if (updatedSpecificationEntity != null) {
                return new ResponseEntity<>(updatedSpecificationEntity.getId(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/specifications/{id}")
    public ResponseEntity<SpecificationEntity> delete(@PathVariable Long id) {
        try {
            SpecificationEntity SpecificationEntity = specificationService.getById(id);

            if (SpecificationEntity == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            specificationService.delete(id);

            return new ResponseEntity<>(SpecificationEntity, HttpStatus.OK);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
