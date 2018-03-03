package com.kiki.kikiwynews.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.bean.zhihu.DetailExtraBean;
import com.kiki.kikiwynews.bean.zhihu.ZhihuDetailBean;
import com.kiki.kikiwynews.injector.component.DaggerZhihuDetailComponent;
import com.kiki.kikiwynews.injector.module.ZhihuHttpModule;
import com.kiki.kikiwynews.presenter.ZhihuDetailPresenter;
import com.kiki.kikiwynews.presenter.impl.ZhiHuDetailPresenterImpl;
import com.kiki.kikiwynews.utils.GlideUtils;
import com.kiki.kikiwynews.utils.HtmlUtil;
import com.kiki.kikiwynews.webview.ShareUtils;

import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by KIKI on 2018/1/18.
 * 346409606@qq.com
 */

public class ZhiHuDetailActivity extends ZhihuDetailBaseActivity<ZhiHuDetailPresenterImpl> implements ZhihuDetailPresenter.View{

    @BindView(R.id.tv_detail_bottom_like)
    TextView tvDetailBottomLike;
    @BindView(R.id.tv_detail_bottom_comment)
    TextView tvDetailBottomComment;
    @BindView(R.id.fl_detail_bottom)
    FrameLayout flDetailBottom;
    @BindView(R.id.nsv_zhihu_detail)
    NestedScrollView nsvZhihuDetail;
    @BindView(R.id.wv_detail_content)
    WebView wvDetailContent;

    int allNum=0;

    int shortNum=0;

    int longNum=0;

    String shareUrl;

    /**
     *是否显示底部菜单
     */
    boolean isBottomShow=true;

    boolean isImageShow=false;

    boolean isTransitionEnd = false;

    String imgUrl;

    boolean isNotTransition = false;

    private int id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void refreshView(ZhihuDetailBean mData) {
        imgUrl=mData.getImage();
        shareUrl=mData.getShare_url();
        if(isNotTransition){
            GlideUtils.loadDetailImg(this,imgUrl,detailBarImage);
        }else{
            if(!isImageShow && isTransitionEnd){
                GlideUtils.loadDetailImg(this,imgUrl,detailBarImage);
            }
        }
        toolbarZhihuDetail.setTitle(mData.getTitle());
        detatilBarCopyright.setText(mData.getImage_source());
        String htmlData= HtmlUtil.createHtmlData(mData.getBody(),mData.getCss(),mData.getJs());
        wvDetailContent.loadData(htmlData,HtmlUtil.MIME_TYPE,HtmlUtil.ENCODING);

    }

    @Override
    public void showExtraInfo(DetailExtraBean detailExtraBean) {
        tvDetailBottomLike.setText(String.format("%d个赞", detailExtraBean.getPopularity()));
        tvDetailBottomComment.setText(String.format("%d条评论", detailExtraBean.getComments()));
        allNum = detailExtraBean.getComments();
        shortNum = detailExtraBean.getShort_comments();
        longNum = detailExtraBean.getLong_comments();
    }

    @Override
    protected void initInject() {
        DaggerZhihuDetailComponent.builder()
                .zhihuHttpModule(new ZhihuHttpModule())
                .build().injectZhiHuDetail(this);
    }

    @Override
    protected void loadData() {
        id=getIntent().getIntExtra("id",0);
        isNotTransition=getIntent().getBooleanExtra("isNotTransition",false);
        mPresenter.fetchDetailInfo(id);
        mPresenter.fetchDetailExtraInfo(id);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_zhihu_detail;
    }

    @Override
    protected void initView() {
        WebSettings settings=wvDetailContent.getSettings();
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        wvDetailContent.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        nsvZhihuDetail.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY>oldScrollY && isBottomShow){
                    //下拉隐藏
                    isBottomShow=false;
                    //5.0以上view动画
                    flDetailBottom.animate().translationY(flDetailBottom.getHeight());
                }else if(scrollY<oldScrollY && !isBottomShow){
                    isBottomShow=true;
                    flDetailBottom.animate().translationY(0);
                }
            }
        });
    }

    @OnClick(R.id.tv_detail_bottom_share)
    void shreUrl(){
        ShareUtils.shareText(this,shareUrl,"分享一篇文章");
    }

    @OnClick(R.id.tv_detail_bottom_comment)
    void gotoComment(){
        Intent intent=new Intent();
        intent.setClass(this,ZhiHuCommentAcitivity.class);
        intent.putExtra("id",id);
        intent.putExtra("allNum",allNum);
        intent.putExtra("shortNum",shortNum);
        intent.putExtra("longNum",longNum);
        startActivity(intent);
    }

}
