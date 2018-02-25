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
 * Created by KIKI on 2018/1/8.
 * 346409606@qq.com
 */

public class RightFragment extends BaseFragment {

    @BindView(R.id.tab_gank)
    TabLayout tabGank;
    @BindView(R.id.vp_gank)
    ViewPager vpGank;

    private ArrayList<String> mTitleList = new ArrayList<>(3);
    private ArrayList<Fragment> mFragments = new ArrayList<>(3);
    private KiFragmentPageAdapter myAdapter;

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void loadData() {
        setState(AppConstants.STATE_SUCCESS);
    }

    @Override
    protected void initView() {
        initFragmentList();
        myAdapter=new KiFragmentPageAdapter(getChildFragmentManager(),mFragments,mTitleList);
        vpGank.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tabGank.setTabMode(TabLayout.MODE_FIXED);
        tabGank.setupWithViewPager(vpGank);
    }

    private void initFragmentList() {
        if(mTitleList.size()!=0){
            return;
        }
        mTitleList.add("wechat");
        mFragments.add(new WeChatFragment());
    }
}
