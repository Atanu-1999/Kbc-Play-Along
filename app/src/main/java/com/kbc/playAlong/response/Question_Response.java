package com.kbc.playAlong.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question_Response {
    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("contestId")
        @Expose
        private String contestId;
        @SerializedName("contest_question")
        @Expose
        private String contestQuestion;
        @SerializedName("option_one")
        @Expose
        private String optionOne;
        @SerializedName("option_two")
        @Expose
        private String optionTwo;
        @SerializedName("option_three")
        @Expose
        private String optionThree;
        @SerializedName("option_four")
        @Expose
        private String optionFour;
        @SerializedName("answer")
        @Expose
        private String answer;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("start_time")
        @Expose
        private String startTime;
        @SerializedName("end_time")
        @Expose
        private String endTime;
        @SerializedName("timmer")
        @Expose
        private String timmer;
        @SerializedName("entry_fee")
        @Expose
        private String entryFee;
        @SerializedName("winning_prize")
        @Expose
        private String winningPrize;
        @SerializedName("status")
        @Expose
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContestId() {
            return contestId;
        }

        public void setContestId(String contestId) {
            this.contestId = contestId;
        }

        public String getContestQuestion() {
            return contestQuestion;
        }

        public void setContestQuestion(String contestQuestion) {
            this.contestQuestion = contestQuestion;
        }

        public String getOptionOne() {
            return optionOne;
        }

        public void setOptionOne(String optionOne) {
            this.optionOne = optionOne;
        }

        public String getOptionTwo() {
            return optionTwo;
        }

        public void setOptionTwo(String optionTwo) {
            this.optionTwo = optionTwo;
        }

        public String getOptionThree() {
            return optionThree;
        }

        public void setOptionThree(String optionThree) {
            this.optionThree = optionThree;
        }

        public String getOptionFour() {
            return optionFour;
        }

        public void setOptionFour(String optionFour) {
            this.optionFour = optionFour;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getTimmer() {
            return timmer;
        }

        public void setTimmer(String timmer) {
            this.timmer = timmer;
        }

        public String getEntryFee() {
            return entryFee;
        }

        public void setEntryFee(String entryFee) {
            this.entryFee = entryFee;
        }

        public String getWinningPrize() {
            return winningPrize;
        }

        public void setWinningPrize(String winningPrize) {
            this.winningPrize = winningPrize;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }
        @SerializedName("data")
        @Expose
        private List<Datum> data;
        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("message")
        @Expose
        private String message;

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
}
