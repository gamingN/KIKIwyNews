package com.kiki.kikiwynews.ui;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.adapter.ZhihuSectionAdapter;
import com.kiki.kikiwynews.adapter.ZhihuThemeAdapter;
import com.kiki.kikiwynews.bean.zhihu.SectionChildListBean;
import com.kiki.kikiwynews.bean.zhihu.ThemeChildListBean;
import com.kiki.kikiwynews.injector.component.DaggerZhihuThemeComponent;
import com.kiki.kikiwynews.injector.module.ZhihuHttpModule;
import com.kiki.kikiwynews.injector.module.ZhihuThemeModule;
import com.kiki.kikiwynews.presenter.ZhihuThemeDetailPresenter;
import com.kiki.kikiwynews.presenter.impl.ZhihuThemeDetailPresenterImpl;
import com.kiki.kikiwynews.utils.GlideUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by KIKI on 2018/1/28.
 * 346409606@qq.com
 */

public class ZhihuThemeActivity extends ZhihuThemeBaseActivity<ZhihuThemeDetailPresenterImpl> implements ZhihuThemeDetailPresenter.View{

    @BindView(R.id.rcv_activity)
    RecyclerView rcvActivity;
    @Inject
    ZhihuThemeAdapter zhihuThemeAdapter;

    private ZhihuSectionAdapter zhihuSectionAdapter;

    private int id;
    private List storiesList;
    private int sectionId;
    private String isSection;

    @Override
    protected void initInject() {
        DaggerZhihuThemeComponent.builder()
                .zhihuHttpModule(new ZhihuHttpModule())
                .zhihuThemeModule(new ZhihuThemeModule())
                .build().injectZhiHuTheme(this);
    }

    @Override
    protected void loadData() {
        id = getIntent().getIntExtra("id", 0);
        sectionId = getIntent().getIntExtra("section_id",0);
        if (sectionId>0){
            mPresenter.fetchSectionChildList(sectionId);
        }else {
            mPresenter.fetchThemeChildList(id);
        }
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void initView() {
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        if (sectionId>0){
            zhihuSectionAdapter = new ZhihuSectionAdapter(storiesList);
            zhihuSectionAdapter.setOnZhihuThemeItemClick(new ZhihuSectionAdapter.OnItemClick() {
                @Override
                public void onItemClick(int id, View view) {
                    startZhiHuDetailActivity(id, view);
                }
            });
            rcvActivity.setAdapter(zhihuSectionAdapter);
        }else {
            zhihuThemeAdapter = new ZhihuThemeAdapter(storiesList);
            zhihuThemeAdapter.setOnZhihuThemeItemClick(new ZhihuThemeAdapter.OnItemClick() {
                @Override
                public void onItemClick(int id, View view) {
                    startZhiHuDetailActivity(id, view);
                }
            });
            rcvActivity.setAdapter(zhihuThemeAdapter);
        }

        rcvActivity.setLayoutManager(new LinearLayoutManager(this));
        appbarThemeChild.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset>=0){
                    swipeRefresh.setEnabled(true);
                }else{
                    swipeRefresh.setEnabled(false);
                }
            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(sectionId>0){
                    mPresenter.fetchThemeChildList(sectionId);
                }else{
                    mPresenter.fetchThemeChildList(id);
                }
            }
        });
    }

    private void startZhiHuDetailActivity(int id, View view) {
        Intent intent = new Intent();
        intent.setClass(this, ZhiHuDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("isNotTransition", true);
        /**
         * 用这个ActivityOptionsCompat比用ActivityOptions兼容性更好，前者是V4下的兼容到16后者到21.
         * ActivityOptionsCompat.makeSceneTransitionAnimation(）的第三个参数则是跳转后图片显示的transitionName的值
         *     <android.support.design.widget.AppBarLayout
         android:transitionName="zhihu_detail_title"
         android:fitsSystemWindows="true">
         */
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                view, this.getResources().getString(R.string.zhihu_detail_title));
        startActivity(intent, options.toBundle());
    }

    @Override
    public void refreshView(ThemeChildListBean data) {
        storiesList = data.getStories();
        zhihuThemeAdapter.setNewData(storiesList);
        zhihuThemeAdapter.notifyDataSetChanged();
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        setToolbar(toolbarThemeBase, data.getName());
        GlideUtils.load(this, data.getBackground(), ivThemeChildOrigin);
        Glide.with(this).load(data.getBackground()).bitmapTransform(new BlurTransformation(this)).into(ivThemeChildBlur);
        tvThemeChildDes.setText(data.getDescription());

    }

    @Override
    public void refreshSectionData(SectionChildListBean data) {
        storiesList = data.getStories();
        zhihuSectionAdapter.setNewData(storiesList);
        zhihuSectionAdapter.notifyDataSetChanged();
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        setToolbar(toolbarThemeBase, data.getName());
        tvThemeChildDes.setText(data.getName());
    }
}
