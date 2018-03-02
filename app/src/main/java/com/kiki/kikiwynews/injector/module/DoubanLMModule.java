package com.kiki.kikiwynews.injector.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kiki.kikiwynews.adapter.DoubanLMAdapter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KIKI on 2018/2/28.
 * 346409606@qq.com
 */

@Module
public class DoubanLMModule {
    @Provides
    @Singleton
    public BaseQuickAdapter provideDoubanAdapter(){
        return new DoubanLMAdapter(new ArrayList());
    }
}
