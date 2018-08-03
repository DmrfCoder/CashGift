package cn.xiaojii.cashgift.presenter.impl;

import java.util.List;

import cn.xiaojii.cashgift.bean.RunningAccountBean;
import cn.xiaojii.cashgift.interactor.RunningAccountInteractor;
import cn.xiaojii.cashgift.presenter.IMainPresenter;
import cn.xiaojii.cashgift.presenter.IRunningAccountPresenter;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class RunningAccountPresenter implements IRunningAccountPresenter,RunningAccountInteractor.OnAddProjrctListener {
    @Override
    public void addProject(RunningAccountBean runningAccountBean) {

    }

    @Override
    public void onAddError() {

    }

    @Override
    public void onAddSuccess(List<RunningAccountBean> runningAccountBeanList) {

    }
}
