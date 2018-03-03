package com.kiki.kikiwynews.http.service;

import com.kiki.kikiwynews.bean.RelaxOneTimeBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by KIKI on 2018/3/3.
 * 346409606@qq.com
 */

public interface RelaxService {
    String API_RELAX="http://c.m.163.com/nc/article/";

    @GET("list/T1350383429665/{id}-20.html")
    Observable<RelaxOneTimeBean> fetchRelax(@Path("id") int id);
}
