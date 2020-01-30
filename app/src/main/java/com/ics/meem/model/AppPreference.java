package com.ics.meem.model;

import android.content.Context;
import android.content.SharedPreferences;


public class AppPreference {

    public static final String SHARED_PREFERENCE_NAME = "EXPENSEMGT";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String SAFE_LOGIN = "safe_share";
    public static final String User_Id = "user_id";
    public static final String USER_TOKEN = "user_token";
    public static final String SESSION = "session";
    public static final String SESSION2 = "session2";
//    public static final String SESSION = "session2";
    public static final String m_token = "m_token";
    public static final String m_email = "m_email";
    public static final String p_token = "p_token";

    Context context;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor editor2;

    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_NAME = "user_fullname";

    public static void setEmail(Context context, String value) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(EMAIL, value);
        editor.commit();
    }

    public static String getEmail(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        return preferences.getString(EMAIL, "");
    }

    public static void setSafeLogin(Context context, String headname) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SAFE_LOGIN, headname);
        editor.commit();
    }

    public static String getSafeLogin(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        return preferences.getString(SAFE_LOGIN, "");
    }

    public static void setUser_Id(Context context, String headname) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(User_Id, headname);
        editor.commit();
    }

    public static String getUser_Id(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        return preferences.getString(User_Id, "");
    }

    public static void setUserToken(Context context, String headname) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_TOKEN, headname);
        editor.commit();
    }

    public static String getUserToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        return preferences.getString(USER_TOKEN, "");
    }


    public static String getMatrimonyToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        return preferences.getString(m_token, "");
    }

    public static void setMatrimonyToken(Context context,String log) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(m_token,log);
        editor.commit();
    }

    public static String getMatrimonyMail(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        return preferences.getString(m_email, "");
    }

    public static void setMatrimonyMail(Context context,String log) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(m_email,log);
        editor.commit();
    }

    public static void set_Matrimony_Login(Context context,Boolean log) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(SESSION,log);
        editor.commit();
    }

    public static Boolean get_Matrimony_Login(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        return  preferences.getBoolean(SESSION,false);
    }


    public static String getPointServiceToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        return preferences.getString(p_token, "");
    }

    public static void setPointServiceToken(Context context,String log) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(p_token,log);
        editor.commit();
    }

    public static void set_PointService_Login(Context context,Boolean log) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(SESSION2,log);
        editor.commit();
    }

    public static Boolean get_PointService_Login(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        return  preferences.getBoolean(SESSION2,false);
    }

    public void cleardatetime() {
        editor2.clear();
        editor2.commit();
    }

    public boolean isLoggedIn()
    {
        return prefs.getBoolean(IS_LOGIN, false);
    }

}
