package com.kiki.kikiwynews.http.service;

import com.kiki.kikiwynews.bean.wx.WXHttpResponse;
import com.kiki.kikiwynews.bean.wx.WXItemBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by KIKI on 2018/2/25.
 * 346409606@qq.com
 */

public interface WeChatService {
    String API_WeChat = "http://api.tianapi.com/";

    @GET("wxnew")
    Observable<WXHttpResponse<List<WXItemBean>>> fetchWXHot(@Query("key") String key, @Query("num") int num, @Query("page") int page);

    @GET("wxnew")
    Observable<WXHttpResponse<List<WXItemBean>>> fetchWXHotSearch(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);

}
