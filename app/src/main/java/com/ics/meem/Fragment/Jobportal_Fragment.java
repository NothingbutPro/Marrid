package com.ics.meem.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.ics.meem.Adapter.Slider_adapter;
import com.ics.meem.Adapter.Slider_data;
import com.ics.meem.MainActivity;
import com.ics.meem.R;
import com.ics.meem.web.Web_View;
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

public class Jobportal_Fragment extends Fragment {
    private FragmentManager fragmentmanager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;

    List<Slider_data> dlist;
    SliderView sliderView;
    Slider_adapter slider_adapter;

    String Email;
    String Password;

    private ImageView images12,images13,images14,images15,ic_menu1,notification;
    private Button btn1,btn2,btn3;
    private Context mContext;
    private Resources mResources;
    private TextView sing_up,job_pass_forget1;
    TextView meemone2;
    private TextView tv_password, tv_email, btn_forgot;

ViewPager viewPager3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_jobportal,container,false);
        ic_menu1 = (ImageView) view.findViewById(R.id.ic_menu1);
/*
viewPager1 = (ViewPager) view.findViewById(R.id.viewPager1);
        Matrimony_Adapter viewPagerAdapter = new Matrimony_Adapter(getActivity());
        viewPager1.setAdapter(viewPagerAdapter);
 */mContext = getActivity();
        sliderView = (SliderView) view.findViewById(R.id.imageSlider_jobportsl);
/////////////drazer click
        ic_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).openDrawer();
            }
        });




        // TextView Call opne the web Site
        meemone2 = (TextView) view.findViewById(R.id.meemone2);
        meemone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getActivity(),Web_View.class);
                String llnk = "https://meem.one/";
                intent.putExtra("link",llnk);
                startActivity(intent);
            }
        });
        // first image click to opne web
        images12=(ImageView) view.findViewById(R.id.images12);

        images12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getActivity(),Web_View.class);
                String llnk = "https://meem.one/jobportal/";
                intent.putExtra("link",llnk);
                startActivity(intent);
            }
        });


        //Image click to use Popup use
        images13=(ImageView) view.findViewById(R.id.images13);
        btn1 = (Button) view.findViewById(R.id.log_in);
        sing_up =(TextView) view.findViewById(R.id.sing_up);
        job_pass_forget1= (TextView) view.findViewById(R.id.job_pass_forget1);


       images13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_jobmatch_loginpop);
                dialog.setTitle("Title...");

                final Button btn1 = (Button) dialog.findViewById(R.id.log_in);
                TextView btn2 = (TextView) dialog.findViewById(R.id.sing_up);
                final EditText editText = (EditText) dialog.findViewById(R.id.editText);
                final EditText editText1 = (EditText) dialog.findViewById(R.id.editText2);

                //login popup
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                           new JLOGIN_API (editText.getText().toString(),editText1.getText().toString(),dialog).execute();
                        }
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent  intent = new Intent(getActivity(),Web_View.class);
                        String llnk = "https://meem.one/jobportal/jobseeker.php";
                        intent.putExtra("link",llnk);
                        startActivity(intent);
                    }
                });
                //image.setImageResource(R.drawable.ic_launcher);
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
        });


//Notification Click Popup

        images14=(ImageView) view.findViewById(R.id.images14);
        images14.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_jobnotification_loginpop);
                dialog.setTitle("Title...");


                final Button btn1 = (Button) dialog.findViewById(R.id.log_in);
                TextView btn2 = (TextView) dialog.findViewById(R.id.sing_up);
                final EditText editText = (EditText) dialog.findViewById(R.id.editText);
                final EditText editText1 = (EditText) dialog.findViewById(R.id.editText2);

                //Matrimony Login

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText1.getText().toString().isEmpty() && editText.getText().toString().isEmpty()) {
                            Toast.makeText(getActivity(), "Please Enter all Fields...", Toast.LENGTH_SHORT).show();
                        } else if (editText.getText().toString().matches("")) {
                            Toast.makeText(getActivity(), "Please enter email adress...", Toast.LENGTH_SHORT).show();
                        } else if (editText1.getText().toString().matches("")) {
                            Toast.makeText(getActivity(), "Please enter password...", Toast.LENGTH_SHORT).show();
                        }
                        else {
                           // new JLOGIN_API(editText.getText().toString(),editText1.getText().toString()).execute();
                        }
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent  intent = new Intent(getActivity(),Web_View.class);
                        String llnk = "https://meem.one/jobportal/jobseeker.php";
                        intent.putExtra("link",llnk);
                        startActivity(intent);
                    }
                });
                //image.setImageResource(R.drawable.ic_launcher);
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
        });
