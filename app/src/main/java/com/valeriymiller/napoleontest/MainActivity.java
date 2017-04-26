package com.valeriymiller.napoleontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements IMainView {

    private List<SliderItemVO> sliderItems;
    private List<NewsItemVO> stockItems;
    private List<NewsItemVO> discountItems;
    private SectionedRecyclerViewAdapter newsListAdapter;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderItems = new ArrayList<>();
        stockItems = new ArrayList<>();
        discountItems = new ArrayList<>();

        newsListAdapter = new SectionedRecyclerViewAdapter();
        newsListAdapter.addSection(new NewsListHeaderSection(this, sliderItems));
        newsListAdapter.addSection(new NewsListSection(this, "Акции", stockItems));
        newsListAdapter.addSection(new NewsListSection(this, "Скидки", discountItems));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(newsListAdapter);

        presenter = new MainPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void setNewsListItems(List<NewsItemVO> stockItems, List<NewsItemVO> discountItems) {
        if (this.stockItems != null) {
            this.stockItems.clear();
            this.stockItems.addAll(stockItems);
        }
        if (this.discountItems != null) {
            this.discountItems.clear();
            this.discountItems.addAll(discountItems);
        }
        newsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void setSliderItems(List<SliderItemVO> sliderItems) {
        if (this.sliderItems != null) {
            this.sliderItems.clear();
            this.sliderItems.addAll(sliderItems);
        }
        newsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {

    }
}
