package com.kiki.kikiwynews.webview;

import android.view.View;

/**
 * 关于webview,进度条，网络视频全屏的操作,js监听
 * Created by KIKI on 2017/12/27.
 * 346409606@qq.com
 */

public interface IWebPageView {
    //隐藏进度条
    void hindProgerssBar();

    //显示webview
    void showWebView();

    //隐藏WebView
    void hindWebView();

    /**
     * 进度条先加载到90%，然后再加载到100%
     */
    void startProgress();

    /**
     * 进度条变化时调用
     */
    void progressChanged(int newProgress);

    /**
     * 添加js监听
     */
    void addImageClickListener();

    /**
     * 播放网络视频全屏调用
     */
    void fullViewAddView(View view);

    void showVideoFullView();

    void hindVideoFullView();
}