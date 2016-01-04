package com.volunteeride.volunteeride.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import static com.volunteeride.rest.volunteeride.VolunteeRideConstantsUtil.UrlConstants.BASE_URL;
import static com.volunteeride.rest.volunteeride.VolunteeRideConstantsUtil.UrlConstants.SEARCH_RIDES_URL;

/**
 * Created by mthosani on 12/23/15.
 */
public class PropertyReaderUtility {

    public static ResourceBundle appProperties = ResourceBundle.getBundle("assets/app");

    public static String baseURL = appProperties.getString(BASE_URL);

    public static String searchRidesURL = appProperties.getString(SEARCH_RIDES_URL);


    private static final PropertyReaderUtility mPropertyUtility = new PropertyReaderUtility();
    Properties mAppProperties = new Properties();

    public static PropertyReaderUtility getInstance(){

        return mPropertyUtility;
    }

    public String getValueFromProperties(String pPropertyKey) {

        String propertyValue = "";
        try {
            InputStream propertiesFileInputStreamCurrentFolder = getClass()
                    .getClassLoader().getResourceAsStream(
                            "assets/app.properties");
            mAppProperties.load(propertiesFileInputStreamCurrentFolder);
            propertyValue = mAppProperties
                    .getProperty(pPropertyKey);
        }catch(IOException e){
            handleException(e);

        }catch(Exception e){
                handleException(e);
        }
        return propertyValue;

    }

    public void handleException(Exception e){

        StackTraceElement[] stack = e.getStackTrace();
        String exception = "";
        for (StackTraceElement s : stack) {
            exception = exception + s.toString() + "\n\t\t";
        }
        System.out
                .println("**************************************************** Exception thrown**************************************************"
                        + exception);

    }



}
