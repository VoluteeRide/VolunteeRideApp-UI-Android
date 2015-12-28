package com.volunteeride.volunteeride.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.volunteeride.dto.User;

/**
 * Created by mthosani on 12/26/15.
 */
public class LocalStoreUtility {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userInfo;

    public LocalStoreUtility(Context context){
        userInfo = context.getSharedPreferences(SP_NAME,0);
    }

    public void storeInfo(User user){
        SharedPreferences.Editor spEditor = userInfo.edit();
        spEditor.putString("name",user.getName());
        spEditor.putString("email",user.getEmail());
        spEditor.commit();
    }

    public User getLoggedInUserInfo(){
        String name = userInfo.getString("name","");
        String email = userInfo.getString("email","");

        User myUser = new User();
        myUser.setEmail(email);
        myUser.setName(name);

        return myUser;
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
