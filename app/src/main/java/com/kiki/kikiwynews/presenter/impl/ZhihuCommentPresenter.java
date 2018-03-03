package com.kiki.kikiwynews.presenter.impl;

import com.kiki.kikiwynews.bean.zhihu.CommentBean;
import com.kiki.kikiwynews.presenter.BaseView;

import java.util.List;

/**
 * Created by KIKI on 2018/1/21.
 * 346409606@qq.com
 */

public interface ZhihuCommentPresenter {
    interface View extends BaseView<List<CommentBean.CommentsBean>>{

    }

    interface Presenter{
        void fetchLongCommentInfo(int id);
        void fetchShortCommentInfo(int id);
    }
}
