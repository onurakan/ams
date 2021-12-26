package com.onur.akan.ams.services.implementations;

import com.onur.akan.ams.domain.SpecificationEntity;
import com.onur.akan.ams.repositories.SpecificationRepository;
import com.onur.akan.ams.services.SpecificationService;
import com.onur.akan.ams.AmsRequestException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Setter
@RequiredArgsConstructor
@Slf4j
public class SpecificationServiceImpl implements SpecificationService {

    private final SpecificationRepository specificationRepository;

    @Override
    public List<?> listAll() {
        List<SpecificationEntity> specificationEntities = new ArrayList<>();
        specificationRepository.findAll().forEach(specificationEntities::add);
        return specificationEntities;
    }

    @Override
    public SpecificationEntity getById(Long id) {
        return specificationRepository.findById(id).get();
    }

    @Override
    public SpecificationEntity save(SpecificationEntity specificationEntity) throws AmsRequestException {
        //TODO implement business rules here
        if (specificationEntity == null) throw new AmsRequestException("specification cannot be null");
        if (specificationEntity.getId() != null) throw new AmsRequestException("Update is not allowed");

        SpecificationEntity newSpecificationEntity = specificationRepository.save(specificationEntity);
        log.info("Created specification with id=" + newSpecificationEntity.getId());
        return newSpecificationEntity;
    }

    @Override
    public SpecificationEntity update(SpecificationEntity specificationEntity) throws AmsRequestException {
        //TODO implement business rules here
        if (specificationEntity == null) throw new AmsRequestException("specification cannot be null");
        if (specificationEntity.getId() == null) throw new AmsRequestException("specification.id cannot be null");
        return specificationRepository.save(specificationEntity);
    }

    @Override
    public void delete(Long id) {
        specificationRepository.deleteById(id);
    }
}
