package cn.xiaojii.cashgift.interactor.inter.fragment;

import java.util.List;

import cn.xiaojii.cashgift.bean.fragment.ProjectBean;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public interface IProjectTableItemInteractor {

    interface UpdatePtiViewListener {
        /**
         * 监听更新数据失败
         */
        void onUpdateViewError();


        /**
         * 监听更新数据成功
         *
         * @param dataList
         * @param inCount
         * @param inMoney
         * @param outCount
         * @param outMoney
         * @param sumMoney
         * @param personName
         */
        void onUpdateViewSuccess(List dataList, int inCount,int inMoney,int outCount,int outMoney,int sumMoney, String personName);

    }

    /**
     * 刷新视图
     * @param updatePtiViewListener
     */
    void updateView(UpdatePtiViewListener updatePtiViewListener);

    /**
     * 初始化数据并刷新视图
     * @param dataList
     * @param updatePtiViewListener
     */
    void initData(List dataList,UpdatePtiViewListener updatePtiViewListener);

    /**
     * 处理增加单个ProjectBean的逻辑，供Interactor调用
     * @param projectBean
     */
     void addSignalProjectBean(ProjectBean projectBean);

    /**
     * 处理增加单个ProjectBean的逻辑,供Presenter调用
     * @param projectBean
     * @param updatePtiViewListener
     */
    void addSignalProjectBean(ProjectBean projectBean,UpdatePtiViewListener updatePtiViewListener);
}
