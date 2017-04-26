package com.valeriymiller.napoleontest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import java.util.ArrayList;
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
    private Context context;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    public MainPresenter(Context context, IMainRepository repository) {
        this.repository = repository;
        this.context = context;
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
        view.showProgress(true);
        if (isNetworkAvailable(context)) {
            view.showNoInternet(false);
            loadNewsListItems();
            loadSliderItems();
        } else {
            view.showNoInternet(true);
            view.showProgress(false);
        }
    }

    private void loadNewsListItems() {
        Subscription getNewsItemsSubscription = repository.getNewsItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccessLoadNews, this::handleErrorLoadNews);
        compositeSubscription.add(getNewsItemsSubscription);
    }

    private void handleSuccessLoadNews(@NonNull List<NewsItemVO> items) {

        // divide results into 2 groups
        List<NewsItemVO> stockItems = new ArrayList<>();
        List<NewsItemVO> discountItems = new ArrayList<>();
        for (NewsItemVO item : items) {
            if (item.getType() == NewsDTO.TYPE_STOCK) {
                stockItems.add(item);
            }
            if (item.getType() == NewsDTO.TYPE_DISCOUNT) {
                discountItems.add(item);
            }
        }

        // show results
        view.setNewsListItems(stockItems, discountItems);
        // hide progress
        view.showProgress(false);
    }

    private void handleErrorLoadNews(Throwable throwable) {
        view.showProgress(false);
        view.showError(throwable.getMessage());
    }

    private void loadSliderItems() {
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
        view.showProgress(false);
    }

    private void handleErrorLoadSlider(Throwable throwable) {
        view.showProgress(false);
        view.showError(throwable.getMessage());
    }

    private boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null;
        } catch (Exception e) {
            return false;
        }
    }

}
