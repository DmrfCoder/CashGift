package cn.xiaojii.cashgift.presenter.impl;

import cn.xiaojii.cashgift.bean.RunningAccountBean;
import cn.xiaojii.cashgift.interactor.RunningAccountInterator;
import cn.xiaojii.cashgift.presenter.IRunningAcountPresenter;
import cn.xiaojii.cashgift.view.IRunningAccountView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class RunningAcountPresenter implements IRunningAcountPresenter ,RunningAccountInterator.OnAddRunningAcountListener{
    private IRunningAccountView runningAccountView;
    private RunningAccountInterator runningAccountInterator;

    public RunningAcountPresenter(IRunningAccountView runningAccountView, RunningAccountInterator runningAccountInterator) {
        this.runningAccountView = runningAccountView;
        this.runningAccountInterator = runningAccountInterator;
    }

    @Override
    public void addRunningAcount(RunningAccountBean runningAccountBean) {

    }

    @Override
    public void onAddError() {

    }

    @Override
    public void onAddSuccess(RunningAccountBean runningAccountBean) {

    }
}
