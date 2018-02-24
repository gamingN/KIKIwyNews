package com.kiki.kikiwynews.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.adapter.KiFragmentPageAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KIKI on 2018/1/21.
 * 346409606@qq.com
 */

public class ZhiHuCommentAcitivity extends BaseActivity{

    @BindView(R.id.toolbar_zhihu_comment)
    Toolbar toolbarZhihuComment;
    @BindView(R.id.tab_zhihu_comment)
    TabLayout tabZhihuComment;
    @BindView(R.id.vp_zhihu_comment)
    ViewPager vpZhihuComment;

    private ArrayList<String> mTitleList=new ArrayList<>(4);
    private ArrayList<Fragment> mFragments=new ArrayList<>(4);
    private FragmentPagerAdapter myAdapter;
    private int shortNum;
    private int longNum;
    private int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        Intent intent=getIntent();
        int allNum=intent.getExtras().getInt("allNum");
        shortNum=intent.getExtras().getInt("shortNum");
        longNum=intent.getExtras().getInt("longNum");
        id=intent.getExtras().getInt("id");
        setToolbar(toolbarZhihuComment,String.format("%d条评论",allNum));
        initFragmentList();
        myAdapter=new KiFragmentPageAdapter(getSupportFragmentManager(),mFragments,mTitleList);
        vpZhihuComment.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        tabZhihuComment.setTabMode(TabLayout.MODE_FIXED);
        tabZhihuComment.setupWithViewPager(vpZhihuComment);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhihu_comment;
    }

    private void initFragmentList(){
        if(mTitleList.size()!=0){
            return;
        }
        mTitleList.add(String.format("短评论(%d)",shortNum));
        mFragments.add(ZhiHuCommentFragment.getInstance(true));
        mTitleList.add(String.format("长评论(%d)",longNum));
        mFragments.add(ZhiHuCommentFragment.getInstance(false));

    }

    public int getId(){
        return id;
    }
}
