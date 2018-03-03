package com.kiki.kikiwynews.injector.module;

import com.kiki.kikiwynews.adapter.ZhihuThemeAdapter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KIKI on 2018/2/23.
 * 346409606@qq.com
 */

@Module
public class ZhihuThemeModule {
    @Provides
    @Singleton
    public ZhihuThemeAdapter provideAdapter(){
        return new ZhihuThemeAdapter(new ArrayList());
    }
}
