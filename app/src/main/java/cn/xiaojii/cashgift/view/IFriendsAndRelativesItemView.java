package cn.xiaojii.cashgift.view;

import java.util.List;

import cn.xiaojii.cashgift.bean.ProjectBean;

/**
 * @author dmrfcoder
 * @date 2018/8/8
 */

public interface IFriendsAndRelativesItemView {
    /**
     * 更新顶部信息栏
     * @param totalMoney
     * @param inCount
     * @param outCount
     */
    public void updateView(int totalMoney,int inCount,int outCount);

    /**
     * 更新；listview
     * @param projectBeanList
     */
    public void updateListView(List<ProjectBean> projectBeanList);
}
