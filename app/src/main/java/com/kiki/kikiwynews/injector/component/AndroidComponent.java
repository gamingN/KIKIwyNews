package com.kiki.kikiwynews.injector.component;

import com.kiki.kikiwynews.injector.module.AndroidModule;
import com.kiki.kikiwynews.injector.module.GankIoHttpModule;
import com.kiki.kikiwynews.ui.AndroidFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by KIKI on 2017/12/26.
 * 346409606@qq.com
 */

@Singleton
@Component(modules = { GankIoHttpModule.class,AndroidModule.class})
public interface AndroidComponent {
    void injectAndroid(AndroidFragment androidFragment);
}
