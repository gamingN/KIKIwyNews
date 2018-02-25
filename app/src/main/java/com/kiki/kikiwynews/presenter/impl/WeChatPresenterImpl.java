package com.kiki.kikiwynews.presenter.impl;

import com.kiki.kikiwynews.app.AppConstants;
import com.kiki.kikiwynews.bean.wx.WXHttpResponse;
import com.kiki.kikiwynews.bean.wx.WXItemBean;
import com.kiki.kikiwynews.http.Callback;
import com.kiki.kikiwynews.http.service.WeChatService;
import com.kiki.kikiwynews.presenter.BasePresenter;
import com.kiki.kikiwynews.presenter.WeChatpresent;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by KIKI on 2018/2/25.
 * 346409606@qq.com
 */

public class WeChatPresenterImpl extends BasePresenter<WeChatpresent.View> implements WeChatpresent.Presenter {
    private WeChatService mWeChatService;

    @Inject
    public WeChatPresenterImpl(WeChatService mWeChatService){
        this.mWeChatService=mWeChatService;
    }

    @Override
    public void fetchWeChatHot(int num, int page) {
        invoke(mWeChatService.fetchWXHot(AppConstants.KEY_API,num,page),new Callback<WXHttpResponse<List<WXItemBean>>>(){
            @Override
            public void onResponse(WXHttpResponse<List<WXItemBean>> data) {
                List<WXItemBean> newslist=data.getNewslist();
                checkState(newslist);
                mView.refreshView(newslist);
            }
        });
    }

    @Override
    public void fetchWXHotSearch(int num, int page, String word) {
        invoke(mWeChatService.fetchWXHotSearch(AppConstants.KEY_API,num,page,word),new Callback<WXHttpResponse<List<WXItemBean>>>(){
            @Override
            public void onResponse(WXHttpResponse<List<WXItemBean>> data) {
                List<WXItemBean> newslist=data.getNewslist();
                checkState(newslist);
                mView.refreshView(newslist);
            }
        });
    }
}
