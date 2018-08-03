package cn.xiaojii.cashgift.presenter.impl;

import java.util.List;

import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.interactor.FriendsAndRelativesInteractor;
import cn.xiaojii.cashgift.presenter.IFriendsAndRelativesPresenter;
import cn.xiaojii.cashgift.view.IFriendsAndRelativesView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class FriendsAndRelativesPresenter implements IFriendsAndRelativesPresenter, FriendsAndRelativesInteractor.OnInquireFinishedListener,FriendsAndRelativesInteractor.InitFriendsAndRelativesListener {
    private IFriendsAndRelativesView friendsAndRelativesView;
    private FriendsAndRelativesInteractor friendsAndRelativesInteractor;

    public FriendsAndRelativesPresenter(IFriendsAndRelativesView friendsAndRelativesView, FriendsAndRelativesInteractor friendsAndRelativesInteractor) {
        this.friendsAndRelativesView = friendsAndRelativesView;
        this.friendsAndRelativesInteractor = friendsAndRelativesInteractor;
    }

    @Override
    public List<FriendsAndRelativesBean> inquireFriendsAndRelatives(String name) {

        return null;

    }



    @Override
    public boolean updateOrder(int Code) {
        return false;
    }

    @Override
    public boolean initFriendsAndRelativesListView() {
        friendsAndRelativesInteractor.InitFriendsAndRelativesListView(this);
        return false;
    }

    @Override
    public void onInquireError() {

    }

    @Override
    public void onInquireSuccess(List<FriendsAndRelativesBean> friendsAndRelativesBeanList) {
        friendsAndRelativesView.updateListView(friendsAndRelativesBeanList);
    }

    @Override
    public void onInitFriendsAndRelativesError() {

    }

    @Override
    public void onInitFriendsAndRelativesSuccess(List<FriendsAndRelativesBean> friendsAndRelativesBeanList) {
        friendsAndRelativesView.updateListView(friendsAndRelativesBeanList);

    }
}
