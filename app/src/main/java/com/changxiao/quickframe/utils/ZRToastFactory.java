package com.changxiao.quickframe.utils;


import android.content.Context;
import android.widget.Toast;

public class ZRToastFactory {
    private static Context context = null;
    private static Toast toast = null;

    public static Toast getToast(Context context, String text) {
        if (ZRToastFactory.context == context) {
            // toast.cancel();
            toast.setText(text);
            toast.setDuration(Toast.LENGTH_SHORT);

        } else {
            ZRToastFactory.context = context;
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        return toast;
    }

    public static Toast getLongToast(Context context, String text) {
        if (ZRToastFactory.context == context) {
            // toast.cancel();
            toast.setText(text);
            toast.setDuration(Toast.LENGTH_LONG);
        } else {
            ZRToastFactory.context = context;
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        }
        return toast;
    }

    /**
     * 显示Toast
     *
     * @param context
     * @param content
     */
    public static void showToast(Context context, String content) {
        if (null == toast) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

}
