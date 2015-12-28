package com.volunteeride.rest.volunteeride;

import com.volunteeride.rest.IRestQueryProvider;
import com.volunteeride.rest.RestQuery;
import com.volunteeride.volunteeride.utility.PropertyReaderUtility;

import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mthosani on 12/19/15.
 */
public class VolunteeRideRestQueryProvider implements IRestQueryProvider{

    private static final Map<String,RestQuery> queriesByNameMap = new HashMap<String,RestQuery>();
    static{

        PropertyReaderUtility myUtility = PropertyReaderUtility.getInstance();
        String baseURL = myUtility.getValueFromProperties(VolunteeRideConstantsUtil.BASE_URL);
        queriesByNameMap.put(VolunteeRideConstantsUtil.GET_CENTERS,new RestQuery(RestQuery.Method.GET,baseURL + VolunteeRideConstantsUtil.CENTERS_RESOURCE));
        queriesByNameMap.put(VolunteeRideConstantsUtil.LOGIN,new RestQuery(RestQuery.Method.POST,baseURL + VolunteeRideConstantsUtil.LOGIN_RESOURCE));
        queriesByNameMap.put(VolunteeRideConstantsUtil.REGISTER_USER,new RestQuery(RestQuery.Method.POST,baseURL + VolunteeRideConstantsUtil.REGISTER_USER_RESOURCE));

    }

    public VolunteeRideRestQueryProvider(){
    }

    @Override
    public RestQuery findQuery(String pQueryName, HttpHeaders pHeaders, Object pRequestParam) {

        RestQuery myRestQuery = queriesByNameMap.get(pQueryName);
        if(pHeaders != null){
            myRestQuery.setHeaders(pHeaders);
        }else{
            myRestQuery.setHeaders(new HttpHeaders());
        }
        myRestQuery.setParam(pRequestParam);
        return myRestQuery;
    }

    @Override
    public Map<String, Object> procesQueryResponse(int statusCode, String pQueryResponse) {
        return null;
    }
}
