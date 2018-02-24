package com.kiki.kikiwynews.presenter;

import com.kiki.kikiwynews.bean.zhihu.SectionChildListBean;
import com.kiki.kikiwynews.bean.zhihu.ThemeChildListBean;

/**
 * Created by KIKI on 2018/1/28.
 * 346409606@qq.com
 */

public interface ZhihuThemeDetailPresenter {
    interface View extends BaseView<ThemeChildListBean>{
        void refreshSectionData(SectionChildListBean data);
    }
    interface  Presenter{
        void fetchThemeChildList(int id);
        void fetchSectionChildList(int id);
    }
}
