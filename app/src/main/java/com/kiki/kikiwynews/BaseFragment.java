package com.kiki.kikiwynews;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kiki.kikiwynews.http.Stateful;
import com.kiki.kikiwynews.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Fragment的基类
 * Created by Administrator on 2017/12/25.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements LifeSubscription,Stateful{
    @Inject
    protected P mPresenter;

    @Inject
    protected BaseQuickAdapter mAdapter;

    public LoadingPage mLoadingPage;

    private boolean mIsVisible=false;  //fragment是否显示了

    private boolean isPrepared=false;

    private boolean isFirst=true;      //只加载一次界面

    protected View contentView;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mLoadingPage==null){
            mLoadingPage=new LoadingPage(getContext()) {
                @Override
                protected void initView() {
                    if(isFirst){
                        BaseFragment.this.contentView=this.successView;//loadingPage的contentView
                        bind= ButterKnife.bind(BaseFragment.this,contentView);
                        BaseFragment.this.initView();
                        isFirst=false;
                    }
                }

                @Override
                protected void loadData() {
                    BaseFragment.this.loadData();
                }

                @Override
                protected int getLayoutId() {
                    return BaseFragment.this.getLayoutId();
                }
            };
        }
        isPrepared=true;
        loadBaseData();
        return mLoadingPage;
    }

    /**
     * 在这里实现Fragment数据的缓加载,执行在onActivityCreated之前的系统回调
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){ //fragment可见
            mIsVisible=true;
            onVisible();
        }else{         //fragment不可见
            mIsVisible=false;
            onInvisible();
        }
    }

    @Override
    public void setState(int state) {
        mLoadingPage.state=state;
        mLoadingPage.showPage();
    }

    /**
     * 显示时加载数据，需要这样的使用
     * 注意声明isPrepared，先初始化
     * 在onActivityCreated之后第一次显示加载数据，只加载一次
     */
    protected void onVisible() {
        if(isFirst){
            initInject();
            if(mPresenter!=null){
                mPresenter.attachView(this);
            }
        }
        loadBaseData();
    }

    /**
     * dagger2注入
     */
    protected abstract void initInject();

    protected void onInvisible() {}

    public void loadBaseData() {
        if(!mIsVisible || !isPrepared || !isFirst){
            return;
        }
        loadData();
    }

    /**
     * 网络请求成功在去加载布局
     */
    protected abstract int getLayoutId();

    /**
     * 根据网络获取的数据返回状态，每一个子类的获取网络返回的都不一样，所以要交给子类去完成
     */
    protected abstract void loadData();

    /**
     * 子类关于View的操作(如setAdapter)都必须在这里面，会因为页面状态不为成功，而binding还没创建就引用而导致空指针。
     * loadData()和initView只执行一次，如果有一些请求需要二次的不要放到loadData()里面。
     */
    protected abstract void initView();

    private CompositeSubscription mCompositeSubscription;
    /**
    *用于添加rx的监听的在onDestroy中记得关闭不然会内存泄漏。
    */
     @Override
    public void bindSubscription(Subscription subscription) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(subscription);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (bind != null) {
            bind.unbind();
        }
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
        }
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }
}
