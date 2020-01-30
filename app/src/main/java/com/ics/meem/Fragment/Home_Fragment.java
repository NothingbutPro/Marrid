package com.ics.meem.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.daimajia.slider.library.SliderLayout;
import com.ics.meem.Adapter.Slider_adapter;
import com.ics.meem.Adapter.Slider_data;
import com.ics.meem.MainActivity;
import com.ics.meem.R;
import com.ics.meem.model.AppPreference;
import com.ics.meem.web.Web_View;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class Home_Fragment extends Fragment
{
    ImageView images1,images2,images3,images4,ic_menu,notification;
    SharedPreferences pref;
    List<Slider_data> dlist;
    private FragmentManager fragmentmanager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    SliderView sliderView;
    Slider_adapter slider_adapter;
    private  ImageView btnFollow,btnFollow1;
    private Context mContext;

    private int counter = 5;
     TextView tet_text,sing_up,meem1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home1,container,false);
        //Notification bail icon............>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        ic_menu = (ImageView) view.findViewById(R.id.ic_menu);

//menu opition visible off ..........>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        ((MainActivity)getActivity()).logout_off();
        ((MainActivity)getActivity()).user_off();
        sliderView = (SliderView) view.findViewById(R.id.imageSlider);

        //Slider_adapter slider_adapter = new Slider_adapter(getActivity());
        mContext = getActivity();
        btnFollow = (ImageView) view.findViewById(R.id.tx_images);

        // Draware click to opne........................................................................................
        ic_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 ((MainActivity)getActivity()).openDrawer();
            }
        });

        // TextView Call opne the web Site.................................................................................
        meem1 = (TextView) view.findViewById(R.id.meem1);
        meem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent  intent = new Intent(getActivity(),Web_View.class);
                String llnk = "https://meem.one/";
                intent.putExtra("link",llnk);
                startActivity(intent);


            }
        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++For Login+++++++++++++++++++++
        try{
            if(getActivity().getIntent().getStringExtra("login").equals("T"))
            {
                if(AppPreference.getEmail(getActivity()).equals("M")) {
                    fragment = new Matrimony_Fragment();
                    //   bn.putString("token",token);
                    //  bn.putString("email",email);

                    fragmentmanager = getActivity().getSupportFragmentManager();
                    // fragment.setArguments(bn);
                    fragmentTransaction = fragmentmanager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_layout, fragment);
                    //Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
                    //fragmentTransaction.addToBackStack(fragment.getTag());
                    fragmentTransaction.commit();
                }else if(AppPreference.getEmail(getActivity()).equals("P")){
                    fragment = new Pointsevice_Fragment();
                    //   bn.putString("token",token);
                    //  bn.putString("email",email);

                    fragmentmanager = getActivity().getSupportFragmentManager();
                    // fragment.setArguments(bn);
                    fragmentTransaction = fragmentmanager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_layout, fragment);
                    //Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
                    //fragmentTransaction.addToBackStack(fragment.getTag());
                    fragmentTransaction.commit();
                }else {
                    fragment = new Matrimony_Fragment();
                    //   bn.putString("token",token);
                    //  bn.putString("email",email);

                    fragmentmanager = getActivity().getSupportFragmentManager();
                    // fragment.setArguments(bn);
                    fragmentTransaction = fragmentmanager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_layout, fragment);
                    //Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
                    //fragmentTransaction.addToBackStack(fragment.getTag());
                    fragmentTransaction.commit();
                }
            }
        }catch (Exception e)
        {
            Log.e("login" , "False");
        }

        //+++++++++++++++++++
