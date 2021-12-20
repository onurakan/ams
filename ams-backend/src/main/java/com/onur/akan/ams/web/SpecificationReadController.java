package com.onur.akan.ams.web;

import com.onur.akan.ams.AmsBusinessApiFactory;
import com.onur.akan.ams.business.api.AmsRequest;
import com.onur.akan.ams.business.api.AmsResponse;
import com.onur.akan.ams.business.specification.AmsSpecification;
import com.onur.akan.ams.business.specification.AmsSpecificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Onur Akan
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SpecificationReadController {

    private static final Logger log = LoggerFactory.getLogger(AmsSpecificationRepository.class);

    @GetMapping("/specifications/{id}")
    public ResponseEntity<List<AmsSpecification>> getSpecification(@PathVariable("id") Long id) {
        try {
            AmsRequest amsRequest = new AmsRequest(new AmsSpecification());
            amsRequest.getAmsSpecification().setId(id);
            AmsResponse amsResponse = AmsBusinessApiFactory.getAmsSpecificationRead().read(amsRequest);

            if (amsResponse == null || amsResponse.getAmsSpecificationList() == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(amsResponse.getAmsSpecificationList(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}