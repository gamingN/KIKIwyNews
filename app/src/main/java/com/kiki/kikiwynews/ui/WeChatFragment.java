package com.kiki.kikiwynews.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kiki.kikiwynews.BaseFragment;
import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.app.AppConstants;
import com.kiki.kikiwynews.bean.wx.WXItemBean;
import com.kiki.kikiwynews.injector.component.DaggerWeChatComponent;
import com.kiki.kikiwynews.injector.module.WeChatHttpModule;
import com.kiki.kikiwynews.injector.module.WeChatModule;
import com.kiki.kikiwynews.presenter.WeChatpresent;
import com.kiki.kikiwynews.presenter.impl.WeChatPresenterImpl;
import com.kiki.kikiwynews.rx.RxBus;

import java.util.List;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by KIKI on 2018/2/25.
 * 346409606@qq.com
 */

public class WeChatFragment extends BaseFragment<WeChatPresenterImpl> implements WeChatpresent.View{
    @BindView(R.id.rcv_activity)
    RecyclerView rcvActivity;

    private static final int NUM_OF_PAGE = 20;

    private int currentPage = 1;
    private List<WXItemBean> data;
 //   private CompositeSubscription searshSubscription;

    @Override
    public void refreshView(List<WXItemBean> mData) {
        mAdapter.setNewData(mData);
    }

    @Override
    protected void initInject() {
        DaggerWeChatComponent.builder()
                .weChatHttpModule(new WeChatHttpModule())
                .weChatModule(new WeChatModule())
                .build().injectWeChat(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void loadData() {
        mPresenter.fetchWeChatHot(NUM_OF_PAGE, currentPage);
//        if (this.searshSubscription == null) {
//            registerEvent();
//        }
    }

//    private void registerEvent() {
//        Subscription mSubscription = RxBus.getDefault().toObservable(AppConstants.WECHA_SEARCH, String.class)
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        mPresenter.fetchWXHotSearch(20, 1, s);
//                    }
//                });
//        if (this.searshSubscription == null) {
//            searshSubscription = new CompositeSubscription();
//        }
//        searshSubscription.add(mSubscription);
//    }

    @Override
    protected void initView() {
        rcvActivity.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvActivity.setAdapter(mAdapter);
    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        if (this.searshSubscription != null && searshSubscription.hasSubscriptions()) {
//            this.searshSubscription.unsubscribe();
//        }
//    }
}
