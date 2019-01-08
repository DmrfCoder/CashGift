package cn.xiaojii.cashgift.interactor.inter.fragment;

import java.util.List;

import cn.xiaojii.cashgift.bean.fragment.ProjectBean;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public interface IProjectTableInteractor {
    interface UpdatePtViewListener {
        /**
         * 监听更新数据失败
         */
        void onUpdateViewError();

        /**
         * 监听更新数据成功
         *
         * @param dataList
         */
        void onUpdateViewSuccess(List dataList, int a, int b, int c, int d, int e, int totalMoney);

    }

    /**
     * 刷新视图
     * @param updateViewListener
     */
    void updateView(UpdatePtViewListener updateViewListener);


    /**
     *初始化数据并刷新视图
     * @param dataList
     */
    void initData(List dataList, UpdatePtViewListener updatePtViewListener);



    public interface ClickProjectTableItemListener {
        /**
         * 获取position处的project list失败
         */
        void onClickProjectTableItemError();

        /**
         *获取position处的project list成功
         *
         * @param list
         */
        void onClickProjectTableItemSuccess(List list);


    }

    /**
     * 处理item的点击事件
     *
     * @param position
     * @param clickProjectTableItemListener
     */
    void clickListViewItem(int position, ClickProjectTableItemListener clickProjectTableItemListener);


    /**
     * 新增条目
     * @param projectBean
     * @return
     */
    boolean addSingleProjectBean(ProjectBean projectBean);


}
