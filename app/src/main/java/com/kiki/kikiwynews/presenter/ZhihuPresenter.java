package com.kiki.kikiwynews.presenter;

import com.kiki.kikiwynews.bean.zhihu.HomeListBean;

import java.util.List;

/**
 * 契约类
 * Created by KIKI on 2018/1/8.
 * 346409606@qq.com
 */

public interface ZhihuPresenter {
    interface View extends BaseView<List<HomeListBean>>{

    }

    interface Presenter{
        void fetchDailyData();
        void fetchThemeList();
        void fetchSectionList();
        void fetchHotList();
    }

}
