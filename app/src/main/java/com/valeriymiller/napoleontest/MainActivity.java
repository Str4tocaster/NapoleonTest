package com.valeriymiller.napoleontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<SliderItemVO> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItemVO("http://i.imgur.com/DvpvklR.png", "First header", "First content"));
        sliderItems.add(new SliderItemVO("http://i.imgur.com/DvpvklR.png", "Second header", "Second content"));
        sliderItems.add(new SliderItemVO("http://i.imgur.com/DvpvklR.png", "Third header", "Third content"));

        List<NewsItemVO> items = new ArrayList<>();
        items.add(new NewsItemVO("http://i.imgur.com/DvpvklR.png", "First news", "Text about first news", 50, 1.50f, 2.99f));
        items.add(new NewsItemVO("http://i.imgur.com/DvpvklR.png", "Second news", "Text about second news", 20, 1.05f, 2.50f));
        items.add(new NewsItemVO("http://i.imgur.com/DvpvklR.png", "Third news", "Text about third news", 30, 1.70f, 3.10f));

        SectionedRecyclerViewAdapter newsListAdapter = new SectionedRecyclerViewAdapter();
        newsListAdapter.addSection(new NewsListHeaderSection(this, sliderItems));
        newsListAdapter.addSection(new NewsListSection(this, "Акции", items));
        newsListAdapter.addSection(new NewsListSection(this, "Скидки", items));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(newsListAdapter);

    }
}
