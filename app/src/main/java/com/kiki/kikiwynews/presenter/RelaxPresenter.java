package com.kiki.kikiwynews.presenter;

import com.kiki.kikiwynews.bean.RelaxOneTimeBean;

/**
 * Created by KIKI on 2018/3/3.
 * 346409606@qq.com
 */

public interface RelaxPresenter {
    interface View extends BaseView<RelaxOneTimeBean>{

    }

    interface Presenter{
        void fetchRelaxList(int id);
    }
}
