package com.kbc.playAlong.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Transaction_Response {
    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("accountId")
        @Expose
        private Integer accountId;
        @SerializedName("contestId")
        @Expose
        private Integer contestId;
        @SerializedName("transactionId")
        @Expose
        private String transactionId;
        @SerializedName("amount")
        @Expose
        private Integer amount;
        @SerializedName("paymentStatus")
        @Expose
        private String paymentStatus;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("title")
        @Expose
        private String title;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getAccountId() {
            return accountId;
        }

        public void setAccountId(Integer accountId) {
            this.accountId = accountId;
        }

        public Integer getContestId() {
            return contestId;
        }

        public void setContestId(Integer contestId) {
            this.contestId = contestId;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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
