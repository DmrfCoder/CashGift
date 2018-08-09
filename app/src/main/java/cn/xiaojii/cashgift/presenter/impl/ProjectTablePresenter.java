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
import cn.xiaojii.cashgift.interactor.IProjectTableInteractor;
import cn.xiaojii.cashgift.interactor.impl.ProjectTableInterator;
import cn.xiaojii.cashgift.presenter.IBasePresenter;
import cn.xiaojii.cashgift.view.impl.ProjectTableFragment;

/**
 * @author dmrfcoder
 * @date 2018/8/7
 */

public class ProjectTablePresenter implements IBasePresenter,
        IBaseInteractor.AddProjectListener, IProjectTableInteractor.UpdatePtViewListener {
    private ProjectTableFragment projectTableFragment;
    private ProjectTableInterator projectTableInterator;


    public ProjectTablePresenter(ProjectTableFragment projectTableFragment, ProjectTableInterator projectTableInterator) {
        this.projectTableFragment = projectTableFragment;
        this.projectTableInterator = projectTableInterator;

        EventBus.getDefault().register(this);

    }

    @Override
    public void addProjectFromDialog(ProjectBean projectBean) {

    }


    @Override
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void initDataFromMainInteractor(ProjectListMessageEvent projectListMessageEvent) {
        if (projectListMessageEvent.getTag().equals(GlobalBean.TAG_MAINPRESENTER)) {
            List dataList = projectListMessageEvent.getProjectBeans();
            projectTableInterator.initData(dataList, this);
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
    public void onAddProjectError() {

    }

    @Override
    public void onAddProjectSuccess(List beanList) {

    }


    @Override
    public void onUpdateViewError() {

    }

    @Override
    public void onUpdateViewSuccess(List dataList, int a, int b, int c, int d, int e, int totalMoney) {
        projectTableFragment.updateListView(dataList);
        projectTableFragment.updateTopBarData(a, b, c, d, e, totalMoney);
    }
}
