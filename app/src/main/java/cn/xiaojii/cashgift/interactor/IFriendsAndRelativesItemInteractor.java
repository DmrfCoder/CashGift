package cn.xiaojii.cashgift.interactor;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public interface IFriendsAndRelativesItemInteractor {
    interface UpdateFariViewListener {
        /**
         * 监听更新数据失败
         */
        void onUpdateViewError();

        /**
         * 监听更新数据成功
         *
         * @param dataList
         */
        void onUpdateViewSuccess(List dataList, int totalMoney, int inCount, int outCount);

    }

    /**
     * 刷新视图
     * @param updateViewListener
     */
    void updateView(UpdateFariViewListener updateViewListener);

    /**
     * 初始化数据并刷新视图
     * @param dataList
     */
    void initData(List dataList);


    public interface ClickListviewItemListener {
        /**
         * 点击item发生错误
         */
        void onClickItemError();

        /**
         * 逻辑处理成功，返回对应信息
         *
         * @param dataList
         */
        void onClickItemSuccess(List dataList);
    }

    /**
     * @param name
     * @param updateFariViewListener
     */
    void clickListViewItem(String name, UpdateFariViewListener updateFariViewListener);
}
