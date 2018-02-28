package com.kiki.kikiwynews.presenter;

import com.kiki.kikiwynews.bean.TopnewsBean;

import java.util.List;

/**
 * Created by KIKI on 2018/2/26.
 * 346409606@qq.com
 */

public interface Topnewspresenter {
    interface view extends BaseView<List<TopnewsBean.ResultBean.DataBean>>{
    }

    interface Presenter{
        void fetchtopNews();
    }
}
