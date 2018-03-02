package com.kiki.kikiwynews.injector.module;

import com.kiki.kikiwynews.http.service.DoubanService;
import com.kiki.kikiwynews.injector.qualifier.DoubanUrl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by KIKI on 2018/2/28.
 * 346409606@qq.com
 */

@Module
public class DoubanHttpModule extends BaseHttpModule {

    @Singleton
    @Provides
    @DoubanUrl
    Retrofit provideDoubanRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, DoubanService.API_DOUBAN);
    }

    @Singleton
    @Provides
    DoubanService provideDoubanService(@DoubanUrl Retrofit retrofit) {
        return retrofit.create(DoubanService.class);
    }
}
