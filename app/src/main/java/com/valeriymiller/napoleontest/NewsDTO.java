package com.valeriymiller.napoleontest;

/**
 * Created by valer on 26.04.2017.
 */

public class NewsDTO {

    private int uuid;
    private int type;
    private String name;
    private String descr;
    private String group;
    private float price;
    private float discount;
    private String urlThumbImage;

    public NewsDTO(int uuid, int type, String name, String descr, String group, float price, float discount, String urlThumbImage) {
        this.uuid = uuid;
        this.type = type;
        this.name = name;
        this.descr = descr;
        this.group = group;
        this.price = price;
        this.discount = discount;
        this.urlThumbImage = urlThumbImage;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getUrlThumbImage() {
        return urlThumbImage;
    }

    public void setUrlThumbImage(String urlThumbImage) {
        this.urlThumbImage = urlThumbImage;
    }
}
