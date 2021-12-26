package com.onur.akan.ams.controllers;

import com.onur.akan.ams.controllers.model.Specification;
import com.onur.akan.ams.controllers.model.SpecificationMapper;
import com.onur.akan.ams.domain.SpecificationEntity;
import com.onur.akan.ams.services.SpecificationService;
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
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
@Setter
@CrossOrigin(origins = "http://localhost:8081") //TODO onur
@Slf4j
@RequiredArgsConstructor
public class SpecificationController {

    private final SpecificationService specificationService;

    @GetMapping("/specification/read")
    public ResponseEntity<List<Specification>> readSpecifications() {
        try {
            List<Specification> specifications = specificationService.listAll().stream()
                                                                                .map(se -> SpecificationMapper.INSTANCE.specificationEntityToSpecification((SpecificationEntity) se))
                                                                                .collect(Collectors.toList());
            
            return new ResponseEntity<>(specifications, HttpStatus.OK);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/specification/read/{id}")
    public ResponseEntity<Specification>  getSpecificationById(@PathVariable Long id) {
        try {
            SpecificationEntity specificationEntity = specificationService.getById(id);
            return new ResponseEntity<>(SpecificationMapper.INSTANCE.specificationEntityToSpecification(specificationEntity), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/specification/create")
    public ResponseEntity<Specification> createSpecification(@RequestBody Specification specification) {

        try {
            SpecificationEntity newSpecificationEntity = specificationService.save(SpecificationMapper.INSTANCE.specificationToSpecificationEntity(specification));
            return new ResponseEntity<>(SpecificationMapper.INSTANCE.specificationEntityToSpecification(newSpecificationEntity), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/specification/update/{id}")
    public ResponseEntity<Long> updateSpecification(@RequestBody Specification specification) {
        try {
            SpecificationEntity updatedSpecificationEntity = specificationService.update(SpecificationMapper.INSTANCE.specificationToSpecificationEntity(specification));
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

    @DeleteMapping("/specification/delete{id}")
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
