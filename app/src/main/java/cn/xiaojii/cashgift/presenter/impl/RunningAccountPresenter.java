package cn.xiaojii.cashgift.presenter.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import java.util.List;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;
import cn.xiaojii.cashgift.interactor.impl.RunningAccountInteractor;
import cn.xiaojii.cashgift.presenter.IBasePresenter;
import cn.xiaojii.cashgift.util.SendBroadCastUtil;
import cn.xiaojii.cashgift.view.IRunningAccountView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class RunningAccountPresenter implements  RunningAccountInteractor.OnAddProjrctListener,
        IBasePresenter, IBaseInteractor.InitDataListener, IBaseInteractor.AddProjectListener {

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


    private BroadcastReceiver addDataProjectReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String fragmentName = bundle.getString(GlobalBean.BROADCAST_ADD_PROJECT_FRAGMENT_NAME_KEY);
            if (!"RunningAccountPresenter".equals(fragmentName)) {
                ProjectBean projectBean = bundle.getParcelable(GlobalBean.BROADCAST_ADD_PROJECT_BEAN_KEY);
                if (projectBean != null) {
                    addProjectFromBC(projectBean);
                }
            }

        }
    };


    public RunningAccountPresenter(IRunningAccountView runningAccountView, RunningAccountInteractor runningAccountInteractor) {
        this.runningAccountView = runningAccountView;
        this.runningAccountInteractor = runningAccountInteractor;


        SendBroadCastUtil.sendNeedDataBC((Fragment) runningAccountView);

        IntentFilter getListBeanfilter = new IntentFilter();
        getListBeanfilter.addAction(GlobalBean.NORMAR_ACTION2);
        getListBeanfilter.setPriority(Integer.MAX_VALUE);
        ((Fragment) runningAccountView).getActivity().registerReceiver(getListBeanBroadcastReceiver, getListBeanfilter);


        IntentFilter addDataProjectReceiverFilter = new IntentFilter();
        addDataProjectReceiverFilter.addAction(GlobalBean.NORMAR_ACTION3);
        addDataProjectReceiverFilter.setPriority(Integer.MAX_VALUE);
        ((Fragment) runningAccountView).getActivity().registerReceiver(addDataProjectReceiver, addDataProjectReceiverFilter);


    }


    private RunningAccountInteractor runningAccountInteractor;


    @Override
    public void addProjectFromDG(ProjectBean projectBean) {

        runningAccountInteractor.addProject(projectBean,this);
        SendBroadCastUtil.sendAddProjectBC((Fragment)runningAccountView,projectBean,"RunningAccountPresenter");
    }

    @Override
    public void addProjectFromBC(ProjectBean projectBean) {
        runningAccountInteractor.addProject(projectBean,this);
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

    @Override
    public void onAddProjectError() {

    }

    @Override
    public void onAddProjectSuccess(List beanList, String BroadCastTag, ProjectBean projectBean) {
        runningAccountView.updateListView(beanList);
    }


}
