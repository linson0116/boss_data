package com.linson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.linson.domain.C71;
import com.linson.domain.Customer;
import com.linson.excel.ExcelUtils;
import com.linson.utils.DBUtils;
import com.linson.utils.ToolsUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.linson.utils.ConfigUtils.JSON_CUSTOMERS_FILENAME;


public class Main {

    private static List<String> kangPingCustomerIdList;
    private static List<Customer> kangPingCustomerList = new ArrayList<>();

    public static void main(String[] args) {
        outputExcelByCityCode("975", "50");
    }

    public static String outputExcelByCityCode(String cityCode,String nums) {
        long beginTime = System.currentTimeMillis();
        String strDate = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(new Date());
        String path = "d:\\" + strDate + ".xls";
        kangPingCustomerIdList = DBUtils.getCityInfosByCityCode(cityCode);
        int size = kangPingCustomerIdList.size();
        if (nums == null || nums.equals("")) {
        } else {
            size = Integer.parseInt(nums);
        }
        int rowNum = 0;
        for (int i = 0; i < size; i++) {
            String customerId = kangPingCustomerIdList.get(i);
            Customer customer = searchByCustomerId(customerId);
            if (customer == null || customer.getAddr() == null ) {
            } else {
                kangPingCustomerList.add(customer);
                rowNum++;
            }
        }
        ExcelUtils.writeExcelKangPing(path,kangPingCustomerList);
        long endTime = System.currentTimeMillis();
        String time = (endTime - beginTime) / 1000 + "秒";
        String result = "共用时 " + time + " " + "导出 "+rowNum + " 条记录";
        System.out.println(result);
        return result;
    }

    private static List<C71> getC71() {
        List list1 = DBUtils.getC71_1();
        List<C71> list2 = DBUtils.getC71_2(list1);
        List<C71> list3 = DBUtils.getC71_3(list2);
        List<C71> list4 = DBUtils.getC71_4(list3);
        return list4;
    }

    public static String createDb2Json(int begin, int end, String[] arr) {
        return exportDB(arr, begin, end);
    }

    public static void createCustomerIdFile() {
        //查询并建立用户ID文件-------------------------------------
        LinkedList<String> linkedList = DBUtils.getCustomerIds(0);
        exportCustomers(linkedList);

    }

    private static void exportCustomers(LinkedList<String> list) {
        GsonBuilder builder = new GsonBuilder();
//        builder.setPrettyPrinting();
        Gson gson = builder.create();
        FileWriter fileWriter = null;
        String fileDir = ToolsUtils.getJarDir();
        try {
            fileWriter = new FileWriter(new File(new File(fileDir), JSON_CUSTOMERS_FILENAME));
            gson.toJson(list, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String exportDB(String[] arr, int begin, int end) {
        long beginTime = System.currentTimeMillis();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        FileWriter fileWriter = null;
        String filePath = ToolsUtils.getJarDir();
        try {
            String fileName = "db_" + begin + "_" + end + ".json";
            fileWriter = new FileWriter(new File(new File(filePath), fileName));
            int num;
            if (end > arr.length) {
                num = arr.length;
            } else {
                num = end;
            }

            for (int i = begin - 1; i < num; i++) {
                String id = arr[i];
                id = id.substring(1, id.length() - 1);
                Customer customer = getSingleCustomerByCustomerId(id);
                gson.toJson(customer, fileWriter);
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                    long endTime = System.currentTimeMillis();
                    String time = (endTime - beginTime) / 1000 + "秒";
                    System.out.println(time);
                    return time;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
    //server
    public static String inputCustomerIdORServiceId(String searchId) {
        System.out.println("请输入客户编号或者IC卡号：");
        String id = "";
        if (null == searchId || searchId.equals("")) {
            Scanner sc = new Scanner(System.in);
            String inputId = sc.nextLine();
            id = inputId;
        } else {
            id = searchId;
        }

        String currentDate = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒").format(new Date());
        String fileName = id + "_" + currentDate + ".txt";

        String filePath = "c://" + fileName;
        File file = new File(filePath);

        Customer customer = null;
        //------------------------------------------------
        if (id.length() < 15) {
            customer = getSingleCustomerByCustomerId(id);
        } else {
            customer = getSingleCustomerByServiceId(id);
        }
        System.out.println(customer);
        System.out.println("查询-->" + filePath);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String strJson = gson.toJson(customer);
        return strJson;
    }

    public static Customer getSingleCustomer(String searchId) {
        Customer customer = getSingleCustomerByCustomerId(searchId);
        return customer;
    }

    //ByServiceId
    public static Customer getSingleCustomerByServiceId(String serviceId) {
        String customerId = DBUtils.getCustomerIdByServiceId(serviceId);
        if (customerId != null) {
            return getSingleCustomerByCustomerId(customerId);
        }
        return null;
    }

    //ByCustomerId
    public static Customer getSingleCustomerByCustomerId(String customerId) {
        Customer customer = searchByCustomerId(customerId);
        return customer;
    }

    private static Customer searchByCustomerId(String customerId) {
        Customer customer = DBUtils.getCustomer(customerId);
        if (customer == null) {
        } else {
            customer = DBUtils.getCityName(customer);
            //System.out.println(customer);
            customer = DBUtils.getTel(customer);
            //System.out.println(customer);
            customer = DBUtils.getRegionName(customer);
            //System.out.println(customer);
            customer = DBUtils.getServiceId(customer);
            //System.out.println(customer);
            customer = DBUtils.getUserTypeName(customer);
            //System.out.println(customer);
            customer = DBUtils.getServingStatusName(customer);
            //System.out.println(customer);
            customer = DBUtils.getProducts(customer);
            //System.out.println(customer);
            customer = DBUtils.getProductNames(customer);
            //System.out.println(customer);
            customer = DBUtils.getBoxIds(customer);
            //System.out.println(customer);
        }
//        customer = DBUtils.getIdentityKindName(customer);
//        System.out.println(customer);
//        customer = DBUtils.getAddr(customer);
//        System.out.println(customer);
//        customer = DBUtils.getFees(customer);
//        System.out.println(customer);
//        customer = DBUtils.getFeeNames(customer);
        return customer;
    }
}
