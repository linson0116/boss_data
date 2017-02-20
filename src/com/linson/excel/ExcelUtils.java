package com.linson.excel;

import com.linson.domain.C71;
import com.linson.domain.Customer;
import com.linson.domain.ProductInfo;
import com.linson.domain.ServiceInfo;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class ExcelUtils {
    public static void writeExcel(String path) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(path));
            //创建工作薄
            WritableWorkbook workbook = Workbook.createWorkbook(os);
            //创建新的一页

            WritableSheet sheet = workbook.createSheet("First Sheet",0);
            //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
            Label xuexiao = new Label(0,0,"学校");
            sheet.addCell(xuexiao);
            Label zhuanye = new Label(1,0,"专业");
            sheet.addCell(zhuanye);
            Label jingzhengli = new Label(2,0,"专业竞争力");
            sheet.addCell(jingzhengli);

            Label qinghua = new Label(0,1,"清华大学");
            sheet.addCell(qinghua);
            Label jisuanji = new Label(1,1,"计算机专业");
            sheet.addCell(jisuanji);
            Label gao = new Label(2,1,"高");
            sheet.addCell(gao);
            //把创建的内容写入到输出流中，并关闭输出流
            workbook.write();
            workbook.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeExcelC71(String path, List<C71> list) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(path));
            //创建工作薄
            WritableWorkbook workbook = Workbook.createWorkbook(os);
            //创建新的一页

            WritableSheet sheet = workbook.createSheet("片区地址",0);
            //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
            Label F_AREA_NAME = new Label(0,0,"F_AREA_NAME");
            sheet.addCell(F_AREA_NAME);
            Label F_REGION_NAME1 = new Label(1,0,"F_REGION_NAME");
            sheet.addCell(F_REGION_NAME1);
            Label F_REGION_NAME2 = new Label(2,0,"F_REGION_NAME");
            sheet.addCell(F_REGION_NAME2);
            Label F_ACTIVE_DATE = new Label(3,0,"F_ACTIVE_DATE");
            sheet.addCell(F_ACTIVE_DATE);

            for (int i = 0; i < list.size(); i++) {
                Label getF_AREA_NAME = new Label(0,i,list.get(i).getF_AREA_NAME());
                sheet.addCell(getF_AREA_NAME);
                Label getF_REGION_NAME1 = new Label(1,i,list.get(i).getF_REGION_NAME1());
                sheet.addCell(getF_REGION_NAME1);
                Label getF_REGION_NAME2 = new Label(2,i,list.get(i).getF_REGION_NAME2());
                sheet.addCell(getF_REGION_NAME2);
                Label getF_ACTIVE_DATE = new Label(3,i,list.get(i).getF_ACTIVE_DATE());
                sheet.addCell(getF_ACTIVE_DATE);
            }

            //把创建的内容写入到输出流中，并关闭输出流
            workbook.write();
            workbook.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//    public static void writeExcelKangPing(String path, List<Customer> list) {
