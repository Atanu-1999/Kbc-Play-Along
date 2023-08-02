package com.kbc.playAlong.model;

public class Banner_Model {
    private String Image;
    private String desc;

    public Banner_Model(String image, String desc) {
        Image = image;
        this.desc = desc;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
