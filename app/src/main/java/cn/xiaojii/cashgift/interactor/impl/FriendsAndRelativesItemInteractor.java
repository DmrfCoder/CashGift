package cn.xiaojii.cashgift.interactor.impl;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.bean.fragment.ProjectBean;
import cn.xiaojii.cashgift.interactor.inter.fragment.IFriendsAndRelativesItemInteractor;

/**
 * @author dmrfcoder
 * @date 2018/8/8
 */

public class FriendsAndRelativesItemInteractor implements
        IFriendsAndRelativesItemInteractor, MainInterator.OnGetDataListener {

    private List<ProjectBean> projectBeanList;
    private int totalSumMoney, inCount, outCount;
    private String name = "";


    public FriendsAndRelativesItemInteractor() {
        projectBeanList = new ArrayList<>();
        MainInterator.GetData(this);
        totalSumMoney = 0;
        inCount = 0;
        outCount = 0;
    }



    @Override
    public void initData(List dataList, UpdateFariViewListener updateFariViewListener) {
        if ("".equals(name)) {
            projectBeanList = dataList;
            updateFariViewListener.onUpdateViewError();
        } else {
            ProjectBean projectBean = (ProjectBean) dataList.get(dataList.size() - 1);
            if (name.equals(projectBean.getName())) {

                projectBeanList.add(projectBean);

                if (projectBean.getIntMoney() > 0) {
                    inCount++;
                } else {
                    outCount++;
                }
                totalSumMoney += projectBean.getIntMoney();
                updateFariViewListener.onUpdateViewSuccess(projectBeanList, totalSumMoney, inCount, outCount,name);
            }else {
                updateFariViewListener.onUpdateViewError();
            }


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

        projectBeanList = projectBeans;
        updateFariViewListener.onUpdateViewSuccess(projectBeanList, totalSumMoney, inCount, outCount,name);
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
            updateViewListener.onUpdateViewSuccess(projectBeanList, totalSumMoney, inCount, outCount,name);
        }
    }
}
