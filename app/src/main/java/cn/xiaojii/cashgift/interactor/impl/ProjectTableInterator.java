package cn.xiaojii.cashgift.interactor.impl;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.bean.fragment.ProjectBean;
import cn.xiaojii.cashgift.bean.fragment.ProjectTableBean;
import cn.xiaojii.cashgift.interactor.inter.base.IBaseFragmentInteractor;
import cn.xiaojii.cashgift.interactor.inter.fragment.IProjectTableInteractor;

/**
 * @author dmrfcoder
 * @date 2018/8/7
 */

public class ProjectTableInterator implements IBaseFragmentInteractor, IProjectTableInteractor {
    private List<ProjectTableBean> projectTableBeanList;
    private String TAG = "ProjectTableInterator";
    private List<String> nameList;

    public ProjectTableInterator() {
        nameList = new ArrayList<>();
    }

    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private int e = 0;

    private int totalMoney = 0;


    @Override
    public void addProject(ProjectBean projectBean, AddProjectListener addProjectListener) {
        if (addSingleProjectBean(projectBean)) {
            addProjectListener.onAddProjectSuccess(projectTableBeanList);
        } else {
            addProjectListener.onAddProjectError();
        }
    }


    @Override
    public boolean addSingleProjectBean(ProjectBean projectBean) {

        Log.i(TAG, "addSingleProjectBean");
        if (projectBean == null) {
            return false;
        }

        if (!nameList.contains(projectBean.getName())) {
            nameList.add(projectBean.getName());
            a++;
        }

        boolean findFlag = false;
        if (projectTableBeanList == null) {
            projectTableBeanList = new ArrayList<>();
        } else {
            for (ProjectTableBean projectTableBean : projectTableBeanList) {
                if (projectTableBean.getProjectName().equals(projectBean.getProject())) {
                    if (projectTableBean.hasTargetBean(projectBean)) {
                        return false;
                    }
                    findFlag = true;


                    projectTableBean.addProjectBean(projectBean);

                    totalMoney += projectBean.getIntMoney();
                    if (projectBean.getIntMoney() < 0) {
                        projectTableBean.updateMoneyOut(projectBean.getIntMoney());
                        d += projectBean.getIntMoney();
                        e++;
                    } else {
                        projectTableBean.updateMoneyIn(projectBean.getIntMoney());
                        b += projectBean.getIntMoney();
                        c++;
                    }
                }
            }
        }

        if (!findFlag) {
            ProjectTableBean projectTableBean = new ProjectTableBean();
            projectTableBean.setProjectName(projectBean.getProject());
            totalMoney += projectBean.getIntMoney();
            if (projectBean.getIntMoney() < 0) {
                projectTableBean.setSumMoneyOut(projectBean.getIntMoney());
                d += projectBean.getIntMoney();
                e++;
            } else {
                projectTableBean.setSumMoneyIn(projectBean.getIntMoney());
                b += projectBean.getIntMoney();
                c++;
            }

            projectTableBean.addProjectBean(projectBean);


            projectTableBeanList.add(projectTableBean);
        }

        return true;

    }


    @Override
    public void updateView(UpdatePtViewListener updateViewListener) {
        if (projectTableBeanList == null) {
            updateViewListener.onUpdateViewError();
            return;
        }

        updateViewListener.onUpdateViewSuccess(projectTableBeanList, a, b, c, d, e, totalMoney);


    }

    @Override
    public void initData(List dataList, UpdatePtViewListener updatePtViewListener) {
        //这里的datalist应该是projectbean类型的
        Log.i(TAG, "initData");
        for (Object projectBean : dataList) {
            if (!addSingleProjectBean((ProjectBean) projectBean)) {
                updatePtViewListener.onUpdateViewError();

            }
        }

        updatePtViewListener.onUpdateViewSuccess(projectTableBeanList, a, b, c, d, e, totalMoney);

    }

    @Override
    public void clickListViewItem(int position, ClickProjectTableItemListener clickProjectTableItemListener) {
        if (position >= projectTableBeanList.size()) {
            clickProjectTableItemListener.onClickProjectTableItemError();
        } else {
            clickProjectTableItemListener.onClickProjectTableItemSuccess(projectTableBeanList.get(position).getProjectBeanList());
        }
    }


}
