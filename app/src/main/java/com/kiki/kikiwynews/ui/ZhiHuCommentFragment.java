package com.kiki.kikiwynews.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kiki.kikiwynews.BaseFragment;
import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.bean.zhihu.CommentBean;
import com.kiki.kikiwynews.injector.component.DaggerZhihuCommentComponent;
import com.kiki.kikiwynews.injector.module.ZhihuCommentModule;
import com.kiki.kikiwynews.injector.module.ZhihuHttpModule;
import com.kiki.kikiwynews.presenter.impl.ZhihuCommentPresenter;
import com.kiki.kikiwynews.presenter.impl.ZhihuCommentPresenterImpl;

import java.util.List;

import butterknife.BindView;

/**
 * Created by KIKI on 2018/1/21.
 * 346409606@qq.com
 */

public class ZhiHuCommentFragment extends BaseFragment<ZhihuCommentPresenterImpl> implements ZhihuCommentPresenter.View{

    @BindView(R.id.rv_zhihu_comment)
    RecyclerView rvZhihuComment;

    private boolean isShort;

    public static ZhiHuCommentFragment getInstance(boolean isShort){
        ZhiHuCommentFragment zhiHuCommentFragment=new ZhiHuCommentFragment();
        zhiHuCommentFragment.isShort=isShort;
        return zhiHuCommentFragment;
    }

    @Override
    public void refreshView(List<CommentBean.CommentsBean> mData) {
        mAdapter.setNewData(mData);
        rvZhihuComment.setAdapter(mAdapter);
    }

    @Override
    protected void initInject() {
        DaggerZhihuCommentComponent.builder()
                .zhihuHttpModule(new ZhihuHttpModule())
                .zhihuCommentModule(new ZhihuCommentModule())
                .build().injectZhihuComment(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu_comment;
    }

    @Override
    protected void loadData() {
        ZhiHuCommentAcitivity mZhiHuCommentAcitivity= (ZhiHuCommentAcitivity) getActivity();
        int id=mZhiHuCommentAcitivity.getId();
        if(isShort){
            mPresenter.fetchShortCommentInfo(id);
        }else{
            mPresenter.fetchLongCommentInfo(id);
        }
    }

    @Override
    protected void initView() {
        rvZhihuComment.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
