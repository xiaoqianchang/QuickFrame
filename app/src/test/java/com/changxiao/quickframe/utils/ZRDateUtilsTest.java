package com.changxiao.quickframe.utils;

import android.util.Log;

import org.junit.*;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * $desc$
 * <p/>
 * Created by Chang.Xiao on 2016/7/1.
 *
 * @version 1.0
 */
public class ZRDateUtilsTest {

    private static String TAG = ZRDateUtilsTest.class.getSimpleName();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testGetCurrentTimeMillis() throws Exception {
        //expected: 6, sum of 1 and 5
        assertEquals(System.currentTimeMillis(), ZRDateUtils.getCurrentTimeMillis(), 0);
        System.out.print(ZRDateUtils.getCurrentTimeMillis());
    }

    @Test
    public void testGetCurrentTimeStr() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(ZRDateUtils.TIME_FORMAT2, Locale.CHINA);
        String dateStr = dateFormat.format(new Date(System.currentTimeMillis()));
        assertEquals(dateStr, ZRDateUtils.getCurrentTimeStr(ZRDateUtils.TIME_FORMAT2));
        System.out.print(dateStr);
    }

    @Test
    public void testFormatTime() throws Exception {
        Date date = new Date();
        String formatTime = ZRDateUtils.formatTime(date.getTime(), ZRDateUtils.TIME_FORMAT2);
        System.out.print(formatTime);
    }

    /*@Test
    public void testTransferStringDateToLong() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(ZRDateUtils.TIME_FORMAT2);
        String dateStr = format.format(new Date());
        long dateToLong = ZRDateUtils.transferStringDateToLong(dateStr);
        System.out.print(dateToLong);
    }*/

    @Test
    public void testGetTimeInMillisByString() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(ZRDateUtils.TIME_FORMAT2);
        String s = dateFormat.format(new Date());
        long timeInMillis = ZRDateUtils.getTimeInMillisByString(s, ZRDateUtils.TIME_FORMAT2);
        System.out.print(timeInMillis);
    }

    @Test
    public void testGetFormatDate() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ZRDateUtils.TIME_FORMAT);
//        String
        String formatDate = ZRDateUtils.getFormatDate(ZRDateUtils.TIME_FORMAT, ZRDateUtils.TIME_FORMAT2, simpleDateFormat.format(new Date()));
        System.out.print(formatDate);
    }

    @Test
    public void testFormatDate() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ZRDateUtils.TIME_FORMAT2);
        String s = simpleDateFormat.format(new Date());
        String formatDate = ZRDateUtils.formatDate(s);
        System.out.print(formatDate);
    }

    @Test
    public void testGetDiffTime() throws Exception {
        long diffTime = ZRDateUtils.getDiffTime(new Date().getTime(), new Date().getTime() - 1 * 60 * 60 * 1000);
        System.out.print("时间差:" + diffTime);
    }

    @Test
    public void testCalTimeDur() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ZRDateUtils.TIME_FORMAT2);
        String format1 = simpleDateFormat.format(new Date());
        String format2 = simpleDateFormat.format(new Date(new Date().getTime() - 1 * 60 * 60 * 1000));
        System.out.print("时间差：" + ZRDateUtils.calTimeDur(format1, format2));
    }

}