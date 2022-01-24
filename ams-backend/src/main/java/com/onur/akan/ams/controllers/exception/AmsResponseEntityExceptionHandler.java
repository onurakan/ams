package com.onur.akan.ams.controllers.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * BindException.class
 * MethodArgumentNotValidException.class
 * AmsRequestException.class
 * NoSuchElementException.class
 * DataIntegrityViolationException.class
 * RuntimeException.class
 *
 *
 * TODO ConstraintViolationException.class
 */
@ControllerAdvice
@Slf4j
public class AmsResponseEntityExceptionHandler {//extends ResponseEntityExceptionHandler {

    public static final String TRACE = "trace";

    @Value("${ams.exception.handler.printStackTrace:false}")
    private boolean printStackTrace;

    @ExceptionHandler(AmsRequestException.class)
    protected ResponseEntity<ErrorResponse> handleAmsRequestException(AmsRequestException amsRequestException) {
        ErrorResponse errorResponse = errorResponse(amsRequestException, "Request Validation error: '" + amsRequestException.getMessage() + "'");
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(BindException bindException) {
        ErrorResponse errorResponse = errorResponse(bindException, "Request Validation error: '" + bindException.getMessage() + "'");
        errorResponse.setErrors(new ArrayList<>(bindException.getAllErrors().size()));
        bindException.getAllErrors().forEach(error -> errorResponse.getErrors().add(error.getDefaultMessage()));

        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {
        ErrorResponse errorResponse = errorResponse(methodArgumentNotValidException, "Validation error. Check 'errors' field for details.");
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(
                fieldError -> errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementFoundException(NoSuchElementException noSuchElementException) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException) {
        ErrorResponse errorResponse = errorResponse(dataIntegrityViolationException, "Data is not valid!");
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(RuntimeException exception) {
        ErrorResponse errorResponse = errorResponse(exception, "Unknown error occurred: " + exception.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    private ErrorResponse errorResponse(Exception exception, String message) {
        log.error(message, exception);
        ErrorResponse errorResponse = new ErrorResponse(message);
        if (printStackTrace) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }
        return errorResponse;
    }
}