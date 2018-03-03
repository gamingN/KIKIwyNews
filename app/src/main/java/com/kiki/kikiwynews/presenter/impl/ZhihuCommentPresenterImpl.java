package com.kiki.kikiwynews.presenter.impl;

import com.kiki.kikiwynews.bean.zhihu.CommentBean;
import com.kiki.kikiwynews.http.Callback;
import com.kiki.kikiwynews.http.service.ZhiHuService;
import com.kiki.kikiwynews.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by KIKI on 2018/1/21.
 * 346409606@qq.com
 */

public class ZhihuCommentPresenterImpl extends BasePresenter<ZhihuCommentPresenter.View> implements ZhihuCommentPresenter.Presenter{
    private ZhiHuService mZhihService;

    @Inject
    public ZhihuCommentPresenterImpl(ZhiHuService mZhihService){
        this.mZhihService=mZhihService;
    }

    @Override
    public void fetchLongCommentInfo(int id) {
        invoke(mZhihService.fetchLongCommentInfo(id),new Callback<CommentBean>(){
            @Override
            public void onResponse(CommentBean commentBean) {
                checkState(commentBean.getComments());
                mView.refreshView(commentBean.getComments());
            }
        });
    }

    @Override
    public void fetchShortCommentInfo(int id) {
        invoke(mZhihService.fetchShortCommentInfo(id),new Callback<CommentBean>(){
            @Override
            public void onResponse(CommentBean commentBean) {
                checkState(commentBean.getComments());
                mView.refreshView(commentBean.getComments());
            }
        });
    }
}
