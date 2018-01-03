package com.kiki.kikiwynews.webview;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.blankj.utilcode.utils.ToastUtils;
import com.kiki.kikiwynews.BaseActivity;
import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.utils.CommonUtils;

/**
 * 网页可以处理:
 * 点击相应控件:拨打电话、发送短信、发送邮件、上传图片、播放视频
 * 进度条、返回网页上一层、显示网页标题
 * Thanks to: https://github.com/youlookwhat/WebViewStudy
 * Created by KIKI on 2017/12/27.
 * 346409606@qq.com
 */

public class WebViewActivity extends BaseActivity implements IWebPageView{

    /**
     * 进度条
     */
    ProgressBar mProgressBar;

    WebView webView;

    /**
     *全屏时视频加载的view
     */
    FrameLayout videoFullView;
    Toolbar mTitleToolBar;

    /**
     * 进度条是否加载到90%
     */
    public boolean mProgress90;

    /**
     * 网页是否加载完成
     */
    public boolean mPageFinish;

    /**
     * 加载视频相关
     */
    private MyWebChromeClient myWebChromeClient;

    /**
     * title
     */
    private String mTitle;

    /**网页链接
     */
    private String mUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
        initTitle();
        initWebView();
        webView.loadUrl(mUrl);
    }

    private void initWebView() {
        mProgressBar.setVisibility(View.VISIBLE);
        WebSettings ws=webView.getSettings();
        //网页内容的宽度是否可大于WebView控件的宽度
        ws.setLoadWithOverviewMode(false);

        //保存表单数据
        ws.setSaveFormData(true);
        //是否应该支持使用其屏幕缩放控件和手势缩放

        ws.setSupportZoom(true);
        ws.setBuiltInZoomControls(true);
        ws.setDisplayZoomControls(false);

        //启动应用缓存
        ws.setAppCacheEnabled(true);

        //设置缓存模式
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);

        //setDefaultZoom api19被废弃
        ws.setUseWideViewPort(true);

        //缩放比例1
        webView.setInitialScale(1);

        //告诉WebView启用JavaScript执行，默认的是false
        ws.setJavaScriptEnabled(true);

        //页面加载好以后，再放开图片
        ws.setBlockNetworkImage(false);

        //使用localStorage则必须打开
        ws.setDomStorageEnabled(true);

        //排版适应屏幕
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        //WebView是否支持多个窗口
        ws.setSupportMultipleWindows(true);

        // webview从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        //设置字体默认缩放放大小
        ws.setTextZoom(100);

        myWebChromeClient=new MyWebChromeClient(this);
        webView.setWebChromeClient(myWebChromeClient);
        //与js交互
        webView.addJavascriptInterface(new ImageClickInterface(this),"injectedObject");

        webView.setWebViewClient(new MyWebViewClient(this));
    }

    public void setTitle(String mTitle) {
        mTitleToolBar.setTitle(mTitle);
    }

    private void initTitle() {
        StatusBarUtil.setColor(this, CommonUtils.getColor(R.color.colorTheme),0);
        mProgressBar= (ProgressBar) findViewById(R.id.pb_webview);
        webView=(WebView) findViewById(R.id.webview_detail);
        videoFullView = (FrameLayout) findViewById(R.id.video_full_view);
        mTitleToolBar = (Toolbar) findViewById(R.id.tb_webview);
        setSupportActionBar(mTitleToolBar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.mipmap.icon_back);
        }

        setTitle(mTitle);
        mTitleToolBar.setOverflowIcon(getResources().getDrawable(R.mipmap.actionbar_more));
        mTitleToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mTitleToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.actionbar_share:
                        String shareText=myWebChromeClient.getTitle()+mUrl+ "（分享自"+R.string.app_name+"）";
                        break;
                    case R.id.actionbar_copy:
                        BaseTools.copy(mUrl);
                        ToastUtils.showShortToast("复制成功");
                        break;
                    case R.id.actionbar_open:
                        BaseTools.openLink(WebViewActivity.this,mUrl);
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webview_menu,menu);
        return true;
    }

    private void getIntentData() {
        if(getIntent()!=null){
            mTitle=getIntent().getStringExtra("mTitle");
            mUrl = getIntent().getStringExtra("mUrl");
        }
    }

    @Override
    public void hindProgerssBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showWebView() {
        webView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hindWebView() {
        webView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startProgress() {
        startprogress90();
    }

    @Override
    public void progressChanged(int newProgress) {
        if(mProgress90){
            int progress=newProgress*100;
            if(progress>900){
                mProgressBar.setProgress(progress);
                if(progress==1000){
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void addImageClickListener() {
        // 这段js函数的功能就是，遍历所有的img节点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
        // 如要点击一张图片在弹出的页面查看所有的图片集合,则获取的值应该是个图片数组
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\");" +
                "for(var i=0;i<objs.length;i++)" +
                "{" +
                "objs[i].onclick=function(){window.injectedObject.imageClick(this.getAttribute(\"src\"),this.getAttribute(\"has_link\"));}" +
                "}" +
                "})()");

        // 遍历所有的a节点,将节点里的属性传递过去(属性自定义,用于页面跳转)
        webView.loadUrl("javascript:(function(){" +
                "var objs =document.getElementsByTagName(\"a\");" +
                "for(var i=0;i<objs.length;i++)" +
                "{" +
                "objs[i].onclick=function(){" +
                "window.injectedObject.textClick(this.getAttribute(\"type\"),this.getAttribute(\"item_pk\"));}" +
                "}" +
                "})()");
    }

    @Override
    public void fullViewAddView(View view) {
        FrameLayout decor= (FrameLayout) getWindow().getDecorView();
        videoFullView=new FullscreenHolder(WebViewActivity.this);
        videoFullView.addView(view);
        decor.addView(videoFullView);
    }

    @Override
    public void showVideoFullView() {
        videoFullView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hindVideoFullView() {
        videoFullView.setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    /**
     * 假装进度条加载到90
     */
    public void startprogress90(){
        for(int i=0;i<900;i++){
            final int progress=i+1;
            mProgressBar.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mProgressBar.setProgress(progress);
                    if(progress==900){
                        mProgress90=true;
                        if(mPageFinish){
                            startProgerss90to100();
                        }
                    }
                }
            },(i+1)*2);
        }
    }

    /**
     * 进度条 加载到100%
     */
    public void startProgerss90to100() {
        for(int i=900;i<=1000;i++){
            final int progress=i+1;
            mProgressBar.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mProgressBar.setProgress(progress);
                    if(progress==1000){
                        mProgressBar.setVisibility(View.GONE);
                    }
                }
            },(i+1)*2);
        }
    }

    public FrameLayout getVideoFullView(){return videoFullView;}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            //如果是全屏播放则退出全屏
            if(myWebChromeClient.inCustomView()){
                hideCustomView();
                return true;
            }else if(webView.canGoBack()){
                webView.goBack();
            }else{
                webView.loadUrl("about:blank");
                finish();
            }
        }
        return false;
    }

    private void hideCustomView() {
        myWebChromeClient.onHideCustomView();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

//    /**
//     * 上传图片之后的回调
//     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        if (requestCode == MyWebChromeClient.FILECHOOSER_RESULTCODE) {
//            mWebChromeClient.mUploadMessage(intent, resultCode);
//        } else if (requestCode == MyWebChromeClient.FILECHOOSER_RESULTCODE_FOR_ANDROID_5) {
//            mWebChromeClient.mUploadMessageForAndroid5(intent, resultCode);
//        }
//    }


    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
        webView.resumeTimers();

        if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoFullView.removeAllViews();
        if(webView!=null){
            ViewGroup parents= (ViewGroup) webView.getParent();
            if(parents!=null){
                parents.removeView(webView);
            }
            webView.removeAllViews();
            webView.loadUrl("about:blank");
            webView.stopLoading();
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            webView.destroy();
            webView=null;
        }
    }

    /**
     * 静态启自
     * @param context
     * @param url
     * @param mTitle
     */
    public static void loadUrl(Context context,String url,String mTitle){
        Intent intent=new Intent(context,WebViewActivity.class);
        intent.putExtra("mUrl",url);
        intent.putExtra("mTitle",mTitle);
        context.startActivity(intent);
    }
}