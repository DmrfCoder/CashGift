package cn.xiaojii.cashgift.presenter.impl;

import android.content.Context;

import java.util.List;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.RunningAccountBean;
import cn.xiaojii.cashgift.interactor.MainInterator;
import cn.xiaojii.cashgift.presenter.IMainPresenter;
import cn.xiaojii.cashgift.util.JsonToListUtil;
import cn.xiaojii.cashgift.util.ReadFileToStringUtil;
import cn.xiaojii.cashgift.view.IMainView;
import cn.xiaojii.cashgift.view.impl.MainActivity;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class MainPresenter implements IMainPresenter, MainInterator.OnAddProjrctListener, MainInterator.OnInitDataListener {
    private IMainView mainView;
    private MainInterator mainInterator;

    public MainPresenter(IMainView mainView, MainInterator mainInterator) {
        this.mainView = mainView;
        this.mainInterator = mainInterator;
    }

    @Override
    public void addProject(RunningAccountBean runningAccountBean) {
        mainInterator.addRunningAccount(runningAccountBean, this);
    }

    @Override
    public void initData() {
        mainInterator.initData(this);
    }

    @Override
    public List<RunningAccountBean> getData() {
        return mainInterator.getRunningAccountBeanList();
    }

    @Override
    public void onAddError() {

    }

    @Override
    public void onAddSuccess(List<RunningAccountBean> runningAccountBeanList) {
        ((MainActivity) mainView).CurFragment.updateData(runningAccountBeanList);
    }

    @Override
    public void OnInitError() {

    }

    @Override
    public void OnInitSuccess(List<RunningAccountBean> runningAccountBeanList) {
    }
}
