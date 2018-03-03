package com.kiki.kikiwynews.app;

import android.app.Application;

import com.blankj.utilcode.utils.Utils;
import com.kiki.kikiwynews.injector.component.AppComponent;
import com.kiki.kikiwynews.injector.component.DaggerAppComponent;
import com.kiki.kikiwynews.injector.module.AppModule;


/**
 * Created by KIKI on 2017/12/27.
 * 346409606@qq.com
 */

public class App extends Application{

    private static App instance;
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        Utils.init(this);
    }

    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .build();
        }
        return appComponent;
    }

}
