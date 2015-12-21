package com.volunteeride.rest;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by mthosani on 12/12/15.
 */
public class RestQuery {

    public enum Method {
        GET, POST, PUT, DELETE
    }

    private Map<String,String> mGetParams;

    private Map<String,String> mHeaderFields;

    private Method mMethod;

    /**
     * The unique name of the query
     */
    private String mName;

    private Map<String, String> mPostParams;

    private Map<String, String> mPutParams;

    private String mURL;

    /**
     * Sometimes a specific string needs to be added to the URL after the query string. This is used
     * for that purpose
     */
    private String mUrlPostQueryStringText;

    /**
     * This is used if there are parameteric values in the URL e.g.
     * http://www.someprovider.com/[someuserid]/friends?limit=50 Here "/friends" would be the
     * UrlPreQueryStringText
     */
    private String mUrlPreQueryStringText;

    public RestQuery(){

    }

    public RestQuery(final String pName, final Method pMethod, final String pURL){

        mName = pName;
        mMethod = pMethod;
        mURL = pURL;

    }

    public RestQuery(final String pName, final Method pMethod, final String pCommonUrl, final String pUrlSuffix){
        this(pName, pMethod, pUrlSuffix.isEmpty() ? pCommonUrl : pCommonUrl + pUrlSuffix);
    }

    /**
     * @return the mQueryParams
     */
    public Map<String, String> getGetParams() {
        if (mGetParams == null) {
            mGetParams = new LinkedHashMap<String, String>();
        }
        return mGetParams;
    }

    /**
     * @return the mHeaderFields
     */
    public Map<String, String> getHeaderFields() {
        if (mHeaderFields == null) {
            mHeaderFields = new LinkedHashMap<String, String>();
        }
        return mHeaderFields;
    }

    /**
     * @return the mMethod
     */
    public Method getMethod() {
        return mMethod;
    }

    /**
     * @return the mName
     */
    public String getName() {
        return mName;
    }

    /**
     * @return the mPostParams
     */
    public Map<String, String> getPostParams() {
        if (mPostParams == null) {
            mPostParams = new LinkedHashMap<String, String>();
        }
        return mPostParams;
    }

    /**
     * @return the mPutParams
     */
    public Map<String, String> getPutParams() {
        if (mPutParams == null) {
            mPutParams = new LinkedHashMap<String, String>();
        }
        return mPutParams;
    }

    /**
     * @return the mURL
     */
    public String getURL() {
        return mURL;
    }

    public void setURL(final String pURL) {
        this.mURL = pURL;
    }

    /**
     * @return the mUrlPostQueryStringText
     */
    public String getUrlPostQueryStringText() {
        return mUrlPostQueryStringText;
    }

    /**
     * @return the mUrlPreQueryStringText
     */
    public String getUrlPreQueryStringText() {
        return mUrlPreQueryStringText;
    }

    /**
     * @param mUrlPostQueryStringText the mUrlPostQueryStringText to set
     */
    public void setUrlPostQueryStringText(final String mUrlPostQueryStringText) {
        this.mUrlPostQueryStringText = mUrlPostQueryStringText;
    }

    /**
     * @param mUrlPreQueryStringText the mUrlPreQueryStringText to set
     */
    public void setUrlPreQueryStringText(final String mUrlPreQueryStringText) {
        this.mUrlPreQueryStringText = mUrlPreQueryStringText;
    }

}
