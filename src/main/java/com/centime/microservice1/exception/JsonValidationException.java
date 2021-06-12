package com.centime.microservice1.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
@RestController
class EndpointExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private String BAD_REQUEST = "BAD_REQUEST";

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex,
            WebRequest request) {
        List<String> details = ex.getConstraintViolations()
                .parallelStream()
                .map(e -> e.getMessage())
                .collect(Collectors.toList());
        LOG.error("Constraint violation Exception:", ex);
        ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceNotAvailableException.class)
    public final ResponseEntity handleServiceNotAvailableException(ServiceNotAvailableException ex) {
        List<String> errorDetails = new ArrayList<>();
        errorDetails.add(ex.getMessage());
        ErrorResponse error = new ErrorResponse("Service Healthcheck Failed.", errorDetails);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public final ResponseEntity handleJsonProcessingException(
            ConnectException ex) {
        LOG.error("Json parsing Exception:", ex);
        List<String> errorDetails = new ArrayList<>();
        errorDetails.add(ex.getMessage());
        ErrorResponse error = new ErrorResponse("JsonParsing Failed", errorDetails);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.error("Invalid Method Argument Exception:", ex);
        List<String> errorDetails = new ArrayList<>();
        errorDetails.add(ex.getBindingResult().toString());
        ErrorResponse error = new ErrorResponse("Validation Failed", errorDetails);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
