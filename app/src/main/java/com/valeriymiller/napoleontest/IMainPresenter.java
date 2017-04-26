package com.valeriymiller.napoleontest;

/**
 * Created by valer on 26.04.2017.
 */

public interface IMainPresenter {

    public void onStart();
    public void bindView(IMainView iMainView);
    public void unbindView();

}
