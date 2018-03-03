package com.kiki.kikiwynews.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kiki.kikiwynews.BaseFragment;
import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.webview.EasyLoadMoreView;
import com.kiki.kikiwynews.bean.GankIoDataBean;
import com.kiki.kikiwynews.injector.component.DaggerAndroidComponent;
import com.kiki.kikiwynews.injector.module.AndroidModule;
import com.kiki.kikiwynews.injector.module.GankIoHttpModule;
import com.kiki.kikiwynews.presenter.GankIoAndroidPresenter;
import com.kiki.kikiwynews.presenter.impl.GankIoAndroidPresenterImpl;

import java.util.List;

import butterknife.BindView;

/**
 * 左边fragment
 * Created by KIKI on 2017/12/26.
 * 346409606@qq.com
 */

public class AndroidFragment extends BaseFragment<GankIoAndroidPresenterImpl> implements GankIoAndroidPresenter.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.rv_android)
    RecyclerView rvAndroid;

    @BindView(R.id.srfl_android)
    SwipeRefreshLayout srflAndroid;

    private int page;
    private final static int PRE_PAGE=10;
    private List<GankIoDataBean.ResultBean> datalist;

    private boolean isRefresh=false;

    @Override
    protected void initInject() {
        DaggerAndroidComponent.builder()
                .gankIoHttpModule(new GankIoHttpModule())
                .androidModule(new AndroidModule())
                .build().injectAndroid(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_android;
    }

    @Override
    protected void loadData() {
        mPresenter.fetchGankIoData(page,PRE_PAGE);
    }

    @Override
    protected void initView() {
        srflAndroid.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        srflAndroid.setOnRefreshListener(this);
        rvAndroid.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAndroid.setAdapter(mAdapter);
        mAdapter.setLoadMoreView(new EasyLoadMoreView());
        mAdapter.setOnLoadMoreListener(this,rvAndroid);
    }

    /**
     * SwipeRefreshLayout的刷新回调
     */
    @Override
    public void onRefresh() {
        page=0;
        isRefresh=true;
        mAdapter.setEnableLoadMore(false);
        mPresenter.fetchGankIoData(page,PRE_PAGE);
    }

    /**
     * BaseQuickAdapter的拉到底部加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        if (page >= 6) {
            mAdapter.loadMoreEnd();
            srflAndroid.setEnabled(true);
        } else {
            loadData();
            srflAndroid.setEnabled(false);
        }
    }

    /**
     * fetchGankIdData后的回调
     * @param mData
     */
    @Override
    public void refreshView(List<GankIoDataBean.ResultBean> mData) {
        if(isRefresh){
            srflAndroid.setRefreshing(false);
            mAdapter.setEnableLoadMore(true);
            isRefresh=false;
            /**
             * setNewData中存在notifyDataSetChanged
             */
            mAdapter.setNewData(mData);
        }else{
            srflAndroid.setEnabled(true);
            page++;
            mAdapter.addData(mData);
            mAdapter.loadMoreComplete();
        }
    }
}
