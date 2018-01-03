package com.kiki.kikiwynews.webview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by KIKI on 2017/12/29.
 * 346409606@qq.com
 */

public class ShareUtils {
    /**
     * 分享到的代码
     */
    public static void share(Context context, int stringRes){
        share(context,context.getString(stringRes));
    }

    /**
     * 分享到的代码
     */
    public static void share(Context context,String charText){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,"分享");
        intent.putExtra(Intent.EXTRA_TEXT,charText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(
                Intent.createChooser(intent,"分享")           //应用选择器
        );
    }

    public static void shareImage(Context context, Uri uri,String title){
        Intent shareIntent=new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM,uri);
        shareIntent.setType("image/jpeg");
        context.startActivity(Intent.createChooser(shareIntent,title));
    }
}
