package com.rga.springbootawssnsproducerexample.controller;

import com.rga.springbootawssnsproducerexample.model.RGABaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class, Exception.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        RGABaseResponse rgaBaseResponse = new RGABaseResponse();
        rgaBaseResponse.setStatus("Service unavailable");
        rgaBaseResponse.setResponseCode(503);
        return new ResponseEntity<>(rgaBaseResponse, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
