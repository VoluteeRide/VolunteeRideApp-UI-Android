package com.volunteeride.volunteeride.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.volunteeride.dto.VolunteerideUser;

/**
 * Created by mthosani on 12/26/15.
 */
public class LocalStoreUtility {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userInfo;

    public LocalStoreUtility(Context context){
        userInfo = context.getSharedPreferences(SP_NAME,0);
    }

    public void storeInfo(VolunteerideUser user){
        SharedPreferences.Editor spEditor = userInfo.edit();
        spEditor.putString("centerId",user.getCenterId());
        spEditor.putString("userId",user.getId());
        spEditor.commit();
    }

    public void storeSessionId(String sessionId){
        SharedPreferences.Editor spEditor = userInfo.edit();
        spEditor.putString("JSESSIONID",sessionId);
        spEditor.commit();
    }

    public String getSessionId() {
        return userInfo.getString("JSESSIONID", "");
    }


    public VolunteerideUser getLoggedInUserInfo(){
        String centerId = userInfo.getString("centerId","");
        String userId = userInfo.getString("userId","");

        VolunteerideUser loggedInUser = new VolunteerideUser();
        loggedInUser.setCenterId(centerId);
        loggedInUser.setId(userId);

        return loggedInUser;
    }

    public void setUserLoggedIn(boolean isLoggedIn){
        SharedPreferences.Editor spEditor = userInfo.edit();
        spEditor.putBoolean("isLoggedIn", isLoggedIn);
        spEditor.commit();
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userInfo.edit();
        spEditor.clear();
        spEditor.commit();

    }
}
