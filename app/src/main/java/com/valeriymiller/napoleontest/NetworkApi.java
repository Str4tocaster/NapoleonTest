package com.valeriymiller.napoleontest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by valer on 26.04.2017.
 */

public class NetworkApi {

    private static final String URL = "https://api.myjson.com/bins/";
    private static INapoleonApi service;

    public static INapoleonApi getNetworkApi() {
        if (service == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            service = retrofit.create(INapoleonApi.class);
        }
        return service;
    }

}
