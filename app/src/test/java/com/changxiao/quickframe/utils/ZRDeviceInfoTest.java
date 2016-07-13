package com.changxiao.quickframe.utils;

import com.changxiao.quickframe.base.ZRApplication;

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
public class ZRDeviceInfoTest {

    @Before
    public void setUp() throws Exception {
        ZRDeviceInfo.init(ZRApplication.applicationContext);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetOSName() throws Exception {
        System.out.print(ZRDeviceInfo.getOSName());
    }

    @Test
    public void testGetOSCode() throws Exception {
        System.out.print(ZRDeviceInfo.getOSCode());
    }

    @Test
    public void testGetDeviceName() throws Exception {
        System.out.print(ZRDeviceInfo.getDeviceName());
    }

    @Test
    public void testGetVersionRelease() throws Exception {
        System.out.print(ZRDeviceInfo.getVersionRelease());
    }

    @Test
    public void testGetVersionCodename() throws Exception {
        System.out.print(ZRDeviceInfo.getVersionCodename());
    }

    @Test
    public void testGetModel() throws Exception {
        System.out.print(ZRDeviceInfo.getModel());
    }

    @Test
    public void testGetDeviceID() throws Exception {
        System.out.print(ZRDeviceInfo.getDeviceID());
    }

    @Test
    public void testGetPackageInfo() throws Exception {
        System.out.print(ZRDeviceInfo.getPackageInfo());
    }

    @Test
    public void testGetClientVersion() throws Exception {
        System.out.print(ZRDeviceInfo.getClientVersion());
    }

    @Test
    public void testGetClientVersionName() throws Exception {
        System.out.print(ZRDeviceInfo.getClientVersionName());
    }

    @Test
    public void testGetResolution() throws Exception {
        System.out.print(ZRDeviceInfo.getResolution());
    }

    @Test
    public void testGetDeviceHeight() throws Exception {
        System.out.print(ZRDeviceInfo.getDeviceHeight());
    }

    @Test
    public void testGetDeviceWidth() throws Exception {
        System.out.print(ZRDeviceInfo.getDeviceWidth());
    }

    @Test
    public void testGetScreenSize() throws Exception {
        System.out.print(ZRDeviceInfo.getScreenSize());
    }

    @Test
    public void testDp2px() throws Exception {
        System.out.print(ZRDeviceInfo.dp2px(20));
    }

    @Test
    public void testPx2dp() throws Exception {
        System.out.print(ZRDeviceInfo.px2dp(20));
    }

    @Test
    public void testIsGpsEnable() throws Exception {
        System.out.print(ZRDeviceInfo.isGpsEnable());
    }
}