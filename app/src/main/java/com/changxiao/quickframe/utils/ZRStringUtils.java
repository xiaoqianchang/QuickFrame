package com.changxiao.quickframe.utils;

import android.app.ActivityManager;
import android.content.Context;

import com.changxiao.quickframe.base.ZRApplication;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String utils used for application.
 * <p>
 * Created by Chang.Xiao on 2016/7/1.
 *
 * @version 1.0
 */
public class ZRStringUtils {

    /**
     * 测试IP地址是否合法
     *
     * @param ipAddress 192.168.21.6
     * @return
     */
    public static boolean isIp(String ipAddress) {
        Pattern pattern = Pattern
                .compile("(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})"
                        + "\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})"
                        + "\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})"
                        + "\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})");
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

    /**
     * Get MD5
     *
     * @param content
     * @return
     */
    public static String getMD5(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            String result = getHashString(digest);
            ZRLog.e("getMD5 contentLength:" + content.length() + ", md5 = "
                    + result);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getHashString(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return builder.toString();
    }

    /**
     * 过滤emoj表情
     *
     * @param content
     * @return
     */
    public static boolean isEmoj(String content) {
        String reg = "^([a-z]|[A-Z]|[0-9]|[\u2E80-\u9FFF]){3,}|@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?|[wap.]{4}|[www.]{4}|[blog.]{5}|[bbs.]{4}|[.com]{4}|[.cn]{3}|[.net]{4}|[.org]{4}|[http://]{7}|[ftp://]{6}$";
        Pattern pattern = Pattern.compile(reg);
        // 正则匹配是否是表情符号
        Matcher matcher = pattern.matcher(content);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * URL检查<br>
     * <br>
     *
     * @param pInput 要检查的字符串<br>
     * @return boolean 返回检查结果<br>
     */
    public static boolean isUrl(String pInput) {
        if (pInput == null) {
            return false;
        }
        boolean res = false;
        if (pInput.startsWith("http://") || pInput.startsWith("https://"))
            res = true;
        return res;
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2)
            throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");// 注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);// 取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length()
                - versionArray2[idx].length()) == 0// 先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {// 再比较字符
            ++idx;
        }
        // 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    /**
     * 格式化双精度数据
     *
     * @param d
     * @return
     */
    public static String getDecimalFormat(double d) {
        try {
            DecimalFormat _df = new DecimalFormat("######0.00");
            return _df.format(d);
        } catch (Exception e) {
            e.printStackTrace();
            return "0.00";
        }
    }

    /**
     * 格式化手机号
     *
     * @param str
     * @return          135*****527
     */
    public static String getFormatPhone(String str) {
        String phoneNumber = str;
        String flgStr = "*****";
        if (str.length() > 10) {
            String preStr = phoneNumber.substring(0, 3);
            String lastStr = phoneNumber.substring(8, 11);
            return preStr + flgStr + lastStr;
        }
        return phoneNumber;
    }

    public static String getFormatDecimal(String format) {
        try {
            BigDecimal input = new BigDecimal(format);
            if (input.scale() > 2) {
                return input.setScale(2, RoundingMode.FLOOR).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return format;
    }

    /**
     * Get UUID
     *
     * @return
     */
    public static String UUID() {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString().replaceAll("-", "");
        return s;
    }

    /**
     * Get current visible activity
     *
     * @return
     */
    public static String getRunningActivityName(){
        ActivityManager activityManager=(ActivityManager) ZRApplication.applicationContext.getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        return runningActivity;
    }
}
