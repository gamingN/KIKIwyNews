package com.kiki.kikiwynews.presenter;

import com.kiki.kikiwynews.bean.RelaxDetailBean;

/**
 * Created by KIKI on 2018/3/3.
 * 346409606@qq.com
 */

public interface RelaxDetailPresenter {
    interface View extends BaseView<RelaxDetailBean>{}
    interface Presenter {
        void fetchRelaxSetail(String id);
    }
}
