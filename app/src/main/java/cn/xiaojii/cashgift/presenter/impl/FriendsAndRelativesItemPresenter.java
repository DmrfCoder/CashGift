package cn.xiaojii.cashgift.presenter.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import java.util.List;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;
import cn.xiaojii.cashgift.interactor.impl.FriendsAndRelativesItemInteractor;
import cn.xiaojii.cashgift.presenter.IBasePresenter;
import cn.xiaojii.cashgift.presenter.IFriendsAndRelativesItemPresenter;
import cn.xiaojii.cashgift.util.SendBroadCastUtil;
import cn.xiaojii.cashgift.view.impl.FriendsAndRelativesItemFragment;

/**
 * @author dmrfcoder
 * @date 2018/8/8
 */

public class FriendsAndRelativesItemPresenter implements IFriendsAndRelativesItemPresenter, IBasePresenter, IBaseInteractor.UpdateViewListener, IBaseInteractor.InitDataListener, IBaseInteractor.ClickListviewItemListener {
    private FriendsAndRelativesItemFragment friendsAndRelativesItemFragment;
    private FriendsAndRelativesItemInteractor friendsAndRelativesItemInteractor;
    private BroadcastReceiver getBeanListBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            List<ProjectBean> projectBeans = bundle.getParcelableArrayList(GlobalBean.BROADCAST_BEAN_LIST_KEY);
           // initFragmentData(projectBeans);
        }
    };


    private BroadcastReceiver addDataProjectReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String fragmentName = bundle.getString(GlobalBean.BROADCAST_ADD_PROJECT_FRAGMENT_NAME_KEY);
            if (!"FriendsAndRelativesItemPresenter".equals(fragmentName)) {
                ProjectBean projectBean = bundle.getParcelable(GlobalBean.BROADCAST_ADD_PROJECT_BEAN_KEY);
                if (projectBean != null) {
                    addProjectFromBC(projectBean);
                }
            }

        }
    };


    public FriendsAndRelativesItemPresenter(FriendsAndRelativesItemFragment friendsAndRelativesItemFragment, FriendsAndRelativesItemInteractor friendsAndRelativesItemInteractor) {
        this.friendsAndRelativesItemFragment = friendsAndRelativesItemFragment;
        this.friendsAndRelativesItemInteractor = friendsAndRelativesItemInteractor;


        IntentFilter getBeanListFilter = new IntentFilter();
        getBeanListFilter.addAction(GlobalBean.NORMAR_ACTION2);
        getBeanListFilter.setPriority(Integer.MAX_VALUE);
        ((Fragment) friendsAndRelativesItemFragment).getActivity().registerReceiver(getBeanListBroadcastReceiver, getBeanListFilter);

        SendBroadCastUtil.sendNeedDataBC((Fragment) friendsAndRelativesItemFragment);

        IntentFilter addDataProjectReceiverFilter = new IntentFilter();
        addDataProjectReceiverFilter.addAction(GlobalBean.NORMAR_ACTION3);
        addDataProjectReceiverFilter.setPriority(Integer.MAX_VALUE);
        ((Fragment) friendsAndRelativesItemFragment).getActivity().registerReceiver(addDataProjectReceiver, addDataProjectReceiverFilter);


    }

    @Override
    public void addProjectFromDG(ProjectBean projectBean) {

    }

    @Override
    public void addProjectFromBC(ProjectBean projectBean) {

    }

    @Override
    public void initFragmentData(List dataList) {
        friendsAndRelativesItemInteractor.initData(dataList, this);
    }

    @Override
    public void updateView() {
        friendsAndRelativesItemInteractor.updateView(this);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onUpdateViewError() {

    }

    @Override
    public void onUpdateViewSuccess(List dataList) {
        friendsAndRelativesItemFragment.updateListView(dataList);
    }

    @Override
    public void onInitDataError() {

    }

    @Override
    public void onInitDataSuccess(List dataList) {
        friendsAndRelativesItemFragment.updateListView(dataList);
    }


    @Override
    public void updateTargetName(String name) {
        friendsAndRelativesItemInteractor.clickListViewItem(name, this);
    }

    @Override
    public void onClickItemError() {

    }

    @Override
    public void onCliskItemSuccess(List dataList) {
        friendsAndRelativesItemFragment.updateListView(dataList);
    }
}
