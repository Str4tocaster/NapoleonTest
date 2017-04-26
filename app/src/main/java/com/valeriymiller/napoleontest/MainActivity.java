package com.valeriymiller.napoleontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements IMainView {

    private ProgressBar progressBar;

    private List<SliderItemVO> sliderItems;
    private List<NewsItemVO> stockItems;
    private List<NewsItemVO> discountItems;
    private RecyclerView recyclerView;
    private SectionedRecyclerViewAdapter newsListAdapter;

    private IMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // don't show keyboard on start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // init progress
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // init lists
        sliderItems = new ArrayList<>();
        stockItems = new ArrayList<>();
        discountItems = new ArrayList<>();

        // init list adapter
        newsListAdapter = new SectionedRecyclerViewAdapter();
        newsListAdapter.addSection(new NewsListHeaderSection(this, sliderItems));
        newsListAdapter.addSection(new NewsListSection(this, "Акции", stockItems));
        newsListAdapter.addSection(new NewsListSection(this, "Скидки", discountItems));

        // init recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(newsListAdapter);

        // init presenter
        presenter = ((CustomApplication)getApplication()).getMainPresenter();
        presenter.bindView(this);
        presenter.onStart();
    }

    @Override
    protected void onDestroy() {
        presenter.unbindView();
        super.onDestroy();
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
    public void showProgress(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
}
