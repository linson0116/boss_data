package com.linson.domain;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/24.
 */
public class ServiceInfo {

    private String serviceId;
    private String userId;

    private String userType;
    private String userTypeName;
    private String servingStatus;
    private String servingStatusName;

//    private String feeId;
//    private String feeName;
//    private String feeValue;
    private String boxId;

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    private String relationOrder;


    private ArrayList<ProductInfo> productInfos = new ArrayList<>();

    //----------------------------------------------------------------
//    public String getFeeName() {
//        return feeName;
//    }
//
//    public void setFeeName(String feeName) {
//        this.feeName = feeName;
//    }

    @Override
    public String toString() {
        return System.lineSeparator()+"\t\t" +"ServiceInfo{" +
                "serviceId='" + serviceId + '\'' +
                ", boxId='" + boxId + '\'' +
                ", userId='" + userId + '\'' +
                ", userType='" + userType + '\'' +
                ", userTypeName='" + userTypeName + '\'' +
                ", servingStatus='" + servingStatus + '\'' +
                ", servingStatusName='" + servingStatusName + '\'' +
//                ", feeId='" + feeId + '\'' +
//                ", feeName='" + feeName + '\'' +
//                ", feeValue='" + feeValue + '\'' +
                ", relationOrder='" + relationOrder + '\'' +
                ", productInfos=" + productInfos +
                '}';
    }

    public String getRelationOrder() {
        return relationOrder;
    }

    public void setRelationOrder(String relationOrder) {
        this.relationOrder = relationOrder;
    }



//    public String getFeeId() {
//        return feeId;
//    }
//
//    public void setFeeId(String feeId) {
//        this.feeId = feeId;
//    }
//
//    public String getFeeValue() {
//        return feeValue;
//    }
//
//    public void setFeeValue(String feeValue) {
//        this.feeValue = feeValue;
//    }



    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<ProductInfo> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(ArrayList<ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getServingStatus() {
        return servingStatus;
    }

    public void setServingStatus(String servingStatus) {
        this.servingStatus = servingStatus;
    }

    public String getServingStatusName() {
        return servingStatusName;
    }

    public void setServingStatusName(String servingStatusName) {
        this.servingStatusName = servingStatusName;
    }

}
