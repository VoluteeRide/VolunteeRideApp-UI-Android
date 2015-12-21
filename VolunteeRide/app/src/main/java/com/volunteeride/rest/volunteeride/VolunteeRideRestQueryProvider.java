package com.volunteeride.rest.volunteeride;

import com.volunteeride.rest.IRestQueryProvider;
import com.volunteeride.rest.RestQuery;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mthosani on 12/19/15.
 */
public class VolunteeRideRestQueryProvider implements IRestQueryProvider{

    private static final String GET_CENTERS = "centers";



    private static final String BASE_URL = "http://10.0.2.2:8080/volunteeride";

    private final Map<String, RestQuery> queriesByNameMap = new HashMap<String, RestQuery>();

    public VolunteeRideRestQueryProvider(){

        initQueriesByNameMap();
    }

    private void initQueriesByNameMap(){

        queriesByNameMap.put(GET_CENTERS, new RestQuery(GET_CENTERS,RestQuery.Method.GET,BASE_URL,"/centers"));
    }

    @Override
    public RestQuery findQuery(String pQueryName) {
        return queriesByNameMap.get(pQueryName);
    }

    @Override
    public Map<String, Object> procesQueryResponse(int statusCode, String pQueryResponse) {
        return null;
    }
}
