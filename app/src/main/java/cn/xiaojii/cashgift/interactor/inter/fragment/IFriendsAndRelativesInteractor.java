package cn.xiaojii.cashgift.interactor.inter.fragment;

import java.util.List;

import cn.xiaojii.cashgift.interactor.impl.FriendsAndRelativesFragmentInteractor;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public interface IFriendsAndRelativesInteractor {
    public interface UpdateFarViewListener {
        /**
         * 监听更新数据失败
         */
        void onUpdateViewError();

        /**
         * 监听更新数据成功
         *
         * @param dataList
         */
        void onUpdateViewSuccess(List dataList);

    }

    /**
     * 刷新视图
     * @param updateViewListener
     */
    void updateView(UpdateFarViewListener updateViewListener);



    /**
     * 初始化数据并刷新视图
     * @param dataList
     */
    void initData(List dataList, UpdateFarViewListener updateFarViewListener);




    public interface NeedPositionNameListener {
        /**
         * 获取position处的姓名失败
         */
        void onNeedPositionNameError();

        /**
         * 获取position处的姓名成功
         *
         * @param name
         */
        void onNeedPositionNameSuccess(String name);


    }
    /**
     * 处理item的点击事件
     *
     * @param position
     * @param needPositionNameListener
     */
    void clickListViewItem(int position, FriendsAndRelativesFragmentInteractor.NeedPositionNameListener needPositionNameListener);


}
