package com.kbc.playAlong.model;

public class Blog_Model {
    private String Image;
    private String desc;
    private String heading;
    private String publish;
    private String date;
    private int id;

    public Blog_Model(String image, String desc, String heading, String publish, String date,int id) {
        Image = image;
        this.desc = desc;
        this.heading = heading;
        this.publish = publish;
        this.date = date;
        this.id = id;
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

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
