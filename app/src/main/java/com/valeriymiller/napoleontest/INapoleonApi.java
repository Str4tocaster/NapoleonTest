package com.valeriymiller.napoleontest;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by valer on 26.04.2017.
 */

public interface INapoleonApi {

    @GET("16jt01")
    Observable<List<SliderDTO>> getSliderItems();

    @GET("ziw29")
    Observable<List<NewsDTO>> getNewsItems();

}
