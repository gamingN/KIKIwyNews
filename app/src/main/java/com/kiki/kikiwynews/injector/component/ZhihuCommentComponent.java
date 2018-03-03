package com.kiki.kikiwynews.injector.component;

import com.kiki.kikiwynews.injector.module.ZhihuCommentModule;
import com.kiki.kikiwynews.injector.module.ZhihuHttpModule;
import com.kiki.kikiwynews.ui.ZhiHuCommentFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by KIKI on 2018/1/23.
 * 346409606@qq.com
 */

@Singleton
@Component(modules = {ZhihuHttpModule.class, ZhihuCommentModule.class})
public interface ZhihuCommentComponent {
    void injectZhihuComment(ZhiHuCommentFragment zhiHuCommentFragment);
}
