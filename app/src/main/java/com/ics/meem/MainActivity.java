package com.ics.meem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/*
import com.ics.meem.BNaigation.Whatsapp;
import com.ics.meem.BNaigation.call;
import com.ics.meem.BNaigation.share;    */
import com.ics.meem.Fragment.Home_Fragment;
import com.ics.meem.Fragment.Jobportal_Fragment;
import com.ics.meem.Fragment.Matrimony_Fragment;
import com.ics.meem.Fragment.Pointsevice_Fragment;
import com.ics.meem.Fragment.VenueBooking_Fragment;
import com.ics.meem.model.AppPreference;
import com.ics.meem.web.Web_View;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private BottomNavigationView bottom_nav1;
    ImageView images12,images13,images14,images15,ic_menu,notification;


    private FragmentManager fragmentmanager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;

    final Context context = this;

    ViewPager viewPager;
    DrawerLayout drawer;
    NavigationView nav_view;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__drawer);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav_view = (NavigationView) findViewById(R.id.nav_view);

        fragment = new Home_Fragment();
        fragmentmanager = getSupportFragmentManager();
        fragmentTransaction =fragmentmanager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout,fragment);
        fragmentTransaction.commit();


        images12 = findViewById(R.id.images12);
        images13 = findViewById(R.id.images13);
        images14 = findViewById(R.id.images14);
        images15 = findViewById(R.id.images15);

        toggle = new ActionBarDrawerToggle(MainActivity.this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        toggle = new ActionBarDrawerToggle(this, drawer,  R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };
        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        bottom_nav1 = findViewById(R.id.bottom_nav);
        bottom_nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.call:
                        // custom dialog
                        final Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.custom_list_number);
                        dialog.setTitle("Title...");
                        final TextView call1 = (TextView) dialog.findViewById(R.id.call1);
                        final TextView call2 = (TextView) dialog.findViewById(R.id.call2);
                        final TextView call3 = (TextView) dialog.findViewById(R.id.call3);
                        // Close the pop Click
                        Button tet_text = (Button) dialog.findViewById(R.id.tet_text);
                        //first call conected
                        call1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                make_call(call1.getText().toString());
                            }
                        });
                        //second call conected
                        call2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                make_call(call2.getText().toString());
                            }
                        });
                        //Thrid call conected
                        call3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                make_call(call3.getText().toString());
                            }
                        });

                        // if button is clicked, close the custom dialog
                        tet_text.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        // call conected

                        break;
                        case R.id.share:
                        shareApp();
                        break;

                    case R.id.home:
                        Intent intent4 = new Intent(MainActivity.this, MainActivity.class);
                        Toast.makeText(MainActivity.this, "Opne to Home", Toast.LENGTH_SHORT).show();
                        startActivity(intent4);
                        break;

                        case R.id.Whatsapp:
                            final Dialog dialog1 = new Dialog(context);
                            dialog1.setContentView(R.layout.custom_list_number);
                            dialog1.setTitle("Title...");
                            TextView call11 = (TextView) dialog1.findViewById(R.id.call1);
                             TextView call21 = (TextView) dialog1.findViewById(R.id.call2);
                            TextView call31 = (TextView) dialog1.findViewById(R.id.call3);


                        Button tet_text1 = (Button) dialog1.findViewById(R.id.tet_text);
                        // if button is clicked, close the custom dialog
                        tet_text1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog1.dismiss();
                            }
                        });
                        dialog1.show();
                //Watsapp Massage
                            call11.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    opneWhatsapp();
                                }
                            });


                            // Second number message whatsapp
                            call21.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    PackageManager pm = MainActivity.this.getPackageManager();
                                    try {
                                        String headerReceiver = "";// Replace with your message.
                                        String bodyMessageFormal = "";// Replace with your message.
                                        String whatsappContain = headerReceiver + bodyMessageFormal;
                                        String trimToNumner = "+917731037526"; //10 digit number
                                        Intent intent = new Intent ( Intent.ACTION_VIEW );
                                        intent.setData ( Uri.parse ( "https://wa.me/" + trimToNumner + "/?text=" + "" ) );
                                        startActivity ( intent );
                                    } catch (Exception e) {
                                        e.printStackTrace ();
                                    }
                                }
                            });
                            //Thrid Number Whatsapp Masage
                            call31.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    PackageManager pm = MainActivity.this.getPackageManager();
                                    try {
                                        String headerReceiver = "";// Replace with your message.
                                        String bodyMessageFormal = "";// Replace with your message.
                                        String whatsappContain = headerReceiver + bodyMessageFormal;
                                        String trimToNumner = "+916309403704"; //10 digit number
                                        Intent intent = new Intent ( Intent.ACTION_VIEW );
                                        intent.setData ( Uri.parse ( "https://wa.me/" + trimToNumner + "/?text=" + "" ) );
                                        startActivity ( intent );
                                    } catch (Exception e) {
                                        e.printStackTrace ();
                                    }
                                }
                            });

                            break;
                }
                return false;
            }
        });
    }

    private void make_call(String number){
      try {
          Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +number));
          startActivity(intent);}
      catch (Exception ex)
      {   ex.printStackTrace();
          Toast.makeText(MainActivity.this, "Error No Sim Card Found......", Toast.LENGTH_SHORT).show();
      }

  }
