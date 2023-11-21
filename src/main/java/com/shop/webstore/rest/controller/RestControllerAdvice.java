package com.shop.webstore.rest.controller;


import com.shop.webstore.data.exception.ResourceNotFoundException;
import com.shop.webstore.rest.api.response.GeneralApiResponse;
import com.shop.webstore.rest.api.response.ResponseCode;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;

@ControllerAdvice
@RequestMapping(produces = "application/json")
@ResponseBody
public class RestControllerAdvice {
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> unhandledPath(final NoHandlerFoundException e) {
        GeneralApiResponse response = new GeneralApiResponse(ResponseCode.INVALID_REQUEST, e.getMessage());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<?> missingPathVariable(final MissingPathVariableException e) {
        GeneralApiResponse response = new GeneralApiResponse(ResponseCode.INVALID_REQUEST, e.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> missingBodyAttribute(final NoSuchElementException e) {
        GeneralApiResponse response = new GeneralApiResponse(ResponseCode.INVALID_REQUEST, e.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFound(final ResourceNotFoundException e){
        GeneralApiResponse response = new GeneralApiResponse(ResponseCode.INVALID_REQUEST, e.getMessage());
        return new ResponseEntity(response, HttpStatus.OK);
    }

//    @ExceptionHandler(NoSuchKeyException.class)
//    public ResponseEntity<?> noSuchKey(final NoSuchKeyException e){
//        return new ResponseEntity(HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> argumentMismatch(final MethodArgumentTypeMismatchException e){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
