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

            Label label8 = new Label(7,0,"机顶盒号");
            sheet.addCell(label8);

            Label label_9 = new Label(8,0,"智能卡1号");
            sheet.addCell(label_9);
            Label label_10 = new Label(9,0,"智能卡2号");
            sheet.addCell(label_10);
            Label label_11 = new Label(10,0,"智能卡3号");
            sheet.addCell(label_11);
            Label label_12 = new Label(11,0,"智能卡4号");
            sheet.addCell(label_12);

            Label label_13 = new Label(12,0,"产品信息1");
            sheet.addCell(label_13);
            Label label_14 = new Label(13,0,"产品信息2");
            sheet.addCell(label_14);
            Label label_15 = new Label(14,0,"产品信息3");
            sheet.addCell(label_15);
            Label label_16 = new Label(15,0,"产品信息4");
            sheet.addCell(label_16);

            Label label_17 = new Label(16,0,"产品1时间");
            sheet.addCell(label_17);
            Label label_18 = new Label(17,0,"产品2时间");
            sheet.addCell(label_18);
            Label label_19 = new Label(18,0,"产品3时间");
            sheet.addCell(label_19);
            Label label_20 = new Label(19,0,"产品4时间");
            sheet.addCell(label_20);

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
                //---------------------------------------------------------------------------
                String serviceId_1 = "";
                String serviceId_2 = "";
                String serviceId_3 = "";
                String serviceId_4 = "";
                String boxIds = "";
                ArrayList<ServiceInfo> serviceInfos = list.get(i - 1).getServiceInfos();
                for (int j = 0; j < serviceInfos.size(); j++) {
                    String relationOrder = serviceInfos.get(j).getRelationOrder();
                    String serviceId = serviceInfos.get(j).getServiceId();
                    String boxId = serviceInfos.get(j).getBoxId();
                    if (relationOrder.equals("母卡")) {
                        Label getLabel_8 = new Label(8,i,serviceId);
                        sheet.addCell(getLabel_8);
                        serviceId_1 = serviceId;
                    } else if (relationOrder.equals("第1子卡")) {
                        Label getLabel_9 = new Label(9,i,serviceId);
                        sheet.addCell(getLabel_9);
                        serviceId_2 = serviceId;
                    }else if (relationOrder.equals("第2子卡")) {
                        Label getLabel_10 = new Label(10,i,serviceId);
                        sheet.addCell(getLabel_10);
                        serviceId_3 = serviceId;
                    }else if (relationOrder.equals("第3子卡")) {
                        Label getLabel_11 = new Label(11,i,serviceId);
                        sheet.addCell(getLabel_11);
                        serviceId_4 = serviceId;
                    }
                    boxIds +=boxId;
                }
                Label getLabel_8 = new Label(7,i,boxIds);
                sheet.addCell(getLabel_8);
                //基本包产品Id 30046
                ArrayList<ProductInfo> productInfos = list.get(i - 1).getProductInfos();
                if (productInfos.size()>0) {
                    String productNames_1 = "";
                    String productNames_2 = "";
                    String productNames_3 = "";
                    String productNames_4 = "";

                    String productTimes_1 = "";
                    String productTimes_2 = "";
                    String productTimes_3 = "";
                    String productTimes_4 = "";

                    for (int j = 0; j < productInfos.size(); j++) {
//                        productNames += " " +productInfos.get(j).getProductName();
//                        productBeginDate+=" " +productInfos.get(j).getBillStartDate();
//                        productEndDate +=" " +productInfos.get(j).getBillEndDate();
                        String productId = productInfos.get(j).getProductId();
                        String serviceId = productInfos.get(j).getServiceId();
                        String productName = productInfos.get(j).getProductName();
                        if (serviceId.equals(serviceId_1)) {
                            productNames_1 += " " +productName;
                            if (productId.equals("30046")) {
                                String begin = productInfos.get(j).getBillStartDate();
                                String end = productInfos.get(j).getBillEndDate();
                                productTimes_1 = begin + "   " + end;
                            }
                        } else if (serviceId.equals(serviceId_2)) {
                            productNames_2 += " " +productName;
                            if (productId.equals("30046")) {
                                String begin = productInfos.get(j).getBillStartDate();
                                String end = productInfos.get(j).getBillEndDate();
                                productTimes_2 = begin + "   " + end;
                            }
                        }else if (serviceId.equals(serviceId_3)) {
                            productNames_3 += " " +productName;
                            if (productId.equals("30046")) {
                                String begin = productInfos.get(j).getBillStartDate();
                                String end = productInfos.get(j).getBillEndDate();
                                productTimes_3 = begin + "   " + end;
                            }
                        }else if (serviceId.equals(serviceId_4)) {
                            productNames_4 += " " +productName;
                            if (productId.equals("30046")) {
                                String begin = productInfos.get(j).getBillStartDate();
                                String end = productInfos.get(j).getBillEndDate();
                                productTimes_4 = begin + "   " + end;
                            }
                        }
                    }
                    Label getLabel_12 = new Label(12,i,productNames_1);
                    sheet.addCell(getLabel_12);
                    Label getLabel_13 = new Label(13,i,productNames_2);
                    sheet.addCell(getLabel_13);
                    Label getLabel_14 = new Label(14,i,productNames_3);
                    sheet.addCell(getLabel_14);
                    Label getLabel_15 = new Label(15,i,productNames_4);
                    sheet.addCell(getLabel_15);
                    //----------------------------------------------------------
                    Label getLabel_16 = new Label(16,i,productTimes_1);
                    sheet.addCell(getLabel_16);
                    Label getLabel_17 = new Label(17,i,productTimes_2);
                    sheet.addCell(getLabel_17);
                    Label getLabel_18 = new Label(18,i,productTimes_3);
                    sheet.addCell(getLabel_18);
                    Label getLabel_19 = new Label(19,i,productTimes_4);
                    sheet.addCell(getLabel_19);

                } else {
//                    Label getLabel10 = new Label(9,i,"无");
//                    sheet.addCell(getLabel10);
//                    Label getLabel11 = new Label(10,i,"无");
//                    sheet.addCell(getLabel11);
//                    Label getLabel12 = new Label(11,i,"无");
//                    sheet.addCell(getLabel12);
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
