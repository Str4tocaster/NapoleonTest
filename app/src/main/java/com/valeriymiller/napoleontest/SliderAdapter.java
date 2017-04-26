package com.valeriymiller.napoleontest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valer on 25.04.2017.
 */

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private Context context;
    private List<String> urls;

    public SliderAdapter(Context context, List<String> urls) {
        this.context = context;
        this.urls = urls;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder holder, int position) {
        String url = urls.get(position);
        Picasso.with(context).load(url).into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public static class SliderViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;

        public SliderViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
        }
    }

}
