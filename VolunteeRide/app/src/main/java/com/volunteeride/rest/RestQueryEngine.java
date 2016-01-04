package com.volunteeride.rest;

import android.os.AsyncTask;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * Created by mthosani on 12/12/15.
 */
public class RestQueryEngine {

    HttpURLConnection mURLConnection;
    private IRestQueryProvider mQueryProvider;
    RestQueryResult myResponse = null;

    public RestQueryEngine(IRestQueryProvider pQueryProvider){

        this.mQueryProvider = pQueryProvider;

    }

    public RestQueryResult runSimpleQuery(String pQueryName, HttpHeaders pHeaders, Object pRequestBody,
                                          Map<String, Object> pUrlParams, Map<String, Object> pQueryParams) throws RestQueryEngineException{
        return this.runSimpleQuery(findQuery(pQueryName,pHeaders,pRequestBody, pUrlParams, pQueryParams));
    }

    public RestQueryResult runSimpleQuery(String pQueryName, HttpHeaders pHeaders) throws RestQueryEngineException{
        return this.runSimpleQuery(findQuery(pQueryName,pHeaders,null));
    }

    public RestQueryResult runSimpleQuery(String pQueryName, HttpHeaders pHeaders, Map<String,String> pQueryParam) throws RestQueryEngineException{
        return this.runSimpleQuery(findQuery(pQueryName,pHeaders,null));
    }

    public RestQueryResult runSimpleQuery(String pQueryName, HttpHeaders pHeaders, Object pRequestParam) throws RestQueryEngineException{
        return this.runSimpleQuery(findQuery(pQueryName,pHeaders,pRequestParam));
    }

    public RestQueryResult runSimpleQuery(String pQueryName, HttpHeaders pHeaders, Map<String,String> pQueryParam, Object pRequestParam) throws RestQueryEngineException{
        return this.runSimpleQuery(findQuery(pQueryName,pHeaders,null));
    }

    private RestQuery findQuery(String pQueryName,HttpHeaders pHeaders,Object pRequestParam) throws RestQueryEngineException{

        if(pQueryName == null || pQueryName.isEmpty()){
            throw new RestQueryEngineException("pQueryName cannot be null or empty");
        }

        if(mQueryProvider == null){
            throw new RestQueryEngineException("No query provider is supplied");
        }

        return mQueryProvider.findQuery(pQueryName, pHeaders, pRequestParam);
    }

    private RestQuery findQuery(String pQueryName, HttpHeaders pHeaders, Object pRequestBody,
                                Map<String, Object> pUrlParams, Map<String, Object> pQueryParams) throws RestQueryEngineException{

        if(pQueryName == null || pQueryName.isEmpty()){
            throw new RestQueryEngineException("pQueryName cannot be null or empty");
        }

        if(mQueryProvider == null){
            throw new RestQueryEngineException("No query provider is supplied");
        }

        return mQueryProvider.findQuery(pQueryName,pHeaders,pRequestBody, pUrlParams, pQueryParams);
    }

    public RestQueryResult runSimpleQuery(RestQuery pQuery) throws RestQueryEngineException{

        if(pQuery == null){
            throw new RestQueryEngineException("Query cannot be null");
        }

        String myUrl = pQuery.getURL();

        if(myUrl == null || myUrl.isEmpty()){
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




    private class MyTask extends AsyncTask<RestQuery,String,RestQueryResult>{

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected RestQueryResult doInBackground(RestQuery... params) throws RestQueryEngineException{


            RestQuery pQuery = params[0];
            ResponseEntity<String> response = null;
            HttpEntity<?> requestEntity;
            if(pQuery.getParam() == null){
                requestEntity = new HttpEntity<Object>(pQuery.getHeaders());
            }else{
                requestEntity = new HttpEntity<Object>(pQuery.getParam(), pQuery.getHeaders());
            }

            RestTemplate restTemplate = new RestTemplate(true);
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());


            if(pQuery.getMethod() == RestQuery.Method.GET){

                try {
                    response = restTemplate.exchange(pQuery.getURL(), HttpMethod.GET,requestEntity,String.class);
                    System.out.println("#########################################################################Response: " + response);
                } catch (Exception e) {
                    //TODO Handle UnAuthorized errors
                    e.printStackTrace();
                }

            }else if(pQuery.getMethod() == RestQuery.Method.POST){

                try {
                    response = restTemplate.exchange(pQuery.getURL(), HttpMethod.POST,requestEntity,String.class);
                    System.out.println("#########################################################################Response: " + response);
                } catch (Exception e) {
                    throw new RestQueryEngineException(e.getMessage());

                }

            }

            String rawResponse = response.getBody();
            System.out.println(" Raw Response Body *************)))))))))))))) " + rawResponse);

            String modifiedResponse =  rawResponse.substring(1, rawResponse.length() - 1);
            System.out.println(" Modified Response Body *************)))))))))))))) " + modifiedResponse);
            myResponse = new RestQueryResult(response.getStatusCode().value(),rawResponse, response.getHeaders());

            return myResponse;
        }
    }


}
