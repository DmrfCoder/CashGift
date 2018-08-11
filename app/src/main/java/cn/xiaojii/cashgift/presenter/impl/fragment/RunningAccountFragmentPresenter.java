package cn.xiaojii.cashgift.presenter.impl.fragment;

import android.content.BroadcastReceiver;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.xiaojii.cashgift.bean.global.ConstantsBean;
import cn.xiaojii.cashgift.bean.fragment.ProjectBean;
import cn.xiaojii.cashgift.bean.message.ProjectListMessageBean;
import cn.xiaojii.cashgift.interactor.inter.base.IBaseFragmentInteractor;
import cn.xiaojii.cashgift.interactor.inter.fragment.IRunningAccountInteractor;
import cn.xiaojii.cashgift.interactor.impl.RunningAccountFragmentInteractor;
import cn.xiaojii.cashgift.presenter.inter.base.IBaseFragmentPresenter;
import cn.xiaojii.cashgift.view.inter.fragment.IRunningAccountView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class RunningAccountFragmentPresenter implements RunningAccountFragmentInteractor.OnAddProjrctListener,
        IBaseFragmentPresenter, IBaseFragmentInteractor.AddProjectListener, IRunningAccountInteractor.UpdateRAViewListener {

    private IRunningAccountView runningAccountView;
    private BroadcastReceiver dataReceive;


    public RunningAccountFragmentPresenter(IRunningAccountView runningAccountView, RunningAccountFragmentInteractor runningAccountInteractor) {
        this.runningAccountView = runningAccountView;
        this.runningAccountInteractor = runningAccountInteractor;
        EventBus.getDefault().register(this);
    }


    private RunningAccountFragmentInteractor runningAccountInteractor;


    @Override
    public void addProjectFromDialog(ProjectBean projectBean) {

        runningAccountInteractor.addProject(projectBean, this);
    }


    @Override
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void initDataFromMainInteractor(ProjectListMessageBean projectListMessageBean) {
        if (projectListMessageBean.getTag().equals(ConstantsBean.TAG_MAINPRESENTER)) {
            List dataList = projectListMessageBean.getProjectBeans();
            runningAccountInteractor.initData(dataList, this);
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
    public void onAddError() {

    }

    @Override
    public void onAddSuccess(List<ProjectBean> projectBeanList) {

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
    public void onUpdateViewSuccess(List dataList) {
        runningAccountView.updateListView(dataList);
    }
}
