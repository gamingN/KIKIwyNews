package com.kiki.kikiwynews.injector.module;

import com.kiki.kikiwynews.http.service.ZhiHuService;
import com.kiki.kikiwynews.injector.qualifier.ZhihuUrl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 *
 * Created by KIKI on 2018/1/8.
 * 346409606@qq.com
 */

@Module
public class ZhihuHttpModule extends BaseHttpModule{
    @Provides
    @Singleton
    @ZhihuUrl
    Retrofit provideZhiHuRetrofit(Retrofit.Builder builder, OkHttpClient client){
        return createRetrofit(builder,client, ZhiHuService.Host);
    }

    @Provides
    @Singleton
    ZhiHuService provideZhihuService(@ZhihuUrl Retrofit retrofit){
        return retrofit.create(ZhiHuService.class);
    }
}
