package com.changxiao.quickframe.utils;

/**
 * Global configuration for application.
 *
 * @author gufei
 * @version 1.0
 * @createDate 2015-10-19
 * @lastUpdate 2015-10-19
 */
public class ZRAppConfig {

    // 服务器数据地址
    public static final String SERVER_URL = "http://172.16.101.91:9000/";

    public static String WORK_FOLDER = ZRFileUtils.getWorkFolder();

    public static String APP_IMG_DIR = ZRFileUtils.getImageFolder("image/");

    public static String APP_DOWNLOAD_DIR = WORK_FOLDER + "download/";

    public static String APP_LOG_DIR = WORK_FOLDER + "mdsjrLog/%s/";

    public static String APP_CRASH_LOG_DIR = APP_LOG_DIR + "crash/";

    public static final boolean CRASH_LOG_TO_FILE = true;

    public static final boolean LOG_TO_FILE = true;

    public static final boolean DEBUG = true;

}