///share icon click........................................................................................................................................
    public void shareApp() {

        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage= "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
             }
        catch(Exception e)
        {
            //e.toString();
        }
    }

private  void opneWhatsapp()
{
    String number = "918686151489";

        try {
            String headerReceiver = "";// Replace with your message.
            String bodyMessageFormal = "";// Replace with your message.
            String whatsappContain = headerReceiver + bodyMessageFormal;
            String trimToNumner = "+918686151489"; //10 digit number
           Intent intent = new Intent ( Intent.ACTION_VIEW );
            intent.setData ( Uri.parse ( "https://wa.me/" + trimToNumner + "/?text=" + "" ) );
            startActivity ( intent );
        } catch (Exception e) {
            e.printStackTrace();
        }
}
//Drawer call item in all fragment....................................................................................................................
    public void openDrawer()
    {
        drawer.openDrawer(Gravity.RIGHT);
    }

    public void closeDrawer()
    {
        drawer.closeDrawers();
    }

   @Override
    public void onBackPressed() {

       if (drawer.isDrawerOpen(GravityCompat.START)) {
           drawer.closeDrawer(GravityCompat.START);
       } else {
           super.onBackPressed();
           closeDrawer();
       }
   }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        android.app.Fragment fm = null;
        Bundle args = new Bundle();

        if (id == R.id.nav_aboutus) {
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_layout);
            if(f instanceof Home_Fragment)
            {
                Log.e(" Current Fragment is "," --------- Home_Fragment");
                String llnk = "https://meem.one/aboutus.php";
                Call_AboutUs(llnk);
            }
            else if (f instanceof Matrimony_Fragment){
                Log.e(" Current Fragment is "," --------- Matrimony_Fragment");
                String link1 = "https://meem.one/aboutus.php";
                Call_AboutUs(link1);
            }
            else if (f instanceof Jobportal_Fragment){
                Log.e(" Current Fragment is "," --------- Jobportal_Fragment");
                String link1 = "https://meem.one/jobportal/aboutus.php";
                Call_AboutUs(link1);
            }
            else if (f instanceof VenueBooking_Fragment){
                Log.e(" Current Fragment is "," --------- VenueBooking_Fragment");
                String link1 = "https://meem.one/venue/aboutus.php";
                Call_AboutUs(link1);
            }
            else {
                Log.e(" Current Fragment is "," --------- Point of Service");
                String link1 = "https://meem.one/reseller/aboutus.html";
                Call_AboutUs(link1);
            }
        }
        if (id == R.id.nav_fqa)
        {
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_layout);
            if(f instanceof Home_Fragment)
            {
                Log.e(" Current Fragment is "," --------- Home_Fragment");
                String llnk = "https://meem.one/faq.php";
                Call_fqa(llnk);
            }
            else if (f instanceof Matrimony_Fragment){
                Log.e(" Current Fragment is "," --------- Matrimony_Fragment");
                String link1 = "https://meem.one/faq.php";
                Call_fqa(link1);
            }
            else if (f instanceof Jobportal_Fragment){
                Log.e(" Current Fragment is "," --------- Jobportal_Fragment");
                String link1 = "";
                Call_fqa(link1);
            }
            else if (f instanceof VenueBooking_Fragment){
                Log.e(" Current Fragment is "," --------- VenueBooking_Fragment");
                String link1 = "";
                Call_fqa(link1);
            }
            else {
                Log.e(" Current Fragment is "," --------- Pointservice_Fragment");
                String link1 = "https://meem.one/reseller/faq.html";
                Call_fqa(link1);
            }
        }
        if (id == R.id.nav_contacts)
        {
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_layout);
            if(f instanceof Home_Fragment)
            {
                Log.e(" Current Fragment is "," --------- Home_Fragment");
                String llnk = "https://meem.one/contactus.php";
                Call_contacts(llnk);
            }
            else if (f instanceof Matrimony_Fragment){
                Log.e(" Current Fragment is "," --------- Matrimony_Fragment");
                String link1 = "https://www.meem.one/contactus.php";
                Call_contacts(link1);
            }
            else if (f instanceof Jobportal_Fragment){
                Log.e(" Current Fragment is "," --------- Jobportal_Fragment");
                String link1 = "https://meem.one/jobportal/contactus.php";
                Call_contacts(link1);
            }
            else if (f instanceof VenueBooking_Fragment){
                Log.e(" Current Fragment is "," --------- VenueBooking_Fragment");
                String link1 = "https://www.meem.one/venue/contactus.php";
                Call_contacts(link1);
            }
            else {
                Log.e(" Current Fragment is "," --------- Pointservice_Fragment");
                String link1 = "https://meem.one/contactus.php";
                Call_contacts(link1);
            }
        }
        if (id == R.id.nav_user)
        {
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_layout);
            if(f instanceof Home_Fragment)
            {
                Log.e(" Current Fragment is "," --------- Home_Fragment");
                String llnk = "";
                Call_user(llnk);
            }
            else if (f instanceof Matrimony_Fragment){

                Log.e(" Current Fragment is "," --------- Matrimony_Fragment");
                String link1 = "https://meem.one/myaccount.php";;
                Call_user(link1);
            }
            else if (f instanceof Jobportal_Fragment){
                Log.e(" Current Fragment is "," --------- Jobportal_Fragment");
                String link1 = "";
                Call_user(link1);
            }
            else if (f instanceof VenueBooking_Fragment){
                Log.e(" Current Fragment is "," --------- VenueBooking_Fragment");
                String link1 = "";
                Call_user(link1);
            }
            else {
                Log.e(" Current Fragment is "," --------- Pointservice_Fragment");
                String link1 = "https://meem.one/myaccount.php";
                Call_user(link1);
            }
        }
       if (id == R.id.nav_regitration)
        {  Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_layout);
            if(f instanceof Home_Fragment)
            {
                Log.e(" Current Fragment is "," --------- Home_Fragment");
                String llnk = "https://www.meem.one/";
                Call_regitration(llnk);
            }
            else if (f instanceof Matrimony_Fragment){
                Log.e(" Current Fragment is "," --------- Matrimony_Fragment");
                String link1 = "https://www.meem.one/";
                Call_regitration(link1);
            }
            else if (f instanceof Jobportal_Fragment){
                Log.e(" Current Fragment is "," --------- Jobportal_Fragment");
                String link1 = "https://meem.one/jobportal/jobseeker.php";
                Call_regitration(link1);
            }
            else if (f instanceof VenueBooking_Fragment){
                Log.e(" Current Fragment is "," --------- VenueBooking_Fragment");
                String link1 = "https://www.meem.one/venue/";
                Call_regitration(link1);
            }
            else {
                Log.e(" Current Fragment is "," --------- Pointservice_Fragment");
                String link1 = "https://www.meem.one/reseller/";
                Call_regitration(link1);
            }
        }
        if (id == R.id.nav_logoutnn) {
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_layout);
            if (f instanceof Matrimony_Fragment){
                Log.e(" Current Fragment is "," >>>>>>  LOGGED OUT OF-------- Matrimony_Fragment");
                AppPreference.set_Matrimony_Login(MainActivity.this,false);
                AppPreference.setEmail(MainActivity.this , "X");
//                AppPreference.set(MainActivity.this , "X");
                Call_MainActivity();
            }
            else if (f instanceof Pointsevice_Fragment){
                Log.e(" Current Fragment is "," >>>>>>  LOGGED OUT OF-------- Point Service _ Fragment");
                AppPreference.set_PointService_Login(MainActivity.this,false);
                AppPreference.setEmail(MainActivity.this , "X");
                Call_MainActivity();
            }
            else{

            }
        }
        return true;
    }

    private void Call_AboutUs(String link){
        Intent log = new Intent(MainActivity.this, Web_View.class);
        log.putExtra("link",link);
        Toast.makeText(this,"Please wait... ",Toast.LENGTH_SHORT).show();
        startActivity(log);
    }
    private void Call_fqa(String link){
        Intent log = new Intent(MainActivity.this, Web_View.class);
        log.putExtra("link",link);
        Toast.makeText(this,"Please wait... ",Toast.LENGTH_SHORT).show();
        startActivity(log);
    }
    private void Call_regitration(String link){
        Intent log = new Intent(MainActivity.this, Web_View.class);
        log.putExtra("link",link);
        Toast.makeText(this,"Please wait... ",Toast.LENGTH_SHORT).show();
        startActivity(log);
    }
    private void Call_contacts(String link){
        Intent log = new Intent(MainActivity.this, Web_View.class);
        log.putExtra("link",link);
        Toast.makeText(this,"Please wait... ",Toast.LENGTH_SHORT).show();
        startActivity(log);
    }
    private void Call_user(String link){
        Intent log = new Intent(MainActivity.this, Web_View.class);
        log.putExtra("link",link);
        Toast.makeText(this,"Please wait... ",Toast.LENGTH_SHORT).show();
        startActivity(log);
    }

    public void logout_off()
    {
        nav_view.getMenu().findItem(R.id.nav_logoutnn).setVisible(false);
    }
    public void logout_onn()
    {
        nav_view.getMenu().findItem(R.id.nav_logoutnn).setVisible(true);
    }
    public void user_off()
    {

        nav_view.getMenu().findItem(R.id.nav_user).setVisible(false);
    }
    public void user_onn()
    {
        nav_view.getMenu().findItem(R.id.nav_user).setVisible(true);
    }

    private void Call_MainActivity(){
        Intent in = new Intent(MainActivity.this,MainActivity.class);
        startActivity(in);
        finish();
    }
