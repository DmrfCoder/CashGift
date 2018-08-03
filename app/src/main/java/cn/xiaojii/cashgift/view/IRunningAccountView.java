package cn.xiaojii.cashgift.view;

import java.util.List;

import cn.xiaojii.cashgift.bean.RunningAccountBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public interface IRunningAccountView {
    /**
     * update the listview on IRunningAccountView
     *
     * @param runningAccountBeanList
     */
    void updateListView(List<RunningAccountBean> runningAccountBeanList);
}
