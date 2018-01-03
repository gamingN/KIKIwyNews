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
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.blankj.utilcode.utils.ToastUtils;
import com.kiki.kikiwynews.adapter.HomeFragmentPageAdapter;
import com.kiki.kikiwynews.app.AppConstants;
import com.kiki.kikiwynews.injector.component.ActivityComponent;
import com.kiki.kikiwynews.injector.component.DaggerActivityComponent;
import com.kiki.kikiwynews.injector.module.ActivityModule;
import com.kiki.kikiwynews.ui.AndroidFragment;
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
        ButterKnife.bind(this);
        setToolbar(tbToolbar,"",false);
        initView();

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                vpContent.setCurrentItem(1);
//                RxBus.getDefault().post(AppConstants.WECHA_SEARCH, query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
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

        List<Fragment> mFragmentList=new ArrayList<>();
        mFragmentList.add(new AndroidFragment());


        vpContent.setAdapter(new HomeFragmentPageAdapter(getSupportFragmentManager(),mFragmentList));
        vpContent.setCurrentItem(0);

        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rgHomeViewPagerContorl.check(R.id.rb_home_pager);
                        break;
                    case 1:
                        rgHomeViewPagerContorl.check(R.id.rb_music_pager);
                        break;
                    case 2:
                        rgHomeViewPagerContorl.check(R.id.rb_friend_pager);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (dlLayout.isDrawerOpen(GravityCompat.START)) {
            dlLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build();
    }

    /**
     * 按返回键不退出应用。
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (dlLayout.isDrawerOpen(GravityCompat.START)) {
                dlLayout.closeDrawer(GravityCompat.START);
            } else {
                // 不退出程序，进入后台
                moveTaskToBack(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            onUserInteraction();
        }
        /**
         * 源码中可以看到，在Activity dispatchTouchEvent中，调用了Window的superDispatchTouchEvent，如果getWindow().superDispatchTouchEvent(ev)返回true,则这个事件被消耗，否则调用Activity的onTouchEvent方法
         */
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
}
