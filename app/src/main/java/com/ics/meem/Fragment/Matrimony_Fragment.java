package com.ics.meem.Fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class Matrimony_Fragment extends Fragment {
    ImageView images1, images2, images3, images4, notification;
    private String e_mail;
    List<Slider_data> dlist;
    SliderView sliderView;
    Slider_adapter slider_adapter;
    private ImageView ic_menu2;
    //+++++++++++++++++++++++++++++++++++++++++++For hide and show++++++++++++++++++++++
    LinearLayout matri_li_id,interrest_li;
    //+++++++++++++++++++++++++++++++++++++Hide and Show of Dots+++++++++++++++++++++++++
    ImageView matri_dot ,noti_dot;
    public static ImageView interest_dot;
    //+++++++++++++++++++++++++++++++++++++++++++++++++
    TextView meemweb1;
    private Context mContext;
    private String token;
    Button business_whatsapp;
    Button normail_whatsapp;
    // Our handler for received Intents. This will be called whenever an Intent
// with an action named "custom-event-name" is broadcasted.
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("message");
            Log.d("receiver", "Got message: " + message);
            if(message.equals("M"))
            {
                matri_dot.setVisibility(View.VISIBLE);
            }
            if(message.equals("N")) {
                noti_dot.setVisibility(View.VISIBLE);
            }
//            if(message.equals("I")) {
//                interest_dot.setVisibility(View.VISIBLE);
//            }
        }
    };

    @Override
    public void onResume() {
        if(!AppPreference.getEmail(getActivity()).equals("M")) {
            matri_li_id.setEnabled(false);
            interrest_li.setEnabled(false);
        }else {
            AppPreference.setEmail(getActivity(), "M");
        }
        super.onResume();
    }

    @Override
    public boolean getUserVisibleHint() {
        if(!AppPreference.getEmail(getActivity()).equals("M")) {
            matri_li_id.setEnabled(false);
            interrest_li.setEnabled(false);
        }else {
            AppPreference.setEmail(getActivity(), "M");
        }
        return super.getUserVisibleHint();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.frament_matrimony, container, false);
        matri_li_id = view.findViewById(R.id.matri_li_id);
        interrest_li = view.findViewById(R.id.interest_li);
        matri_dot = view.findViewById(R.id.matri_dot);
        interest_dot = view.findViewById(R.id.interest_dot);
        noti_dot = view.findViewById(R.id.noti_dot);
        //++++++++++++++++++++++++++++++++Recive Red Dot++++++++++++++++++++++++++++++++++++++
        // Register to receive messages.
        // We are registering an observer (mMessageReceiver) to receive Intents
        // with actions named "custom-event-name".
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-event-name"));
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //logut option is visible  onnnnnn.........................>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        ((MainActivity)getActivity()).user_onn();
        ((MainActivity)getActivity()).logout_onn();
        //++++++++++++++++++++++++++++++++++++++++++++++++Fragment remember+++++++++++++++++++++++++++++++++++
        if(!AppPreference.getEmail(getActivity()).equals("M")) {
            matri_li_id.setEnabled(false);
            interrest_li.setEnabled(false);
        }else {
            AppPreference.setEmail(getActivity(), "M");
        }
        //+++++++++++++++++++++++++++++++++++
        e_mail = AppPreference.getMatrimonyMail(getActivity());
        token = AppPreference.getMatrimonyToken(getActivity());
