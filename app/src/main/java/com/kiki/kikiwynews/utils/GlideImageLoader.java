package com.kiki.kikiwynews.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kiki.kikiwynews.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by KIKI on 2018/1/17.
 * 346409606@qq.com
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path)
                .placeholder(R.mipmap.img_two_bi_one)
                .error(R.mipmap.img_two_bi_one)
                .crossFade(1000)
                .into(imageView);
    }
}
