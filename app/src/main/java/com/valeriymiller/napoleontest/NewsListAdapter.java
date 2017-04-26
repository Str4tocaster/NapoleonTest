package com.valeriymiller.napoleontest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by valer on 25.04.2017.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    private List<NewsItemVO> items;

    public NewsListAdapter(List<NewsItemVO> items) {
        this.items = items;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        NewsItemVO item = items.get(position);

        holder.tvHeader.setText(item.getHeaderText());
        holder.tvContent.setText(item.getContentText());
        holder.tvSale.setText(String.valueOf(item.getSale()));
        holder.tvPrice.setText(String.valueOf(item.getPrice()));
        holder.tvOldPrice.setText(String.valueOf(item.getOldPrice()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvHeader;
        TextView tvContent;
        TextView tvSale;
        TextView tvPrice;
        TextView tvOldPrice;

        public NewsViewHolder(View itemView) {
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
