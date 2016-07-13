package com.changxiao.quickframe.utils;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * WebView utils used for application.
 * <p/>
 * Created by Chang.Xiao on 2016/7/4.
 *
 * @version 1.0
 */
public class ZRWebViewUtils {

    /**
     * 初始化webview设置
     *
     * @param context
     * @param webView
     */
    public static void initWebSettings(Context context, WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setAppCacheMaxSize(10 * 1024 * 1024);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setAppCachePath(context.getCacheDir().getAbsolutePath());
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        // 允许执行javascript语句
        webSettings.setJavaScriptEnabled(true);
        // html页面大小自适应
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        webSettings.setDomStorageEnabled(true);
    }
}
