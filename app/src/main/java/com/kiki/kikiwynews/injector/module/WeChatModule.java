package com.kiki.kikiwynews.injector.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kiki.kikiwynews.adapter.WeChatAdapter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KIKI on 2018/2/25.
 * 346409606@qq.com
 */

@Module
public class WeChatModule {
    @Provides
    @Singleton
    public BaseQuickAdapter provideAdapter(){
        return new WeChatAdapter(new ArrayList());
    }
}
