package com.kiki.kikiwynews.presenter.impl;

import com.kiki.kikiwynews.bean.zhihu.SectionChildListBean;
import com.kiki.kikiwynews.bean.zhihu.ThemeChildListBean;
import com.kiki.kikiwynews.http.Callback;
import com.kiki.kikiwynews.http.service.ZhiHuService;
import com.kiki.kikiwynews.presenter.BasePresenter;
import com.kiki.kikiwynews.presenter.ZhihuThemeDetailPresenter;

import javax.inject.Inject;

/**
 * Created by KIKI on 2018/1/28.
 * 346409606@qq.com
 */

public class ZhihuThemeDetailPresenterImpl extends BasePresenter<ZhihuThemeDetailPresenter.View> implements ZhihuThemeDetailPresenter.Presenter{

    ZhiHuService zhiHuService;

    @Inject
    public ZhihuThemeDetailPresenterImpl(ZhiHuService zhiHuService){
        this.zhiHuService=zhiHuService;
    }

    @Override
    public void fetchThemeChildList(int id) {
        invoke(zhiHuService.fetchThemeChildList(id),new Callback<ThemeChildListBean>(){
            @Override
            public void onResponse(ThemeChildListBean data) {
                mView.refreshView(data);
            }
        });
    }

    @Override
    public void fetchSectionChildList(int id) {
        invoke(zhiHuService.fetchSectionChildList(id),new Callback<SectionChildListBean>(){
            @Override
            public void onResponse(SectionChildListBean data) {
                mView.refreshSectionData(data);
            }
        });
    }
}
