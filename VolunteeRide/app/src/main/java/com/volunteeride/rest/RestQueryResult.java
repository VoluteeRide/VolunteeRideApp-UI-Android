package com.volunteeride.rest;

import org.springframework.util.MultiValueMap;

/**
 * Created by mthosani on 12/12/15.
 */
public class RestQueryResult {

    private int mStatusCode;

    private String mResponse;

    private MultiValueMap<String, String> headers;

    public RestQueryResult(int pStatusCode, String pResponse, MultiValueMap<String, String> headers){
        this.mStatusCode = pStatusCode;
        this.mResponse = pResponse;
        this.headers = headers;
    }

    public int getstatusCode(){
        return mStatusCode;
    }

    public String getResponse(){
        return mResponse;
    }

    public MultiValueMap<String, String> getHeaders() {
        return headers;
    }
}
