package cn.xiaojii.cashgift.presenter.impl;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectListMessageEvent;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;
import cn.xiaojii.cashgift.interactor.IFriendsAndRelativesInteractor;
import cn.xiaojii.cashgift.interactor.impl.FriendsAndRelativesInteractor;
import cn.xiaojii.cashgift.presenter.IBasePresenter;
import cn.xiaojii.cashgift.presenter.IFriendsAndRelativesPresenter;
import cn.xiaojii.cashgift.view.IFriendsAndRelativesView;
import cn.xiaojii.cashgift.view.impl.FriendsAndRelativesFragment;
import cn.xiaojii.cashgift.view.impl.FriendsAndRelativesItemFragment;
import cn.xiaojii.cashgift.view.impl.MainActivity;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class FriendsAndRelativesPresenter implements IFriendsAndRelativesPresenter,
        IBasePresenter, FriendsAndRelativesInteractor.OnInquireFinishedListener,
        IBaseInteractor.AddProjectListener,
        IFriendsAndRelativesInteractor.UpdateFarViewListener,
        FriendsAndRelativesInteractor.NeedPositionNameListener {
    private IFriendsAndRelativesView friendsAndRelativesView;
    private FriendsAndRelativesInteractor friendsAndRelativesInteractor;


    public FriendsAndRelativesPresenter(IFriendsAndRelativesView friendsAndRelativesView, final FriendsAndRelativesInteractor friendsAndRelativesInteractor) {
        this.friendsAndRelativesView = friendsAndRelativesView;
        this.friendsAndRelativesInteractor = friendsAndRelativesInteractor;

        EventBus.getDefault().register(this);


    }

    @Override
    public void inquireFriendsAndRelatives(String name) {
        friendsAndRelativesInteractor.Inquire(name, this);

    }


    @Override
    public boolean updateOrder(int Code) {
        return false;
    }

    @Override
    public void clickListViewItem(int position) {
        friendsAndRelativesInteractor.clickListViewItem(position, this);
    }


    @Override
    public void onInquireError() {

    }

    @Override
    public void onInquireSuccess(List<FriendsAndRelativesBean> friendsAndRelativesBeanList) {
        friendsAndRelativesView.updateListView(friendsAndRelativesBeanList);
    }


    @Override
    public void addProjectFromDialog(ProjectBean projectBean) {
        friendsAndRelativesInteractor.addProject(projectBean, this);
    }


    @Override
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void initDataFromMainInteractor(ProjectListMessageEvent projectListMessageEvent) {
        if (projectListMessageEvent.getTag().equals(GlobalBean.TAG_MAINPRESENTER)) {
            List dataList = projectListMessageEvent.getProjectBeans();
            friendsAndRelativesInteractor.initData(dataList, this);
        }

    }

    @Override
    public void updateView() {
        friendsAndRelativesInteractor.updateView(this);
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);

    }


    @Override
    public void onAddProjectError() {

    }

    @Override
    public void onAddProjectSuccess(List beanList) {
        friendsAndRelativesView.updateListView(beanList);
    }


    @Override
    public void onUpdateViewError() {

    }

    @Override
    public void onUpdateViewSuccess(List dataList) {
        friendsAndRelativesView.updateListView(dataList);
    }


    @Override
    public void onNeedPositionNameError() {

    }

    @Override
    public void onNeedPositionNameSuccess(String name) {

        FriendsAndRelativesItemFragment friendsAndRelativesItemFragment = new FriendsAndRelativesItemFragment();
        ((MainActivity) ((Fragment) friendsAndRelativesView).getActivity()).startfragment(friendsAndRelativesItemFragment, true);
        EventBus.getDefault().postSticky(name);
    }


}
