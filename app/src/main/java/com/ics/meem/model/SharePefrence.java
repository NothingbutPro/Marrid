package com.ics.meem.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;

public class SharePefrence {
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    Context context;

    int mode=0;
    String Filename="sdfile";

    String data="b";

    public SharePefrence (Context context)
    {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Filename,mode);
        editor= sharedPreferences.edit();

    }
    public  void secondtime()
    {
        editor.putBoolean(data,true);
        editor.commit();
    }
    public void firsttime()
        {}

}
