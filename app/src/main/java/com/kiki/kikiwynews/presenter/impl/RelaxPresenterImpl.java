package com.kiki.kikiwynews.presenter.impl;

import com.kiki.kikiwynews.bean.RelaxOneTimeBean;
import com.kiki.kikiwynews.http.Callback;
import com.kiki.kikiwynews.http.service.RelaxService;
import com.kiki.kikiwynews.presenter.BasePresenter;
import com.kiki.kikiwynews.presenter.RelaxPresenter;

import javax.inject.Inject;

/**
 * Created by KIKI on 2018/3/3.
 * 346409606@qq.com
 */

public class RelaxPresenterImpl extends BasePresenter<RelaxPresenter.View> implements RelaxPresenter.Presenter {
    private RelaxService relaxService;

    @Inject
    public RelaxPresenterImpl(RelaxService relaxService){
        this.relaxService=relaxService;
    }

    @Override
    public void fetchRelaxList(int id) {
        invoke(relaxService.fetchRelax(id),new Callback<RelaxOneTimeBean>(){});
    }
}
