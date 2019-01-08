package cn.xiaojii.cashgift.presenter.inter.fragment;

import cn.xiaojii.cashgift.bean.fragment.ProjectBean;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public interface IRunningAccountPresenter {

    /**
     * 新增流水帐，新增完之后调用view的update listview
     *
     * @param projectBean
     */
    void addProject(ProjectBean projectBean);

}
