package com.kiki.kikiwynews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 *
 * Created by KIKI on 2018/1/2.
 * 346409606@qq.com
 */

public class KiFragmentPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private List<String> mTitleList;


    public KiFragmentPageAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }

    public KiFragmentPageAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> mTitleList){
        super(fm);
        this.fragmentList=fragmentList;
        this.mTitleList=mTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    /**
     * 首页显示title,每日推荐等
     * 若有问题，移到对应单独页面
     */
    public CharSequence getPageTitle(int position){
        if(mTitleList!=null){
            return  mTitleList.get(position);
        }else{
            return "";
        }
    }
}
