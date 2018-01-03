package com.kiki.kikiwynews.webview;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 监听网页链接：优酷视频直接跳到自带浏览器
 * 根据标识：打电话发短信，发邮件
 * 进度条的显示
 * 添加javascript监听
 * Created by KIKI on 2017/12/31.
 * 346409606@qq.com
 */

public class MyWebViewClient extends WebViewClient{
    private IWebPageView mIWebPageView;
    private WebViewActivity mWebViewActivity;

    public MyWebViewClient(IWebPageView mIWebPageView){
        this.mIWebPageView=mIWebPageView;
        mWebViewActivity= (WebViewActivity) mIWebPageView;
    }

    /**
     * 去掉警告的注释
     */
    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //优酷视频跳转浏览器播放
        if(url.startsWith("http://v.youku.com/")){
            Intent intent=new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addCategory("android.intent.category.BROWSABLE");
            Uri content_url=Uri.parse(url);
            intent.setData(content_url);
            mWebViewActivity.startActivity(intent);
            return true;
        }//电话，短信，邮箱
        else if(url.startsWith(WebView.SCHEME_TEL) || url.startsWith("sms:") || url.startsWith(WebView.SCHEME_MAILTO)){
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            mWebViewActivity.startActivity(intent);
            return true;
        }
        mIWebPageView.startProgress();
        view.loadUrl(url);
        return false;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if(mWebViewActivity.mProgress90){
            mIWebPageView.hindProgerssBar();
        }else{
            mWebViewActivity.mPageFinish=true;
        }
        if(!CheckNetwork.isNetworkConnected(mWebViewActivity)){
            mIWebPageView.hindProgerssBar();
        }
        //html加载完成之后,添加监听图片的点击js函数
        mIWebPageView.addImageClickListener();
        super.onPageFinished(view,url);
    }

    //视频全屏播放按返回页面被放大的问题

    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        super.onScaleChanged(view, oldScale, newScale);
        if(newScale-oldScale>7){
            view.setInitialScale((int) (oldScale/newScale*100));//异常放大，缩回去
        }
    }
}