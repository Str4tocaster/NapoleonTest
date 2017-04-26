package com.valeriymiller.napoleontest;

/**
 * Created by valer on 25.04.2017.
 */

public class NewsItemVO {

    private String imgUrl;
    private String headerText;
    private String contentText;
    private int sale;
    private float price;
    private float oldPrice;

    public NewsItemVO(String imgUrl, String headerText, String contentText, int sale, float price, float oldPrice) {
        this.imgUrl = imgUrl;
        this.headerText = headerText;
        this.contentText = contentText;
        this.sale = sale;
        this.price = price;
        this.oldPrice = oldPrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(float oldPrice) {
        this.oldPrice = oldPrice;
    }
}
