package com.kiki.kikiwynews.ui;

import android.view.View;

import com.kiki.kikiwynews.utils.LoginUtils;
import com.kiki.kikiwynews.R;

/**
 * Created by Administrator on 2017/12/21.
 */

public class FeedbackActivity extends ToolbarBaseActivity {
    @Override
    protected void initUI() {
        tvToolbarTitle.setText("意见反馈");
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_feedback;
    }

    public void isLogin(final View view){
        LoginUtils.setIlogin(new LoginUtils.Ilogin() {
            @Override
            public void onlogin() {
                //onViewClick(view.getId());
            }
        },this);
    }

    @Override
    protected void onDestroy() {
        LoginUtils.clear();
        super.onDestroy();
    }
}
