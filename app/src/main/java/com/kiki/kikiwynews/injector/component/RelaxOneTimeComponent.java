package com.kiki.kikiwynews.injector.component;

import com.kiki.kikiwynews.injector.module.RelaxHttpModule;
import com.kiki.kikiwynews.injector.module.RelaxModule;
import com.kiki.kikiwynews.ui.RelaxFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by KIKI on 2018/3/3.
 * 346409606@qq.com
 */
@Singleton
@Component(modules = {RelaxModule.class, RelaxHttpModule.class})
public interface RelaxOneTimeComponent {
    void injectRelax(RelaxFragment relaxFragment);
}
