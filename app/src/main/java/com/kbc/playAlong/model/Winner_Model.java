package com.kbc.playAlong.model;

public class Winner_Model {
    private String Image;
    private String name;
    private int price;

    public Winner_Model(String image, String name, int price) {
        Image = image;
        this.name = name;
        this.price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
