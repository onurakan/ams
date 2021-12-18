package com.onur.akan.ams.web;

import com.onur.akan.ams.business.api.AmsRequest;
import com.onur.akan.ams.business.api.AmsSpecificationWrite;
import com.onur.akan.ams.business.specification.AmsSpecificationRepository;
import com.onur.akan.ams.web.model.ModelMapper;
import com.onur.akan.ams.web.model.Specification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SpecificationWriteController {

    private static final Logger log = LoggerFactory.getLogger(SpecificationWriteController.class);

    @Autowired
    private AmsSpecificationRepository amsSpecificationRepository;

    @PutMapping(value = "/specifications/{id}")
    public ResponseEntity<Long> updateSpecification(@RequestBody Specification specification) {
        try {
            boolean isUpdated = new AmsSpecificationWrite(amsSpecificationRepository).update(new AmsRequest(ModelMapper.toAmsSpecification(specification)));

            return (isUpdated) ? new ResponseEntity<>(specification.getId(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
