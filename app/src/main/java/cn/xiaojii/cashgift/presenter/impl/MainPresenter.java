package cn.xiaojii.cashgift.presenter.impl;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ParcelableListBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.impl.MainInterator;
import cn.xiaojii.cashgift.presenter.IMainPresenter;
import cn.xiaojii.cashgift.view.IMainView;
import cn.xiaojii.cashgift.view.impl.MainActivity;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class MainPresenter implements IMainPresenter, MainInterator.OnInitDataListener, MainInterator.OnAddProjectListener {
    private IMainView mainView;
    private MainInterator mainInterator;

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

    }


    @Override
    public void onAddProjectError() {

    }

    @Override
    public void onAddProjectSuccess(List<ProjectBean> list) {
        ParcelableListBean parcelableListBean = new ParcelableListBean(list);
        Intent intent = new Intent(GlobalBean.NORMAR_ACTION);
        Bundle bundle=new Bundle();
        bundle.putParcelable(GlobalBean.BROADCAST_TAG,parcelableListBean);
        intent.putExtras(bundle);
        ((MainActivity) mainView).sendBroadcast(intent);
    }


}
