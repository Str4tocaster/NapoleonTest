package com.valeriymiller.napoleontest;

import java.util.List;

/**
 * Created by valer on 26.04.2017.
 */

public interface IMainView {
    public void setNewsListItems(List<NewsItemVO> stockItems, List<NewsItemVO> discountItems);
    public void setSliderItems(List<SliderItemVO> items);
    public void showSliderProgress();
    public void hideSliderProgress();
    public void showNewsProgress();
    public void hideNewsProgress();
    public void showError();
}