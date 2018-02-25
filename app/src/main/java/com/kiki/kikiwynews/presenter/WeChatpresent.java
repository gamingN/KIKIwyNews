package com.kiki.kikiwynews.presenter;

import com.kiki.kikiwynews.bean.wx.WXItemBean;

import java.util.List;

/**
 * Created by KIKI on 2018/2/24.
 * 346409606@qq.com
 */

public interface WeChatpresent {
    interface View extends BaseView<List<WXItemBean>>{

    }
    interface Presenter{
        void fetchWeChatHot(int num,int page);
        void fetchWXHotSearch(int num,int page,String word);
    }
}
