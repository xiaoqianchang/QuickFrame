package com.changxiao.quickframe.presenter;

import android.content.Context;

import com.changxiao.quickframe.iView.IMainView;

/**
 * $desc$
 * <p/>
 * Created by Chang.Xiao on 2016/7/25.
 *
 * @version 1.0
 */
public class MainPresenter extends BasePresenter<IMainView> {

    public MainPresenter(Context context, IMainView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }
}
