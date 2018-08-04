package cn.xiaojii.cashgift.presenter.impl;

import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.impl.MainInterator;
import cn.xiaojii.cashgift.presenter.IMainPresenter;
import cn.xiaojii.cashgift.view.IMainView;
import cn.xiaojii.cashgift.view.impl.DiscoverFragment;
import cn.xiaojii.cashgift.view.impl.FriendsAndRelativesFragment;
import cn.xiaojii.cashgift.view.impl.MainActivity;
import cn.xiaojii.cashgift.view.impl.MoreFragment;
import cn.xiaojii.cashgift.view.impl.ProjectTableFragment;
import cn.xiaojii.cashgift.view.impl.RunningAccountFragment;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class MainPresenter implements IMainPresenter, MainInterator.OnInitDataListener ,MainInterator.OnAddProjectListener{
    private IMainView mainView;
    private MainInterator mainInterator;

    public MainPresenter(IMainView mainView, MainInterator mainInterator) {
        this.mainView = mainView;
        this.mainInterator = mainInterator;
    }




    @Override
    public void initData() {
        mainInterator.initData(this);
    }

    @Override
    public void getData(MainInterator.OnGetDataListener onGetDataListener) {
        mainInterator.GetData(onGetDataListener);
    }

    @Override
    public void addProject(ProjectBean projectBean) {
        mainInterator.AddProject(projectBean,this);
    }


    @Override
    public void OnInitError() {

    }

    @Override
    public void OnInitSuccess(List<ProjectBean> projectBeanList) {

    }


    @Override
    public void onAddProjectError() {

    }

    @Override
    public void onAddProjectSuccess() {

    }
}
