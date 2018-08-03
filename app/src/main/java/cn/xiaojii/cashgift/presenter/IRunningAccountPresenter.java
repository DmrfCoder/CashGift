package cn.xiaojii.cashgift.presenter;

import cn.xiaojii.cashgift.bean.RunningAccountBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public interface IRunningAccountPresenter {

    /**
     * 新增流水帐，新增完之后调用view的update listview
     *
     * @param runningAccountBean
     */
    void addProject(RunningAccountBean runningAccountBean);

}
