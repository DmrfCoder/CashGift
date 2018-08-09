package cn.xiaojii.cashgift.presenter.impl;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.bean.ProjectListMessageEvent;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;
import cn.xiaojii.cashgift.interactor.impl.FriendsAndRelativesInteractor;
import cn.xiaojii.cashgift.presenter.IBasePresenter;
import cn.xiaojii.cashgift.presenter.IFriendsAndRelativesPresenter;
import cn.xiaojii.cashgift.view.IFriendsAndRelativesView;
import cn.xiaojii.cashgift.view.impl.FriendsAndRelativesFragment;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class FriendsAndRelativesPresenter implements IFriendsAndRelativesPresenter,
        IBasePresenter, FriendsAndRelativesInteractor.OnInquireFinishedListener,
        IBaseInteractor.InitDataListener, IBaseInteractor.AddProjectListener,
        IBaseInteractor.UpdateViewListener, IBaseInteractor.ClickListviewItemListener,
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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveEventBus(ProjectListMessageEvent projectListMessageEvent) {
        if (projectListMessageEvent != null) {
            initDataFromMainInteractor(projectListMessageEvent.getProjectBeans());
        }
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
    @Subscribe
    public void addProjectFromEventBus(ProjectBean projectBean) {
        friendsAndRelativesInteractor.addProject(projectBean, this);
    }


    @Override
    public void initDataFromMainInteractor(List dataList) {
        friendsAndRelativesInteractor.initData(dataList, this);
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
    public void onInitDataError() {

    }

    @Override
    public void onInitDataSuccess(List dataList) {
        friendsAndRelativesView.updateListView(dataList);
    }

    @Override
    public void onAddProjectError() {

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onAddProjectSuccess(List beanList, String BroadCastTag, ProjectBean projectBean) {
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
    public void onClickItemError() {

    }

    @Override
    public void onCliskItemSuccess(List dataList) {
        //发送广播
    }

    @Override
    public void onNeedPositionNameError() {

    }

    @Override
    public void onNeedPositionNameSuccess(String name) {
        ((FriendsAndRelativesFragment) friendsAndRelativesView).jumpToTargetItemFragment(name);
    }
}
