package cn.xiaojii.cashgift.presenter.impl;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.xiaojii.cashgift.bean.ProjectBean;
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
    public void addProject(ProjectBean projectBean) {
        mainInterator.AddProject(projectBean, this);
    }

    @Override
    public void onDestroy() {
        mainInterator.onDestroy();

    }


    @Override
    public void OnInitError() {

    }

    @Override
    public void OnInitSuccess(List<ProjectBean> projectBeanList) {

        ProjectListMessageEvent projectListMessageEvent = new ProjectListMessageEvent(projectBeanList);
        EventBus.getDefault().postSticky(projectListMessageEvent);
    }


    @Override
    public void onAddProjectError() {
        Log.e(TAG, "onAddProjectError");
    }

    @Override
    public void onAddProjectSuccess(List<ProjectBean> list) {
        Log.i(TAG, "onAddProjectSuccess,list size:" + list.size());
    }


}
