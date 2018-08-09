package cn.xiaojii.cashgift.presenter.impl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectListMessageEvent;
import cn.xiaojii.cashgift.interactor.IProjectTableItemInteractor;
import cn.xiaojii.cashgift.interactor.impl.ProjectTableItemInteractor;
import cn.xiaojii.cashgift.presenter.IBasePresenter;
import cn.xiaojii.cashgift.presenter.IProjectTableItemPresenter;
import cn.xiaojii.cashgift.view.IProjectTableItemView;

/**
 * @author dmrfcoder
 * @date 2018/8/9
 */

public class ProjectTableItemPresenter implements IProjectTableItemPresenter, IBasePresenter, IProjectTableItemInteractor.UpdatePtiViewListener {
    private IProjectTableItemView iProjectTableItemView;
    private ProjectTableItemInteractor projectTableItemInteractor;

    public ProjectTableItemPresenter(IProjectTableItemView iProjectTableItemView, ProjectTableItemInteractor projectTableItemInteractor) {
        this.iProjectTableItemView = iProjectTableItemView;
        this.projectTableItemInteractor = projectTableItemInteractor;
        EventBus.getDefault().register(this);
    }

    @Override
    public void addProjectFromDialog(ProjectBean projectBean) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void initDataFromMainInteractor(ProjectListMessageEvent projectListMessageEvent) {
        if (projectListMessageEvent.getTag().equals(GlobalBean.TAG_PROJECTTABLE)) {
            projectTableItemInteractor.initData(projectListMessageEvent.getProjectBeans(), this);
        } else if (projectListMessageEvent.getTag().equals(GlobalBean.TAG_MAINPRESENTER)) {
            List<ProjectBean> projectBeans = projectListMessageEvent.getProjectBeans();
            ProjectBean projectBean = projectBeans.get(projectBeans.size() - 1);
            projectTableItemInteractor.addSignalProjectBean(projectBean,this);
        }

    }

    @Override
    public void updateView() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onUpdateViewError() {

    }

    @Override
    public void onUpdateViewSuccess(List dataList, int inCount, int inMoney, int outCount, int outMoney, int sumMoney, String personName) {
        iProjectTableItemView.updateListView(dataList);
        iProjectTableItemView.updateView(inCount, inMoney, outCount, outMoney, sumMoney, personName);

    }
}
