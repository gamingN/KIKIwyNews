package com.kiki.kikiwynews;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.blankj.utilcode.utils.ToastUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity{

    @BindView(R.id.fl_title_menu)
    FrameLayout nvMenu;

    @BindView(R.id.dl_layout)
    DrawerLayout dlLayout;

    @BindView(R.id.toolbar)
    Toolbar tbToolbar;

    @BindView(R.id.rg_home_viewpager_contorl)
    RadioGroup rgHomeViewPagerContorl;

    @BindView(R.id.vp_content)
    ViewPager vpContent;

    @BindView(R.id.view_search)
    MaterialSearchView searchView;

    @OnClick(R.id.fl_title_menu)
    public void flTitleMenu(){
        dlLayout.openDrawer(GravityCompat.START);
    }

    @OnClick(R.id.fl_exit_app)
    public void exitApp(){
        this.killAll();
    }

    @OnClick(R.id.fl_feedback)
    public void feedback(){
        startActivity(new Intent(this,FeedbackActivity.class));
    }

    @OnClick(R.id.fl_about_us)
    public void aboutUs() {
        startActivity(new Intent(this, AboutUsActivity.class));
    }

    @OnClick(R.id.fl_setting)
    public void setting() {
        ToastUtils.showShortToast("设置暂时还没有开发哦");
    }

    @OnClick(R.id.fl_theme_color)
    public void themeColor() {
        ToastUtils.showShortToast("个性换肤暂时还没有开发");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar(tbToolbar,"",false);
        initView();
    }

    private void initView() {
        rgHomeViewPagerContorl.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_home_pager:
                        vpContent.setCurrentItem(0);//为了不造成资源浪费，adapter默认从首页开始加载
                        break;
                    case R.id.rb_music_pager:
                        vpContent.setCurrentItem(1);
                        break;
                    case R.id.rb_friend_pager:
                        vpContent.setCurrentItem(2);
                        break;
                }
            }
        });

//        List<Fragment> mFragmentList=new ArrayList<>();
//        mFragmentList.add(new AndroidFragment());
//        mFragmentList.add(new HomeFragment());
//        mFragmentList.add(new RightFragment());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
