package com.valeriymiller.napoleontest;

/**
 * Created by valer on 26.04.2017.
 */

public class SliderItemVO {

    private String imageUrl;
    private String headerText;
    private String contentText;

    public SliderItemVO(String imageUrl, String headerText, String contentText) {
        this.imageUrl = imageUrl;
        this.headerText = headerText;
        this.contentText = contentText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
}
