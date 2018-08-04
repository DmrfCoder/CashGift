package cn.xiaojii.cashgift.presenter.impl;

import java.util.List;

import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.impl.RunningAccountInteractor;
import cn.xiaojii.cashgift.presenter.IBasePresenter;
import cn.xiaojii.cashgift.presenter.IRunningAccountPresenter;
import cn.xiaojii.cashgift.view.IRunningAccountView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class RunningAccountPresenter implements IRunningAccountPresenter, RunningAccountInteractor.OnAddProjrctListener,
        IBasePresenter {

    private IRunningAccountView runningAccountView;

    public RunningAccountPresenter(IRunningAccountView runningAccountView, RunningAccountInteractor runningAccountInteractor) {
        this.runningAccountView = runningAccountView;
        this.runningAccountInteractor = runningAccountInteractor;
    }

    private RunningAccountInteractor runningAccountInteractor;


    @Override
    public void addProject(ProjectBean projectBean) {

    }

    @Override
    public void initFragmentData(List dataList) {

    }

    @Override
    public void updateView() {

    }

    @Override
    public void onAddError() {

    }

    @Override
    public void onAddSuccess(List<ProjectBean> projectBeanList) {

    }
}
