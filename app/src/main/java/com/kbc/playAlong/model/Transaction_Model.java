package com.kbc.playAlong.model;

public class Transaction_Model {
    private String title;
    private String createdAt;
    private int amount;

    public Transaction_Model(String title, String createdAt, int amount) {
        this.title = title;
        this.createdAt = createdAt;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
