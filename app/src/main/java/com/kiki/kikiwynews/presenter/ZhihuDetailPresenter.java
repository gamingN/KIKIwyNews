package com.kiki.kikiwynews.presenter;

import com.kiki.kikiwynews.bean.zhihu.DetailExtraBean;
import com.kiki.kikiwynews.bean.zhihu.ZhihuDetailBean;

/**
 * Created by KIKI on 2018/1/17.
 * 346409606@qq.com
 */

public interface ZhihuDetailPresenter {

    interface View extends BaseView<ZhihuDetailBean>{
        void showExtraInfo(DetailExtraBean detailExtraBean);
    }

    interface Presenter{
        void fetchDetailInfo(int id);
        void fetchDetailExtraInfo(int id);
    }
}
