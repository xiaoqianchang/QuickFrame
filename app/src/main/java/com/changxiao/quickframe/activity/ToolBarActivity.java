package com.changxiao.quickframe.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.changxiao.quickframe.R;
import com.changxiao.quickframe.base.BaseActivity;

import butterknife.Bind;

/**
 * 带Toolbar的基类Activity
 * <p/>
 * Created by Chang.Xiao on 2016/7/25.
 *
 * @version 1.0
 */
public abstract class ToolBarActivity extends BaseActivity {

    @Bind(R.id.toolBar)
    protected Toolbar mToolbar;

    protected ActionBar mActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolBar();
    }

    protected void initToolBar() {
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        if (null != mActionBar) {
            mActionBar.setDisplayHomeAsUpEnabled(canBack());
        }
    }

    /**
     * 设置 home icon 是否可见
     *
     * @return
     */
    protected boolean canBack(){
        return true;
    }
}
