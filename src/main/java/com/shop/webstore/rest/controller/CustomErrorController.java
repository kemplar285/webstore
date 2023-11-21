package com.shop.webstore.rest.controller;

import com.shop.webstore.rest.api.response.GeneralApiResponse;
import com.shop.webstore.rest.api.response.ResponseCode;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {
    @RequestMapping
    public ResponseEntity<?> error(){
        GeneralApiResponse response = new GeneralApiResponse(ResponseCode.INVALID_REQUEST, "No content");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}