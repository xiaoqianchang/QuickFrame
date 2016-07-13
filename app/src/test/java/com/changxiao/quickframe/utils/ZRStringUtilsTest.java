package com.changxiao.quickframe.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * $desc$
 * <p/>
 * Created by Chang.Xiao on 2016/7/4.
 *
 * @version 1.0
 */
public class ZRStringUtilsTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testIsIp() throws Exception {
        String str = "https://www.baidu.com/";
        String str2 = "192.168.21.6";
        System.out.println(ZRStringUtils.isIp(str));
        System.out.print(ZRStringUtils.isIp(str2));
    }

    @Test
    public void testGetMD5() throws Exception {
        String str = "zxcvbnm";
        System.out.print(ZRStringUtils.getMD5(str));
    }

    @Test
    public void testIsEmoj() throws Exception {
        String str = "0.0";
        System.out.print(ZRStringUtils.isEmoj(str));
    }

    @Test
    public void testIsUrl() throws Exception {
        String str = "https://www.baidu.com/";
        System.out.print(ZRStringUtils.isUrl(str));
    }

    @Test
    public void testCompareVersion() throws Exception {
        String version1 = "2.0.0";
        String version2 = "2.0.1";
        System.out.print(ZRStringUtils.compareVersion(version1, version2));
    }

    @Test
    public void testGetDecimalFormat() throws Exception {
        System.out.print("双精度:" + ZRStringUtils.getDecimalFormat(2.0));
    }

    @Test
    public void testGetFormatPhone() throws Exception {
        String phone = "13564228527";
        System.out.print(ZRStringUtils.getFormatPhone(phone));
    }

    @Test
    public void testUUID() throws Exception {
        System.out.print(ZRStringUtils.UUID());
    }

    @Test
    public void testGetRunningActivityName() throws Exception {

    }
}