package com.volunteeride.rest;

/**
 * Created by mthosani on 12/12/15.
 */
public class RestQuery {

    public enum Method {
        GET, POST, PUT, DELETE
    }

    private Method mMethod;

    private String mURL;

    private Object mParam;

    public RestQuery(Method pMethod, String pURL){
        this.mMethod = pMethod;
        this.mURL = pURL;
    }


    public Object getParam() {
        return mParam;
    }

    public void setParam(Object mParam) {
        this.mParam = mParam;
    }

    public String getURL() {
        return mURL;
    }

    public void setURL(String mURL) {
        this.mURL = mURL;
    }

    public Method getMethod() {
        return mMethod;
    }

    public void setMethod(Method mMethod) {
        this.mMethod = mMethod;
    }

}
