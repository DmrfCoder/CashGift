package cn.xiaojii.cashgift.presenter.impl.fragment;

import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.xiaojii.cashgift.bean.global.ConstantsBean;
import cn.xiaojii.cashgift.bean.fragment.ProjectBean;
import cn.xiaojii.cashgift.bean.message.ProjectListMessageBean;
import cn.xiaojii.cashgift.interactor.inter.base.IBaseFragmentInteractor;
import cn.xiaojii.cashgift.interactor.inter.fragment.IProjectTableInteractor;
import cn.xiaojii.cashgift.interactor.impl.ProjectTableInterator;
import cn.xiaojii.cashgift.presenter.inter.base.IBaseFragmentPresenter;
import cn.xiaojii.cashgift.presenter.inter.fragment.IProjectTablePresenter;
import cn.xiaojii.cashgift.view.inter.fragment.IProjectTableView;
import cn.xiaojii.cashgift.view.impl.activity.MainActivity;
import cn.xiaojii.cashgift.view.impl.fragment.ProjectTableItemFragment;

/**
 * @author dmrfcoder
 * @date 2018/8/7
 */

public class ProjectTableFragmentPresenter implements IProjectTablePresenter, IBaseFragmentPresenter,
        IBaseFragmentInteractor.AddProjectListener, IProjectTableInteractor.UpdatePtViewListener,
        IProjectTableInteractor.ClickProjectTableItemListener {
    private IProjectTableView iProjectTableView;
    private ProjectTableInterator projectTableInterator;

    public ProjectTableFragmentPresenter(IProjectTableView iProjectTableView, ProjectTableInterator projectTableInterator) {
        this.iProjectTableView = iProjectTableView;
        this.projectTableInterator = projectTableInterator;
        EventBus.getDefault().register(this);
    }


    @Override
    public void addProjectFromDialog(ProjectBean projectBean) {

    }


    @Override
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void initDataFromMainInteractor(ProjectListMessageBean projectListMessageBean) {
        if (projectListMessageBean.getTag().equals(ConstantsBean.TAG_MAINPRESENTER)) {
            List dataList = projectListMessageBean.getProjectBeans();
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
        ProjectListMessageBean projectListMessageBean = new ProjectListMessageBean(list, ConstantsBean.TAG_PROJECTTABLE);
        EventBus.getDefault().postSticky(projectListMessageBean);
    }
}
