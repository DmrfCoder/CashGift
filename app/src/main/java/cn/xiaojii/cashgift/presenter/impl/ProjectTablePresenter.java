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
import cn.xiaojii.cashgift.interactor.impl.ProjectTableInterator;
import cn.xiaojii.cashgift.presenter.IBasePresenter;
import cn.xiaojii.cashgift.util.SendBroadCastUtil;
import cn.xiaojii.cashgift.view.impl.ProjectTableFragment;

/**
 * @author dmrfcoder
 * @date 2018/8/7
 */

public class ProjectTablePresenter implements IBasePresenter, IBaseInteractor.AddProjectListener, IBaseInteractor.InitDataListener, ProjectTableInterator.OnUpdateTopBarDataListener {
    private ProjectTableFragment projectTableFragment;
    private ProjectTableInterator projectTableInterator;

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
            if (!"ProjectTablePresenter".equals(fragmentName)) {
                ProjectBean projectBean = bundle.getParcelable(GlobalBean.BROADCAST_ADD_PROJECT_BEAN_KEY);
                if (projectBean != null) {
                    addProjectFromBC(projectBean);
                }
            }
        }
    };


    public ProjectTablePresenter(ProjectTableFragment projectTableFragment, ProjectTableInterator projectTableInterator) {
        this.projectTableFragment = projectTableFragment;
        this.projectTableInterator = projectTableInterator;

        SendBroadCastUtil.sendNeedDataBC((Fragment) projectTableFragment);

        IntentFilter getListBeanfilter = new IntentFilter();
        getListBeanfilter.addAction(GlobalBean.NORMAR_ACTION2);
        getListBeanfilter.setPriority(Integer.MAX_VALUE);
        ((Fragment) projectTableFragment).getActivity().registerReceiver(getListBeanBroadcastReceiver, getListBeanfilter);


        IntentFilter addDataProjectReceiverFilter = new IntentFilter();
        addDataProjectReceiverFilter.addAction(GlobalBean.NORMAR_ACTION3);
        addDataProjectReceiverFilter.setPriority(Integer.MAX_VALUE);
        ((Fragment) projectTableFragment).getActivity().registerReceiver(addDataProjectReceiver, addDataProjectReceiverFilter);


    }

    @Override
    public void addProjectFromDG(ProjectBean projectBean) {

    }

    @Override
    public void addProjectFromBC(ProjectBean projectBean) {
        projectTableInterator.addProject(projectBean, this);
    }

    @Override
    public void initFragmentData(List dataList) {
        projectTableInterator.initData(dataList, this);
    }

    @Override
    public void updateView() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onAddProjectError() {

    }

    @Override
    public void onAddProjectSuccess(List beanList, String BroadCastTag, ProjectBean projectBean) {
        projectTableInterator.updateTopBarData(this);
        projectTableFragment.updateListView(beanList);

    }

    @Override
    public void onInitDataError() {

    }

    @Override
    public void onInitDataSuccess(List dataList) {
        projectTableInterator.updateTopBarData(this);
        projectTableFragment.updateListView(dataList);

    }

    @Override
    public void onUpdateTopBarDataError() {

    }

    @Override
    public void onUpdateTopBarDataSuccess(int a, int b, int c, int d, int e,int totalMoney) {
        projectTableFragment.updateTopBarData(a, b, c, d, e,totalMoney);
    }
}
