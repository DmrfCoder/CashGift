package cn.xiaojii.cashgift.interactor;

import java.util.List;

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

}
