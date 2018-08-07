package cn.xiaojii.cashgift.interactor.impl;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectTableBean;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;

/**
 * @author dmrfcoder
 * @date 2018/8/7
 */

public class ProjectTableInterator implements IBaseInteractor {
    private List<ProjectTableBean> projectTableBeanList;
    private String TAG = "ProjectTableInterator";


    @Override
    public void addProject(ProjectBean projectBean, AddProjectListener addProjectListener) {
        if (addSingleProjectBean(projectBean)) {
            addProjectListener.onAddProjectSuccess(projectTableBeanList, null, null);
        } else {
            addProjectListener.onAddProjectError();
        }
    }


    private boolean addSingleProjectBean(ProjectBean projectBean) {

        Log.i(TAG, "addSingleProjectBean");
        if (projectBean == null) {
            return false;
        }
        boolean findFlag = false;
        if (projectTableBeanList == null) {
            projectTableBeanList = new ArrayList<>();
        } else {
            for (ProjectTableBean projectTableBean : projectTableBeanList) {
                if (projectTableBean.getName().equals(projectBean.getProject())) {
                    if (projectTableBean.hasTargetBean(projectBean) ){
                        return false;
                    }
                    findFlag = true;

                    projectTableBean.addProjectBean(projectBean);

                    if (projectBean.getIntMoney() < 0) {
                        projectTableBean.updateMoneyOut(projectBean.getIntMoney());
                    } else {
                        projectTableBean.updateMoneyIn(projectBean.getIntMoney());
                    }
                }
            }
        }

        if (!findFlag) {
            ProjectTableBean projectTableBean = new ProjectTableBean();
            projectTableBean.setName(projectBean.getProject());
            if (projectBean.getIntMoney() < 0) {
                projectTableBean.setSumMoneyOut(projectBean.getIntMoney());
            } else {
                projectTableBean.setSumMoneyIn(projectBean.getIntMoney());
            }

            projectTableBean.addProjectBean(projectBean);
            projectTableBeanList.add(projectTableBean);
        }

        return true;

    }



    @Override
    public void initData(List dataList, InitDataListener initDataListener) {

        //这里的datalist应该是projectbean类型的
        Log.i(TAG,"initData");
        for (Object projectBean : dataList) {
            if (!addSingleProjectBean((ProjectBean) projectBean)) {
                initDataListener.onInitDataError();

            }
        }

        initDataListener.onInitDataSuccess(projectTableBeanList);
    }

    @Override
    public void updateView(UpdateViewListener updateViewListener) {
        if (projectTableBeanList == null) {
            updateViewListener.onUpdateViewError();
            return;
        }

        updateViewListener.onUpdateViewSuccess(projectTableBeanList);


    }
}
