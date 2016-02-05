package com.volunteeride.rest;

import android.os.AsyncTask;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * Created by mthosani on 12/12/15.
 */
public class RestQueryEngine {

    private IRestQueryProvider mQueryProvider;
    RestQueryResult myResponse = null;

    public RestQueryEngine(IRestQueryProvider pQueryProvider) {
        this.mQueryProvider = pQueryProvider;
    }

    public RestQueryResult runSimpleQuery(String pQueryName, HttpHeaders pHeaders, Object pRequestBody,
                                          Map<String, Object> pUrlParams, MultiValueMap<String, Object> pQueryParams) throws RestQueryEngineException {
        return this.runSimpleQuery(findQuery(pQueryName, pHeaders, pRequestBody, pUrlParams, pQueryParams));
    }

    public RestQueryResult runSimpleQuery(String pQueryName, HttpHeaders pHeaders) throws RestQueryEngineException {
        return this.runSimpleQuery(findQuery(pQueryName, pHeaders, null));
    }

    public RestQueryResult runSimpleQuery(String pQueryName, HttpHeaders pHeaders, Object pRequestParam) throws RestQueryEngineException {
        return this.runSimpleQuery(findQuery(pQueryName, pHeaders, pRequestParam));
    }

    private RestQuery findQuery(String pQueryName, HttpHeaders pHeaders, Object pRequestParam) throws RestQueryEngineException {

        if (pQueryName == null || pQueryName.isEmpty()) {
            throw new RestQueryEngineException("pQueryName cannot be null or empty");
        }

        if (mQueryProvider == null) {
            throw new RestQueryEngineException("No query provider is supplied");
        }

        return mQueryProvider.findQuery(pQueryName, pHeaders, pRequestParam);
    }

    private RestQuery findQuery(String pQueryName, HttpHeaders pHeaders, Object pRequestBody,
                                Map<String, Object> pUrlParams, MultiValueMap<String, Object> pQueryParams) throws RestQueryEngineException {

        if (pQueryName == null || pQueryName.isEmpty()) {
            throw new RestQueryEngineException("pQueryName cannot be null or empty");
        }

        if (mQueryProvider == null) {
            throw new RestQueryEngineException("No query provider is supplied");
        }

        return mQueryProvider.findQuery(pQueryName, pHeaders, pRequestBody, pUrlParams, pQueryParams);
    }

    public RestQueryResult runSimpleQuery(RestQuery pQuery) throws RestQueryEngineException {

        if (pQuery == null) {
            throw new RestQueryEngineException("Query cannot be null");
        }

        String myUrl = pQuery.getURL();

        if (myUrl == null || myUrl.isEmpty()) {
            throw new RestQueryEngineException("No Url found in query  ");
        }

        MyTask task = new MyTask();
        try {
            myResponse = task.execute(pQuery).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new RestQueryEngineException(e.getMessage());
        }
        return myResponse;
    }

    private class MyTask extends AsyncTask<RestQuery, String, RestQueryResult> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected RestQueryResult doInBackground(RestQuery... params) throws RestQueryEngineException {

            RestQuery pQuery = params[0];
            ResponseEntity<String> response = null;
            HttpEntity<?> requestEntity;
            if (pQuery.getmBody() == null) {
                requestEntity = new HttpEntity<Object>(pQuery.getHeaders());
            } else {
                requestEntity = new HttpEntity<>(pQuery.getmBody(), pQuery.getHeaders());
            }

            RestTemplate restTemplate = new RestTemplate(true);
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            try {
                response = restTemplate.exchange(pQuery.getURL(), pQuery.getMethod(), requestEntity, String.class);
            } catch (Exception e) {
                //TODO Handle UnAuthorized errors
                e.printStackTrace();
            }

            String rawResponse = response.getBody();
            myResponse = new RestQueryResult(response.getStatusCode().value(), rawResponse, response.getHeaders());
            return myResponse;
        }
    }

}
