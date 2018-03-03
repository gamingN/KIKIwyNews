package com.kiki.kikiwynews.presenter.impl;

import com.kiki.kikiwynews.bean.zhihu.DetailExtraBean;
import com.kiki.kikiwynews.bean.zhihu.ZhihuDetailBean;
import com.kiki.kikiwynews.http.Callback;
import com.kiki.kikiwynews.http.service.ZhiHuService;
import com.kiki.kikiwynews.presenter.BasePresenter;
import com.kiki.kikiwynews.presenter.ZhihuDetailPresenter;

import javax.inject.Inject;

/**
 * Created by KIKI on 2018/1/17.
 * 346409606@qq.com
 */

public class ZhiHuDetailPresenterImpl extends BasePresenter<ZhihuDetailPresenter.View> implements ZhihuDetailPresenter.Presenter{
    private ZhiHuService zhiHuService;

    @Inject
    public ZhiHuDetailPresenterImpl(ZhiHuService zhiHuService){
        this.zhiHuService=zhiHuService;
    }

    @Override
    public void fetchDetailInfo(int id) {
        invoke(zhiHuService.fetchDetailInfo(id),new Callback<ZhihuDetailBean>(){
            @Override
            public void onResponse(ZhihuDetailBean data) {
                mView.refreshView(data);
            }
        });
    }

    @Override
    public void fetchDetailExtraInfo(int id) {
        invoke(zhiHuService.fetchDetailExtraInfo(id),new Callback<DetailExtraBean>(){
            @Override
            public void onResponse(DetailExtraBean data) {
                mView.showExtraInfo(data);
            }
        });
    }
}
