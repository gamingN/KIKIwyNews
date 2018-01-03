package com.kiki.kikiwynews.injector.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.kiki.kikiwynews.injector.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KIKI on 2018/1/3.
 * 346409606@qq.com
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }

}
