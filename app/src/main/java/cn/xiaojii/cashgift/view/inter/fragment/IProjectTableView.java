package cn.xiaojii.cashgift.view.inter.fragment;

import java.util.List;

import cn.xiaojii.cashgift.bean.fragment.ProjectTableBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public interface IProjectTableView {


    /**
     * @param projectTableBeanList
     */
    void updateListView(List<ProjectTableBean> projectTableBeanList);

    /**
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param totalMoney
     */
    void updateTopBarData(int a, int b, int c, int d, int e, int totalMoney);

    /**
     * 更新textView
     */
    void updateTextView();
}