//        if(AppPreference.get_Matrimony_Login(getActivity()))
//        {
//            fragment = new Matrimony_Fragment();
//            //   bn.putString("token",token);
//            //  bn.putString("email",email);
//
//            fragmentmanager = getActivity().getSupportFragmentManager();
//            // fragment.setArguments(bn);
//            fragmentTransaction =fragmentmanager.beginTransaction();
//            fragmentTransaction.replace(R.id.fragment_layout,fragment);
//            //Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
//            //fragmentTransaction.addToBackStack(fragment.getTag());
//            fragmentTransaction.commit();
//        }else {
//            Toast.makeText(mContext, "sad", Toast.LENGTH_SHORT).show();
//        }
        /*
        //Notification icon cleck..................................................................
//Notification Click ....................................................................
        notification = view.findViewById(R.id.notification_home);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "This is Notification example.";
                NotificationCompat.Builder builder= new NotificationCompat.Builder(getActivity())
                        .setSmallIcon(R.drawable.notification)
                        .setContentTitle("new Notification")
                        .setContentText(message)
                        .setAutoCancel(true);
                Intent intent = new Intent(getActivity(), NotificationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message",message);

                PendingIntent pendingIntent= PendingIntent.getActivity(getActivity(), 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0,builder.build());

            }
        });
*/
        // add button listener..................................................................
        btnFollow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(AppPreference.get_Matrimony_Login(getActivity())){
                    fragment = new Matrimony_Fragment();
                    fragmentmanager = getActivity().getSupportFragmentManager();
                    fragmentTransaction =fragmentmanager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_layout,fragment);
                    Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
                    fragmentTransaction.addToBackStack(fragment.getTag());
                    fragmentTransaction.commit();
                }
                else{
                    show_Matrimony_Login();
                }
            }
        });

        // ponit services click to opne popup.................................................................................
        btnFollow1 =(ImageView) view.findViewById(R.id.tx_images3);

        btnFollow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!AppPreference.get_PointService_Login(getActivity())) {
                    final Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.custom_pointservices_loginpop);
                    dialog.setTitle("Title...");

                    final Button btn1 = (Button) dialog.findViewById(R.id.log_in);
                    TextView btn2 = (TextView) dialog.findViewById(R.id.sing_up);
                    final EditText editText = (EditText) dialog.findViewById(R.id.editTextemail);
                    final EditText editText1 = (EditText) dialog.findViewById(R.id.editTextpasss);
                    //point services login

                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (editText1.getText().toString().isEmpty() && editText.getText().toString().isEmpty()) {
                                Toast.makeText(getActivity(), "Please Enter all Fields...", Toast.LENGTH_SHORT).show();
                            } else if (editText.getText().toString().matches("")) {
                                Toast.makeText(getActivity(), "Please enter email adress...", Toast.LENGTH_SHORT).show();
                            } else if (editText1.getText().toString().matches("")) {
                                Toast.makeText(getActivity(), "Please enter password...", Toast.LENGTH_SHORT).show();
                            } else {
                                new PointLOGIN_API(editText.getText().toString(), editText1.getText().toString(), dialog).execute();
                            }
                        }
                    });
                    //resistration page opne......................................................................................
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), Web_View.class);
                            String llnk = "https://www.meem.one/reseller/";
                            intent.putExtra("link", llnk);
                            startActivity(intent);
                        }
                    });
                    TextView tet_text = (TextView) dialog.findViewById(R.id.tet_text1);
                    tet_text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                } else {
                    fragment = new Pointsevice_Fragment();
                    fragmentmanager = getActivity().getSupportFragmentManager();
                    fragmentTransaction =fragmentmanager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_layout,fragment);
                    Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
                    fragmentTransaction.addToBackStack(fragment.getTag());
                    fragmentTransaction.commit();
                }
            }
        });

        images1 = view.findViewById(R.id.tx_images);
        images2 = view.findViewById(R.id.tx_images1);
        images3 = view.findViewById(R.id.tx_images2);
        images4 = view.findViewById(R.id.tx_images3);

      /* images1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                fragment = new Matrimony_Fragment();
                fragmentmanager = getActivity().getSupportFragmentManager();

                fragmentTransaction =fragmentmanager.beginTransaction();
                fragmentTransaction.replace(R.id.tx_images1,fragment);
                Toast.makeText(getActivity(),"4hjlufofofuioufo",Toast.LENGTH_SHORT).show();
                fragmentTransaction.addToBackStack(fragment.getTag());
                fragmentTransaction.commit();


            }
        });   */
        images2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                fragment = new Jobportal_Fragment();
                fragmentmanager = getActivity().getSupportFragmentManager();
                fragmentTransaction =fragmentmanager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout,fragment);
                fragmentTransaction.addToBackStack(fragment.getTag());
                fragmentTransaction.commit();


            }
        });
        images3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                fragment = new VenueBooking_Fragment();
                fragmentmanager = getActivity().getSupportFragmentManager();
                // fragmentTransaction.addToBackStack(fragment.getTag());
                fragmentTransaction =fragmentmanager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout,fragment);
                fragmentTransaction.addToBackStack(fragment.getTag());
                fragmentTransaction.commit();
            }

        });
  /*      images4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                fragment = new Pointsevice_Fragment();
                fragmentmanager = getActivity().getSupportFragmentManager();
                fragmentTransaction =fragmentmanager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout,fragment);
                fragmentTransaction.addToBackStack(fragment.getTag());
                fragmentTransaction.commit();

            }
        });
*/              // Toolbar Notification click to opne web notification
        init_Slider();
        //++++++++++++++++++++++++++++++++++++++++++++++++For getting the notification/////
        Log.e("user id is" , ""+AppPreference.getUser_Id(getActivity()));
        new GetNotification(AppPreference.getUser_Id(getActivity()), AppPreference.getUserToken(getActivity())).execute();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++
        return view;
    }
    private void init_Slider(){

        dlist = new ArrayList<>();
        dlist.add(new Slider_data(R.drawable.matrimony1, "First image"));
        dlist.add(new Slider_data(R.drawable.matrimony1,"First Image"));
        dlist.add(new Slider_data(R.drawable.matrimony2,"Second Image"));
//        dlist.add(new Slider_data(R.drawable.matrimony1,"Second Image"));
//        dlist.add(new Slider_data(R.drawable.matrimony2,"Second Image"));
        slider_adapter = new Slider_adapter(getActivity(),dlist);
        slider_adapter.setCount(dlist.size());

        sliderView.isAutoCycle();
        sliderView.setAutoCycle(true);
        sliderView.setScrollTimeInSec(2);
        sliderView.setSliderAdapter(slider_adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.BLUE);

        sliderView.setIndicatorUnselectedColor(Color.BLACK);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        }); }


    private class LOGIN_API extends AsyncTask<String, Void, String> {
        String email; String password;
        Dialog dialog;
        public LOGIN_API(String email,String password,Dialog dialog) {
            this.email = email;
            this.password = password;
            this.dialog = dialog;

        }
        @Override
        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://meem.one/UserApi/userLogin.php");
                JSONObject postDataParams = new JSONObject();
//                postDataParams.put("otp", Mobile_Number);
                postDataParams.put("authToken","IzpFfdTN9U");
//                postDataParams.put("authToken",AppPreference.getUserToken(getActivity()));
                postDataParams.put("email", email);
                postDataParams.put("password", password);
                postDataParams.put("device_id", AppPreference.getUserToken(getActivity()));

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
             dialog.dismiss();

                JSONObject jsonObject1 = null;
                Log.e("LOGIN API ", "-------    "+result.toString());
                try {
                    jsonObject1 = new JSONObject(result);
                    String message = jsonObject1.getString("message");
                    String token = jsonObject1.getString("token");
                    String Action = jsonObject1.getString("Action");

                    if(jsonObject1.getString("status_code").equals("1"))
                    {
                        if (jsonObject1.getString("Action").equals("safe_share"))
                        {
                            fragment = new Matrimony_Fragment();
                            //   bn.putString("token",token);
                            //  bn.putString("email",email);
                            AppPreference.getSafeLogin(getActivity());
                            AppPreference.setMatrimonyToken(getActivity(), token);
                            AppPreference.setEmail(getActivity() , "S");
                            AppPreference.set_Matrimony_Login(getActivity(), true);
                            fragmentmanager = getActivity().getSupportFragmentManager();
                            // fragment.setArguments(bn);
                            fragmentTransaction =fragmentmanager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_layout,fragment);
                            //Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
                            //fragmentTransaction.addToBackStack(fragment.getTag());
                            fragmentTransaction.commit();
//                        Bundle bn = new Bundle();
//                        Intent  intent = new Intent(getActivity(),Web_View.class);
//                        String llnk = "https://meem.one/myprofile.php?token="+token;
//                         llnk = "https://meem.one/myprofile.php?option=share_safe?token="+token;
//                        Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
//                        intent.putExtra("link",llnk);
//                        startActivity(intent);
//                        Toast.makeText(mContext, ""+message, Toast.LENGTH_SHORT).show();

                        }else {

                            AppPreference.setUser_Id(getActivity(), token);
                            AppPreference.setMatrimonyToken(getActivity(), token);
                            AppPreference.setMatrimonyMail(getActivity(), email);
                            AppPreference.setEmail(getActivity() , "M");
                            AppPreference.set_Matrimony_Login(getActivity(), true);
                            // startActivity(new Intent(getActivity(),Web_View.class));
                            dialog.dismiss();
                            // Bundle bn = new Bundle();
                            fragment = new Matrimony_Fragment();
                            //   bn.putString("token",token);
                            //  bn.putString("email",email);

                            fragmentmanager = getActivity().getSupportFragmentManager();
                            // fragment.setArguments(bn);
                            fragmentTransaction = fragmentmanager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_layout, fragment);
                            //Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
                            //fragmentTransaction.addToBackStack(fragment.getTag());
                            fragmentTransaction.commit();
                            Toast.makeText(mContext, "" + message, Toast.LENGTH_SHORT).show();
                            new  GetNotification(AppPreference.getUser_Id(getActivity()), AppPreference.getUserToken(getActivity())).execute();
                        }
                    }
                    else if (jsonObject1.getString("Action").equals("")&&jsonObject1.getString("status_code").equals("0"))
                    {
                        Toast.makeText(mContext, ""+message, Toast.LENGTH_SHORT).show();
                    }

                     if (jsonObject1.getString("Action").equals("Renew"))
                    // else if(jsonObject1.getString("status_code").equals("0"))
                    {
                        Bundle bn = new Bundle();

                        Intent  intent = new Intent(getActivity(),Web_View.class);
                        String llnk = "https://meem.one/Membership-Renewal.php?token="+token;
                        Log.e("Link Received ",">>>>>>   ------  "+llnk);

                        Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
                        intent.putExtra("link",llnk);
                        startActivity(intent);
                        Toast.makeText(mContext, ""+message, Toast.LENGTH_SHORT).show();
                    }



                }



                catch (JSONException e) {

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
    // muslim matrimony  forget password.............<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<>>>>>>>>>>>>>

    private class FORGET_API extends AsyncTask<String, Void, String> {

        String email;
        Dialog dialog;

        public FORGET_API(String email,Dialog dialog) {
            this.email = email;
            this.dialog = dialog;                     }

        @Override
        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://meem.one/UserApi/forgot_password.php");
                JSONObject postDataParams = new JSONObject();
//                postDataParams.put("otp", Mobile_Number);
                postDataParams.put("authToken","IzpFfdTN9U");
                postDataParams.put("email", email);




                Log.e("postDataParams", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /*milliseconds*/);
                conn.setConnectTimeout(15000 /*milliseconds*/);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
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
                dialog.dismiss();
                JSONObject jsonObject1 =null;
                Log.e("JOB LOGIN API ", "------- >>>>>>>>>>>>>>>>>>>>>   "+result.toString());
                try {
                    jsonObject1 = new JSONObject(result);
                    String message = jsonObject1.getString("message");
                  //  String token = jsonObject1.getString("token");


                    if (jsonObject1.getString("status_code").equals("1"))
                    {
                        // startActivity(new Intent(getActivity(),Web_View.class));
                        dialog.dismiss();
                        Bundle bn = new Bundle();
                        Intent  intent = new Intent(getActivity(),Web_View.class);
                        //bn.putString("message",message);
//                        String llnk = "https://meem.one/UserApi/forgot_password.php?token="+token;
                        String llnk = "https://meem.one/UserApi/forgot_password.php";
                        Toast.makeText(getActivity(),""+message,Toast.LENGTH_SHORT).show();
                        intent.putExtra("link",llnk);
                        startActivity(intent);
                        Toast.makeText(getActivity(),""+message,Toast.LENGTH_SHORT).show();
                    }
                    else  if (jsonObject1.getString("status_code").equals("0")){
                        Toast.makeText(mContext, ""+message, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e) {
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



//point service click to opne pop the next page.................................................................

    private class PointLOGIN_API extends AsyncTask<String, Void, String> {


        String email; String password;
        Dialog dialog;

        public PointLOGIN_API(String email,String password,Dialog dialog) {
            this.email = email;
            this.password = password;
            this.dialog = dialog;

        }


        @Override
        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://meem.one/reseller/UserApi/userLogin.php");
                JSONObject postDataParams = new JSONObject();
//                postDataParams.put("otp", Mobile_Number);
//                postDataParams.put("authToken","IzpFfdTN9U");
                postDataParams.put("authToken",AppPreference.getUserToken(getActivity()));
                postDataParams.put("email", email);
                postDataParams.put("password", password);

                Log.e("postDataParams", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /*milliseconds*/);
                conn.setConnectTimeout(15000 /*milliseconds*/);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
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
                dialog.dismiss();

                JSONObject jsonObject1 = null;
                Log.e("Point LOGIN API ", "-------------------------->>>>>>>>>>>>>>>>>>>>>>>>    "+result.toString());
                try {
                    jsonObject1 = new JSONObject(result);
                    String message = jsonObject1.getString("message");
                    String token = jsonObject1.getString("token");
                    if(jsonObject1.getString("status_code").equals("1")){
                        // startActivity(new Intent(getActivity(),Web_View.class));

                        AppPreference.set_PointService_Login(getActivity(),true);
                        AppPreference.setPointServiceToken(getActivity(),token);
                        dialog.dismiss();
                        fragment = new Pointsevice_Fragment();
                        fragmentmanager = getActivity().getSupportFragmentManager();
                        fragmentTransaction =fragmentmanager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_layout,fragment);
                        Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
                        fragmentTransaction.addToBackStack(fragment.getTag());
                        fragmentTransaction.commit();

                        Toast.makeText(mContext, ""+message, Toast.LENGTH_SHORT).show();
                    } else if (jsonObject1.getString("status_code").equals("1")&&jsonObject1.getString("status_code").equals("0"))
                    {
                        Toast.makeText(mContext, ""+message, Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(mContext, ""+message, Toast.LENGTH_SHORT).show();
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


    private void show_Matrimony_Login(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_muslim_loginpop);
        dialog.setTitle("Title...");

        // Session Manager
        // session = new Session_management(getActivity());

        final Button btn1 = (Button) dialog.findViewById(R.id.log_in);
        TextView btn2 = (TextView) dialog.findViewById(R.id.sing_up);
        TextView muslim_pass_forget = (TextView) dialog.findViewById(R.id.muslim_pass_forget);
        final EditText editText = (EditText) dialog.findViewById(R.id.username);
        final EditText editText1 = (EditText) dialog.findViewById(R.id.password);

        //Matrimony Login
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if (editText1.getText().toString().isEmpty() && editText.getText().toString().isEmpty())
                {
                    Toast.makeText(getActivity(), "Please Enter all Fields...", Toast.LENGTH_SHORT).show();
                }
                else if (editText.getText().toString().matches(""))
                {
                    Toast.makeText(getActivity(), "Please enter email adress...", Toast.LENGTH_SHORT).show();
                }
                else if (editText1.getText().toString().matches(""))
                {
                    Toast.makeText(getActivity(), "Please enter password...", Toast.LENGTH_SHORT).show();
                }
                else {
                    new LOGIN_API(editText.getText().toString(),editText1.getText().toString(),dialog).execute();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getActivity(),Web_View.class);
                String llnk = "https://www.meem.one/";
                intent.putExtra("link",llnk);
                startActivity(intent);
            }
        });

        //.........forget password  change <><><<<<<>>>>><<>>>>><>><><><>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        muslim_pass_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //>>>>>>>>>>>>>opne popup<<<<<<<<<<<<<<<<<
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_forget_password);
                dialog.setTitle("Title...");
                dialog.show();

                final Button password_send = (Button) dialog.findViewById(R.id.password_send);

                final EditText enter_email_id = (EditText) dialog.findViewById(R.id.enter_email_id);
                final  TextView forget_close = (TextView) dialog.findViewById(R.id.forget_close);

                password_send.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

        if (enter_email_id.getText().toString().isEmpty())
                {
                    Toast.makeText(getActivity(), "Please Enter valid Email id...", Toast.LENGTH_SHORT).show();

                }
                else if (enter_email_id.getText().toString().matches(""))
               {
                    Toast.makeText(getActivity(), "Please enter email adress...", Toast.LENGTH_SHORT).show();
                }

                else {
                      new FORGET_API(enter_email_id.getText().toString(),dialog).execute();
                }
                 }
                });
                Button password_login_page = (Button) dialog.findViewById(R.id.password_login_page);
             password_login_page.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     final Dialog dialog = new Dialog(getActivity());
                     dialog.setContentView(R.layout.custom_muslim_loginpop);
                     dialog.setTitle("Title...");
                     dialog.show();
                 }
             });

                forget_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        //image.setImageResource(R.drawable.ic_launcher);.................................................................
        TextView tet_text = (TextView) dialog.findViewById(R.id.tet_text);
        // if button is clicked, close the custom dialog
        tet_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
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
                        Toast.makeText(mContext, "New Data found", Toast.LENGTH_SHORT).show();
                        if(jsonObject.getInt("new_match") >0) {
                            Log.d("sender", "Broadcasting message");
                            Intent intent = new Intent("custom-event-name");
                            // You can also include some extra data.
                            intent.putExtra("message", "M");
                            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                        }else if(jsonObject.getInt("notifications") >0)
                        {
                            Log.d("sender", "Broadcasting message");
                            Intent intent = new Intent("custom-event-name");
                            // You can also include some extra data.
                            intent.putExtra("message", "N");
                            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
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
