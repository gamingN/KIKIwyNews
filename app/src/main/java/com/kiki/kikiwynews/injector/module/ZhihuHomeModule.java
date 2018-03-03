package com.kiki.kikiwynews.injector.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kiki.kikiwynews.adapter.ZhihuAdapter;
import com.kiki.kikiwynews.bean.zhihu.HomeListBean;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KIKI on 2018/1/8.
 * 346409606@qq.com
 */

@Module
public class ZhihuHomeModule {
    @Provides
    @Singleton
    public BaseQuickAdapter provideAdapter(){
        return new ZhihuAdapter(new ArrayList<HomeListBean>());
    }
}
