package cn.xiaojii.cashgift.presenter.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectListMessageEvent;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;
import cn.xiaojii.cashgift.interactor.IFriendsAndRelativesInteractor;
import cn.xiaojii.cashgift.interactor.IFriendsAndRelativesItemInteractor;
import cn.xiaojii.cashgift.interactor.impl.FriendsAndRelativesItemInteractor;
import cn.xiaojii.cashgift.presenter.IBasePresenter;
import cn.xiaojii.cashgift.presenter.IFriendsAndRelativesItemPresenter;
import cn.xiaojii.cashgift.view.impl.FriendsAndRelativesItemFragment;

/**
 * @author dmrfcoder
 * @date 2018/8/8
 */

public class FriendsAndRelativesItemPresenter implements IFriendsAndRelativesItemPresenter,
        IBasePresenter, IFriendsAndRelativesItemInteractor.UpdateFariViewListener {
    private FriendsAndRelativesItemFragment friendsAndRelativesItemFragment;
    private FriendsAndRelativesItemInteractor friendsAndRelativesItemInteractor;


    public FriendsAndRelativesItemPresenter(FriendsAndRelativesItemFragment friendsAndRelativesItemFragment, FriendsAndRelativesItemInteractor friendsAndRelativesItemInteractor) {
        this.friendsAndRelativesItemFragment = friendsAndRelativesItemFragment;
        this.friendsAndRelativesItemInteractor = friendsAndRelativesItemInteractor;
        EventBus.getDefault().register(this);
    }

    @Override
    public void addProjectFromDialog(ProjectBean projectBean) {

    }


    @Override
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void initDataFromMainInteractor(ProjectListMessageEvent projectListMessageEvent) {
        friendsAndRelativesItemInteractor.initData(projectListMessageEvent.getProjectBeans(),this);
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
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onUpdateViewError() {

    }


    @Override
    public void onUpdateViewSuccess(List dataList, int totalMoney, int inCount, int outCount,String name) {
        friendsAndRelativesItemFragment.updateListView(dataList);
        friendsAndRelativesItemFragment.updateView(totalMoney, inCount, outCount,name);
    }


    @Override
    @Subscribe(sticky = true)
    public void updateTargetName(String name) {
        friendsAndRelativesItemInteractor.clickListViewItem(name, this);
    }


}
