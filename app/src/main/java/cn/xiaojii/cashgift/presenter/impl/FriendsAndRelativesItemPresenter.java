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
import cn.xiaojii.cashgift.view.impl.FriendsAndRelativesItemFragment;

/**
 * @author dmrfcoder
 * @date 2018/8/8
 */

public class FriendsAndRelativesItemPresenter implements IFriendsAndRelativesItemPresenter, IBasePresenter, IBaseInteractor.UpdateViewListener, IBaseInteractor.InitDataListener, IBaseInteractor.ClickListviewItemListener {
    private FriendsAndRelativesItemFragment friendsAndRelativesItemFragment;
    private FriendsAndRelativesItemInteractor friendsAndRelativesItemInteractor;



    public FriendsAndRelativesItemPresenter(FriendsAndRelativesItemFragment friendsAndRelativesItemFragment, FriendsAndRelativesItemInteractor friendsAndRelativesItemInteractor) {
        this.friendsAndRelativesItemFragment = friendsAndRelativesItemFragment;
        this.friendsAndRelativesItemInteractor = friendsAndRelativesItemInteractor;




    }

    @Override
    public void addProjectFromDialog(ProjectBean projectBean) {

    }

    @Override
    public void addProjectFromEventBus(ProjectBean projectBean) {

    }

    @Override
    public void initDataFromMainInteractor(List dataList) {
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
    public void onDestroy() {

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
