package com.valeriymiller.napoleontest;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by valer on 25.04.2017.
 */

public class NewsListHeaderSection extends StatelessSection {

    private Context context;
    private List<SliderItemVO> items;

    public NewsListHeaderSection (Context context, List<SliderItemVO> items) {
        super(R.layout.news_list_header, R.layout.news_list_item);
        this.context = context;
        this.items = items;
    }

    @Override
    public int getContentItemsTotal() {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return null;
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

        SliderAdapter sliderAdapter = new SliderAdapter(context, items);

        SnapHelper snapHelper = new PagerSnapHelper();
        headerHolder.recyclerView.setOnFlingListener(null);
        snapHelper.attachToRecyclerView(headerHolder.recyclerView);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        headerHolder.recyclerView.setLayoutManager(layoutManager);
        headerHolder.recyclerView.setAdapter(sliderAdapter);
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView recyclerView;

        public HeaderViewHolder(View view) {
            super(view);
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        }
    }
}
