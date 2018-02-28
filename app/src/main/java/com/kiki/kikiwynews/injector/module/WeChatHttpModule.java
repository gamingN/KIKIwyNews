package com.kiki.kikiwynews.injector.module;

import com.kiki.kikiwynews.http.service.WeChatService;
import com.kiki.kikiwynews.injector.qualifier.WeChatUrl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by KIKI on 2018/2/25.
 * 346409606@qq.com
 */

@Module
public class WeChatHttpModule extends BaseHttpModule{
    @Singleton
    @Provides
    @WeChatUrl
    Retrofit provideChatRetrofit(Retrofit.Builder builder, OkHttpClient client){
        return createRetrofit(builder,client, WeChatService.API_WeChat);
    }

    @Singleton
    @Provides
    WeChatService provideWeChatService(@WeChatUrl Retrofit retrofit){
        return  retrofit.create(WeChatService.class);
    }
}
