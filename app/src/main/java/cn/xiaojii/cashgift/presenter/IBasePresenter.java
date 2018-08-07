package cn.xiaojii.cashgift.presenter;

import java.util.List;

import cn.xiaojii.cashgift.bean.ProjectBean;

/**
 * @author dmrfcoder
 * @date 2018/8/4
 */

public interface IBasePresenter {

    /**
     *
     * 从dialog中增加条目，参数为泛型,
     * @param projectBean
     */
    void addProjectFromDG(ProjectBean projectBean);


    /**
     * 增加从brodcast中获取的条目
     * @param projectBean
     */
    void addProjectFromBC(ProjectBean projectBean);

    /**
     * 初始化
     * @param dataList
     */
    void initFragmentData(List dataList);

    /**
     * 从model拿到数据更新视图
     */
    void updateView();

    /**
     * 当Fragment进入后台模式时调用
     */
    void onPause();
}
