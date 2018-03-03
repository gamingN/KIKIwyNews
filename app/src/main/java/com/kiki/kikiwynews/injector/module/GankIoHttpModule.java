package com.kiki.kikiwynews.injector.module;

import com.kiki.kikiwynews.http.service.GankIoService;
import com.kiki.kikiwynews.injector.qualifier.GankUrl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by KIKI on 2017/12/26.
 * 346409606@qq.com
 */
@Module
public class GankIoHttpModule extends BaseHttpModule {
    @Singleton
    @Provides
    @GankUrl
    Retrofit provideGankIoRetrofit(Retrofit.Builder builder, OkHttpClient client){
        return createRetrofit(builder,client, GankIoService.API_GANKIO);
    }

    /**
     * 补全GankIoService的retrofit请求
     */
    @Singleton
    @Provides
    GankIoService provideGankIoService(@GankUrl Retrofit retrofit){
        return retrofit.create(GankIoService.class);
    }
}
