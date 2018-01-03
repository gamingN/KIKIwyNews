package com.kiki.kikiwynews.webview;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * js通讯接口
 * Created by KIKI on 2017/12/31.
 * 346409606@qq.com
 */

public class ImageClickInterface {
    private Context context;

    public ImageClickInterface(Context context){
        this.context=context;
    }

    @JavascriptInterface
    public void imageClick(String imgUrl, String hasLink) {
        Log.e("----点击了图片 url: ", "" + imgUrl);
    }

    @JavascriptInterface
    public void textClick(String type, String item_pk) {
        if (!TextUtils.isEmpty(type) && !TextUtils.isEmpty(item_pk)) {
            Toast.makeText(context, "----点击了文字", Toast.LENGTH_SHORT).show();
        }
    }

}
