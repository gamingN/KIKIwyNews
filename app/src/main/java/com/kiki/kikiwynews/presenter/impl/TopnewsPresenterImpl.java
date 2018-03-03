package com.kiki.kikiwynews.presenter.impl;

import com.kiki.kikiwynews.bean.TopnewsBean;
import com.kiki.kikiwynews.http.Callback;
import com.kiki.kikiwynews.http.service.TopnewsService;
import com.kiki.kikiwynews.presenter.BasePresenter;
import com.kiki.kikiwynews.presenter.Topnewspresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by KIKI on 2018/2/26.
 * 346409606@qq.com
 */

public class TopnewsPresenterImpl extends BasePresenter<Topnewspresenter.view> implements Topnewspresenter.Presenter{

    private TopnewsService mtopnewsService;

    @Inject
    public TopnewsPresenterImpl(TopnewsService mtopnewsService){
        this.mtopnewsService=mtopnewsService;
    }

    @Override
    public void fetchtopNews() {
        invoke(mtopnewsService.fetchTopnews(TopnewsService.TYPE_TOP,TopnewsService.KEY_TOP),new Callback<TopnewsBean>(){
            @Override
            public void onResponse(TopnewsBean data) {
                List<TopnewsBean.ResultBean.DataBean> lists=data.getResult().getData();
                checkState(lists);
                mView.refreshView(lists);
            }
        });
    }
}
