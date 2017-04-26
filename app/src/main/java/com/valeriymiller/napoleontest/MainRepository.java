package com.valeriymiller.napoleontest;

import java.util.ArrayList;
import java.util.List;

import rx.Single;

/**
 * Created by valer on 26.04.2017.
 */

public class MainRepository implements IMainRepository {

    // return slider view objects
    @Override
    public Single<List<SliderItemVO>> getSliderItems() {
        return getSliderItemsFromNet()
                .flatMap(dtos -> dtoToSliderItemsVO(dtos));
    }

    // load slider items from net
    private Single<List<SliderDTO>> getSliderItemsFromNet() {
        return NetworkApi.getNetworkApi().getSliderItems().toSingle();
    }

    // convert slider dto to view objects
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

    // return news view objects
    @Override
    public Single<List<NewsItemVO>> getNewsItems() {
        return getNewsItemsFromNet()
                .flatMap(dtos -> dtoToNewsItemsVO(dtos));
    }

    // load news items from net
    private Single<List<NewsDTO>> getNewsItemsFromNet() {
        return NetworkApi.getNetworkApi().getNewsItems().toSingle();
    }

    // convert news dto to view objects
    private Single<List<NewsItemVO>> dtoToNewsItemsVO(List<NewsDTO> dtos) {
        List<NewsItemVO> newsItems = new ArrayList<>();
        for (NewsDTO dto : dtos) {
            if (dto.getType() == NewsDTO.TYPE_STOCK) {
                newsItems.add(new NewsItemVO(
                        dto.getType(),
                        dto.getUrlThumbImage(),
                        dto.getName(),
                        dto.getDescr(),
                        0,
                        0,
                        0
                        ));
            }
            if (dto.getType() == NewsDTO.TYPE_DISCOUNT) {
                newsItems.add(new NewsItemVO(
                        dto.getType(),
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
