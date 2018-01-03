package com.kiki.kikiwynews.webview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by KIKI on 2018/1/1.
 * 346409606@qq.com
 */

class FullscreenHolder extends FrameLayout {
    public FullscreenHolder(@NonNull Context context) {
        super(context);
        setBackgroundColor(context.getResources().getColor(android.R.color.black));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
