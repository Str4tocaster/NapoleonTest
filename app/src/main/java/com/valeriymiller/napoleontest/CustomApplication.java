package com.valeriymiller.napoleontest;

import android.app.Application;

/**
 * Created by valer on 26.04.2017.
 */

public class CustomApplication extends Application {

    private MainPresenter mainPresenter;

    public MainPresenter getMainPresenter() {
        if (mainPresenter == null)
            mainPresenter = new MainPresenter(this, new MainRepository());
        return mainPresenter;
    }

}
