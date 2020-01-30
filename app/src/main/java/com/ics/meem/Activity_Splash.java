package com.ics.meem;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.ics.meem.Fragment.Home_Fragment;
import com.ics.meem.model.AppPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class Activity_Splash extends AppCompatActivity
{
    private static  int SPLASH_TIME_OUT = 3000;
    ImageView imagelogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imagelogo = findViewById(R.id.imagelogo);


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                Intent intent = new Intent(Activity_Splash.this,MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try{
                    new GetNotification(AppPreference.getUser_Id(Activity_Splash.this), AppPreference.getUserToken(Activity_Splash.this)).execute();
                }catch (Exception e){

                }
                if (AppPreference.get_Matrimony_Login(Activity_Splash.this) || AppPreference.get_PointService_Login(Activity_Splash.this)) {

                    Intent intent = new Intent(Activity_Splash.this, MainActivity.class);
                    intent.putExtra("login","T");
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(Activity_Splash.this, MainActivity.class);
                    intent.putExtra("login","F");
                    startActivity(intent);
                    finish();
                }
            }
            }, SPLASH_TIME_OUT);
       }
    private class GetNotification extends AsyncTask<String, Void, String> {
        String user_id; String authToken;
        Dialog dialog;
        public GetNotification(String user_id,String authToken) {
            this.user_id = user_id;
            this.authToken = authToken;

        }
        @Override
        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://meem.one/UserApi/notify.php");
                JSONObject postDataParams = new JSONObject();
//                postDataParams.put("otp", Mobile_Number);
                postDataParams.put("authToken","IzpFfdTN9U");
                postDataParams.put("userid", user_id);
//                postDataParams.put("authToken", authToken);
//                postDataParams.put("device_id", AppPreference.getUserToken(getActivity()));

                Log.e("postDataParams", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /*milliseconds*/);
                conn.setConnectTimeout(15000 /*milliseconds*/);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        StringBuffer Ss = sb.append(line);
                        Log.e("Ss", Ss.toString());
                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
//                dialog.dismiss();

                JSONObject jsonObject1 = null;
                Log.e("Notification APi Data ", "-------    "+result.toString());
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if(jsonObject.getInt("status_code") !=0)
                    {
                        Toast.makeText(Activity_Splash.this, "New Data found", Toast.LENGTH_SHORT).show();
                        if(jsonObject.getInt("new_match") >0) {
                            Log.d("sender", "Broadcasting message");
                            Intent intent = new Intent("custom-event-name");
                            // You can also include some extra data.
                            intent.putExtra("message", "M");
                            LocalBroadcastManager.getInstance(Activity_Splash.this).sendBroadcast(intent);
                        }else if(jsonObject.getInt("notifications") >0)
                        {
                            Log.d("sender", "Broadcasting message");
                            Intent intent = new Intent("custom-event-name");
                            // You can also include some extra data.
                            intent.putExtra("message", "N");
                            LocalBroadcastManager.getInstance(Activity_Splash.this).sendBroadcast(intent);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while (itr.hasNext()) {

                String key = itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }
    }
}
