package com.valeriymiller.napoleontest;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by valer on 26.04.2017.
 */

public class MainPresenter implements IMainPresenter {

    private IMainView view;
    private IMainRepository repository;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    public MainPresenter(IMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void unbindView() {
        compositeSubscription.clear();
        view = null;
    }

    @Override
    public void bindView(IMainView iMainView) {
        this.view = iMainView;
    }

    @Override
    public void onStart() {
        loadNewsListItems();
        loadSliderItems();
    }

    private void loadNewsListItems() {
        view.showNewsProgress();

        Subscription getNewsItemsSubscription = repository.getNewsItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccessLoadNews, this::handleErrorLoadNews);
        compositeSubscription.add(getNewsItemsSubscription);
    }

    private void handleSuccessLoadNews(@NonNull List<NewsItemVO> items) {
        // show results
        view.setNewsListItems(items, items);
        // hide progress
        view.hideNewsProgress();
    }

    private void handleErrorLoadNews(Throwable throwable) {
        view.hideNewsProgress();
        view.showError();
    }

    private void loadSliderItems() {
        view.showSliderProgress();

        Subscription getSliderItemsSubscription = repository.getSliderItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccessLoadSlider, this::handleErrorLoadSlider);
        compositeSubscription.add(getSliderItemsSubscription);
    }

    private void handleSuccessLoadSlider(@NonNull List<SliderItemVO> items) {
        // show results
        view.setSliderItems(items);
        // hide progress
        view.hideSliderProgress();
    }

    private void handleErrorLoadSlider(Throwable throwable) {
        view.hideSliderProgress();
        view.showError();
    }

}
