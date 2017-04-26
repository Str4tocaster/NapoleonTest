package com.valeriymiller.napoleontest;

import java.util.ArrayList;
import java.util.List;

import rx.Single;

/**
 * Created by valer on 26.04.2017.
 */

public class MainRepository implements IMainRepository {

    @Override
    public Single<List<SliderItemVO>> getSliderItems() {

        List<SliderItemVO> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItemVO("http://i.imgur.com/DvpvklR.png", "First header", "First content"));
        sliderItems.add(new SliderItemVO("http://i.imgur.com/DvpvklR.png", "Second header", "Second content"));
        sliderItems.add(new SliderItemVO("http://i.imgur.com/DvpvklR.png", "Third header", "Third content"));

        return Single.just(sliderItems);
    }

    @Override
    public Single<List<NewsItemVO>> getNewsItems() {

        List<NewsItemVO> items = new ArrayList<>();
        items.add(new NewsItemVO("http://i.imgur.com/DvpvklR.png", "First news", "Text about first news", 50, 1.50f, 2.99f));
        items.add(new NewsItemVO("http://i.imgur.com/DvpvklR.png", "Second news", "Text about second news", 20, 1.05f, 2.50f));
        items.add(new NewsItemVO("http://i.imgur.com/DvpvklR.png", "Third news", "Text about third news", 30, 1.70f, 3.10f));

        return Single.just(items);
    }
}
