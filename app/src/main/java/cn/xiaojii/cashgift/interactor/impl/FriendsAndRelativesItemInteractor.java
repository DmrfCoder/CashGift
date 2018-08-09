package cn.xiaojii.cashgift.interactor.impl;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;
import cn.xiaojii.cashgift.interactor.IFriendsAndRelativesItemInteractor;

/**
 * @author dmrfcoder
 * @date 2018/8/8
 */

public class FriendsAndRelativesItemInteractor implements IBaseInteractor,
        IFriendsAndRelativesItemInteractor, MainInterator.OnGetDataListener {

    private List<ProjectBean> projectBeanList;
    private int totalSumMoney, inCount, outCount;
    private String name;


    public FriendsAndRelativesItemInteractor() {
        projectBeanList = new ArrayList<>();
        MainInterator.GetData(this);
        totalSumMoney = 0;
        inCount = 0;
        outCount = 0;
    }

    @Override
    public void addProject(ProjectBean projectBean, AddProjectListener addProjectListener) {
        if (projectBean == null) {
            addProjectListener.onAddProjectError();
        } else {
            if (projectBean.getName().equals(name)) {
                projectBeanList.add(projectBean);
                if (projectBean.getIntMoney() > 0) {
                    inCount++;
                } else {
                    outCount++;
                }
                totalSumMoney += projectBean.getIntMoney();
            }

            addProjectListener.onAddProjectSuccess(projectBeanList);
        }

    }

    @Override
    public void initData(List dataList) {
        if (dataList == null) {
            return;
        } else {
            projectBeanList = dataList;
        }
    }

    @Override
    public void clickListViewItem(String name, UpdateFariViewListener updateFariViewListener) {
        if (name == null) {
            updateFariViewListener.onUpdateViewError();
            return;
        }
        if (projectBeanList == null) {
            updateFariViewListener.onUpdateViewError();
            return;
        }
        this.name = name;
        List<ProjectBean> projectBeans = new ArrayList<>();

        for (ProjectBean projectBean : projectBeanList) {
            if (projectBean.getName().equals(name)) {
                projectBeans.add(projectBean);
                if (projectBean.getIntMoney() > 0) {
                    inCount++;
                } else {
                    outCount++;
                }
                totalSumMoney += projectBean.getIntMoney();
            }
        }

        projectBeanList=projectBeans;
        updateFariViewListener.onUpdateViewSuccess(projectBeanList, totalSumMoney, inCount, outCount);
    }


    @Override
    public void OnGetDataError() {

    }

    @Override
    public void OnGetDataSuccess(List<ProjectBean> projectBeanList) {
        this.projectBeanList = projectBeanList;
    }

    @Override
    public void updateView(UpdateFariViewListener updateViewListener) {
        if (projectBeanList == null) {
            updateViewListener.onUpdateViewError();
        } else {
            updateViewListener.onUpdateViewSuccess(projectBeanList, totalSumMoney, inCount, outCount);
        }
    }
}