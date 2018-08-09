package cn.xiaojii.cashgift.presenter.impl;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectBeanMessageBean;
import cn.xiaojii.cashgift.bean.ProjectListMessageEvent;
import cn.xiaojii.cashgift.interactor.impl.MainInterator;
import cn.xiaojii.cashgift.presenter.IMainPresenter;
import cn.xiaojii.cashgift.view.IMainView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class MainPresenter implements IMainPresenter, MainInterator.OnInitDataListener, MainInterator.OnAddProjectListener {
    private IMainView mainView;
    private MainInterator mainInterator;

    private String TAG = "MainPresenter";


    public MainPresenter(IMainView mainView, MainInterator mainInterator) {
        this.mainView = mainView;
        this.mainInterator = mainInterator;
        EventBus.getDefault().register(this);
    }


    @Override
    public void initActivityData() {
        mainInterator.initData(this);
    }

    @Override
    public void getData(MainInterator.OnGetDataListener onGetDataListener) {
        mainInterator.GetData(onGetDataListener);
    }


    @Override
    @Subscribe
    public void addProjectFromEventBus(ProjectBeanMessageBean projectBeanMessageBean) {
        if (projectBeanMessageBean.getTag().equals(GlobalBean.TAG_DIALOGFRAGMENT)) {
            ProjectBean projectBean = projectBeanMessageBean.getProjectBean();
            mainInterator.AddProject(projectBean, this);
        }

    }

    @Override
    public void onDestroy() {
        mainInterator.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void OnInitError() {

    }

    @Override
    public void OnInitSuccess(List<ProjectBean> projectBeanList) {
        sendListEventBus(projectBeanList);

    }


    @Override
    public void onAddProjectError() {
        Log.e(TAG, "onAddProjectError");
    }

    @Override
    public void onAddProjectSuccess(List<ProjectBean> list) {
        Log.i(TAG, "onAddProjectSuccess,list size:" + list.size());
        sendListEventBus(list);
    }

    private void sendListEventBus(List list) {
        ProjectListMessageEvent projectListMessageEvent = new ProjectListMessageEvent(list, GlobalBean.TAG_MAINPRESENTER);
        EventBus.getDefault().postSticky(projectListMessageEvent);
    }

}
