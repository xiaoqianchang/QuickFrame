package com.changxiao.quickframe.utils;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Snackbar Tip
 * <p>
 * Created by Chang.Xiao on 2016/7/27.
 *
 * @version 1.0
 */
public final class SnackbarUtils {

    private SnackbarUtils() {}

    /**
     * Show Snackbar normal by LENGTH_SHORT
     *
     * @param view
     * @param tipText
     */
    public static void showSnackbar(@NonNull View view, @NonNull String tipText) {
        Snackbar.make(view, tipText, Snackbar.LENGTH_SHORT).show();
    }

    public static void showSnackbar(@NonNull View view, @NonNull String tipText, @NonNull int duration, String actionText, View.OnClickListener listener) {
        Snackbar.make(view, tipText, duration).setAction(actionText, listener).show();
    }
}
