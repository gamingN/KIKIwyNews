package com.kiki.kikiwynews.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.kiki.kikiwynews.BaseFragment;
import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.adapter.KiFragmentPageAdapter;
import com.kiki.kikiwynews.app.AppConstants;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by KIKI on 2018/1/7.
 * 346409606@qq.com
 */

public class HomeFragment extends BaseFragment{

    @BindView(R.id.tab_home)
    TabLayout tab_home;

    @BindView(R.id.vp_home)
    ViewPager vp_home;

    private ArrayList<String> mTitleLists=new ArrayList<>(4);
    private ArrayList<Fragment> mFragmentLists=new ArrayList<>(4);
    private KiFragmentPageAdapter myAdapter;

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void loadData() {
        setState(AppConstants.STATE_SUCCESS);
    }

    @Override
    protected void initView() {
        initfragmentList();
        myAdapter=new KiFragmentPageAdapter(getFragmentManager(),mFragmentLists,mTitleLists);
        vp_home.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tab_home.setTabMode(TabLayout.MODE_FIXED);
        tab_home.setupWithViewPager(vp_home);
    }

    private void initfragmentList() {
        if(mTitleLists.size()!=0){
            return;
        }
        mTitleLists.add("知乎日报");
        mTitleLists.add("头条新闻");
        mTitleLists.add("排行榜");
        mTitleLists.add("最新电影");

        mFragmentLists.add(new ZhiHuHomeFragment());
        mFragmentLists.add(new ZhiHuHomeFragment());
        mFragmentLists.add(new ZhiHuHomeFragment());
        mFragmentLists.add(new ZhiHuHomeFragment());
    }
}
