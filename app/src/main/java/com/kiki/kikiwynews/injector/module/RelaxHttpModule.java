package com.kiki.kikiwynews.injector.module;

import com.kiki.kikiwynews.http.service.RelaxService;
import com.kiki.kikiwynews.injector.qualifier.RelaxUrl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by KIKI on 2018/3/3.
 * 346409606@qq.com
 */
@Module
public class RelaxHttpModule extends BaseHttpModule{
    @Singleton
    @Provides
    @RelaxUrl
    Retrofit provideRelaxRetrofit(Retrofit.Builder builder, OkHttpClient client){
        return  createRetrofit(builder,client, RelaxService.API_RELAX);
    }

    @Singleton
    @Provides
    RelaxService provideRelaxService(@RelaxUrl Retrofit retrofit){
        return retrofit.create(RelaxService.class);
    }
}
