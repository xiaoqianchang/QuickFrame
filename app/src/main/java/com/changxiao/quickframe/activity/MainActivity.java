package com.changxiao.quickframe.activity;

import android.os.Bundle;

import com.changxiao.quickframe.R;
import com.changxiao.quickframe.base.BaseActivity;
import com.changxiao.quickframe.presenter.MainPresenter;

public class MainActivity extends ToolBarActivity {

    private MainPresenter mMainPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
//        this.mMainPresenter = new MainPresenter(this, this);
    }
}
