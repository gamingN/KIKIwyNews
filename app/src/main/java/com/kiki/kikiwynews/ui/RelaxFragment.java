package com.kiki.kikiwynews.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kiki.kikiwynews.BaseFragment;
import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.adapter.RelaxAdapter;
import com.kiki.kikiwynews.bean.RelaxOneTimeBean;
import com.kiki.kikiwynews.injector.component.DaggerRelaxOneTimeComponent;
import com.kiki.kikiwynews.injector.module.RelaxHttpModule;
import com.kiki.kikiwynews.injector.module.RelaxModule;
import com.kiki.kikiwynews.presenter.RelaxPresenter;
import com.kiki.kikiwynews.presenter.impl.RelaxPresenterImpl;
import com.kiki.kikiwynews.view.RelaxLoadMoreView;
import com.kiki.kikiwynews.webview.WebViewActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by KIKI on 2018/3/3.
 * 346409606@qq.com
 */

public class RelaxFragment extends BaseFragment<RelaxPresenterImpl> implements RelaxPresenter.View,SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{
    @BindView(R.id.rv_android)
    RecyclerView rvAndroid;

    @BindView(R.id.srfl_android)
    SwipeRefreshLayout srlAndroid;

    private boolean isRefresh = false;


    private int index = 0;

    @Override
    public void refreshView(RelaxOneTimeBean mData) {
        List<RelaxOneTimeBean.T1350383429665Bean> lists=mData.getT1350383429665();
        if (isRefresh) {
            srlAndroid.setRefreshing(false);
            mAdapter.setEnableLoadMore(true);
            isRefresh = false;
            mAdapter.setNewData(lists);
        } else {
            srlAndroid.setEnabled(true);
            index += 20;
            mAdapter.addData(lists);
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    protected void initInject() {
        DaggerRelaxOneTimeComponent.builder()
                .relaxHttpModule(new RelaxHttpModule())
                .relaxModule(new RelaxModule())
                .build().injectRelax(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_android;
    }

    @Override
    protected void loadData() {
        mPresenter.fetchRelaxList(index);
    }

    @Override
    protected void initView() {
        srlAndroid.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        rvAndroid.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAndroid.setAdapter(mAdapter);
        srlAndroid.setOnRefreshListener(this);
        mAdapter.setLoadMoreView(new RelaxLoadMoreView());
        mAdapter.setOnLoadMoreListener(this, rvAndroid);

    }


    @Override
    public void onRefresh() {
        index = 0;
        isRefresh = true;
        mAdapter.setEnableLoadMore(false);
        mPresenter.fetchRelaxList(index);
    }

    @Override
    public void onLoadMoreRequested() {
        if (index >= 60) {
            mAdapter.loadMoreEnd();
            srlAndroid.setEnabled(true);
        } else {
            loadData();
            srlAndroid.setEnabled(false);
        }
    }
}
