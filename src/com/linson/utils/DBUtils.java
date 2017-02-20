package com.linson.utils;

import com.linson.domain.C71;
import com.linson.domain.Customer;
import com.linson.domain.ProductInfo;
import com.linson.domain.ServiceInfo;

import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/24.
 */
public class DBUtils {
    private static Connection conn = null;
    private static String password;
    private static String url;
    private static String user;
    private static ConfigUtils configUtils;
    private static HashMap<String, String> identityKindHashMap = new HashMap<>();
    private static HashMap<String, String> cityCodeHashMap = new HashMap<>();
    private static HashMap<String, String> userTypeHashMap = new HashMap<>();
    private static HashMap<String, String> productNameHashMap = new HashMap<>();
    private static HashMap<String, String> servingStatusNameHashMap = new HashMap<>();
    static {
        try {
            configUtils = new ConfigUtils(ConfigUtils.SERVER);
            Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
             //url = "jdbc:oracle:" + "thin:@127.0.0.1:1521:orcl";// 127.0.0.1是本机地址，orcl是Oracle的默认数据库名
             //user = "scott";// 用户名
             //password = "tiger";// 密码
            url = configUtils.url;
            user = configUtils.user;
            password = configUtils.password;
            //固定信息初始化
            initIdentityKind();
            initCityCode();
            initUserType();
            initProductName();
            initServingStatusName();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initServingStatusName() {
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "SELECT * from BB_SERVING_STATUS_T";//" where KIND = ?";
        try {
            pre = getConn().prepareStatement(sql);
//            if (customer.getServiceInfos().size() > 0) {
//                pre.setString(1, customer.getServiceInfos().get(0).getServingStatus());
//            } else {
//                return customer;
//            }

            result = pre.executeQuery();
            while (result.next()) {
                servingStatusNameHashMap.put(result.getString("KIND"), result.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pre != null)
                    pre.close();
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void initProductName() {
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = configUtils.sql1;
        try {
            pre = getConn().prepareStatement(sql);
            //pre.setString(1, productId);
            result = pre.executeQuery();
            while (result.next()) {
                //return result.getString("F_PROD_NAME");
                productNameHashMap.put(result.getString("F_PROD_ID"),result.getString("F_PROD_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pre.close();
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void initUserType() {
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "SELECT * from BB_USER_TYPE_T";
        try {
            pre = getConn().prepareStatement(sql);
            result = pre.executeQuery();
            while (result.next()) {
                userTypeHashMap.put(result.getString("USER_TYPE"), result.getString("REMARK"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pre != null)
                    pre.close();
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void initCityCode() {
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "SELECT * from BS_CITY_ID_T";
        try {
            pre = getConn().prepareStatement(sql);
//            pre.setString(1,customer.getCityCode());
            result = pre.executeQuery();
            while (result.next()) {
                cityCodeHashMap.put(result.getString("CITY_CODE"), result.getString("CITY_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pre != null)
                    pre.close();
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void initIdentityKind() {
        identityKindHashMap.put("1","身份证");
        identityKindHashMap.put("2","未知");
        identityKindHashMap.put("3","军官证");
        identityKindHashMap.put("4","护照");
        identityKindHashMap.put("5","驾驶证");
        identityKindHashMap.put("6","工作证");
        identityKindHashMap.put("7","暂住证");
        identityKindHashMap.put("8","士兵证");
        identityKindHashMap.put("9","营业执照");
        identityKindHashMap.put("10","警官证");
    }


    public static Connection getConn() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return conn;
    }

    public static Customer getCustomer(String customer_id) {
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "SELECT * from BB_CUSTOMER_INFO_T t where t.CUSTOMER_ID = ? and t.logout_date is null";
        try {
            pre = getConn().prepareStatement(sql);
            pre.setString(1,customer_id);
            result = pre.executeQuery();
            while (result.next()) {
                //注销日期
                String logout_date = result.getString("LOGOUT_DATE");
                if (logout_date != null && !logout_date.equals(""))
                    break;
                Customer customer = new Customer();
                customer.setFirstName(ToolsUtils.trimAll(result.getString("FIRST_NAME")));
                //CUSTOMER_ID
                customer.setCustomerId(result.getString("CUSTOMER_ID"));
                //IDENTITY_KIND
                customer.setIdentityKind(result.getString("IDENTITY_KIND"));
                //IdentityKindName
                customer.setIdentityKindName(identityKindHashMap.get(customer.getIdentityKind()));
                //IDENTITY_CODE
                customer.setIdentityCode(result.getString("IDENTITY_CODE"));
                //CITY_CODE
                customer.setCityCode(result.getString("CITY_CODE"));
                //REGION_CODE
                customer.setRegionCode(result.getString("REGION_CODE"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pre != null)
                    pre.close();
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Customer getCityName(Customer customer) {
        if (cityCodeHashMap.isEmpty()) {
            PreparedStatement pre = null;
            ResultSet result = null;
            String sql = "SELECT * from BS_CITY_ID_T where CITY_CODE = ?";
            try {
                pre = getConn().prepareStatement(sql);
                pre.setString(1,customer.getCityCode());
                result = pre.executeQuery();
                while (result.next()) {
                    customer.setCityName(result.getString("CITY_NAME"));
                    return customer;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (pre != null)
                        pre.close();
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            customer.setCityName(cityCodeHashMap.get(customer.getCityCode()));
        }

        return customer;
    }
    public static Customer getTel(Customer customer) {
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "SELECT * from CM_CONTACT_T where F_CUSTOMER_ID = ?";
        try {
            pre = getConn().prepareStatement(sql);
            pre.setString(1,customer.getCustomerId());
            result = pre.executeQuery();
            while (result.next()) {
                customer.setTel(ToolsUtils.trimAll(result.getString("F_CONTACT_DETAIL")));
                System.out.println(ToolsUtils.trimAll(result.getString("F_CONTACT_DETAIL")));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pre != null)
                    pre.close();
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }
    public static Customer getRegionName(Customer customer) {
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "SELECT * from OM_REGION_T where F_CITY_CODE = ?";
        try {
            pre = getConn().prepareStatement(sql);
            pre.setString(1, customer.getRegionCode());
            result = pre.executeQuery();
            while (result.next()) {
                customer.setRegionName(result.getString("F_REGION_NAME"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pre != null)
                    pre.close();
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }
//    public static Customer getIdentityKindName(Customer customer) {
//        PreparedStatement pre = null;
//        ResultSet result = null;
//        String sql = "SELECT * from OM_CERT_TYPE_T where F_CERT_TYPE = ?";
//        try {
//            pre = getConn().prepareStatement(sql);
//            pre.setString(1, customer.getIdentityKind());
//            result = pre.executeQuery();
//            while (result.next()) {
//                customer.setIdentityKindName(result.getString("F_CERT_TYPE_DESC"));
//                return customer;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if (pre != null)
//                    pre.close();
//                if (result != null)
//                    result.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return customer;
//    }

    public static Customer getAddr(Customer customer) {
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "SELECT * from BB_SERVICE_RELATION_T where CUSTOMER_ID = ?";
        try {
            pre = getConn().prepareStatement(sql);
            pre.setString(1, customer.getCustomerId());
//            System.out.println(customer.getCustomerId());
            result = pre.executeQuery();
            while (result.next()) {
                customer.setAddr(result.getString("INSTALL_ADDRESS"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pre != null)
                    pre.close();
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }
    //-----------------------------------------------------------------
    public static Customer getServiceId(Customer customer) {
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "SELECT * from BB_SERVICE_RELATION_T t where t.CUSTOMER_ID = ?  and t.IF_VALID = 1  and t.install_address is not null";
        try {
            pre = getConn().prepareStatement(sql);
            pre.setString(1, customer.getCustomerId());
            result = pre.executeQuery();
            while (result.next()) {
                ServiceInfo serviceInfo = new ServiceInfo();
                serviceInfo.setServiceId(result.getString("SERVICE_ID"));
                serviceInfo.setUserType(result.getString("USER_TYPE"));
                serviceInfo.setServingStatus(result.getString("SERVING_STATUS"));
                serviceInfo.setUserId(result.getString("USER_ID"));
                serviceInfo.setRelationOrder(getRelationOrder(serviceInfo).getRelationOrder());

                customer.getServiceInfos().add(serviceInfo);
                customer.setAddr(result.getString("INSTALL_ADDRESS"));
            }
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pre != null)
                    pre.close();
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }
    public static Customer getUserTypeName(Customer customer) {
        for (ServiceInfo info:customer.getServiceInfos()) {
            info.setUserTypeName(userTypeHashMap.get(info.getUserType()));
        }
        return customer;
    }
    public static Customer getServingStatusName(Customer customer) {
        for (ServiceInfo info:customer.getServiceInfos()) {
            info.setServingStatusName(servingStatusNameHashMap.get(info.getServingStatus()));
        }
        return customer;
    }

    public static Customer getProducts(Customer customer) {
        for (ServiceInfo serviceInfo :customer.getServiceInfos()) {
            ArrayList<ProductInfo> list = getProduct(serviceInfo.getUserId());
            serviceInfo.setProductInfos(list);
        }
        return customer;
    }
    public static ArrayList<ProductInfo> getProduct(String userId) {
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "SELECT * from BB_USER_PRODUCT_INFO_T where F_USER_ID = ?";
        ArrayList<ProductInfo> list = new ArrayList<>();
        try {
            pre = getConn().prepareStatement(sql);
            pre.setString(1, userId);
            result = pre.executeQuery();
            while (result.next()) {
                //过期产品判断日期不显示
                Date date = new SimpleDateFormat(configUtils.dateFormat).parse(result.getString("F_BILL_END_DATE"));
                if (date.before(new Date())) {
                    continue;
                }
                ProductInfo info = new ProductInfo();
                info.setProductId(result.getString("F_PRODUCT_ID"));
                info.setBillStartDate(result.getString("F_BILL_START_DATE"));
                info.setBillEndDate(result.getString("F_BILL_END_DATE"));
                info.setProductStopDate(result.getString("F_PRODUCT_STOP_DATE"));
                info.setProductPrice(result.getString("F_PRODUCT_PRICE"));
                list.add(info);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                pre.close();
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Customer getProductNames(Customer customer) {
        for (ServiceInfo serviceInfo:customer.getServiceInfos()) {
            for (ProductInfo productInfo:serviceInfo.getProductInfos()) {
                productInfo.setProductName(getProductName(productInfo.getProductId()));
                customer.getProductInfos().add(productInfo);
            }
        }
        return customer;
    }


    /*
    * 修改服务器对应表名称 PM_PRODUCT_T
    * */
    public static String getProductName(String productId) {
        return productNameHashMap.get(productId);
//        PreparedStatement pre = null;
//        ResultSet result = null;
//        String sql = "SELECT * from PM_PRODUCT_T where F_PROD_ID = ?";
//        sql = configUtils.sql1;
//        try {
//            pre = getConn().prepareStatement(sql);
//            pre.setString(1, productId);
//            result = pre.executeQuery();
//            while (result.next()) {
//                return result.getString("F_PROD_NAME");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                pre.close();
//                result.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
    }

//    public static Customer getFees(Customer customer) {
//        for (ServiceInfo serviceInfo:customer.getServiceInfos()) {
//            getFee(serviceInfo);
//        }
//        return customer;
//    }
//    public static ServiceInfo getFee(ProductInfo productInfo) {
//        PreparedStatement pre = null;
//        ResultSet result = null;
//        String sql = "SELECT * from BB_HAVE_PAY_T where SERVICE_ID = ?";
//        try {
//            pre = getConn().prepareStatement(sql);
//            pre.setString(1, serviceInfo.getServiceId());
//            result = pre.executeQuery();
//            while (result.next()) {
//                serviceInfo.setFeeId(result.getString("FEE_ID"));
//                //serviceInfo.setFeeValue(result.getString("FEE_VALUE"));
//                return serviceInfo;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                pre.close();
//                result.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//    public static Customer getFeeNames(Customer customer) {
//        for (ServiceInfo serviceInfo:customer.getServiceInfos()) {
//            getFeeName(serviceInfo);
//        }
//        return customer;
//    }
//    public static ServiceInfo getFeeName(ServiceInfo serviceInfo) {
//        PreparedStatement pre = null;
//        ResultSet result = null;
//        String sql = "SELECT * from BB_BUS_FEE_KIND_T where FEE_ID = ?";
//        try {
//            pre = getConn().prepareStatement(sql);
//            pre.setString(1, serviceInfo.getFeeId());
//            result = pre.executeQuery();
//            while (result.next()) {
//                serviceInfo.setFeeName(result.getString("FEE_NAME"));
//                return serviceInfo;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                pre.close();
//                result.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
    public static ServiceInfo getRelationOrder(ServiceInfo serviceInfo) {
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "SELECT * from BB_MOTHER_CHILD_INFO_T where SERVICE_ID = ?";
        try {
            pre = getConn().prepareStatement(sql);
            pre.setString(1, serviceInfo.getServiceId());
            result = pre.executeQuery();

            if (result.next()) {
                serviceInfo.setRelationOrder(getRelationOrderName(result.getString("RELATION_ORDER")));
                return serviceInfo;
            } else {
                serviceInfo.setRelationOrder(getRelationOrderName(null));
                return serviceInfo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pre.close();
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return serviceInfo;
    }

    public static String getRelationOrderName(String order) {
        if (order == null || order.equals("0")) {
            return "母卡";
        } else {
            return "第" + order + "子卡";
        }
    }
    public static Customer getBoxIds(Customer customer) {
        for (ServiceInfo serviceInfo:customer.getServiceInfos()) {
            getBoxId(serviceInfo);
        }
        return customer;
    }
    public static ServiceInfo getBoxId(ServiceInfo serviceInfo) {
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "SELECT * from BB_TV_SERVICE_INFO_T where SERVICE_ID = ?";
        try {
            pre = getConn().prepareStatement(sql);
            pre.setString(1, serviceInfo.getServiceId());
            result = pre.executeQuery();

            if (result.next()) {
                serviceInfo.setBoxId(result.getString("SET_TOP_BOX"));
                return serviceInfo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pre.close();
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return serviceInfo;
    }

    public static String getCustomerIdByServiceId(String serviceId) {
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "SELECT * from BB_SERVICE_RELATION_T where SERVICE_ID = ?";
        try {
            pre = getConn().prepareStatement(sql);
            pre.setString(1, serviceId);
            result = pre.executeQuery();
            if (result.next()) {
                return result.getString("CUSTOMER_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pre.close();
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static LinkedList<String> getCustomerIds(int num) {
        LinkedList<String> linkedList = new LinkedList<String>();
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "SELECT t.CUSTOMER_ID from BB_CUSTOMER_INFO_T t where rowNum <= ?";
        if (num == 0) {
            sql = "SELECT t.CUSTOMER_ID from BB_CUSTOMER_INFO_T t";
        }
        try {
            pre = getConn().prepareStatement(sql);
            if (num == 0) {
            } else {
                pre.setInt(1, num);
            }
            result = pre.executeQuery();
            while (result.next()) {
                String id = result.getString("CUSTOMER_ID");
                linkedList.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pre.close();
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return linkedList;
    }

    //--------------------------------------------------------------------------------
    //Demo
    public static List get() {
        List list = new ArrayList();
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "SELECT * from CM_CONTACT_T where F_CUSTOMER_ID = ?";
        try {
            pre = getConn().prepareStatement(sql);
            pre.setString(1,"");
            result = pre.executeQuery();
            while (result.next()) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pre != null)
                    pre.close();
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    //--------------------------------------------------------------------------
    public static List<String> getKangPing() {
        List<String> list = new ArrayList<>();
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "select t.customer_id from Bb_Customer_Info_t t where t.city_code='975'";
        try {
            pre = getConn().prepareStatement(sql);

            result = pre.executeQuery();
            while (result.next()) {
                list.add(result.getString("customer_id"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pre != null)
                    pre.close();
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public static List<String> getCityInfosByCityCode(String cityCode) {
        List<String> list = new ArrayList<>();
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "select t.customer_id from Bb_Customer_Info_t t where t.city_code=" + cityCode;
        try {
            pre = getConn().prepareStatement(sql);

            result = pre.executeQuery();
            while (result.next()) {
                list.add(result.getString("customer_id"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pre != null)
                    pre.close();
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public static List<C71> getC71_1() {
        List<C71> list = new ArrayList<C71>();
        PreparedStatement pre = null;
        ResultSet result = null;
        String sql = "SELECT t.F_ACTIVE_DATE,t.F_REGION_NAME,t.F_REGION_ID,t.F_PARENT_REGION_ID " +
                "from OM_REGION_T t where t.F_REGION_LEVEL = ?";
        try {
            pre = getConn().prepareStatement(sql);
            pre.setString(1,"5");
            result = pre.executeQuery();
            while (result.next()) {
                C71 c = new C71();
                c.setF_ACTIVE_DATE(result.getString("F_ACTIVE_DATE"));
                c.setF_REGION_NAME2(result.getString("F_REGION_NAME"));
                c.setF_REGION_ID(result.getString("F_REGION_ID"));
                c.setF_PARENT_REGION_ID(result.getString("F_PARENT_REGION_ID"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pre != null)
                    pre.close();
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public static List<C71> getC71_2(List<C71> list) {
        for (C71 c: list) {
            String id = c.getF_PARENT_REGION_ID();
            PreparedStatement pre = null;
            ResultSet result = null;
            String sql = "SELECT t.F_REGION_NAME from OM_REGION_T t where t.F_REGION_LEVEL = 4 and t.F_REGION_ID = ?";
            try {
                pre = getConn().prepareStatement(sql);
                pre.setString(1,id);
                result = pre.executeQuery();
                while (result.next()) {
                    c.setF_REGION_NAME1(result.getString("F_REGION_NAME"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (pre != null)
                        pre.close();
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
    public static List<C71> getC71_3(List<C71> list) {
        for (C71 c: list) {
            String id = c.getF_REGION_ID();
            PreparedStatement pre = null;
            ResultSet result = null;
//            String sql = "select t.F_AREA_NAME from OM_AREA_T t " +
//                    "where t.f_area_id = (select t1.f_area_id from OM_AREA_REGION_RELA_T t1 where t1.f_region_id = ?)";
            String sql = "select t1.f_area_id from OM_AREA_REGION_RELA_T t1 where t1.f_region_id = ?";
            try {
                pre = getConn().prepareStatement(sql);
                pre.setString(1,id);
                result = pre.executeQuery();
                while (result.next()) {
                    c.getF_AREA_ID().add(result.getString("f_area_id"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(c.getF_REGION_ID());
            }finally {
                try {
                    if (pre != null)
                        pre.close();
                    if (result != null)
                        result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
    public static List<C71> getC71_4(List<C71> list) {
        List<C71> myList = new ArrayList<C71>();
        for (C71 c: list) {
            for (String id : c.getF_AREA_ID()) {
                PreparedStatement pre = null;
                ResultSet result = null;
                String sql = "select t.F_AREA_NAME from OM_AREA_T t " +
                        "where t.f_area_id = ?";
                try {
                    pre = getConn().prepareStatement(sql);
                    pre.setString(1,id);
                    result = pre.executeQuery();
                    while (result.next()) {
                        if ( c.getF_AREA_NAME() == null || c.getF_AREA_NAME().equals("")) {
                            c.setF_AREA_NAME(result.getString("F_AREA_NAME"));
                        } else {
                            C71 cc = new C71();
                            cc.setF_AREA_NAME(result.getString("F_AREA_NAME"));
                            cc.setF_ACTIVE_DATE(c.getF_ACTIVE_DATE());
                            cc.setF_REGION_NAME1(c.getF_REGION_NAME1());
                            cc.setF_REGION_NAME2(c.getF_REGION_NAME2());
                            cc.setF_REGION_ID(c.getF_REGION_ID());
                            cc.setF_PARENT_REGION_ID(c.getF_PARENT_REGION_ID());
                            cc.setF_AREA_ID(c.getF_AREA_ID());
//                            System.out.println(c);
//                            System.out.println(cc);
                            myList.add(cc);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println(c.getF_REGION_ID());
                }finally {
                    try {
                        if (pre != null)
                            pre.close();
                        if (result != null)
                            result.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
//        System.out.println(list.size());
        for (C71 cc:myList) {
            list.add(cc);
        }
//        System.out.println(list.size());
        return list;
    }
}
