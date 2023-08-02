package com.kbc.playAlong.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserProfile_Response {

    public class Datum {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("phoneNumber")
        @Expose
        private String phoneNumber;
        @SerializedName("deviceId")
        @Expose
        private String deviceId;
        @SerializedName("profile")
        @Expose
        private Object profile;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("emailId")
        @Expose
        private String emailId;
        @SerializedName("address")
        @Expose
        private Object address;
        @SerializedName("city")
        @Expose
        private Object city;
        @SerializedName("pincode")
        @Expose
        private Object pincode;
        @SerializedName("dob")
        @Expose
        private Object dob;
        @SerializedName("winning_amount")
        @Expose
        private Integer winningAmount;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public Object getProfile() {
            return profile;
        }

        public void setProfile(Object profile) {
            this.profile = profile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getPincode() {
            return pincode;
        }

        public void setPincode(Object pincode) {
            this.pincode = pincode;
        }

        public Object getDob() {
            return dob;
        }

        public void setDob(Object dob) {
            this.dob = dob;
        }

        public Integer getWinningAmount() {
            return winningAmount;
        }

        public void setWinningAmount(Integer winningAmount) {
            this.winningAmount = winningAmount;
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
