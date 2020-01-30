package com.ics.meem.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ics.meem.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class Slider_adapter extends
        SliderViewAdapter<Slider_adapter.SliderAdapterVH> {

    private Activity mactivity;
    private int mCount;
    private List<Slider_data> dataList;

    public Slider_adapter(Activity activity, List<Slider_data> dlist) {
        this.mactivity = activity;
        this.dataList = dlist;
    }

    public void setCount(int count) {
        this.mCount = count;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_image_slider, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        Slider_data sob = dataList.get(position);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });


     /*   Glide.with(viewHolder.itemView)
                .load(BaseUrl.baseimg+""+sob.getImage())
                .fitCenter()
                .into(viewHolder.imageViewBackground);*/

        switch (position) {
            case 0:
                viewHolder.textViewDescription.setText("" + sob.getName());

                Glide.with(viewHolder.itemView)
                        .load(sob.getId())
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                viewHolder.textViewDescription.setText("" + sob.getName());
                Glide.with(viewHolder.itemView)
                        .load(sob.getId())
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 2:
                viewHolder.textViewDescription.setText("" + sob.getName());
                Glide.with(viewHolder.itemView)
                        .load(sob.getId())
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 3:
                viewHolder.textViewDescription.setText("" + sob.getName());
                Glide.with(viewHolder.itemView)
                        .load(sob.getId())
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;

         }
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mCount;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }



}
