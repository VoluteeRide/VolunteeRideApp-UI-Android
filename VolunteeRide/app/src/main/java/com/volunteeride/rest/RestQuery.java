package com.volunteeride.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 * Created by mthosani on 12/12/15.
 */
public class RestQuery {

    private HttpMethod mMethod;

    private String mURL;

    private Object mParam;

    private HttpHeaders mHeaders;

    private Object mBody;

    public RestQuery(HttpMethod pMethod, String pURL){
        this.mMethod = pMethod;
        this.mURL = pURL;
    }

    public RestQuery(HttpMethod mMethod, String mURL, HttpHeaders mHeaders) {
        this.mMethod = mMethod;
        this.mURL = mURL;
        this.mHeaders = mHeaders;
    }

    public HttpHeaders getHeaders() {
        return mHeaders;
    }

    public void setHeaders(HttpHeaders mHeaders) {
        this.mHeaders = mHeaders;
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

    public HttpMethod getMethod() {
        return mMethod;
    }

    public void setMethod(HttpMethod mMethod) {
        this.mMethod = mMethod;
    }

    public Object getmBody() {
        return mBody;
    }

    public void setmBody(Object mBody) {
        this.mBody = mBody;
    }
}
