package cn.xiaojii.cashgift.view.inter.fragment;

import java.util.List;

import cn.xiaojii.cashgift.bean.fragment.ProjectTableItemBean;

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
     * @param sumMoney
     * @param personName
     */
    public void updateView(int inCount,int inMoney,int outCount,int outMoney,int sumMoney,String personName);

    /**
     * 更新；listview
     * @param projectTableItemBeans
     */
    public void updateListView(List<ProjectTableItemBean> projectTableItemBeans);

    /**
     * 更新TextView
     */
    void updateTextView();

}
