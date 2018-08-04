package cn.xiaojii.cashgift.interactor.impl;

import java.util.List;

import cn.xiaojii.cashgift.bean.ProjectBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class RunningAccountInteractor {

    private List<ProjectBean> projectBeanList;

    public RunningAccountInteractor(List<ProjectBean> projectBeanList) {
        this.projectBeanList = projectBeanList;
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
