package com.volunteeride.rest;

import java.util.Map;

/**
 * Created by mthosani on 12/12/15.
 */

public interface IRestQueryProvider {

    /**
     * Finds the query object with the given name
     * @param pQueryName The unique name of the query
     * @return The query object matching the given name
     */
    RestQuery findQuery(String pQueryName);

    /**
     * Executes common tasks with the HTTPUrlConnection such as adding specific HTTP headers.
     * @param pRequest The HTTP request object
     */
   // void executeCommonQueryTasks(HttpRequestBase pRequest);

    /**
     * Processes the response returned from the REST service and returns the result as a map
     * @param statusCode HTTP status code
     * @param pQueryResponse The response from the REST service
     * @return The map containing String, Object pairs
     */
    Map<String, Object> procesQueryResponse(int statusCode, String pQueryResponse);


}
