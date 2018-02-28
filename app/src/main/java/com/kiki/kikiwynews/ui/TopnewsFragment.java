package com.kiki.kikiwynews.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kiki.kikiwynews.BaseFragment;
import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.bean.TopnewsBean;
import com.kiki.kikiwynews.injector.component.DaggerTopnewsComponent;
import com.kiki.kikiwynews.injector.module.TopnewsHttpModule;
import com.kiki.kikiwynews.injector.module.TopnewsModule;
import com.kiki.kikiwynews.presenter.Topnewspresenter;
import com.kiki.kikiwynews.presenter.impl.TopnewsPresenterImpl;

import java.util.List;

import butterknife.BindView;

/**
 * Created by KIKI on 2018/2/26.
 * 346409606@qq.com
 */

public class TopnewsFragment extends BaseFragment<TopnewsPresenterImpl> implements Topnewspresenter.view{
    @BindView(R.id.rcv_activity)
    RecyclerView recyclerView;


    @Override
    public void refreshView(List<TopnewsBean.ResultBean.DataBean> mData) {
        mAdapter.setNewData(mData);
    }

    @Override
    protected void initInject() {
        DaggerTopnewsComponent.builder()
                .topnewsHttpModule(new TopnewsHttpModule())
                .topnewsModule(new TopnewsModule())
                .build().injectTopnewsFragment(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void loadData() {
        mPresenter.fetchtopNews();
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);
    }
}
