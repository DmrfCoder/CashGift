package cn.xiaojii.cashgift.interactor.impl;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.bean.fragment.ProjectBean;
import cn.xiaojii.cashgift.interactor.inter.base.IBaseFragmentInteractor;
import cn.xiaojii.cashgift.interactor.inter.fragment.IRunningAccountInteractor;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class RunningAccountFragmentInteractor implements IBaseFragmentInteractor,IRunningAccountInteractor {

    private List<ProjectBean> projectBeanList;






    @Override
    public void addProject(ProjectBean projectBean, AddProjectListener addProjectListener) {
        if (projectBean == null) {
            addProjectListener.onAddProjectError();
        }
        if (projectBeanList == null) {
            projectBeanList = new ArrayList<>();
        }
        projectBeanList.add(projectBean);
        addProjectListener.onAddProjectSuccess(projectBeanList);
    }






    @Override
    public void updateView(UpdateRAViewListener updateViewListener) {
        if (projectBeanList != null) {
            updateViewListener.onUpdateViewSuccess(projectBeanList);
        } else {
            updateViewListener.onUpdateViewError();
        }
    }

    @Override
    public void initData(List dataList, UpdateRAViewListener updateRAViewListener) {
        if (dataList != null) {
            projectBeanList = dataList;
            updateRAViewListener.onUpdateViewSuccess(dataList);
        } else {
            updateRAViewListener.onUpdateViewError();
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
