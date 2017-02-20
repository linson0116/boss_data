package com.linson.utils;

/**
 * Created by Administrator on 2016/8/26.
 */
public class ConfigUtils {
    public static final String SERVER = "SERVER";
    public static final String CLIENT = "CLIENT";
    String url;
    String user;
    String password;

    String sql1;
    String dateFormat;
    public static final String JSON_CUSTOMERS_FILENAME = "customers.json";
//    public static final String JSON_DB_FILENAME = "db.json";
    public ConfigUtils(String type) {
        if (type.equals(SERVER)) {
            url = "jdbc:oracle:" + "thin:@10.11.1.4:1521:indb";;
            user = "crm_owner_user";
            password = "bss_crm_xxp1";
            //
            sql1 = "SELECT * from PRODUCT_USER.PM_PRODUCT_T";
            dateFormat = "yyyy-MM-dd HH:mm:ss";
        } else if (type.equals(CLIENT)) {
            url = "jdbc:oracle:" + "thin:@127.0.0.1:1521:orcl";
            user = "scott";
            password = "tiger";
            //
            sql1 = "SELECT * from PM_PRODUCT_T";
            dateFormat = "yyyy/MM/dd HH:mm:ss";
        }
    }
}
