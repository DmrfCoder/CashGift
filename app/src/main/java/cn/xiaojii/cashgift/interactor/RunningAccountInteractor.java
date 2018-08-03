package cn.xiaojii.cashgift.interactor;

import java.util.List;

import cn.xiaojii.cashgift.bean.RunningAccountBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class RunningAccountInteractor {

    private List<RunningAccountBean> runningAccountBeanList;

    public RunningAccountInteractor(List<RunningAccountBean> runningAccountBeanList) {
        this.runningAccountBeanList = runningAccountBeanList;
    }

    public interface OnAddProjrctListener {
        /**
         * 增加流水账失败时调用
         */
        void onAddError();

        /**
         * 增加流水账成功时调用，传入Bean对象（因为一次增加一个，所以传入单个Bean）
         *
         * @param runningAccountBeanList
         */
        void onAddSuccess(List<RunningAccountBean> runningAccountBeanList);
    }

    private void AddRunningAcount(RunningAccountBean runningAccountBean, OnAddProjrctListener onAddProjrctListener) {
        if (runningAccountBeanList == null) {
            onAddProjrctListener.onAddError();
        } else {
            runningAccountBeanList.add(runningAccountBean);
            onAddProjrctListener.onAddSuccess(runningAccountBeanList);
        }

    }
}
