package com.kiki.kikiwynews.ui;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blankj.utilcode.utils.SPUtils;
import com.kiki.kikiwynews.BaseFragment;
import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.adapter.ZhihuAdapter;
import com.kiki.kikiwynews.bean.zhihu.DailyListBean;
import com.kiki.kikiwynews.bean.zhihu.HomeListBean;
import com.kiki.kikiwynews.injector.component.DaggerZhihuHomeComponent;
import com.kiki.kikiwynews.injector.module.ZhihuHomeModule;
import com.kiki.kikiwynews.injector.module.ZhihuHttpModule;
import com.kiki.kikiwynews.presenter.BasePresenter;
import com.kiki.kikiwynews.presenter.ZhihuPresenter;
import com.kiki.kikiwynews.presenter.impl.ZhiHuPresenterImpl;
import com.kiki.kikiwynews.utils.GlideImageLoader;
import com.kiki.kikiwynews.webview.WebViewActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by KIKI on 2018/1/8.
 * 346409606@qq.com
 */

public class ZhiHuHomeFragment extends BaseFragment<ZhiHuPresenterImpl> implements ZhihuPresenter.View{

    @BindView(R.id.rv_everyday)
    RecyclerView rvZhihu;

    private Banner banner;

    private List<HomeListBean> homeListBeen;

    @Override
    public void onResume() {
        SPUtils spUtils=new SPUtils("home_list");
        if(spUtils.getBoolean("home_list_ischange")){
            homeListBeen=mPresenter.getHomeList();
            if(mAdapter!=null){
                mAdapter.setNewData(homeListBeen);
                mAdapter.notifyDataSetChanged();
                spUtils.putBoolean("home_list_ischange",false);
            }
        }
        super.onResume();
    }

    @Override
    public void refreshView(List<HomeListBean> mData) {
        Log.e("KIKI", "refreshView: zhihuhomefragment");
        homeListBeen=mData;
        List<DailyListBean.TopStoriesBean> topStoriesList=mPresenter.getTopStoriesList();
        if(homeListBeen.size()==12){
            View footerView = getActivity().getLayoutInflater().inflate(R.layout.item_zhihu_footer, (ViewGroup) rvZhihu.getParent(), false);
            View headerView = getActivity().getLayoutInflater().inflate(R.layout.item_zhihu_header, (ViewGroup) rvZhihu.getParent(), false);
            banner= (Banner) headerView.findViewById(R.id.banner);
            TextView tvZhihuHomeFooter= (TextView) footerView.findViewById(R.id.tv_zhihu_home_footer);
            ImageButton ibXiandu= (ImageButton) headerView.findViewById(R.id.ib_xiandu);
            ibXiandu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WebViewActivity.loadUrl(getActivity(), "https://gank.io/xiandu", "加载中...");
                }
            });
            initBanner(topStoriesList);
            mAdapter.setNewData(homeListBeen);
            mAdapter.addFooterView(footerView);
            mAdapter.addHeaderView(headerView);
            rvZhihu.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvZhihu.setAdapter(mAdapter);
            tvZhihuHomeFooter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(),HomeAdjustmentListActivity.class));
                }
            });

            ((ZhihuAdapter)mAdapter).setmOnItemClickListener(new ZhihuAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener(int id, View view) {
                    startZhiHuDetailActivity(id,view);
                }

                @Override
                public void onItemThemeClickListener(int id, View view) {
                    startZhihuThemeActivity("id",id,view);
                }

                @Override
                public void onItemSectionClickListener(int id, View view) {
                    startZhihuThemeActivity("section_id",id,view);
                }
            });
        }
    }

    private void initBanner(final List<DailyListBean.TopStoriesBean> topStoriesList) {
        banner.startAutoPlay();
        banner.setDelayTime(3000);
        List<String> imageList = new ArrayList<>();
        for (DailyListBean.TopStoriesBean topStoriesBean : topStoriesList) {
            imageList.add(topStoriesBean.getImage());
        }
        banner.setImages(imageList).setImageLoader(new GlideImageLoader()).start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                int id = topStoriesList.get(position).getId();
                startZhiHuDetailActivity(id, banner);
            }
        });
    }

    private void startZhihuThemeActivity(String name,int id,View view){
        Intent intent=new Intent();
        intent.setClass(getActivity(),ZhihuThemeActivity.class);
        intent.putExtra(name,id);
        ActivityOptionsCompat options=ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),view,"zhihu_theme");
        getActivity().startActivity(intent,options.toBundle());
    }

    private void startZhiHuDetailActivity(int id, View view) {
        Intent intent=new Intent();
        intent.setClass(getActivity(),ZhiHuDetailActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("isNotTransition",true);
        /**
         * ActivityOptionsCompat用于Activity跳转动画
         * 用这个ActivityOptionsCompat比用ActivityOptions兼容性更好，前者是V4下的兼容到16后者到21.
         * ActivityOptionsCompat.makeSceneTransitionAnimation(）的第三个参数则是跳转后图片显示的transitionName的值
         <android.support.design.widget.AppBarLayout
         android:transitionName="zhihu_detail_title"
         android:fitsSystemWindows="true">
         */
        ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                view,getActivity().getResources().getString(R.string.zhihu_detail_title));
        getActivity().startActivity(intent,optionsCompat.toBundle());

    }

    @Override
    protected void initInject() {
        DaggerZhihuHomeComponent.builder()
                .zhihuHttpModule(new ZhihuHttpModule())
                .zhihuHomeModule(new ZhihuHomeModule())
                .build().injectZhihuhome(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_everyday;
    }

    @Override
    protected void loadData() {
        Log.e("KIKI","zhihuhomefragment:loadData");
        mPresenter.fetchDailyData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onVisible() {
        super.onVisible();
        if (banner != null) {
            banner.start();
        }
    }

    @Override
    protected void onInvisible() {
        // 不可见时轮播图停止滚动
        if (banner != null) {
            banner.stopAutoPlay();
        }
    }
}
