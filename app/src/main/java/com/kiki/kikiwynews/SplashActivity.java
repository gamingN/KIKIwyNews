package com.kiki.kikiwynews;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kiki.kikiwynews.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/12/23.
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv_splash_pic)
    ImageView ivsplashPic;

    @BindView(R.id.textView3)
    TextView textView;

    private Unbinder bind;//解绑对象

    Handler handler;

    Runnable runnable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ButterKnife.bind(this);
        ivsplashPic.setImageResource(R.mipmap.splash_bg);

        handler=new Handler();

        runnable=new Runnable() {
            @Override
            public void run() {
                toMainActivity();
            }
        };

        handler.postDelayed(runnable,3000);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMainActivity();
                handler.removeCallbacks(runnable);
            }
        });


    }
    private void toMainActivity() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.screen_zoom_in,R.anim.screen_zoom_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bind!=null){
            bind.unbind();
        }
    }
}
