package com.linson.utils;

import com.linson.Main;

import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/**
 * Created by Administrator on 2016/8/26.
 */
public class ToolsUtils {
    public static String trimAll(String str) {
        if (str != null)
            return str.trim();
        else
            return null;
    }

    public static String getJarDir() {
        URL url = new Main().getClass().getProtectionDomain().getCodeSource().getLocation();
        String path = url.getPath();
        path = path.substring(0, path.lastIndexOf('/'));
        return path;
    }

    public static String readFile2String(File file) {
        InputStream is = null;
        ByteArrayOutputStream os = null;
        try {
            is = new FileInputStream(file);
            byte[] buff = new byte[1024];
            int len = -1;
            os = new ByteArrayOutputStream();
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
            }
            String str = os.toString();
            return str;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String[] getCustomersNum(String str) {
        str = str.substring(1, str.length() - 1);
        String[] split = str.split(",");
        return split;
    }

    public static String md5Encode(String psd) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(psd.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : bytes) {
                int i = b & 0xff;
                String hexString = Integer.toHexString(i);
                if (hexString.length() < 2) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(md5Encode("bd120831"));
    }
}
