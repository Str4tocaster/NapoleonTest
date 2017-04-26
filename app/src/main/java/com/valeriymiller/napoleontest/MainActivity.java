package com.valeriymiller.napoleontest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements IMainView {

    private ProgressBar progressBar;
    private RelativeLayout noInternetLayout;
    private TextView tvTopTen;
    private TextView tvShops;
    private TextView tvProducts;

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

        // init views
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        noInternetLayout = (RelativeLayout) findViewById(R.id.noInternet);
        ImageView ivFilter = (ImageView) findViewById(R.id.ivFilter);
        ivFilter.setOnClickListener(view -> showAlertDialog());

        // init filter
        tvTopTen = (TextView) findViewById(R.id.tvTopTen);
        tvTopTen.setOnClickListener(view -> setActiveFilter(tvTopTen));

        tvShops = (TextView) findViewById(R.id.tvShops);
        tvShops.setOnClickListener(view -> setActiveFilter(tvShops));

        tvProducts = (TextView) findViewById(R.id.tvProducts);
        tvProducts.setOnClickListener(view -> setActiveFilter(tvProducts));

        // init lists
        sliderItems = new ArrayList<>();
        stockItems = new ArrayList<>();
        discountItems = new ArrayList<>();

        // init list adapter
        newsListAdapter = new SectionedRecyclerViewAdapter();
        newsListAdapter.addSection(new NewsListHeaderSection(this, sliderItems));
        newsListAdapter.addSection(new NewsListSection(this, getString(R.string.stock), stockItems));
        newsListAdapter.addSection(new NewsListSection(this, getString(R.string.discount), discountItems));

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

    @Override
    public void showNoInternet(boolean show) {
        if (show) noInternetLayout.setVisibility(View.VISIBLE);
        else noInternetLayout.setVisibility(View.GONE);
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.alert_title)
                .setMessage(R.string.alert_content)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> dialog.cancel())
                .show();
    }

    private void setActiveFilter(TextView textView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DrawableCompat.setTint(tvTopTen.getBackground(), getResources().getColor(R.color.colorPrimary));
            DrawableCompat.setTint(tvShops.getBackground(), getResources().getColor(R.color.colorPrimary));
            DrawableCompat.setTint(tvProducts.getBackground(), getResources().getColor(R.color.colorPrimary));
            DrawableCompat.setTint(textView.getBackground(), getResources().getColor(R.color.colorFilterIndicator));
        }
    }
}
