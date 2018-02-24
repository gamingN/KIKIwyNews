package com.kiki.kikiwynews.presenter.impl;

import com.kiki.kikiwynews.bean.GankIoDataBean;
import com.kiki.kikiwynews.http.Callback;
import com.kiki.kikiwynews.http.service.GankIoService;
import com.kiki.kikiwynews.presenter.BasePresenter;
import com.kiki.kikiwynews.presenter.GankIoAndroidPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * gankio接口请求的实现类,
 * Created by KIKI on 2017/12/25.
 * 346409606@qq.com
 */

public class GankIoAndroidPresenterImpl extends BasePresenter<GankIoAndroidPresenter.View> implements GankIoAndroidPresenter.Presenter{

    private GankIoService mGankIoService;

    @Inject
    public GankIoAndroidPresenterImpl(GankIoService mGankIoService){
        this.mGankIoService=mGankIoService;
    }

    /**
     * 回调BasePresenter.invoke
     * @param page  请求个数
     * @param pre_page  请求页数
     */
    @Override
    public void fetchGankIoData(int page, int pre_page) {
        invoke(mGankIoService.getGankIoData("Android",page,pre_page),new Callback<GankIoDataBean>(){
            /**
             * onNext中的回调
             * @param data
             */
            @Override
            public void onResponse(GankIoDataBean data) {
                List<GankIoDataBean.ResultBean> results=data.getResults();
                checkState(results);
                /**
                 * mView为Androidfragment，p层调用v层显示数据
                 */
                mView.refreshView(results);
            }
        });
    }
}