//    private class LOGOUT_API extends AsyncTask<String, Void, String> {
//        String userid; String password; String device_id;
//        public LOGOUT_API(String email,String password,Dialog dialog) {
//            this.userid  = userid;
//            this.password = password;
//            this.device_id = device_id;
//
//
//        }
//        @Override
//        protected String doInBackground(String... arg0) {
//
//            try {
//
//                URL url = new URL("https://meem.one/UserApi/userLogin.php");
//                JSONObject postDataParams = new JSONObject();
////                postDataParams.put("otp", Mobile_Number);
//                postDataParams.put("authToken","IzpFfdTN9U");
//                postDataParams.put("email", "");
//                postDataParams.put("password", password);
//                postDataParams.put("device_id", AppPreference.getUserToken(MainActivity.this));
//
//                Log.e("postDataParams", postDataParams.toString());
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(15000 /*milliseconds*/);
//                conn.setConnectTimeout(15000 /*milliseconds*/);
//                conn.setRequestMethod("POST");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//                writer.write(getPostDataString(postDataParams));
//
//                writer.flush();
//                writer.close();
//                os.close();
//
//                int responseCode = conn.getResponseCode();
//
//                if (responseCode == HttpsURLConnection.HTTP_OK) {
//
//                    BufferedReader in = new BufferedReader(new
//                            InputStreamReader(
//                            conn.getInputStream()));
//
//                    StringBuffer sb = new StringBuffer("");
//                    String line = "";
//
//                    while ((line = in.readLine()) != null) {
//
//                        StringBuffer Ss = sb.append(line);
//                        Log.e("Ss", Ss.toString());
//                        sb.append(line);
//                        break;
//                    }
//
//                    in.close();
//                    return sb.toString();
//
//                } else {
//                    return new String("false : " + responseCode);
//                }
//            } catch (Exception e) {
//                return new String("Exception: " + e.getMessage());
//            }
//
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            if (result != null) {
//                dialog.dismiss();
//
//                JSONObject jsonObject1 = null;
//                Log.e("LOGIN API ", "-------    "+result.toString());
//                try {
//                    jsonObject1 = new JSONObject(result);
//                    String message = jsonObject1.getString("message");
//                    String token = jsonObject1.getString("token");
//                    String Action = jsonObject1.getString("Action");
//
//                    if(jsonObject1.getString("status_code").equals("1"))
//                    {
//
//                        AppPreference.setUser_Id(getActivity(),token);
//                        AppPreference.setMatrimonyToken(getActivity(),token);
//                        AppPreference.setMatrimonyMail(getActivity(),email);
//                        AppPreference.set_Matrimony_Login(getActivity(),true);
//                        // startActivity(new Intent(getActivity(),Web_View.class));
//                        dialog.dismiss();
//                        // Bundle bn = new Bundle();
//                        fragment = new Matrimony_Fragment();
//                        //   bn.putString("token",token);
//                        //  bn.putString("email",email);
//
//                        fragmentmanager = getActivity().getSupportFragmentManager();
//                        // fragment.setArguments(bn);
//                        fragmentTransaction =fragmentmanager.beginTransaction();
//                        fragmentTransaction.replace(R.id.fragment_layout,fragment);
//                        //Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
//                        //fragmentTransaction.addToBackStack(fragment.getTag());
//                        fragmentTransaction.commit();
//                        Toast.makeText(mContext, ""+message, Toast.LENGTH_SHORT).show();
//                    }
//                    else if (jsonObject1.getString("Action").equals("")&&jsonObject1.getString("status_code").equals("0"))
//                    {
//                        Toast.makeText(mContext, ""+message, Toast.LENGTH_SHORT).show();
//                    }
//
//                    else if (jsonObject1.getString("Action").equals("Renew"))
//                    // else if(jsonObject1.getString("status_code").equals("0"))
//                    {
//                        Bundle bn = new Bundle();
//                        Intent  intent = new Intent(getActivity(),Web_View.class);
//                        String llnk = "https://meem.one/Membership-Renewal.php?token="+token;
//                        Log.e("Link Received ",">>>>>>   ------  "+llnk);
//
//                        Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
//                        intent.putExtra("link",llnk);
//                        startActivity(intent);
//                        Toast.makeText(mContext, ""+message, Toast.LENGTH_SHORT).show();
//                    }
//
//                    else if (jsonObject1.getString("status_code").equals("1"))
//                    {
//                        Bundle bn = new Bundle();
//                        Intent  intent = new Intent(getActivity(),Web_View.class);
//                        String llnk = "https://meem.one/myprofile.php?token="+token;
//                        Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
//                        intent.putExtra("link",llnk);
//                        startActivity(intent);
//                        Toast.makeText(mContext, ""+message, Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//
//                    }
//                }
//
//
//
//                catch (JSONException e) {
//
//                    e.printStackTrace();
//                }
//
//            }
//        }
//
//        public String getPostDataString(JSONObject params) throws Exception {
//
//            StringBuilder result = new StringBuilder();
//            boolean first = true;
//
//            Iterator<String> itr = params.keys();
//
//            while (itr.hasNext()) {
//
//                String key = itr.next();
//                Object value = params.get(key);
//
//                if (first)
//                    first = false;
//                else
//                    result.append("&");
//
//                result.append(URLEncoder.encode(key, "UTF-8"));
//                result.append("=");
//                result.append(URLEncoder.encode(value.toString(), "UTF-8"));
//
//            }
//            return result.toString();
//        }
//    }
}