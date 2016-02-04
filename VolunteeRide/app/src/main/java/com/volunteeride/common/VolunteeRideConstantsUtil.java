package com.volunteeride.common;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by mthosani on 12/26/15.
 */
public class VolunteeRideConstantsUtil {

    public static ObjectMapper mapper = new ObjectMapper();

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    public static final String GET_CENTERS = "centers";

    public static final String LOGIN = "login";

    public static final String REGISTER_USER = "register";

    public static final String CENTERS_RESOURCE = "/centers";

    public static final String LOGIN_RESOURCE = "/users/login";

    public static final String REGISTER_USER_RESOURCE = "/users";

    public static class UrlConstants {

        public static final String BASE_URL = "base_url";
        public static final String SEARCH_RIDES_URL = "search_rides_url";
        public static final String RETRIEVE_CENTER_DETAILS_URL = "retrieve_center_details_url";
        public static final String RETRIEVE_USER_DETAILS_URL = "retrieve_user_details_url";
        public static final String EXECUTE_RIDE_OPERATION_URL = "execute_ride_operation_url";
    }

    public static class UrlNameConstants {

        public static final String SEARCH_RIDES_URL_NAME = "searchRides";
        public static final String RETRIEVE_CENTER_DETAILS_URL_NAME = "retrieveCenterDetails";
        public static final String RETRIEVE_USER_DETAILS_URL_NAME = "retrieveUserDetails";
        public static final String EXECUTE_RIDE_OPERATION_URL_NAME = "executeRideOperation";

    }

    public static class QueryParamsConstants {

        public static final String RIDE_STATUS = "status";

    }

}
