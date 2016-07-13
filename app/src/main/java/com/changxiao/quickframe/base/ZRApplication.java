package com.changxiao.quickframe.base;

import android.app.Application;
import android.content.Context;

/**
 * Custom application.
 * <p>
 * Created by Chang.Xiao on 2016/7/1.
 *
 * @version 1.0
 */
public class ZRApplication extends Application {

    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
    }
}
