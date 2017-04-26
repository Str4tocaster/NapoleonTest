package com.valeriymiller.napoleontest;

import java.util.List;

import rx.Single;


/**
 * Created by valer on 26.04.2017.
 */

public interface IMainRepository {

    public Single<List<SliderItemVO>> getSliderItems();
    public Single<List<NewsItemVO>> getNewsItems();

}
