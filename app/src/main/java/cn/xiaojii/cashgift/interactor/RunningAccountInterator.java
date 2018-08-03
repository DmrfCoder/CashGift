package cn.xiaojii.cashgift.interactor;

import java.util.List;

import cn.xiaojii.cashgift.bean.RunningAccountBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class RunningAccountInterator {
    public interface OnAddRunningAcountListener {
        /**
         * 增加流水账失败时调用
         */
        void onAddError();

        /**
         * 增加流水账成功时调用，传入Bean对象（因为一次增加一个，所以传入单个Bean）
         * @param runningAccountBean
         */
        void onAddSuccess(RunningAccountBean runningAccountBean);
    }

    private void AddRunningAcount(RunningAccountBean runningAccountBean, OnAddRunningAcountListener onAddRunningAcountListener) {

    }
}
