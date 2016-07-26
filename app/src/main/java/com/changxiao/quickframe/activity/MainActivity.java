package com.changxiao.quickframe.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;

import com.changxiao.quickframe.R;
import com.changxiao.quickframe.iView.IMainView;
import com.changxiao.quickframe.presenter.MainPresenter;
import com.changxiao.quickframe.widget.XCRecyclerView;

import butterknife.Bind;

public class MainActivity extends ToolBarActivity<MainPresenter> implements IMainView {

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view)
    XCRecyclerView recyclerView;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    private MainPresenter mMainPresenter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        this.mMainPresenter = new MainPresenter(this, this);
        this.mMainPresenter.init();
    }

    @Override
    public void initView() {
        mCenterTitle.setText("sssss");
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
