package com.valeriymiller.napoleontest;

import java.util.ArrayList;
import java.util.List;

import rx.Single;

/**
 * Created by valer on 26.04.2017.
 */

public class MainRepository implements IMainRepository {

    private static final int TYPE_STOCK = 1;
    private static final int TYPE_DISCOUNT = 2;

    @Override
    public Single<List<SliderItemVO>> getSliderItems() {
        return getSliderItemsFromNet()
                .flatMap(dtos -> dtoToSliderItemsVO(dtos));
    }

    private Single<List<SliderDTO>> getSliderItemsFromNet() {
        return NetworkApi.getNetworkApi().getSliderItems().toSingle();
    }

    private Single<List<SliderItemVO>> dtoToSliderItemsVO(List<SliderDTO> dtos) {
        List<SliderItemVO> sliderItems = new ArrayList<>();
        for (SliderDTO dto : dtos) {
            sliderItems.add(new SliderItemVO(
                    dto.getUrlThumbImage(),
                    dto.getLineOne(),
                    dto.getLineTwo()
            ));
        }

        return Single.just(sliderItems);
    }

    @Override
    public Single<List<NewsItemVO>> getNewsItems() {
        return getNewsItemsFromNet()
                .flatMap(dtos -> dtoToNewsItemsVO(dtos));
    }

    private Single<List<NewsDTO>> getNewsItemsFromNet() {
        return NetworkApi.getNetworkApi().getNewsItems().toSingle();
    }

    private Single<List<NewsItemVO>> dtoToNewsItemsVO(List<NewsDTO> dtos) {
        List<NewsItemVO> newsItems = new ArrayList<>();
        for (NewsDTO dto : dtos) {
            if (dto.getType() == TYPE_STOCK) {
                newsItems.add(new NewsItemVO(
                        dto.getUrlThumbImage(),
                        dto.getName(),
                        dto.getDescr(),
                        0,
                        0,
                        0
                        ));
            }
            if (dto.getType() == TYPE_DISCOUNT) {
                newsItems.add(new NewsItemVO(
                        dto.getUrlThumbImage(),
                        dto.getName(),
                        dto.getGroup(),
                        dto.getDiscount(),
                        dto.getPrice() - ((dto.getPrice() / 100) * dto.getDiscount()),
                        dto.getPrice()
                ));
            }
        }

        return Single.just(newsItems);
    }
}
