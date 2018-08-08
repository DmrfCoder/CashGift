package cn.xiaojii.cashgift.interactor.impl;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;

/**
 * @author dmrfcoder
 * @date 2018/8/8
 */

public class FriendsAndRelativesItemInteractor implements IBaseInteractor, MainInterator.OnGetDataListener {

    private List<ProjectBean> projectBeanList;
    private int totalSumMoney, inCount, outCount;
    private String name;


    public FriendsAndRelativesItemInteractor() {
        projectBeanList = new ArrayList<>();
        MainInterator.GetData(this);
    }

    @Override
    public void addProject(ProjectBean projectBean, AddProjectListener addProjectListener) {

    }

    @Override
    public void initData(List dataList, InitDataListener initDataListener) {
        if (dataList == null) {
            initDataListener.onInitDataError();
        } else {
            projectBeanList = dataList;
            initDataListener.onInitDataSuccess(dataList);
        }
    }

    @Override
    public void updateView(UpdateViewListener updateViewListener) {
        if (projectBeanList == null) {
            updateViewListener.onUpdateViewError();
        } else {
            updateViewListener.onUpdateViewSuccess(projectBeanList);
        }
    }

    @Override
    public void clickListViewItem(String name, ClickListviewItemListener clickListviewItemListener) {

        if (name == null) {
            clickListviewItemListener.onClickItemError();
            return;
        }
        if (projectBeanList == null) {
            clickListviewItemListener.onClickItemError();
            return;
        }
        this.name = name;


        List<ProjectBean> projectBeans = new ArrayList<>();

        for (ProjectBean projectBean : projectBeanList) {
            if (projectBean.getName().equals(name)) {
                projectBeans.add(projectBean);
            }
        }
        clickListviewItemListener.onCliskItemSuccess(projectBeans);


    }


    @Override
    public void OnGetDataError() {

    }

    @Override
    public void OnGetDataSuccess(List<ProjectBean> projectBeanList) {
        this.projectBeanList = projectBeanList;
    }
}
