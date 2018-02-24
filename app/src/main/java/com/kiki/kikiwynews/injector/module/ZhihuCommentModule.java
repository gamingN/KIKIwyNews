package com.kiki.kikiwynews.injector.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kiki.kikiwynews.adapter.ZhiHuCommentAdapter;
import com.kiki.kikiwynews.bean.zhihu.CommentBean;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KIKI on 2018/1/23.
 * 346409606@qq.com
 */
@Module
public class ZhihuCommentModule {
    @Provides
    @Singleton
    public BaseQuickAdapter provideAdapter(){
        return  new ZhiHuCommentAdapter(new ArrayList<CommentBean.CommentsBean>());
    }
}
