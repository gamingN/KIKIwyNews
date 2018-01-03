package com.kiki.kikiwynews;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.kiki.kikiwynews.app.AppConstants;

import static com.kiki.kikiwynews.app.AppConstants.STATE_EMPTY;
import static com.kiki.kikiwynews.app.AppConstants.STATE_ERROR;
import static com.kiki.kikiwynews.app.AppConstants.STATE_LOADING;
import static com.kiki.kikiwynews.app.AppConstants.STATE_SUCCESS;
import static com.kiki.kikiwynews.app.AppConstants.STATE_UNKNOWN;

/**
 * 自定义layout，负责加载过程中的view
 * Created by Administrator on 2017/12/25.
 */

public abstract class LoadingPage extends FrameLayout{

    private View loadingView;       //加载中的界面
    private View errorView;         //错误界面
    private View emptyView;         //空界面
    public View successView;        //加载成功的界面

    private AnimationDrawable mAnimationDrawable;
    private ImageView img;

    public int state= AppConstants.STATE_UNKNOWN;

    public  Context mContext;

    public LoadingPage(@NonNull Context context) {
        this(context,null);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        init();
    }

    private void init(){
        this.setBackgroundColor(getResources().getColor(R.color.colorPageBg));

        //把loadingView添加到frameLayout
        if(loadingView==null){
            //Toast.makeText(mContext, "Loadingpage:loadingView==null", Toast.LENGTH_SHORT).show();
            loadingView=createLoadingView();
            this.addView(loadingView,LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        }

        //把emptyView添加到frameLayout上
        if (emptyView == null) {
            emptyView = createEmptyView();
            this.addView(emptyView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        }
        //把errorView添加到frameLayout上
        if (errorView == null) {
            errorView = createErrorView();
            this.addView(errorView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }
        showPage();//根据状态显示界面

    }


    private void startAnimation() {
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }

    private void stopAnimation() {
        if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
    }

    public void showPage() {
        if(loadingView !=null){
            if(state==STATE_UNKNOWN || state==STATE_LOADING){
                loadingView.setVisibility(View.VISIBLE);
                startAnimation();
            }else {
                // 关闭动画
                stopAnimation();
                loadingView.setVisibility(View.GONE);
            }
        }

        if (state == STATE_EMPTY || state == STATE_ERROR || state == STATE_SUCCESS) {
            // 关闭动画
            stopAnimation();
        }


        if (emptyView != null) {
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.GONE);
        }

        if (errorView != null) {
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.GONE);
        }

        if(state==STATE_SUCCESS){
            Log.d("KIKI", "showPage: onsucce");
            if(successView==null){
               successView = LayoutInflater.from(mContext).inflate(getLayoutId(), null);
                addView(successView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                initView();
            }
        }
    }

    /**
     * 子类关于View的操作(如setAdapter)都必须在这里面，会因为页面状态不为成功，而binding还没创建就引用而导致空指针。
     */
    protected abstract void initView();

    /**
     * 根据网络获取的数据返回状态，每一个子类的获取网络返回的都不一样，所以要交给子类去完成
     */
    protected abstract void loadData();

    protected abstract int getLayoutId();

    private View createErrorView() {
        errorView = LayoutInflater.from(mContext).inflate(R.layout.basefragment_state_error, null);
        errorView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                state = STATE_LOADING;
                showPage();
                loadData();
            }
        });
        return errorView;
    }



    private View createEmptyView() {
        emptyView=LayoutInflater.from(mContext).inflate(R.layout.basefragment_state_empty,null);
        return emptyView;
    }

    private View createLoadingView(){
        loadingView= LayoutInflater.from(mContext).inflate(R.layout.basefragment_state_loading,null);
        img= (ImageView) loadingView.getRootView().findViewById(R.id.img_progress);
        //加载动画 这边也可以直接用progressbar
        mAnimationDrawable= (AnimationDrawable) img.getDrawable();
        if(!mAnimationDrawable.isRunning()){
            mAnimationDrawable.start();
        }
        return loadingView;
    }



}
