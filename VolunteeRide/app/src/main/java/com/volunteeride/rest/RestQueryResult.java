package com.volunteeride.rest;

/**
 * Created by mthosani on 12/12/15.
 */
public class RestQueryResult {

    private int mStatusCode;

    private String mResponse;

    public RestQueryResult(int pStatusCode, String pResponse){
        this.mStatusCode = pStatusCode;
        this.mResponse = pResponse;
    }

    public int getstatusCode(){
        return mStatusCode;
    }

    public String getResponse(){
        return mResponse;
    }


}
