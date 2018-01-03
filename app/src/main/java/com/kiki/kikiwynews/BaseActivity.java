package com.kiki.kikiwynews;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.Window;

import java.util.LinkedList;
import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 管理所有Activity操作，子类重写getLayoutId来返回布局，
 * 还有setoolbar，管理subscription的容器
 * Created by Administrator on 2017/12/20.
 */

public abstract class BaseActivity extends AppCompatActivity implements LifeSubscription {
    //管理所有activity
    public final static List<AppCompatActivity> mActivities = new LinkedList<AppCompatActivity>();
    public BaseActivity activity;

    //滑动变量
    private int endX;
    private int startX;
    private int deltaX;
    private int endY;
    private int startY;
    private int deltaY;

    private View decorView;
    private VelocityTracker mVelocityTracker;//追踪手指滑动速度
    private boolean isClose = true;

    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
    }

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        mVelocityTracker = mVelocityTracker.obtain();
        decorView = getWindow().getDecorView();
        synchronized (mActivities) {
            mActivities.add(this);
        }
    }

    /**
     * 可直接使用
     *
     * @param toolbar
     * @param title
     */
    protected void setToolbar(Toolbar toolbar, String title) {
        setToolbar(toolbar, title, true);
    }

    protected void setToolbar(Toolbar toolbar, String title, boolean enable) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
        getSupportActionBar().setDisplayShowHomeEnabled(enable);
        getSupportActionBar().setDisplayShowTitleEnabled(enable);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    //管理subscription的容器
    private CompositeSubscription mCompositeSubscription;

    //取消订阅
    @Override
    public void bindSubscription(Subscription subscription) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();

        }
        this.mCompositeSubscription.add(subscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (mActivities) {
            mActivities.remove(this);
        }
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    public void killAll() {
        List<AppCompatActivity> copy;
        synchronized (mActivities) {
            copy = new LinkedList<>(mActivities);
        }
        for (AppCompatActivity activity : copy) {
            activity.finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    //用于侧滑关闭Activity
    public void touchFinish() {
        super.finish();
        overridePendingTransition(R.anim.alpha_enter, R.anim.alpha_exit);
    }

    /**
     * 关闭activity时执行这个动画
     */
    public void closeAnimator(int deltaX) {
        if (isClose) {
            ValueAnimator animator = ValueAnimator.ofInt(deltaX, decorView.getWidth());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (int) animation.getAnimatedValue();
                    decorView.scrollTo(-value, 0);
                }
            });

            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (isClose) {
                        touchFinish();
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.setDuration(300);
            animator.start();
        } else {
            ValueAnimator animator = ValueAnimator.ofInt(deltaX, 0);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (int) animation.getAnimatedValue();
                    decorView.scrollTo(-value, 0);
                }
            });
            animator.setDuration(300);
            animator.start();
        }
    }

    /**
     * 颜色过度变化
     */
    public Object evaluateColor(float fraction, Object startValue, Object endvalue) {
        int startInt = (int) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (int) endvalue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (startA + (int) (fraction * (endA - startA))) << 24 |
                (startR + (int) (fraction * (endR - startR))) << 16 |
                (startG + (int) (fraction * (endG - startG))) << 8 |
                (startB + (int) (fraction * (endB - startB)));

    }

    //需要侧滑关闭时再打开这个注释

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mVelocityTracker.addMovement(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getRawX();
                startY = (int) ev.getRawY();
                if (startX < getWindow().getDecorView().getWidth() / 32) {
                    return true;
                } else {
                    return super.dispatchTouchEvent(ev);
                }
            case MotionEvent.ACTION_MOVE:
                endX = (int) ev.getRawX();
                endY = (int) ev.getRawY();
                deltaX = endX - startX;
                deltaY = endY - startY;
                if (deltaX > deltaY && startX < getWindow().getDecorView().getWidth() / 32) {
                    decorView.scrollTo(-deltaX, 0);
                    decorView.getBackground().setColorFilter((Integer) evaluateColor((float) deltaX / (float) decorView.getWidth(), Color.BLACK, Color.TRANSPARENT), PorterDuff.Mode.SRC_OVER);
                    return true;
                } else {
                    return super.dispatchTouchEvent(ev);
                }
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                if (-25 < xVelocity && xVelocity <= 50 && deltaX > decorView.getWidth() / 3 && startX < getWindow().getDecorView().getWidth() / 32
                        || xVelocity > 50 && startX < getWindow().getDecorView().getWidth() / 32) {
                    isClose = true;
                    closeAnimator(deltaX);
                    return true;
                } else {
                    if (deltaX > 0 && startX < getWindow().getDecorView().getWidth() / 32) {
                        isClose = false;
                        closeAnimator(deltaX);
                        return true;
                    } else {
                        if (startX < getWindow().getDecorView().getWidth() / 32) {
                            decorView.scrollTo(0, 0);
                        }
                        return super.dispatchTouchEvent(ev);
                    }
                }
            case MotionEvent.ACTION_CANCEL:
                mVelocityTracker.clear();
                mVelocityTracker.recycle();
                return super.dispatchTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }
}
