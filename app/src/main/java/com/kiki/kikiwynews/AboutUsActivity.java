package com.kiki.kikiwynews;

import android.support.v7.widget.Toolbar;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/22.
 */

public class AboutUsActivity extends ToolbarBaseActivity{
    @Override
    protected void initUI() {
        tvToolbarTitle.setText("关于我们");
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_about_us;
    }

//    @OnClick(R.id.cv_github)
//    public void cvGithub() {
//        WebViewActivity.loadUrl(this, "https://github.com/laotan7237/EasyReader", "加载中。。。");
//    }
}
