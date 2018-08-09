package cn.xiaojii.cashgift.presenter.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectListMessageEvent;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;
import cn.xiaojii.cashgift.interactor.impl.RunningAccountInteractor;
import cn.xiaojii.cashgift.presenter.IBasePresenter;
import cn.xiaojii.cashgift.view.IRunningAccountView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class RunningAccountPresenter implements RunningAccountInteractor.OnAddProjrctListener,
        IBasePresenter, IBaseInteractor.InitDataListener, IBaseInteractor.AddProjectListener {

    private IRunningAccountView runningAccountView;
    private BroadcastReceiver dataReceive;


    public RunningAccountPresenter(IRunningAccountView runningAccountView, RunningAccountInteractor runningAccountInteractor) {
        this.runningAccountView = runningAccountView;
        this.runningAccountInteractor = runningAccountInteractor;
        EventBus.getDefault().register(this);
    }


    private RunningAccountInteractor runningAccountInteractor;


    @Override
    public void addProjectFromDialog(ProjectBean projectBean) {

        runningAccountInteractor.addProject(projectBean, this);
    }



    @Override
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void initDataFromMainInteractor(ProjectListMessageEvent projectListMessageEvent) {
        if (projectListMessageEvent.getTag().equals(GlobalBean.TAG_MAINPRESENTER)) {
            List dataList = projectListMessageEvent.getProjectBeans();
            runningAccountInteractor.initData(dataList, this);
        }
    }

    @Override
    public void updateView() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onAddError() {

    }

    @Override
    public void onAddSuccess(List<ProjectBean> projectBeanList) {

    }

    @Override
    public void onInitDataError() {

    }

    @Override
    public void onInitDataSuccess(List dataList) {
        runningAccountView.updateListView(dataList);
    }

    @Override
    public void onAddProjectError() {

    }

    @Override
    public void onAddProjectSuccess(List beanList, String BroadCastTag, ProjectBean projectBean) {
        runningAccountView.updateListView(beanList);
    }


}
