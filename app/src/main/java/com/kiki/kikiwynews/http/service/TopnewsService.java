package com.kiki.kikiwynews.http.service;

import com.kiki.kikiwynews.bean.TopnewsBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by KIKI on 2018/2/26.
 * 346409606@qq.com
 */

public interface TopnewsService {
    String API_TOP="http://v.juhe.cn/toutiao/";
    String KEY_TOP="d71a81426419a35599459672300b9481";
    String TYPE_TOP="top";

    @GET("index")
    Observable<TopnewsBean> fetchTopnews(@Query("type") String type,@Query("key") String key);
}
