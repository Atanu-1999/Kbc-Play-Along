package com.kbc.playAlong.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Notification_Response {

    public class Datum {
        @SerializedName("notiId")
        @Expose
        private String notiId;
        @SerializedName("notiSend")
        @Expose
        private String notiSend;
        @SerializedName("notiTitle")
        @Expose
        private String notiTitle;
        @SerializedName("notiDesc")
        @Expose
        private String notiDesc;
        @SerializedName("sendDate")
        @Expose
        private String sendDate;

        public String getNotiId() {
            return notiId;
        }

        public void setNotiId(String notiId) {
            this.notiId = notiId;
        }

        public String getNotiSend() {
            return notiSend;
        }

        public void setNotiSend(String notiSend) {
            this.notiSend = notiSend;
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
