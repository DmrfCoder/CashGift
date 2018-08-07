package cn.xiaojii.cashgift.presenter.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.List;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;
import cn.xiaojii.cashgift.interactor.impl.RunningAccountInteractor;
import cn.xiaojii.cashgift.presenter.IBasePresenter;
import cn.xiaojii.cashgift.presenter.IRunningAccountPresenter;
import cn.xiaojii.cashgift.view.IRunningAccountView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class RunningAccountPresenter implements IRunningAccountPresenter, RunningAccountInteractor.OnAddProjrctListener,
        IBasePresenter ,IBaseInteractor.InitDataListener{

    private IRunningAccountView runningAccountView;
    private BroadcastReceiver dataReceive;
    private BroadcastReceiver getListBeanBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            List<ProjectBean> projectBeans = bundle.getParcelableArrayList(GlobalBean.BROADCAST_BEAN_LIST_KEY);
            initFragmentData(projectBeans);
        }
    };


    public RunningAccountPresenter(IRunningAccountView runningAccountView, RunningAccountInteractor runningAccountInteractor) {
        this.runningAccountView = runningAccountView;
        this.runningAccountInteractor = runningAccountInteractor;
        IntentFilter getListBeanfilter = new IntentFilter();
        getListBeanfilter.addAction(GlobalBean.NORMAR_ACTION2);
        getListBeanfilter.setPriority(Integer.MAX_VALUE);
        ((Fragment) runningAccountView).getActivity().registerReceiver(getListBeanBroadcastReceiver, getListBeanfilter);
    }

    private RunningAccountInteractor runningAccountInteractor;


    @Override
    public void addProject(ProjectBean projectBean) {

    }

    @Override
    public void initFragmentData(List dataList) {
        runningAccountInteractor.initData(dataList, this);
    }

    @Override
    public void updateView() {

    }

    @Override
    public void onPause() {

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
}
