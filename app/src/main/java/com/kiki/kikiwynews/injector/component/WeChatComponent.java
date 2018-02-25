package com.kiki.kikiwynews.injector.component;

import com.kiki.kikiwynews.injector.module.WeChatHttpModule;
import com.kiki.kikiwynews.injector.module.WeChatModule;
import com.kiki.kikiwynews.ui.WeChatFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by KIKI on 2018/2/25.
 * 346409606@qq.com
 */

@Singleton
@Component(modules = {WeChatHttpModule.class,WeChatModule.class})
public interface WeChatComponent {
    void injectWeChat(WeChatFragment weChatFragment);
}