//        if(llnk)
        Log.d("Token received ",">>>>>>>>>>>>>>> ------------------------    "+token);
        sliderView = (SliderView) view.findViewById(R.id.imageSlider_matrimony);
        // Home_Adapter viewPagerAdapter = new Home_Adapter(getActivity());

        mContext = getActivity();
        ic_menu2 = (ImageView) view.findViewById(R.id.ic_menu2);
        // Call drazer Navigation
        ic_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openDrawer();
            }
        });

        meemweb1 = (TextView) view.findViewById(R.id.meemweb1);
        meemweb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getActivity(),Web_View.class);
                String llnk = "https://meem.one/";
                intent.putExtra("link",llnk);
                startActivity(intent);
            }
        });

        images1 = view.findViewById(R.id.tx_images11);
        images2 = view.findViewById(R.id.tx_images22);
        images3 = view.findViewById(R.id.tx_images33);
        images4 = view.findViewById(R.id.tx_images44);

        images1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!AppPreference.getEmail(getActivity()).equals("M")) {
                    AppPreference.setEmail(getActivity(), "S");
//                    matri_li_id.setEnabled(false);
//                    interrest_li.setEnabled(false);
//                    Toast.makeText(mContext, "Sorry you need paid subscription", Toast.LENGTH_SHORT).show();
//                    AppPreference.setEmail(getActivity(), "M");
                    Intent  intent = new Intent(getActivity(),Web_View.class);
                    String llnk = "https://meem.one/myprofile.php?option=share_safe";
                    intent.putExtra("link",llnk);
                    startActivity(intent);

                }else {
                    AppPreference.setEmail(getActivity(), "M");
                    Intent  intent = new Intent(getActivity(),Web_View.class);
                    String llnk = "https://meem.one/search.php?token="+token;
                    intent.putExtra("link",llnk);
                    startActivity(intent);
                }

            }
        });
        images2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!AppPreference.getEmail(getActivity()).equals("M")) {
                    AppPreference.setEmail(getActivity(), "S");
//                    matri_li_id.setEnabled(false);
//                    interrest_li.setEnabled(false);
                    Intent  intent = new Intent(getActivity(),Web_View.class);
                    String llnk = "https://meem.one/myprofile.php?option=share_safe";
                    intent.putExtra("link",llnk);
                    startActivity(intent);
//                    Toast.makeText(mContext, "Sorry you need paid subscription", Toast.LENGTH_SHORT).show();
                }else {
                    AppPreference.setEmail(getActivity(), "M");
                    Intent  intent = new Intent(getActivity(),Web_View.class);
                    String llnk = "https://meem.one/search.php?view=interested_profiles&token="+token;
                    intent.putExtra("link",llnk);
                    startActivity(intent);
                }

            }
        });
        images3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getActivity(),Web_View.class);
                String llnk = "https://meem.one/inbox.php?token="+token;
                intent.putExtra("link",llnk);
                startActivity(intent);
            }
        });
                         images4.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View v) {
                                           final Dialog dialog = new Dialog(getActivity());
                                           dialog.setContentView(R.layout.custom_muslimshare_pop);
                                           dialog.setTitle("Title...");
                                           dialog.show();

                                           final CheckBox checkb1 = dialog.findViewById(R.id.checkb1);
                                           final CheckBox checkb2 = dialog.findViewById(R.id.checkb2);
                                           final CheckBox checkb3 = dialog.findViewById(R.id.checkb3);
                                           final CheckBox checkb4 = dialog.findViewById(R.id.checkb4);
                                           final CheckBox checkb5 = dialog.findViewById(R.id.checkb5);
                                           final  TextView  share_mail = dialog.findViewById(R.id.share_mail);
                                           final TextView iv_subcat_mixmum = dialog.findViewById(R.id.iv_subcat_mixmum);
                                           final TextView tv_contetiy = dialog.findViewById(R.id.tv_contetiy);
                                           final TextView iv_subcat_minimum = dialog.findViewById(R.id.iv_subcat_minimum);
                                           final Spinner spinner = dialog.findViewById(R.id.spinnerpop);
                                           final  TextView  share_whatsapp = dialog.findViewById(R.id.share_whatsapp);
                                            final TextView Share_my_profile_close= dialog.findViewById(R.id.Share_my_profile_close);
                                          //""""""""""""""Close pop up"""""""""""""""
                                            Share_my_profile_close.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog.dismiss();
                                                }
                                            });
                                           share_whatsapp.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {

                                                   final Dialog dialog = new Dialog(getActivity());
                                                   dialog.setContentView(R.layout.custom_share_whatsapp);
                                                   dialog.setTitle("Title...");
                                                   dialog.show();
                                                   PackageManager pm = v.getContext().getPackageManager();
                                                   boolean isNormal = isPackageInstalled("com.whatsapp", pm);
                                                   boolean isBussiness = isPackageInstalled("com.whatsapp.w4b", pm);
                                                   normail_whatsapp = dialog.findViewById(R.id.normail_whatsapp);
                                                   business_whatsapp = dialog.findViewById(R.id.business_whatsapp);
                                                   if(isNormal) {
                                                       normail_whatsapp.setVisibility(View.VISIBLE);
                                                   }else {
                                                       normail_whatsapp.setVisibility(View.GONE);
                                                   }
                                                   if(isBussiness) {
                                                       business_whatsapp.setVisibility(View.VISIBLE);
                                                   }else {
                                                       business_whatsapp.setVisibility(View.GONE);
                                                   }
//..........................................<<<<<<<<<<<<<<<<<<<<<<<<<<<business Whatsapp>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                                                   business_whatsapp.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {
                                                           String a1,a2,a3,a4,a5;

                                                           if (checkb1.isChecked()){
                                                               a1="1";
                                                           }else { a1="0";   }
                                                           if (checkb2.isChecked()){
                                                               a2="1";
                                                           }else { a2="0";   }
                                                           if (checkb3.isChecked()){
                                                               a3="1";
                                                           }else { a3="0";   }
                                                           if (checkb4.isChecked()){
                                                               a4="1";
                                                           }else { a4="0";   }
                                                           if (checkb5.isChecked()){
                                                               a5="1";
                                                           }else { a5="0";   }

                                                           // new SHARELOGIN_API(editText.getText().toString(),editText1.getText().toString(),dialog).execute();
                                                           Log.e("ALL CHECKBOX ","  "+a1+"   "+a2+"  "+a3+"  "+a4+"   "+a5+"  "+tv_contetiy.getText().toString()+"   "+spinner.getSelectedItem().toString());

                                                           SHARE_MAIL_API asob = new SHARE_MAIL_API(a1,a2,a3,
                                                                   a4,a5,tv_contetiy.getText().toString(),spinner.getSelectedItem().toString(),"whatsapp", "bussiness");
                                                           asob.execute();
                                                       }

                                                   });

                                //normail whatsapp user........><><<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>
                                                   normail_whatsapp.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {

                                                           String a1,a2,a3,a4,a5;

                                                           if (checkb1.isChecked()){
                                                               a1="1";
                                                           }else { a1="0";   }
                                                           if (checkb2.isChecked()){
                                                               a2="1";
                                                           }else { a2="0";   }
                                                           if (checkb3.isChecked()){
                                                               a3="1";
                                                           }else { a3="0";   }
                                                           if (checkb4.isChecked()){
                                                               a4="1";
                                                           }else { a4="0";   }
                                                           if (checkb5.isChecked()){
                                                               a5="1";
                                                           }else { a5="0";   }


                                                           // new SHARELOGIN_API(editText.getText().toString(),editText1.getText().toString(),dialog).execute();

                                                           Log.e("ALL CHECKBOX ","  "+a1+"   "+a2+"  "+a3+"  "+a4+"   "+a5+"  "+tv_contetiy.getText().toString()+"   "+spinner.getSelectedItem().toString());
                                                           SHARE_MAIL_API asob = new SHARE_MAIL_API(a1,a2,a3,
                                                                   a4,a5,tv_contetiy.getText().toString(),spinner.getSelectedItem().toString(),"whatsapp","normal");
                                                           asob.execute();
                                                           Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
                                                       }

                                                   });


                                               }

                                               private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
                                                   try {
                                                       packageManager.getPackageInfo(packageName, 0);
                                                       return true;
                                                   } catch (PackageManager.NameNotFoundException e) {
                                                       return false;
                                                   }
                                               }
                                           });
                                           share_mail.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   String a1,a2,a3,a4,a5;

                                                   if (checkb1.isChecked()){
                                                       a1="1";
                                                   }else { a1="0";   }
                                                   if (checkb2.isChecked()){
                                                       a2="1";
                                                   }else { a2="0";   }
                                                   if (checkb3.isChecked()){
                                                       a3="1";
                                                   }else { a3="0";   }
                                                   if (checkb4.isChecked()){
                                                       a4="1";
                                                   }else { a4="0";   }
                                                   if (checkb5.isChecked()){
                                                       a5="1";
                                                   }else { a5="0";   }


                                                   // new SHARELOGIN_API(editText.getText().toString(),editText1.getText().toString(),dialog).execute();

                                                   Log.e("ALL CHECKBOX ","  "+a1+"   "+a2+"  "+a3+"  "+a4+"   "+a5+"  "+tv_contetiy.getText().toString()+"   "+spinner.getSelectedItem().toString());
                                                   SHARE_MAIL_API asob = new SHARE_MAIL_API(a1,a2,a3,a4,a5,
                                                           tv_contetiy.getText().toString(),spinner.getSelectedItem().toString(),"","");
                                                   asob.execute();
                                               }
                                           });

                                           iv_subcat_mixmum.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {

                                                   int qty = Integer.valueOf(tv_contetiy.getText().toString());

                                                   if (qty < 12) {
                                                       qty = qty + 1;

                                                       tv_contetiy.setText(String.valueOf(qty));
                                                   }
                                               }
                                           });
                                           iv_subcat_minimum.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                   int qty = 0;
                                                   if (!tv_contetiy.getText().toString().equalsIgnoreCase(""))
                                                       qty = Integer.valueOf(tv_contetiy.getText().toString());

                                                   if (qty > 0) {
                                                       qty = qty - 1;
                                                       tv_contetiy.setText(String.valueOf(qty));
                                                   }
                                               }
                                           });
                                         //spiner use this number:::=========>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                                           final ArrayList<String> fruits = new ArrayList <>();

                                           fruits.add("   15");fruits.add("   30");fruits.add("   45");
                                           ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,fruits);
                                           spinner.setSelection(15);
                                           spinner.setAdapter(adapter);

                                           spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {
                                                 //  Toast.makeText(getActivity(), "You Selected "+ fruits.get(position), Toast.LENGTH_SHORT).show();
                                               }
                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           });
                                       }
                                    });

        init_Slider();
        return view;
    }
    /***
     * Auto slider  use code...........................................>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     */
    private void init_Slider() {

        dlist = new ArrayList<>();
        dlist.add(new Slider_data(R.drawable.matrimony1, "First Image"));
        dlist.add(new Slider_data(R.drawable.matrimony2, "Second Image"));


        slider_adapter = new Slider_adapter(getActivity(), dlist);
        slider_adapter.setCount(dlist.size());

        sliderView.isAutoCycle();
        sliderView.setAutoCycle(true);
        sliderView.setScrollTimeInSec(2);
        sliderView.setSliderAdapter(slider_adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });

    }

