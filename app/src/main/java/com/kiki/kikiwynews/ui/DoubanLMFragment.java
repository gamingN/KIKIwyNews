package com.kiki.kikiwynews.ui;

import android.animation.Animator;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.animation.BaseAnimation;
import com.kiki.kikiwynews.BaseFragment;
import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.adapter.DoubanLMAdapter;
import com.kiki.kikiwynews.bean.douban.HotMovieBean;
import com.kiki.kikiwynews.injector.component.DaggerDoubanLMComponent;
import com.kiki.kikiwynews.injector.module.DoubanHttpModule;
import com.kiki.kikiwynews.injector.module.DoubanLMModule;
import com.kiki.kikiwynews.presenter.DoubanLMPresenter;
import com.kiki.kikiwynews.presenter.impl.DoubanLMPresenterImpl;

import java.util.List;

import butterknife.BindView;

/**
 * Created by KIKI on 2018/2/28.
 * 346409606@qq.com
 */

public class DoubanLMFragment extends BaseFragment<DoubanLMPresenterImpl> implements DoubanLMPresenter.View {
    @BindView(R.id.rcv_activity)
    RecyclerView rcvActivity;
    private List<HotMovieBean.SubjectsBean> subjectsBeanList;

    @Override
    public void refreshView(HotMovieBean mData) {
        subjectsBeanList=mData.getSubjects();
        mPresenter.checkState(subjectsBeanList);
        mAdapter.addData(subjectsBeanList);
    }

    @Override
    protected void initInject() {
        DaggerDoubanLMComponent.builder()
                .doubanHttpModule(new DoubanHttpModule())
                .doubanLMModule(new DoubanLMModule())
                .build().injectDoubanLMFragment(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void loadData() {
        mPresenter.fetchHotMovie();
    }

    @Override
    protected void initView() {
        rcvActivity.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvActivity.setAdapter(mAdapter);
        mAdapter.openLoadAnimation(new BaseAnimation() {
            @Override
            public Animator[] getAnimators(View view) {
                return new Animator[0];
            }
        });
        ((DoubanLMAdapter)mAdapter).setOnItemClickListener(new DoubanLMAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(HotMovieBean.SubjectsBean positionData, View view) {
                startZhiHuDetailActivity(positionData,view);
            }
        });
    }

    private void startZhiHuDetailActivity(HotMovieBean.SubjectsBean positionData, View view) {
        Intent intent=new Intent();
        intent.setClass(getActivity(),MovieDetailActivity.class);
        intent.putExtra("bean",positionData);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                view, "douban_detail_iamge");
        getActivity().startActivity(intent, options.toBundle());
    }


}
