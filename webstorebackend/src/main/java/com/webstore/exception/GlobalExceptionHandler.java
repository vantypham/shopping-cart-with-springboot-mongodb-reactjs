package com.webstore.exception;

import java.util.*;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Map<String, Object> fieldErrorMap = new HashMap<>();
        List<FieldError> fieldErrors= ex.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            fieldErrorMap.put(error.getField(), error.getDefaultMessage());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", false);
        map.put("data", null);
        map.put("status", HttpStatus.BAD_REQUEST);
        map.put("fieldError", fieldErrorMap);
        return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
    }
}
