package cn.xiaojii.cashgift.interactor.impl;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectTableItemBean;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;
import cn.xiaojii.cashgift.interactor.IProjectTableItemInteractor;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public class ProjectTableItemInteractor implements IProjectTableItemInteractor, IBaseInteractor {


    private int inCount, inMoney, outCount, outMoney, sumMoney;
    private String projectName;
    private List<ProjectTableItemBean> projectTableItemBeanList;

    public ProjectTableItemInteractor() {
        projectTableItemBeanList = new ArrayList<>();
        inCount = 0;
        inMoney = 0;
        outCount = 0;
        outMoney = 0;
        sumMoney = 0;
        projectName = "";

    }

    @Override
    public void addProject(ProjectBean projectBean, AddProjectListener addProjectListener) {

    }

    @Override
    public void updateView(UpdatePtiViewListener updatePtiViewListener) {
        if (projectTableItemBeanList.size() == 0) {
            updatePtiViewListener.onUpdateViewError();
        } else {
            updatePtiViewListener.onUpdateViewSuccess(projectTableItemBeanList, inCount, inMoney, outCount, outCount, sumMoney, projectName);
        }
    }

    @Override
    public void initData(List dataList, UpdatePtiViewListener updatePtiViewListener) {

        if (dataList == null) {
            updatePtiViewListener.onUpdateViewError();
            return;
        } else {
            List<ProjectBean> projectBeans = dataList;
            for (ProjectBean projectBean : projectBeans) {
                projectName = projectBean.getProject();
                addSignalProjectBean(projectBean);
            }
        }

        updatePtiViewListener.onUpdateViewSuccess(projectTableItemBeanList, inCount, inMoney, outCount, outCount, sumMoney, projectName);
    }

    @Override
    public void addSignalProjectBean(ProjectBean projectBean) {
        int money = projectBean.getIntMoney();
        boolean findFlag = false;
        for (ProjectTableItemBean projectTableItemBean : projectTableItemBeanList) {
            if (projectTableItemBean.getPersonName().equals(projectBean.getName())) {

                findFlag = true;

                projectTableItemBean.updateTotalMoney(money);
                if (money > 0) {
                    inCount++;
                    inMoney += money;
                    projectTableItemBean.updateInMoney(money);
                } else {
                    outCount++;
                    outMoney += money;
                    projectTableItemBean.updateOutMoney(money);
                }

            }
        }

        if (!findFlag) {
            ProjectTableItemBean projectTableItemBean = new ProjectTableItemBean();
            if (money > 0) {
                projectTableItemBean.setInMoney(money);
                inCount++;
                inMoney += money;
            } else {
                projectTableItemBean.setOutMoney(money);
                outCount++;
                outMoney += money;
            }

            projectTableItemBean.setPersonName(projectBean.getName());
            projectTableItemBean.setTotalMoney(money);
            projectTableItemBeanList.add(projectTableItemBean);
        }

        sumMoney += money;
    }

    @Override
    public void addSignalProjectBean(ProjectBean projectBean, UpdatePtiViewListener updatePtiViewListener) {
        if (projectBean == null) {
            updatePtiViewListener.onUpdateViewError();
        } else if (projectBean.getProject().equals(projectName)) {
            addSignalProjectBean(projectBean);
            updatePtiViewListener.onUpdateViewSuccess(projectTableItemBeanList, inCount, inMoney, outCount, outCount, sumMoney, projectName);
        } else {
            updatePtiViewListener.onUpdateViewError();
        }
    }


}
