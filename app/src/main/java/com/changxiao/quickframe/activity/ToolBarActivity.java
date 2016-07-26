package com.changxiao.quickframe.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.changxiao.quickframe.R;
import com.changxiao.quickframe.base.BaseActivity;
import com.changxiao.quickframe.presenter.BasePresenter;
import com.changxiao.quickframe.utils.SystemUtils;

import butterknife.Bind;

/**
 * 带Toolbar自定义Title的基类Activity
 * <p/>
 * Created by Chang.Xiao on 2016/7/25.
 *
 * @version 1.0
 */
public abstract class ToolBarActivity<T extends BasePresenter> extends BaseActivity {

    @Bind(R.id.app_bar_layout)
    protected AppBarLayout mAppBarLayout;
    @Bind(R.id.status_bar)
    protected View mStatusBar;
    @Bind(R.id.toolBar)
    protected Toolbar mToolbar;

    @Bind(R.id.nav_left_text)
    protected TextView mNavLeftText;
    @Bind(R.id.center_title)
    protected TextView mCenterTitle;
    @Bind(R.id.nav_right_text)
    protected TextView mNavRightText;

    protected T mPresenter;
    protected boolean isToolBarHiding = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();
        initToolBar();
    }

    protected void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mStatusBar.setVisibility(View.VISIBLE);
            mStatusBar.getLayoutParams().height = SystemUtils.getStatusHeight(this);
            mStatusBar.setLayoutParams(mStatusBar.getLayoutParams());
        } else {
            mStatusBar.setVisibility(View.GONE);
        }
    }

    protected void initToolBar() {
        if (null == mAppBarLayout || null == mToolbar) {
            throw new IllegalStateException("The subclass of ToolbarActivity must contain a toolbar.");
        }
        mToolbar.setOnClickListener(v -> onToolbarClick());
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (canBack()) {
            if (null != actionBar) {
                actionBar.setDisplayHomeAsUpEnabled(canBack()); // 设置返回箭头
            }
        } else {
            if (null != actionBar) {
                actionBar.setDisplayShowTitleEnabled(false);
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            mAppBarLayout.setElevation(10.6f);
        }
    }

    /**
     * onToolbarClick
     */
    protected void onToolbarClick() {
        // empty
    }

    /**
     * 设置 NavigationButton 是否可见
     *
     * @return
     */
    protected boolean canBack(){
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //在Action Bar的最左边，就是Home icon和标题的区域
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setAppBarAlpha(float alpha) {
        mAppBarLayout.setAlpha(alpha);
    }

    /**
     * Control ToolBar show or hiden
     */
    protected void hideOrShowToolBar() {
        mAppBarLayout.animate()
                .translationY(isToolBarHiding ? 0 : -mAppBarLayout.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        isToolBarHiding = !isToolBarHiding;
    }
}
