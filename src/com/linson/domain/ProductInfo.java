package com.linson.domain;

/**
 * Created by Administrator on 2016/8/24.
 */
public class ProductInfo {
    //private String serviceId;
    private String productId;
    private String productName;
    private String billStartDate;
    private String billEndDate;
    private String productPrice;

//    private String feeId;
//    private String feeName;

    private String productStopDate;
    //-------------------------------------------------

//    public String getFeeId() {
//        return feeId;
//    }
//
//    public void setFeeId(String feeId) {
//        this.feeId = feeId;
//    }
//
//    public String getFeeName() {
//        return feeName;
//    }
//
//    public void setFeeName(String feeName) {
//        this.feeName = feeName;
//    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductStopDate() {
        return productStopDate;
    }

    public void setProductStopDate(String productStopDate) {
        this.productStopDate = productStopDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBillStartDate() {
        return billStartDate;
    }

    public void setBillStartDate(String billStartDate) {
        this.billStartDate = billStartDate;
    }

    public String getBillEndDate() {
        return billEndDate;
    }

    public void setBillEndDate(String billEndDate) {
        this.billEndDate = billEndDate;
    }

    @Override
    public String toString() {
        return System.lineSeparator() + "\t" + "\t" + "\t" + "ProductInfo{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", billStartDate='" + billStartDate + '\'' +
                ", billEndDate='" + billEndDate + '\'' +
//                ", feeId='" + feeId + '\'' +
//                ", feeName='" + feeName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productStopDate='" + productStopDate + '\'' +
                '}';
    }
}