// Share my profile click popup opne login
        images15=(ImageView) view.findViewById(R.id.images15);
        images15.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_jobshare_loginpop);
                dialog.setTitle("Title...");


                final Button btn1 = (Button) dialog.findViewById(R.id.log_in);
                TextView btn2 = (TextView) dialog.findViewById(R.id.sing_up);
                final EditText editText = (EditText) dialog.findViewById(R.id.editText);
                final EditText editText1 = (EditText) dialog.findViewById(R.id.editText2);

                //Matrimony Login

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText1.getText().toString().isEmpty() && editText.getText().toString().isEmpty()) {
                            Toast.makeText(getActivity(), "Please Enter all Fields...", Toast.LENGTH_SHORT).show();
                        } else if (editText.getText().toString().matches("")) {
                            Toast.makeText(getActivity(), "Please enter email adress...", Toast.LENGTH_SHORT).show();
                        } else if (editText1.getText().toString().matches("")) {
                            Toast.makeText(getActivity(), "Please enter password...", Toast.LENGTH_SHORT).show();
                        }
                        else {
                           // Intent intent = new Intent(getActivity(),Web_View.class);
                            //new JLOGIN_API(editText.getText().toString(),editText1.getText().toString()).execute();
                        }
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent  intent = new Intent(getActivity(),Web_View.class);
                        String llnk = "https://meem.one/jobportal/jobseeker.php";
                        intent.putExtra("link",llnk);
                        startActivity(intent);
                    }
                });
                //image.setImageResource(R.drawable.ic_launcher);
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
        });


        notification = (ImageView) view.findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notification_intent = new Intent(getActivity(),Web_View.class);
                Toast.makeText(getActivity(),"Opne Notification",Toast.LENGTH_SHORT).show();
                startActivity(notification_intent);
            }
        });


        init_Slider();
        return view;
    }
    private void init_Slider(){

        dlist = new ArrayList<>();
        dlist.add(new Slider_data(R.drawable.job_slider1,"First Image"));
        dlist.add(new Slider_data(R.drawable.job_slider2,"Second Image"));

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
        });

    }

    private class JLOGIN_API extends AsyncTask<String, Void, String> {
        String email; String password;
        Dialog dialog;
        public JLOGIN_API(String email,String password,Dialog dialog) {
            this.email = email;
            this.password = password;
            this.dialog = dialog;

        }

        @Override
        protected String doInBackground(String... arg0) {
            try {
                URL url = new URL("https://meem.one/jobportal/UserApi/userLogin.php");
                JSONObject postDataParams = new JSONObject();
//                postDataParams.put("otp", Mobile_Number);
                postDataParams.put("authToken","IzpFfdTN9U");
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
            JSONObject jsonObject2 =null;
            Log.e("JOB LOGIN API ", "------- >>>>>>>>>>>>>>>>>>>>>   "+result.toString());
                try {
                    jsonObject2 = new JSONObject(result);
                    String message = jsonObject2.getString("message");
                    String token = jsonObject2.getString("token");


                    if (jsonObject2.getString("status_code").equals("1"))
 {
                        // startActivity(new Intent(getActivity(),Web_View.class));
                        dialog.dismiss();
                        Bundle bn = new Bundle();
                          Intent  intent = new Intent(getActivity(),Web_View.class);
                         String llnk = "https://meem.one/jobportal/matching-jobs.php?token="+token;
                            Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
                         intent.putExtra("link",llnk);
                         startActivity(intent);

                        Toast.makeText(mContext, ""+message, Toast.LENGTH_SHORT).show();
                    }else {
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


}
