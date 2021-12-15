package com.onur.akan.ams.database.jpa;


import org.springframework.data.repository.CrudRepository;

/**
 * @Author Onur Akan
 */
public interface SpecificationRepository  extends CrudRepository<SpecificationEntity, Long> {

    SpecificationEntity findById(long id);
}
