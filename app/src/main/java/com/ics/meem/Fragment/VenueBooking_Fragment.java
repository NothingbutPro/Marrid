package com.ics.meem.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

public class VenueBooking_Fragment extends Fragment {


    ViewPager viewPager5;
    List<Slider_data> dlist;
    SliderView sliderView;
    Slider_adapter slider_adapter;
    private Context mContext;
    private ImageView tx_images02,tx_images03,tx_images01,tx_images04,ic_menu3,notification;
    private Button btn1,btn2;
TextView meem3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_venuebooking,container,false);
        sliderView = (SliderView) view.findViewById(R.id.imageSlider_venue);

        //Home_Adapter viewPagerAdapter = new VenueBooking_Fragment(getActivity());
       // Venue_Adapter viewPagerAdapter = new Venue_Adapter(getActivity());
       //viwPager5.setAdapter(viewPagerAdapter);

        mContext = getActivity();
/// return view;
        ic_menu3 = (ImageView) view.findViewById(R.id.ic_menu3);
        // call drazer navigation
        ic_menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).openDrawer();
            }
        });
//First image click to opne web page
       tx_images01= (ImageView) view.findViewById(R.id.tx_images01);
       tx_images01.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent  intent = new Intent(getActivity(),Web_View.class);
               String llnk = "https://meem.one/venue/";
               intent.putExtra("link",llnk);
               startActivity(intent);
           }
       });
       // TextView Call opne the web Site
        meem3 = (TextView) view.findViewById(R.id.meem3);
        meem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getActivity(),Web_View.class);
                String llnk = "https://meem.one/";
                intent.putExtra("link",llnk);
                startActivity(intent);
            }
        });


//click to opne popup
            tx_images02 = (ImageView) view.findViewById(R.id.tx_images02);
        tx_images02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_venuebook_loginpop);
                dialog.setTitle("Title...");

                final Button btn1 = (Button) dialog.findViewById(R.id.log_in);
                TextView btn2 = (TextView) dialog.findViewById(R.id.sing_up);
                final EditText editText = (EditText) dialog.findViewById(R.id.editText);
                final EditText editText1 = (EditText) dialog.findViewById(R.id.editText2);

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
                            new PLOGIN_API(editText.getText().toString(),editText1.getText().toString(),dialog).execute();  }
                       // String url=btn1.getText().toString();
                        //Intent intent= new Intent(getActivity(), Web_View.class);
                       // startActivity(intent);
                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent  intent = new Intent(getActivity(),Web_View.class);
                        String llnk = "https://www.meem.one/venue/";
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
//Click to opne notification popup
        tx_images03 = (ImageView) view.findViewById(R.id.tx_images03);
        tx_images03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_venuenotificantion_loginpop);
                dialog.setTitle("Title...");

                final Button btn1 = (Button) dialog.findViewById(R.id.log_in);
                TextView btn2 = (TextView) dialog.findViewById(R.id.sing_up);
                final EditText editText = (EditText) dialog.findViewById(R.id.editText);
                final EditText editText1 = (EditText) dialog.findViewById(R.id.editText2);

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
                           // new LOGIN_API(editText.getText().toString(),editText1.getText().toString()).execute();
                        }
                       // String url=btn1.getText().toString();
                        //Intent intent= new Intent(getActivity(), Web_View.class);
                        //startActivity(intent);
                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent  intent = new Intent(getActivity(),Web_View.class);
                        String llnk = "https://www.meem.one/venue/";
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
        // click to account setting popup login
        tx_images04 = (ImageView) view.findViewById(R.id.tx_images04);
        tx_images04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_venueaccount_loginpop);
                dialog.setTitle("Title...");

                final Button btn1 = (Button) dialog.findViewById(R.id.log_in);
                TextView btn2 = (TextView) dialog.findViewById(R.id.sing_up);
                final EditText editText = (EditText) dialog.findViewById(R.id.editText);
                final EditText editText1 = (EditText) dialog.findViewById(R.id.editText2);

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
                         //   new VenueBooking_Fragment.LOGIN_API(editText.getText().toString(),editText1.getText().toString()).execute();
                        }
                        // please read use code 3 line in next page opne.................................................................
                       // String url=btn1.getText().toString();
                        //Intent intent= new Intent(getActivity(), Web_View.class);
                        //startActivity(intent);
                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent  intent = new Intent(getActivity(),Web_View.class);
                        String llnk = "https://www.meem.one/venue/";
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

//Notification bail
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
        dlist.add(new Slider_data(R.drawable.venue2,"First Image"));
        dlist.add(new Slider_data(R.drawable.venue1,"Second Image"));

        slider_adapter = new Slider_adapter(getActivity(),dlist);
        slider_adapter.setCount(dlist.size());

        sliderView.isAutoCycle();
        sliderView.setAutoCycle(true);
        sliderView.setScrollTimeInSec(2);
        sliderView.setSliderAdapter(slider_adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.GREEN);
        sliderView.setIndicatorUnselectedColor(Color.WHITE);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });

    }


    private class PLOGIN_API extends AsyncTask<String, Void, String> {


        String mobile; String password;
        Dialog dialog;

        public PLOGIN_API(String mobile,String password,Dialog dialog) {

            this.password = password;
            this.dialog = dialog;
            this.mobile = mobile;

        }


        @Override
        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("http://meem.one/venue/api/userLogin.php");
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("mobile", "8686151489");
                postDataParams.put("password", "School7007");
                postDataParams.put("authToken","IzpFfdTN9U");


                Log.e("postDataParams", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(150000 /*milliseconds*/);
                conn.setConnectTimeout(150000 /*milliseconds*/);
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

                Log.e("JOB LOGIN API ", "------- >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   "+result.toString());
                Log.d("JOB LOGIN API ", "------- >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   "+result.toString());
                try {
                    JSONObject jsonObject2 = new JSONObject(result);
                    /*String message = jsonObject2.getString("message");
                    String token = jsonObject2.getString("authToken");*/



                    /*if (jsonObject2.getString("status_code").trim().equals("1"))
                    {
                        // startActivity(new Intent(getActivity(),Web_View.class));
                        dialog.dismiss();
                        Bundle bn = new Bundle();
                        Intent  intent = new Intent(getActivity(),Web_View.class);
                        String llnk = "http://meem.one/venue/api/userLogin.php?token="+"IzpFfdTN9U";
                        Toast.makeText(getActivity(),"",Toast.LENGTH_SHORT).show();
                        intent.putExtra("link",llnk);
                        startActivity(intent);

                        Toast.makeText(mContext, ""+jsonObject2.getString("message"), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(mContext, ""+jsonObject2.getString("message"), Toast.LENGTH_SHORT).show();
                    }*/
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
