package com.kiki.kikiwynews.presenter;

import com.kiki.kikiwynews.bean.GankIoDataBean;

import java.util.List;

/**
 * 包含一个接口View，一个接口Presenter
 * AndroidFragment分别实现了里面的2个接口
 * Created by KIKI on 2017/12/25.
 * 346409606@qq.com
 */

public interface GankIoAndroidPresenter {
    /**
     * 继承BaseView
     */
    interface View extends BaseView<List<GankIoDataBean.ResultBean>> {
    }

    /**
     * 只有一个获取gankIO数据的方法，fetchGankIoData
     */
    interface Presenter{
        void fetchGankIoData(int page, int pre_page);
    }
}
