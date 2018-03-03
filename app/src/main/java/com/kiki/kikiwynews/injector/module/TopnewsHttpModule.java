package com.kiki.kikiwynews.injector.module;

import com.kiki.kikiwynews.http.service.TopnewsService;
import com.kiki.kikiwynews.injector.qualifier.TopnewsUrl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by KIKI on 2018/2/26.
 * 346409606@qq.com
 */
@Module
public class TopnewsHttpModule extends BaseHttpModule{
    @Singleton
    @Provides
    @TopnewsUrl
    Retrofit provideTopnewsRetrofit(Retrofit.Builder builder, OkHttpClient client){
        return createRetrofit(builder,client, TopnewsService.API_TOP);
    }

    @Singleton
    @Provides
    TopnewsService provideTopnewsService(@TopnewsUrl Retrofit retrofit){
        return retrofit.create(TopnewsService.class);
    }
}
