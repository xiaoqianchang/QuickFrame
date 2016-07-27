package com.changxiao.quickframe.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.changxiao.quickframe.R;
import com.changxiao.quickframe.adapter.MeiziAdapter;
import com.changxiao.quickframe.bean.Meizi;
import com.changxiao.quickframe.iView.IMainView;
import com.changxiao.quickframe.presenter.MainPresenter;
import com.changxiao.quickframe.utils.SnackbarUtils;
import com.changxiao.quickframe.utils.ZRToastFactory;
import com.changxiao.quickframe.widget.XCRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends ToolBarActivity<MainPresenter> implements IMainView, XCRecyclerView.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view)
    XCRecyclerView recyclerView;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    private MainPresenter mMainPresenter;
    private List<Meizi> meizis;
    private MeiziAdapter adapter;
    private int page = 1;
    private boolean isRefresh = true;
    private boolean canLoading = true;

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
        if (meizis == null)
            meizis = new ArrayList<>();
        adapter = new MeiziAdapter(this, meizis);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.applyFloatingActionButton(fab);
        recyclerView.setLoadMoreListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, android.R.color.darker_gray);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(() -> {
            swipeRefreshLayout.setRefreshing(true);
            mMainPresenter.fetchMeiziData(page);
        });
    }

    @Override
    protected void onToolbarClick() {
        super.onToolbarClick();
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 1;
        mMainPresenter.fetchMeiziData(page);
    }

    @Override
    public void loadMore() {
        if (canLoading) {
            mMainPresenter.fetchMeiziData(page);
            canLoading = false;
        }
    }

    @Override
    public void showProgress() {
        if (!swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showErrorView() {
        canLoading = true;
        SnackbarUtils.showSnackbar(fab, "加载失败", Snackbar.LENGTH_INDEFINITE, "重试", v -> mMainPresenter.fetchMeiziData(page));
    }

    @Override
    public void showNoMoreData() {
        canLoading = false;
        SnackbarUtils.showSnackbar(fab, "全部加载完啦！");
    }

    @Override
    public void showMeiziList(List<Meizi> meiziList) {
        canLoading = true;
        page++;
        if (isRefresh) {
            meizis.clear();
            meizis.addAll(meiziList);
            adapter.notifyDataSetChanged();
            isRefresh = false;
        } else {
            meizis.addAll(meiziList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected boolean canBack() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.release();
    }
}
