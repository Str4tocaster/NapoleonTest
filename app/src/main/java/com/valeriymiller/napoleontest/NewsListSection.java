package com.valeriymiller.napoleontest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by valer on 25.04.2017.
 */

public class NewsListSection extends StatelessSection {

    private Context context;
    private String sectionTitle;
    private List<NewsItemVO> items;

    public NewsListSection (Context context, String sectionTitle, List<NewsItemVO> items) {
        super(R.layout.news_list_section, R.layout.news_list_item);

        this.context = context;
        this.sectionTitle = sectionTitle;
        this.items = items;
    }

    @Override
    public int getContentItemsTotal() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new NewsItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final NewsItemViewHolder itemHolder = (NewsItemViewHolder) holder;
        NewsItemVO item = items.get(position);

        Picasso.with(context).load(item.getImgUrl()).into(itemHolder.ivImage);
        itemHolder.tvHeader.setText(item.getHeaderText());
        itemHolder.tvContent.setText(item.getContentText());
        itemHolder.tvSale.setText(String.valueOf(item.getSale()));
        itemHolder.tvPrice.setText(String.valueOf(item.getPrice()));
        itemHolder.tvOldPrice.setText(String.valueOf(item.getOldPrice()));
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        SectionViewHolder headerHolder = (SectionViewHolder) holder;

        headerHolder.tvTitle.setText(sectionTitle);
    }

    class SectionViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        public SectionViewHolder(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        }
    }

    public static class NewsItemViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvHeader;
        TextView tvContent;
        TextView tvSale;
        TextView tvPrice;
        TextView tvOldPrice;

        public NewsItemViewHolder(View itemView) {
            super(itemView);

            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvHeader = (TextView) itemView.findViewById(R.id.tvHeader);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            tvSale = (TextView) itemView.findViewById(R.id.tvSale);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvOldPrice = (TextView) itemView.findViewById(R.id.tvOldPrice);
        }
    }
}