//        OutputStream os = null;
//        try {
//            os = new FileOutputStream(new File(path));
//            //创建工作薄
//            WritableWorkbook workbook = Workbook.createWorkbook(os);
//            //创建新的一页
//
//            WritableSheet sheet = workbook.createSheet("康平用户信息",0);
//            //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
//            Label label1 = new Label(0,0,"客户姓名");
//            sheet.addCell(label1);
//            Label label2 = new Label(1,0,"联系电话");
//            sheet.addCell(label2);
//            Label label3 = new Label(2,0,"地址");
//            sheet.addCell(label3);
//            Label label4 = new Label(3,0,"证件类型");
//            sheet.addCell(label4);
//
//            Label label5 = new Label(4,0,"证件号码");
//            sheet.addCell(label5);
//            Label label6 = new Label(5,0,"用户类型");
//            sheet.addCell(label6);
//
//            Label label7 = new Label(6,0,"智能卡号");
//            sheet.addCell(label7);
//            Label label8 = new Label(7,0,"机顶盒号");
//            sheet.addCell(label8);
//            Label label9 = new Label(8,0,"产品信息");
//            sheet.addCell(label9);
//            Label label10 = new Label(9,0,"产品开始时间");
//            sheet.addCell(label10);
//            Label label11 = new Label(10,0,"产品结束时间");
//            sheet.addCell(label11);
//
//            for (int i = 1; i < list.size()+1; i++) {
//                Label getLabel1 = new Label(0,i,list.get(i-1).getFirstName());
//                sheet.addCell(getLabel1);
//                Label getLabel2 = new Label(1,i,list.get(i-1).getTel());
//                sheet.addCell(getLabel2);
//
//                Label getLabel3 = new Label(2,i,list.get(i-1).getAddr());
//                sheet.addCell(getLabel3);
//
//                Label getLabel4 = new Label(3,i,list.get(i-1).getIdentityKindName());
//                sheet.addCell(getLabel4);
//
//                Label getLabel5 = new Label(4,i,list.get(i-1).getIdentityCode());
//                sheet.addCell(getLabel5);
//                Label getLabel6 = new Label(5,i,"普通用户");
//                sheet.addCell(getLabel6);
//
//                ArrayList<ServiceInfo> serviceInfos = list.get(i - 1).getServiceInfos();
//                String serviceIds = "";
//                String boxIds = "";
//                for (int j = 0; j < serviceInfos.size(); j++) {
//                    String relationOrder = serviceInfos.get(j).getRelationOrder();
//                    serviceIds += " " +relationOrder +" "+ serviceInfos.get(j).getServiceId();
//                    boxIds += " " + serviceInfos.get(j).getBoxId();
//                }
//                Label getLabel7 = new Label(6,i,serviceIds);
//                sheet.addCell(getLabel7);
//
//                Label getLabel8 = new Label(7,i,boxIds);
//                sheet.addCell(getLabel8);
//
//                ArrayList<ProductInfo> productInfos = list.get(i - 1).getProductInfos();
//                if (productInfos.size()>0) {
//                    String productNames = "";
//                    String productBeginDate = "";
//                    String productEndDate = "";
//                    for (int j = 0; j < productInfos.size(); j++) {
//                        productNames += " " +productInfos.get(j).getProductName();
//                        productBeginDate+=" " +productInfos.get(j).getBillStartDate();
//                        productEndDate +=" " +productInfos.get(j).getBillEndDate();
//                    }
//                    Label getLabel9 = new Label(8,i,productNames);
//                    sheet.addCell(getLabel9);
//                    Label getLabel10 = new Label(9,i,productBeginDate);
//                    sheet.addCell(getLabel10);
//                    Label getLabel11 = new Label(10,i,productEndDate);
//                    sheet.addCell(getLabel11);
//
//                } else {
//                    Label getLabel9 = new Label(8,i,"无");
//                    sheet.addCell(getLabel9);
//                    Label getLabel10 = new Label(9,i,"无");
//                    sheet.addCell(getLabel10);
//                    Label getLabel11 = new Label(10,i,"无");
//                    sheet.addCell(getLabel11);
//                }
//            }
//            //把创建的内容写入到输出流中，并关闭输出流
//            workbook.write();
//            workbook.close();
//            os.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (WriteException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public static void writeExcelKangPing(String path, List<Customer> list) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(path));
            //创建工作薄
            WritableWorkbook workbook = Workbook.createWorkbook(os);
            //创建新的一页

            WritableSheet sheet = workbook.createSheet("康平用户信息",0);
            //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
            Label label1 = new Label(0,0,"客户姓名");
            sheet.addCell(label1);
            Label label2 = new Label(1,0,"客户ID");
            sheet.addCell(label2);
            Label label3 = new Label(2,0,"联系电话");
            sheet.addCell(label3);
            Label label4 = new Label(3,0,"地址");
            sheet.addCell(label4);
            Label label5 = new Label(4,0,"证件类型");
            sheet.addCell(label5);

            Label label6 = new Label(5,0,"证件号码");
            sheet.addCell(label6);
            Label label7 = new Label(6,0,"用户类型");
            sheet.addCell(label7);

            Label label8 = new Label(7,0,"智能卡号");
            sheet.addCell(label8);
            Label label9 = new Label(8,0,"机顶盒号");
            sheet.addCell(label9);
            Label label10 = new Label(9,0,"产品信息");
            sheet.addCell(label10);
            Label label11 = new Label(10,0,"产品开始时间");
            sheet.addCell(label11);
            Label label12 = new Label(11,0,"产品结束时间");
            sheet.addCell(label12);

            for (int i = 1; i < list.size()+1; i++) {
                Label getLabel1 = new Label(0,i,list.get(i-1).getFirstName());
                sheet.addCell(getLabel1);
                Label getLabel2 = new Label(1,i,list.get(i-1).getCustomerId());
                sheet.addCell(getLabel2);
                Label getLabel3 = new Label(2,i,list.get(i-1).getTel());
                sheet.addCell(getLabel3);

                Label getLabel4 = new Label(3,i,list.get(i-1).getAddr());
                sheet.addCell(getLabel4);

                Label getLabel5 = new Label(4,i,list.get(i-1).getIdentityKindName());
                sheet.addCell(getLabel5);

                Label getLabel6 = new Label(5,i,list.get(i-1).getIdentityCode());
                sheet.addCell(getLabel6);
                Label getLabel7 = new Label(6,i,"普通用户");
                sheet.addCell(getLabel7);

                ArrayList<ServiceInfo> serviceInfos = list.get(i - 1).getServiceInfos();
                String serviceIds = "";
                String boxIds = "";
                for (int j = 0; j < serviceInfos.size(); j++) {
                    String relationOrder = serviceInfos.get(j).getRelationOrder();
                    serviceIds += " " +relationOrder +" "+ serviceInfos.get(j).getServiceId();
                    boxIds += " " + serviceInfos.get(j).getBoxId();
                }
                Label getLabel8 = new Label(7,i,serviceIds);
                sheet.addCell(getLabel8);

                Label getLabel9 = new Label(8,i,boxIds);
                sheet.addCell(getLabel9);

                ArrayList<ProductInfo> productInfos = list.get(i - 1).getProductInfos();
                if (productInfos.size()>0) {
                    String productNames = "";
                    String productBeginDate = "";
                    String productEndDate = "";
                    for (int j = 0; j < productInfos.size(); j++) {
                        productNames += " " +productInfos.get(j).getProductName();
                        productBeginDate+=" " +productInfos.get(j).getBillStartDate();
                        productEndDate +=" " +productInfos.get(j).getBillEndDate();
                    }
                    Label getLabel10 = new Label(9,i,productNames);
                    sheet.addCell(getLabel10);
                    Label getLabel11 = new Label(10,i,productBeginDate);
                    sheet.addCell(getLabel11);
                    Label getLabel12 = new Label(11,i,productEndDate);
                    sheet.addCell(getLabel12);

                } else {
                    Label getLabel10 = new Label(9,i,"无");
                    sheet.addCell(getLabel10);
                    Label getLabel11 = new Label(10,i,"无");
                    sheet.addCell(getLabel11);
                    Label getLabel12 = new Label(11,i,"无");
                    sheet.addCell(getLabel12);
                }
            }
            //把创建的内容写入到输出流中，并关闭输出流
            workbook.write();
            workbook.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
