package cn.xiaojii.cashgift.presenter.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.impl.MainInterator;
import cn.xiaojii.cashgift.presenter.IMainPresenter;
import cn.xiaojii.cashgift.util.SendBroadCastUtil;
import cn.xiaojii.cashgift.view.IMainView;
import cn.xiaojii.cashgift.view.impl.MainActivity;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class MainPresenter implements IMainPresenter, MainInterator.OnInitDataListener, MainInterator.OnAddProjectListener {
    private IMainView mainView;
    private MainInterator mainInterator;

    private String TAG = "MainPresenter";

    private BroadcastReceiver needDataReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String key = bundle.getString(GlobalBean.BROADCAST_NEED_DATA_KEY);
            if (GlobalBean.BROADCAST_NEED_DATA.equals(key)) {
               sendDataBroadCast();
            }
        }
    };

    private BroadcastReceiver addDataProjectReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            ProjectBean projectBean = bundle.getParcelable(GlobalBean.BROADCAST_ADD_PROJECT_BEAN_KEY);
            if (projectBean != null) {
                addProject(projectBean);
            }
        }
    };




    public MainPresenter(IMainView mainView, MainInterator mainInterator) {
        this.mainView = mainView;
        this.mainInterator = mainInterator;
        IntentFilter needDataReceiverFilter = new IntentFilter();
        needDataReceiverFilter.addAction(GlobalBean.NORMAR_ACTION);
        needDataReceiverFilter.setPriority(Integer.MAX_VALUE);
        ((MainActivity) mainView).registerReceiver(needDataReceiver, needDataReceiverFilter);

        IntentFilter addDataProjectReceiverFilter = new IntentFilter();
        addDataProjectReceiverFilter.addAction(GlobalBean.NORMAR_ACTION3);
        addDataProjectReceiverFilter.setPriority(Integer.MAX_VALUE);
        ((MainActivity) mainView).registerReceiver(addDataProjectReceiver, addDataProjectReceiverFilter);


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void sendDataBroadCast() {
        List<ProjectBean> projectBeans = mainInterator.getProjectBeanList();
        SendBroadCastUtil.sendDataBC((MainActivity) mainView, projectBeans);
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
        Log.e(TAG, "onAddProjectError");
    }

    @Override
    public void onAddProjectSuccess(List<ProjectBean> list) {
        Log.i(TAG, "onAddProjectSuccess,list size:" + list.size());
    }


}
