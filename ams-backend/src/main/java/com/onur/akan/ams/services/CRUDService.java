package com.onur.akan.ams.services;

import com.onur.akan.ams.controllers.exception.AmsRequestException;

import java.util.List;

public interface CRUDService<T> {
    List<?> listAll();

    T getById(Long id);

    T save(T domainObject) throws AmsRequestException;

    T update(T domainObject) throws AmsRequestException;

    void delete(Long id);
}