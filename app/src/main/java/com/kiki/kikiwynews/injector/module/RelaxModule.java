package com.kiki.kikiwynews.injector.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kiki.kikiwynews.adapter.RelaxAdapter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KIKI on 2018/3/3.
 * 346409606@qq.com
 */

@Module
public class RelaxModule {
    @Provides
    @Singleton
    public BaseQuickAdapter provideRelaxAdapter(){
        return new RelaxAdapter(new ArrayList());
    }
}
