package com.volunteeride.rest;

import android.os.AsyncTask;

import org.springframework.http.ResponseEntity;
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

    public RestQueryResult runSimpleQuery(String pQueryName) throws RestQueryEngineException{
        return this.runSimpleQuery(findQuery(pQueryName), null, null, null, null, null, null);
    }

    private RestQuery findQuery(String pQueryName) throws RestQueryEngineException{

        if(pQueryName == null || pQueryName.isEmpty()){
            throw new RestQueryEngineException("pQueryName cannot be null or empty");
        }

        if(mQueryProvider == null){
            throw new RestQueryEngineException("No query provider is supplied");
        }

        return mQueryProvider.findQuery(pQueryName);
    }

    private RestQueryResult runSimpleQuery(RestQuery pQuery, String pUrlPostfix, Map<String, String> pHeaderFields,
                                           Map<String,String> pGetParams, Map<String,String> pPostParams, Map<String,String> pPutParams,
                                           String pPostBody) throws RestQueryEngineException{

        if(pQuery == null){
            throw new RestQueryEngineException("Query cannot be null");
        }

        String myUrl = pQuery.getURL();

        if(myUrl == null || myUrl.isEmpty()){
            throw new RestQueryEngineException("No Url found in query : " + pQuery.getName());
        }

        MyTask task = new MyTask();
        try {
            myResponse = task.execute(pQuery).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(myResponse == null) {
            System.out.println("Is null &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        }else{
            System.out.println(" Not nul %%%%%%%%%%%%%%%%");
        }
        return myResponse;
    }




    private class MyTask extends AsyncTask<RestQuery,String,RestQueryResult>{

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected RestQueryResult doInBackground(RestQuery... params) {


            RestQuery pQuery = params[0];
            ResponseEntity<String> response = null;


            if(pQuery.getMethod() == RestQuery.Method.GET){

                System.out.println("****************************************************************Rest URL *********" + pQuery.getURL());
                RestTemplate restTemplate = new RestTemplate(true);
                //restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());



                try {
                    response = restTemplate.getForEntity(pQuery.getURL(),String.class);
                    System.out.println("#########################################################################Response: " + response);
                } catch (Exception e) {
                    //TODO Handle UnAuthorized errors
                    e.printStackTrace();
                }

            }
            String rawResponse = response.getBody();
            System.out.println(" Raw Response Body *************)))))))))))))) " + rawResponse);

            String modifiedResponse =  rawResponse.substring(1, rawResponse.length() - 1);
            System.out.println(" Modified Response Body *************)))))))))))))) " + modifiedResponse);
            myResponse = new RestQueryResult(response.getStatusCode().value(),rawResponse);

            return myResponse;
        }
    }


}
