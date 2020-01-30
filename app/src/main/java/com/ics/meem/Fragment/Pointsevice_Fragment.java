package com.ics.meem.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

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

import java.util.ArrayList;
import java.util.List;

public class Pointsevice_Fragment extends Fragment
{

    ViewPager viewPager4;

    List<Slider_data> dlist;
    SliderView sliderView;
    Slider_adapter slider_adapter;
    private ImageView ic_menu4;
    private String token;
    TextView meem4;
    ImageView notification,tx_images001,tx_images002,tx_images003,tx_images004;

    @Override
    public void onResume() {
        AppPreference.setEmail(getActivity(),"P");
        super.onResume();
    }

    @Override
    public boolean getUserVisibleHint() {
        AppPreference.setEmail(getActivity(),"P");
        return super.getUserVisibleHint();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pointservice,container,false);

        sliderView = (SliderView) view.findViewById(R.id.imageSlider_pointservices);
        //logut option is visible is show.................>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        ((MainActivity)getActivity()).logout_onn();
        ((MainActivity)getActivity()).user_onn();
        AppPreference.setEmail(getActivity(),"P");


        //Home_Adapter viewPagerAdapter = new Pointsevice_Fragment(getActivity());
       // PointServices_Adapter viewPagerAdapter = new PointServices_Adapter(getActivity());
        //viewPager4.setAdapter(viewPagerAdapter);
        //meem text view call to web site
        token = AppPreference.getPointServiceToken(getActivity());
        Log.d("Token received ",">>>>>>>>>>>>>>> ------------------------    "+token);


        meem4 = (TextView) view.findViewById(R.id.meem4);
        meem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getActivity(),Web_View.class);
                String llnk = "https://meem.one/";
                intent.putExtra("link",llnk);
                startActivity(intent);
            }
        });

        ic_menu4 = (ImageView) view.findViewById(R.id.ic_menu4);
        // call drazer navigation
        ic_menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).openDrawer();
            }
        });
        //Image click call buy memebership..............................................................................
        tx_images001  = (ImageView) view.findViewById(R.id.tx_images001);
        tx_images001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getActivity(),Web_View.class);
                String llnk = "https://meem.one/reseller/payment.php?page=generate-voucher?token="+token;
                intent.putExtra("link",llnk);
                startActivity(intent);
            }
        });

//Active profile ...........................................................................
        tx_images002  = (ImageView) view.findViewById(R.id.tx_images002);
        tx_images002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getActivity(),Web_View.class);
                String llnk = "https://meem.one/reseller/activation.php?page=activate?token="+token;
                intent.putExtra("link",llnk);
                startActivity(intent);
            }
        });

        //Renue  profile ...........................................................................
        tx_images003  = (ImageView) view.findViewById(R.id.tx_images003);
        tx_images003.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getActivity(),Web_View.class);
                String llnk = "https://meem.one/reseller/renewal.php?page=renew-user?token="+token;
                intent.putExtra("link",llnk);
                startActivity(intent);
            }
        });
        //Register  profile ...........................................................................
        tx_images004  = (ImageView) view.findViewById(R.id.tx_images004);
        tx_images004.setOnClickListener(new View.OnClickListener() {
            @Override

                public void onClick(View v) {
                    Intent  intent = new Intent(getActivity(),Web_View.class);
                    String llnk = "https://meem.one/reseller/registration.php?token="+token;
                    intent.putExtra("link",llnk);
                    startActivity(intent);

                }
            });
        init_Slider();
        return view;
    }
    private void init_Slider(){

        dlist = new ArrayList<>();
        dlist.add(new Slider_data(R.drawable.matrimony1,"First Image"));
        dlist.add(new Slider_data(R.drawable.matrimony2,"Second Image"));

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

}
