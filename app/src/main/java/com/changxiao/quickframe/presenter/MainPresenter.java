package com.changxiao.quickframe.presenter;

import android.content.Context;

import com.changxiao.quickframe.bean.MeiziData;
import com.changxiao.quickframe.http.ZRRetrofit;
import com.changxiao.quickframe.iView.IMainView;
import com.changxiao.quickframe.utils.ZRToastFactory;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        mSubscription.unsubscribe();
    }

    public void fetchMeiziData(int page) {
        iView.showProgress();
        mSubscription = ZRRetrofit.getNetApiInstance().getMeiziData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meiziData -> {
                    if (meiziData.results.size() == 0){
                        iView.showNoMoreData();
                    }else {
                        iView.showMeiziList(meiziData.results);
                    }
                    iView.hideProgress();
                }, throwable -> {
                    iView.showErrorView();
                    iView.hideProgress();
                });
    }
}
