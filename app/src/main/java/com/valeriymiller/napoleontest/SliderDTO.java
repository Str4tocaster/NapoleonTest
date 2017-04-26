package com.valeriymiller.napoleontest;

/**
 * Created by valer on 26.04.2017.
 */

public class SliderDTO {

    private int uuid;
    private String lineOne;
    private String lineTwo;
    private String urlThumbImage;

    public SliderDTO(int uuid, String lineOne, String lineTwo, String urlThumblmage) {
        this.uuid = uuid;
        this.lineOne = lineOne;
        this.lineTwo = lineTwo;
        this.urlThumbImage = urlThumblmage;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getLineOne() {
        return lineOne;
    }

    public void setLineOne(String lineOne) {
        this.lineOne = lineOne;
    }

    public String getLineTwo() {
        return lineTwo;
    }

    public void setLineTwo(String lineTwo) {
        this.lineTwo = lineTwo;
    }

    public String getUrlThumbImage() {
        return urlThumbImage;
    }

    public void setUrlThumbImage(String urlThumbImage) {
        this.urlThumbImage = urlThumbImage;
    }
}
