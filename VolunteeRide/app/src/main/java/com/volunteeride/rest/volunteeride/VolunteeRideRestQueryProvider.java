package com.volunteeride.rest.volunteeride;

import com.volunteeride.common.VolunteeRideConstantsUtil;
import com.volunteeride.rest.IRestQueryProvider;
import com.volunteeride.rest.RestQuery;
import com.volunteeride.volunteeride.utility.PropertyReaderUtility;

import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import static com.volunteeride.common.VolunteeRideConstantsUtil.UrlConstants.BASE_URL;
import static com.volunteeride.common.VolunteeRideConstantsUtil.UrlConstants.RETRIEVE_CENTER_DETAILS_URL;
import static com.volunteeride.common.VolunteeRideConstantsUtil.UrlConstants.RETRIEVE_USER_DETAILS_URL;
import static com.volunteeride.common.VolunteeRideConstantsUtil.UrlConstants.SEARCH_RIDES_URL;
import static com.volunteeride.common.VolunteeRideConstantsUtil.UrlNameConstants.RETRIEVE_CENTER_DETAILS_URL_NAME;
import static com.volunteeride.common.VolunteeRideConstantsUtil.UrlNameConstants.RETRIEVE_USER_DETAILS_URL_NAME;
import static com.volunteeride.common.VolunteeRideConstantsUtil.UrlNameConstants.SEARCH_RIDES_URL_NAME;

/**
 * Created by mthosani on 12/19/15.
 */
public class VolunteeRideRestQueryProvider implements IRestQueryProvider{

    private static final Map<String,RestQuery> queriesByNameMap = new HashMap<String,RestQuery>();
    private static final String baseURL;
    static {

        baseURL = PropertyReaderUtility.getValue(BASE_URL);

        queriesByNameMap.put(VolunteeRideConstantsUtil.GET_CENTERS,
                new RestQuery(RestQuery.Method.GET, baseURL + VolunteeRideConstantsUtil.CENTERS_RESOURCE));
        queriesByNameMap.put(VolunteeRideConstantsUtil.LOGIN,
                new RestQuery(RestQuery.Method.POST, baseURL + VolunteeRideConstantsUtil.LOGIN_RESOURCE));
        queriesByNameMap.put(VolunteeRideConstantsUtil.REGISTER_USER,
                new RestQuery(RestQuery.Method.POST, baseURL + VolunteeRideConstantsUtil.REGISTER_USER_RESOURCE));

        queriesByNameMap.put(SEARCH_RIDES_URL_NAME,
                new RestQuery(RestQuery.Method.GET, PropertyReaderUtility.getValue(SEARCH_RIDES_URL)));
        queriesByNameMap.put(RETRIEVE_CENTER_DETAILS_URL_NAME,
                new RestQuery(RestQuery.Method.GET, PropertyReaderUtility.getValue(RETRIEVE_CENTER_DETAILS_URL)));
        queriesByNameMap.put(RETRIEVE_USER_DETAILS_URL_NAME,
                new RestQuery(RestQuery.Method.GET, PropertyReaderUtility.getValue(RETRIEVE_USER_DETAILS_URL)));

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
    public RestQuery findQuery(String pQueryName, HttpHeaders pHeaders, Object pRequestBody,
                               Map<String, Object> pUrlParams, Map<String, Object> pQueryParams) {

        RestQuery myRestQuery = queriesByNameMap.get(pQueryName);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(baseURL).pathSegment(myRestQuery.getURL());

        if(pQueryParams !=  null && !pQueryParams.isEmpty()){

            for(Map.Entry<String, Object> entry : pQueryParams.entrySet()){
                uriComponentsBuilder.queryParam(entry.getKey(), entry.getValue());
            }
        }

        UriComponents uriComponents;

        if(pUrlParams != null && !pUrlParams.isEmpty()){
            uriComponents = uriComponentsBuilder.buildAndExpand(pUrlParams);
        }else{
            uriComponents = uriComponentsBuilder.build();
        }

        myRestQuery.setURL(uriComponents.toUriString());

        if(pHeaders != null){
            myRestQuery.setHeaders(pHeaders);
        }else{
            myRestQuery.setHeaders(new HttpHeaders());
        }
        myRestQuery.setmBody(pRequestBody);
        return myRestQuery;
    }

    @Override
    public Map<String, Object> procesQueryResponse(int statusCode, String pQueryResponse) {
        return null;
    }
}
