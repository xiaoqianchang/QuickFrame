package com.changxiao.quickframe.activity;

import com.changxiao.quickframe.R;
import com.changxiao.quickframe.iView.IMainView;
import com.changxiao.quickframe.presenter.MainPresenter;

public class MainActivity extends ToolBarActivity<MainPresenter> implements IMainView {

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
