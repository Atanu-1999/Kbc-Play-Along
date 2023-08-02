package com.kbc.playAlong.model;

public class Contest_Model {
    private String title;
    private String time;
    private String date;
    private String contest_id;
    private String entry_fee;
    private String winning_prize;
    private String timmer;


    public Contest_Model(String title, String time, String date, String contest_id, String entry_fee, String winning_prize, String timmer) {
        this.title = title;
        this.time = time;
        this.date = date;
        this.contest_id = contest_id;
        this.entry_fee = entry_fee;
        this.winning_prize = winning_prize;
        this.timmer = timmer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContest_id() {
        return contest_id;
    }

    public void setContest_id(String contest_id) {
        this.contest_id = contest_id;
    }

    public String getEntry_fee() {
        return entry_fee;
    }

    public void setEntry_fee(String entry_fee) {
        this.entry_fee = entry_fee;
    }

    public String getWinning_prize() {
        return winning_prize;
    }

    public void setWinning_prize(String winning_prize) {
        this.winning_prize = winning_prize;
    }

    public String getTimmer() {
        return timmer;
    }

    public void setTimmer(String timmer) {
        this.timmer = timmer;
    }
}
