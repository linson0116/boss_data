package com.linson.domain;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/24.
 */
public class Customer {
    private String customerId;
    private String firstName;
    private String tel;//电话
//    private String companyName;
    private String regionName;
    private String regionCode;
    private String identityKind;
    private String identityKindName;
    private String identityCode;
    private String addr;
    private String cityCode;
    private String cityName;

    private ArrayList<ServiceInfo> serviceInfos = new ArrayList<>();
    public ArrayList<ProductInfo> productInfos = new ArrayList<>();

    public ArrayList<ProductInfo> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(ArrayList<ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }

    public ArrayList<ServiceInfo> getServiceInfos() {
        return serviceInfos;
    }

    public void setServiceInfos(ArrayList<ServiceInfo> serviceInfos) {
        this.serviceInfos = serviceInfos;
    }

    public String getIdentityKindName() {
        return identityKindName;
    }

    public void setIdentityKindName(String identityKindName) {
        this.identityKindName = identityKindName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getIdentityKind() {
        return identityKind;
    }

    public void setIdentityKind(String identityKind) {
        this.identityKind = identityKind;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", tel='" + tel + '\'' +
                ", regionName='" + regionName + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", identityKind='" + identityKind + '\'' +
                ", identityKindName='" + identityKindName + '\'' +
                ", identityCode='" + identityCode + '\'' +
                ", addr='" + addr + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", serviceInfos=" + serviceInfos +
                ", productInfos=" + productInfos +
                '}';
    }
}
