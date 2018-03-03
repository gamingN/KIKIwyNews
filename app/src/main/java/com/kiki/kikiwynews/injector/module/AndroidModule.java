package com.kiki.kikiwynews.injector.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kiki.kikiwynews.adapter.GankIoAndroidAdapter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KIKI on 2017/12/26.
 * 346409606@qq.com
 */
@Module
public class AndroidModule {
    @Provides
    @Singleton
    public BaseQuickAdapter provideAdapter() {
        return new GankIoAndroidAdapter(new ArrayList());
    }
}
