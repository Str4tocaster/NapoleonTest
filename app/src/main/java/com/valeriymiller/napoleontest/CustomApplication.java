package com.valeriymiller.napoleontest;

import android.app.Application;

/**
 * Created by valer on 26.04.2017.
 */

public class CustomApplication extends Application {

    private MainPresenter mainPresenter;

    @Override
    public void onCreate() {
        super.onCreate();
        // place for realm initialization
    }

    public MainPresenter getMainPresenter() {
        if (mainPresenter == null)
            mainPresenter = new MainPresenter(new MainRepository());
        return mainPresenter;
    }

}
