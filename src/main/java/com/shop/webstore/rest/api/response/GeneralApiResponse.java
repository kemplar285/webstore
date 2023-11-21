package com.shop.webstore.rest.api.response;

public class GeneralApiResponse extends AbstractResponse{
    public GeneralApiResponse(ResponseCode responseCode, String message){
        setMessage(message);
        setResponseCode(responseCode);
    }
    public GeneralApiResponse(){}
}