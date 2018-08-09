package cn.xiaojii.cashgift.presenter.impl;

import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectListMessageEvent;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;
import cn.xiaojii.cashgift.interactor.IProjectTableInteractor;
import cn.xiaojii.cashgift.interactor.impl.ProjectTableInterator;
import cn.xiaojii.cashgift.presenter.IBasePresenter;
import cn.xiaojii.cashgift.presenter.IProjectTablePresenter;
import cn.xiaojii.cashgift.view.IProjectTableView;
import cn.xiaojii.cashgift.view.impl.MainActivity;
import cn.xiaojii.cashgift.view.impl.ProjectTableFragment;
import cn.xiaojii.cashgift.view.impl.ProjectTableItemFragment;

/**
 * @author dmrfcoder
 * @date 2018/8/7
 */

public class ProjectTablePresenter implements IProjectTablePresenter, IBasePresenter,
        IBaseInteractor.AddProjectListener, IProjectTableInteractor.UpdatePtViewListener,
        IProjectTableInteractor.ClickProjectTableItemListener {
    private IProjectTableView iProjectTableView;
    private ProjectTableInterator projectTableInterator;

    public ProjectTablePresenter(IProjectTableView iProjectTableView, ProjectTableInterator projectTableInterator) {
        this.iProjectTableView = iProjectTableView;
        this.projectTableInterator = projectTableInterator;
        EventBus.getDefault().register(this);
    }


    @Override
    public void addProjectFromDialog(ProjectBean projectBean) {

    }


    @Override
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void initDataFromMainInteractor(ProjectListMessageEvent projectListMessageEvent) {
        if (projectListMessageEvent.getTag().equals(GlobalBean.TAG_MAINPRESENTER)) {
            List dataList = projectListMessageEvent.getProjectBeans();
            projectTableInterator.initData(dataList, this);
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
    public void onAddProjectError() {

    }

    @Override
    public void onAddProjectSuccess(List beanList) {

    }


    @Override
    public void onUpdateViewError() {

    }

    @Override
    public void onUpdateViewSuccess(List dataList, int a, int b, int c, int d, int e, int totalMoney) {
        iProjectTableView.updateListView(dataList);
        iProjectTableView.updateTopBarData(a, b, c, d, e, totalMoney);
    }

    @Override
    public void clickListViewItem(int position) {
        projectTableInterator.clickListViewItem(position, this);
    }

    @Override
    public void onClickProjectTableItemError() {

    }

    @Override
    public void onClickProjectTableItemSuccess(List list) {
        ProjectTableItemFragment projectTableItemFragment=new ProjectTableItemFragment();
        ((MainActivity) ((Fragment) iProjectTableView).getActivity()).startfragment(projectTableItemFragment, true);
        ProjectListMessageEvent projectListMessageEvent = new ProjectListMessageEvent(list, GlobalBean.TAG_PROJECTTABLE);
        EventBus.getDefault().postSticky(projectListMessageEvent);
    }
}
