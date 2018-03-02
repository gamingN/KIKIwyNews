package com.kiki.kikiwynews.presenter;

import com.kiki.kikiwynews.bean.douban.HotMovieBean;

/**
 * Created by KIKI on 2018/2/28.
 * 346409606@qq.com
 */

public interface DoubanLMPresenter {
    interface View extends BaseView<HotMovieBean>{

    }

    interface Presenter{
        void fetchHotMovie();
    }
}