/**
 * Web View call in this sentence Share profile and whatsapp
 */

private class SHARE_MAIL_API extends AsyncTask<String, Void, String> {
    String useremail;
    String authToken;
    String userid;
    String shareby,  message_typ;
   // Dialog dialog;
    //check box parameter
    String photo;  String album;  String biodata;  String email;  String mobile;
    //For duration are use ............,,,,,,,<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>
    String hour;  String minute;
    public SHARE_MAIL_API(String photo,String album,String biodata,String email,String mobile,String hour, String minute,String shareby, String message_typ) {

        this.biodata = biodata;
        this.album = album;
        this.photo = photo;
        this.email = email;
        this.mobile = mobile;
        this.hour = hour;
        this.minute = minute;
        this.shareby = shareby;
        this.message_typ = message_typ;
    }
    @Override
    protected String doInBackground(String... arg0) {

        try {
            URL url = new URL("https://meem.one/UserApi/share_profile.php");
            JSONObject postDataParams = new JSONObject();
//                postDataParams.put("otp", Mobile_Number);
            postDataParams.put("authToken","IzpFfdTN9U");
            postDataParams.put("useremail", email);
          //  postDataParams.put("MzkxNw==", userid);
            postDataParams.put("email", email);
            postDataParams.put("biodata", biodata);
            postDataParams.put("album", album);
            postDataParams.put("mobile", mobile);
            postDataParams.put("photo",photo);
            postDataParams.put("hour", hour);
            postDataParams.put("minute", minute);
            //postDataParams.put("useremail", useremail);
            postDataParams.put("shareby",shareby);
            postDataParams.put("userid", AppPreference.getUser_Id(getActivity()));

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
           // dialog.dismiss();
            JSONObject jsonObject2 =null;
            Log.e(" SHARE_MAIL_API  ", "------- >>>>>>>>>>>>>>>>>>>>>   "+result.toString());
            try {
                jsonObject2 = new JSONObject(result);
                String message = jsonObject2.getString("message");
                String Link = jsonObject2.getString("Link");


                if (jsonObject2.getString("status_code").equals("1"))
                {
                    // startActivity(new Intent(getActivity(),Web_View.class));
                    //dialog.dismiss();
                    if (shareby.equals("whatsapp") && message_typ.equals("normal") )
                    {
                        Bundle bn = new Bundle();
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        bn.putString("message", message);

                        intent.putExtra(Intent.EXTRA_TEXT, "" + Link);
                        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
                        intent.setType("text/plain");
                        intent.setPackage("com.whatsapp");
                        startActivity(intent);
                   }
                    //<<<<<<Business Whasapp>>>>>
                    else if (shareby.equals("whatsapp")&& message_typ.equals("bussiness"))
                        {
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_TEXT, "" + Link);
                            Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
                            intent.setType("text/plain");
                            intent.setPackage("com.whatsapp.w4b");
                            startActivity(intent);
                        }
                    else if (shareby.equals(""))
                    {
                        Intent intent=new Intent(Intent.ACTION_SEND);

                       // String[] recipients={"mailto@gmail.com"};
                        intent.putExtra(Intent.EXTRA_EMAIL, "");
                        intent.putExtra(Intent.EXTRA_SUBJECT,"Share Profile on Meem");
                        intent.putExtra(Intent.EXTRA_TEXT,""+Link);
                        //intent.putExtra(Intent.EXTRA_CC,"");
                        intent.setType("text/html");
                        intent.setPackage("com.google.android.gm");
                        startActivity(Intent.createChooser(intent, "Send mail"));
                    }
                    else
                        {
                            Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
                        }

                            }
//                else {
//                    Toast.makeText(mContext, ""+message, Toast.LENGTH_SHORT).show();
//                }
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

    /**
     * Web View call in this sentence Share profile and whatsapp
     */


}
