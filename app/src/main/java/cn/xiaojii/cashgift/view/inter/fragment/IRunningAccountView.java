package cn.xiaojii.cashgift.view.inter.fragment;

import java.util.List;

import cn.xiaojii.cashgift.bean.fragment.ProjectBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public interface IRunningAccountView {
    /**
     * update the listview on IRunningAccountView
     *
     * @param projectBeanList
     */
    void updateListView(List<ProjectBean> projectBeanList);
}
