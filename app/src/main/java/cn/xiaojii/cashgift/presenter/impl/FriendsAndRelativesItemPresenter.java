package cn.xiaojii.cashgift.presenter.impl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectListMessageEvent;
import cn.xiaojii.cashgift.interactor.IFriendsAndRelativesItemInteractor;
import cn.xiaojii.cashgift.interactor.impl.FriendsAndRelativesItemInteractor;
import cn.xiaojii.cashgift.presenter.IBasePresenter;
import cn.xiaojii.cashgift.presenter.IFriendsAndRelativesItemPresenter;
import cn.xiaojii.cashgift.view.IFriendsAndRelativesItemView;
import cn.xiaojii.cashgift.view.IFriendsAndRelativesView;
import cn.xiaojii.cashgift.view.impl.FriendsAndRelativesItemFragment;

/**
 * @author dmrfcoder
 * @date 2018/8/8
 */

public class FriendsAndRelativesItemPresenter implements IFriendsAndRelativesItemPresenter,
        IBasePresenter, IFriendsAndRelativesItemInteractor.UpdateFariViewListener {
    private IFriendsAndRelativesItemView iFriendsAndRelativesItemView;
    private FriendsAndRelativesItemInteractor friendsAndRelativesItemInteractor;

    public FriendsAndRelativesItemPresenter(IFriendsAndRelativesItemView iFriendsAndRelativesItemView, FriendsAndRelativesItemInteractor friendsAndRelativesItemInteractor) {
        this.iFriendsAndRelativesItemView = iFriendsAndRelativesItemView;
        this.friendsAndRelativesItemInteractor = friendsAndRelativesItemInteractor;
        EventBus.getDefault().register(this);
    }



    @Override
    public void addProjectFromDialog(ProjectBean projectBean) {

    }


    @Override
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void initDataFromMainInteractor(ProjectListMessageEvent projectListMessageEvent) {
        if (projectListMessageEvent.getTag().equals(GlobalBean.TAG_MAINPRESENTER)){
            friendsAndRelativesItemInteractor.initData(projectListMessageEvent.getProjectBeans(),this);
        }

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
        iFriendsAndRelativesItemView.updateListView(dataList);
        iFriendsAndRelativesItemView.updateView(totalMoney, inCount, outCount,name);
    }


    @Override
    @Subscribe(sticky = true)
    public void updateTargetName(String name) {
        friendsAndRelativesItemInteractor.clickListViewItem(name, this);
    }


}
