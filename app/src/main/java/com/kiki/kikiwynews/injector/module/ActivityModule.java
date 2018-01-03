package com.kiki.kikiwynews.injector.module;

import android.app.Activity;

import com.kiki.kikiwynews.injector.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KIKI on 2018/1/2.
 * 346409606@qq.com
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity){
        this.mActivity=activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity(){
        return mActivity;
    }
}
