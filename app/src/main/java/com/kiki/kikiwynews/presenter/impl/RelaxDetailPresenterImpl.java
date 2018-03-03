package com.kiki.kikiwynews.presenter.impl;

import com.kiki.kikiwynews.http.service.RelaxService;
import com.kiki.kikiwynews.presenter.BasePresenter;
import com.kiki.kikiwynews.presenter.RelaxPresenter;
import com.kiki.kikiwynews.utils.OkHttpUtils;

/**
 * Created by KIKI on 2018/3/3.
 * 346409606@qq.com
 */

public class RelaxDetailPresenterImpl extends BasePresenter<RelaxPresenter.View> implements RelaxPresenter.Presenter {

    private RelaxService relaxService;

    public RelaxDetailPresenterImpl(RelaxService relaxService){
        this.relaxService=relaxService;
    }

    @Override
    public void fetchRelaxList(int id) {
    }
}
