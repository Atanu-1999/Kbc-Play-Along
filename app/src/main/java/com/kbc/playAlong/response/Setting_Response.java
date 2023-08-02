package com.kbc.playAlong.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Setting_Response {
    public class Datum {
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("logo")
        @Expose
        private String logo;
        @SerializedName("favicon")
        @Expose
        private String favicon;
        @SerializedName("website_name")
        @Expose
        private String websiteName;
        @SerializedName("contect_person")
        @Expose
        private String contectPerson;
        @SerializedName("content_email")
        @Expose
        private String contentEmail;
        @SerializedName("contect_phone")
        @Expose
        private String contectPhone;
        @SerializedName("contect_address")
        @Expose
        private String contectAddress;
        @SerializedName("payment_link")
        @Expose
        private String paymentLink;
        @SerializedName("link_status")
        @Expose
        private String linkStatus;
        @SerializedName("qrimage")
        @Expose
        private String qrimage;
        @SerializedName("qr_status")
        @Expose
        private String qrStatus;
        @SerializedName("upi_address")
        @Expose
        private String upiAddress;
        @SerializedName("upi_status")
        @Expose
        private String upiStatus;
        @SerializedName("contect_map_ifream")
        @Expose
        private Object contectMapIfream;
        @SerializedName("footer_content")
        @Expose
        private String footerContent;
        @SerializedName("footer_copyright")
        @Expose
        private String footerCopyright;
        @SerializedName("app_status")
        @Expose
        private String appStatus;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getFavicon() {
            return favicon;
        }

        public void setFavicon(String favicon) {
            this.favicon = favicon;
        }

        public String getWebsiteName() {
            return websiteName;
        }

        public void setWebsiteName(String websiteName) {
            this.websiteName = websiteName;
        }

        public String getContectPerson() {
            return contectPerson;
        }

        public void setContectPerson(String contectPerson) {
            this.contectPerson = contectPerson;
        }

        public String getContentEmail() {
            return contentEmail;
        }

        public void setContentEmail(String contentEmail) {
            this.contentEmail = contentEmail;
        }

        public String getContectPhone() {
            return contectPhone;
        }

        public void setContectPhone(String contectPhone) {
            this.contectPhone = contectPhone;
        }

        public String getContectAddress() {
            return contectAddress;
        }

        public void setContectAddress(String contectAddress) {
            this.contectAddress = contectAddress;
        }

        public String getPaymentLink() {
            return paymentLink;
        }

        public void setPaymentLink(String paymentLink) {
            this.paymentLink = paymentLink;
        }

        public String getLinkStatus() {
            return linkStatus;
        }

        public void setLinkStatus(String linkStatus) {
            this.linkStatus = linkStatus;
        }

        public String getQrimage() {
            return qrimage;
        }

        public void setQrimage(String qrimage) {
            this.qrimage = qrimage;
        }

        public String getQrStatus() {
            return qrStatus;
        }

        public void setQrStatus(String qrStatus) {
            this.qrStatus = qrStatus;
        }

        public String getUpiAddress() {
            return upiAddress;
        }

        public void setUpiAddress(String upiAddress) {
            this.upiAddress = upiAddress;
        }

        public String getUpiStatus() {
            return upiStatus;
        }

        public void setUpiStatus(String upiStatus) {
            this.upiStatus = upiStatus;
        }

        public Object getContectMapIfream() {
            return contectMapIfream;
        }

        public void setContectMapIfream(Object contectMapIfream) {
            this.contectMapIfream = contectMapIfream;
        }

        public String getFooterContent() {
            return footerContent;
        }

        public void setFooterContent(String footerContent) {
            this.footerContent = footerContent;
        }

        public String getFooterCopyright() {
            return footerCopyright;
        }

        public void setFooterCopyright(String footerCopyright) {
            this.footerCopyright = footerCopyright;
        }

        public String getAppStatus() {
            return appStatus;
        }

        public void setAppStatus(String appStatus) {
            this.appStatus = appStatus;
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
