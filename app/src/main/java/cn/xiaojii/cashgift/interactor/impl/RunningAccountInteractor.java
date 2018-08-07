package cn.xiaojii.cashgift.interactor.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class RunningAccountInteractor implements IBaseInteractor {

    private List<ProjectBean> projectBeanList;
    private BroadcastReceiver broadcastReceiver;



    private void addProject(ProjectBean projectBean) {
        if (projectBeanList == null) {
            projectBeanList = new ArrayList<>();
        }
        projectBeanList.add(projectBean);
    }

    @Override
    public void addProject(ProjectBean projectBean, AddProjectListener addProjectListener) {
        if (projectBean == null) {
            addProjectListener.onAddProjectError();
        }
        if (projectBeanList == null) {
            projectBeanList = new ArrayList<>();

        }
        projectBeanList.add(projectBean);
        addProjectListener.onAddProjectSuccess(projectBeanList, "RunningAccount",projectBean);
    }

    @Override
    public void initData(List dataList, InitDataListener initDataListener) {
        if (dataList != null) {
            projectBeanList = dataList;
            initDataListener.onInitDataSuccess(dataList);
        } else {
            initDataListener.onInitDataError();
        }
    }

    @Override
    public void updateView(UpdateViewListener updateViewListener) {
        if (projectBeanList != null) {
            updateViewListener.onUpdateViewSuccess(projectBeanList);
        } else {
            updateViewListener.onUpdateViewError();
        }
    }

    public interface OnAddProjrctListener {
        /**
         * 增加流水账失败时调用
         */
        void onAddError();

        /**
         * 增加流水账成功时调用，传入Bean对象（因为一次增加一个，所以传入单个Bean）
         *
         * @param projectBeanList
         */
        void onAddSuccess(List<ProjectBean> projectBeanList);
    }

    private void AddRunningAcount(ProjectBean projectBean, OnAddProjrctListener onAddProjrctListener) {
        if (projectBeanList == null) {
            onAddProjrctListener.onAddError();
        } else {
            projectBeanList.add(projectBean);
            onAddProjrctListener.onAddSuccess(projectBeanList);
        }

    }
}
