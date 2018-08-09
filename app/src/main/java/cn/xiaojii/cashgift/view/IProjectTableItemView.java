package cn.xiaojii.cashgift.view;

import java.util.List;

import cn.xiaojii.cashgift.bean.ProjectBean;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public interface IProjectTableItemView {


    /**
     * 更新顶部信息栏
     * @param inCount
     * @param inMoney
     * @param outCount
     * @param outMoney
     */
    public void updateView(int inCount,int inMoney,int outCount,int outMoney);

    /**
     * 更新；listview
     * @param projectBeanList
     */
    public void updateListView(List<ProjectBean> projectBeanList);

}
