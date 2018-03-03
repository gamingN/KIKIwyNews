package com.kiki.kikiwynews.webview;

import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.kiki.kikiwynews.R;

/**
 * - 播放网络视频配置
 * - 上传图片(兼容)
 * Created by KIKI on 2017/12/27.
 * 346409606@qq.com
 */

public class MyWebChromeClient extends WebChromeClient {

    private IWebPageView mIWebPageView;
    private WebViewActivity mActivity;

    /**
     * 全屏View
     */
    private View mXCustomView;


    private CustomViewCallback mXCustomViewCallback;

    /**
     * 视频加载时的loading
     */
    private View mXProgressVideo;

    public MyWebChromeClient(IWebPageView mIWebPageView){
        this.mIWebPageView=mIWebPageView;
        this.mActivity= (WebViewActivity) mIWebPageView;
    }
    /**
     *播放网络视频时全屏会被调用的方法
     */
    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mIWebPageView.hindWebView();
        // 如果一个视图已经存在，那么立刻终止并新建一个
        if (mXCustomView != null) {
            callback.onCustomViewHidden();
            return;
        }

        mActivity.fullViewAddView(view);
        mXCustomView = view;
        mXCustomViewCallback = callback;
        mIWebPageView.showVideoFullView();
    }
    /**
     *重写方法，视频播放退出全屏会被调用的
     */
    @Override
    public void onHideCustomView() {
        if (mXCustomView == null)// 不是全屏播放状态
            return;
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mXCustomView.setVisibility(View.GONE);
        if (mActivity.getVideoFullView() != null) {
            mActivity.getVideoFullView().removeView(mXCustomView);
        }
        mXCustomView = null;
        mIWebPageView.hindVideoFullView();
        mXCustomViewCallback.onCustomViewHidden();
        mIWebPageView.showWebView();
    }
    /**
     *视频加载时进程loading
     */
    @Override
    public View getVideoLoadingProgressView() {
        if (mXProgressVideo == null) {
            LayoutInflater inflater = LayoutInflater.from(mActivity);
            mXProgressVideo = inflater.inflate(R.layout.video_loading_progress, null);
        }
        return mXProgressVideo;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        mIWebPageView.progressChanged(newProgress);
    }

    /**
     * 判断是否是全屏
     */
    public boolean inCustomView() {
        return (mXCustomView != null);
    }


    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        // 设置title
        mActivity.setTitle(title);
        this.title = title;
    }

    private String title = "";

    public String getTitle() {
        return title + " ";
    }

}
