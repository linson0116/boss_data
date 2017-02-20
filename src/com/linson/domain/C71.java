package com.linson.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class C71 {
    //71片区地址20160104
    //F_AREA_NAME  F_REGION_NAME  F_REGION_NAME  F_ACTIVE_DATE
    private String F_AREA_NAME;
    private String F_REGION_NAME1;
    private String F_REGION_NAME2;
    private String F_ACTIVE_DATE;

    private String F_REGION_ID;
    private String F_PARENT_REGION_ID;
    private List<String> F_AREA_ID = new ArrayList<String>();



    public String getF_AREA_NAME() {
        return F_AREA_NAME;
    }

    public void setF_AREA_NAME(String f_AREA_NAME) {
        F_AREA_NAME = f_AREA_NAME;
    }

    public String getF_REGION_NAME1() {
        return F_REGION_NAME1;
    }

    public void setF_REGION_NAME1(String f_REGION_NAME1) {
        F_REGION_NAME1 = f_REGION_NAME1;
    }

    public String getF_REGION_NAME2() {
        return F_REGION_NAME2;
    }

    public void setF_REGION_NAME2(String f_REGION_NAME2) {
        F_REGION_NAME2 = f_REGION_NAME2;
    }

    public String getF_ACTIVE_DATE() {
        return F_ACTIVE_DATE;
    }

    public void setF_ACTIVE_DATE(String f_ACTIVE_DATE) {
        F_ACTIVE_DATE = f_ACTIVE_DATE;
    }

    public String getF_REGION_ID() {
        return F_REGION_ID;
    }

    public void setF_REGION_ID(String f_REGION_ID) {
        F_REGION_ID = f_REGION_ID;
    }

    public String getF_PARENT_REGION_ID() {
        return F_PARENT_REGION_ID;
    }

    public void setF_PARENT_REGION_ID(String f_PARENT_REGION_ID) {
        F_PARENT_REGION_ID = f_PARENT_REGION_ID;
    }

    public List<String> getF_AREA_ID() {
        return F_AREA_ID;
    }

    public void setF_AREA_ID(List<String> f_AREA_ID) {
        F_AREA_ID = f_AREA_ID;
    }

    @Override
    public String toString() {
        return "C71{" +
                "F_AREA_NAME='" + F_AREA_NAME + '\'' +
                ", F_REGION_NAME1='" + F_REGION_NAME1 + '\'' +
                ", F_REGION_NAME2='" + F_REGION_NAME2 + '\'' +
                ", F_ACTIVE_DATE='" + F_ACTIVE_DATE + '\'' +
                ", F_REGION_ID='" + F_REGION_ID + '\'' +
                ", F_PARENT_REGION_ID='" + F_PARENT_REGION_ID + '\'' +
                ", F_AREA_ID=" + F_AREA_ID +
                '}';
    }
}

