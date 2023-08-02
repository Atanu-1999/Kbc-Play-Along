package com.kbc.playAlong.model;

public class Notification_Model {
    private String notiTitle;
    private String notiDesc;
    private String sendDate;
    private String notiId;

    public Notification_Model(String notiTitle, String notiDesc, String sendDate, String notiId) {
        this.notiTitle = notiTitle;
        this.notiDesc = notiDesc;
        this.sendDate = sendDate;
        this.notiId = notiId;
    }

    public String getNotiTitle() {
        return notiTitle;
    }

    public void setNotiTitle(String notiTitle) {
        this.notiTitle = notiTitle;
    }

    public String getNotiDesc() {
        return notiDesc;
    }

    public void setNotiDesc(String notiDesc) {
        this.notiDesc = notiDesc;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getNotiId() {
        return notiId;
    }

    public void setNotiId(String notiId) {
        this.notiId = notiId;
    }
}
