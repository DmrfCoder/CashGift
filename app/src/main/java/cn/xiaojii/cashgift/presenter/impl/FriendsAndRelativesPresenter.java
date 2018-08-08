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

import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;
import cn.xiaojii.cashgift.interactor.impl.FriendsAndRelativesInteractor;
import cn.xiaojii.cashgift.presenter.IBasePresenter;
import cn.xiaojii.cashgift.presenter.IFriendsAndRelativesPresenter;
import cn.xiaojii.cashgift.util.SendBroadCastUtil;
import cn.xiaojii.cashgift.view.IFriendsAndRelativesView;
import cn.xiaojii.cashgift.view.impl.FriendsAndRelativesFragment;
import cn.xiaojii.cashgift.view.impl.FriendsAndRelativesItemFragment;

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


    private BroadcastReceiver getBeanListBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            List<ProjectBean> projectBeans = bundle.getParcelableArrayList(GlobalBean.BROADCAST_BEAN_LIST_KEY);
            initFragmentData(projectBeans);
        }
    };


    private BroadcastReceiver addDataProjectReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            String fragmentName = bundle.getString(GlobalBean.BROADCAST_ADD_PROJECT_FRAGMENT_NAME_KEY);
            if (!"FriendsAndRelativesPresenter".equals(fragmentName)) {
                ProjectBean projectBean = bundle.getParcelable(GlobalBean.BROADCAST_ADD_PROJECT_BEAN_KEY);
                if (projectBean != null) {
                    addProjectFromBC(projectBean);
                }
            }

        }
    };

    public FriendsAndRelativesPresenter(IFriendsAndRelativesView friendsAndRelativesView, final FriendsAndRelativesInteractor friendsAndRelativesInteractor) {
        this.friendsAndRelativesView = friendsAndRelativesView;
        this.friendsAndRelativesInteractor = friendsAndRelativesInteractor;
        IntentFilter getBeanListFilter = new IntentFilter();
        getBeanListFilter.addAction(GlobalBean.NORMAR_ACTION2);
        getBeanListFilter.setPriority(Integer.MAX_VALUE);
        ((Fragment) friendsAndRelativesView).getActivity().registerReceiver(getBeanListBroadcastReceiver, getBeanListFilter);
        SendBroadCastUtil.sendNeedDataBC((Fragment) friendsAndRelativesView);

        IntentFilter addDataProjectReceiverFilter = new IntentFilter();
        addDataProjectReceiverFilter.addAction(GlobalBean.NORMAR_ACTION3);
        addDataProjectReceiverFilter.setPriority(Integer.MAX_VALUE);
        ((Fragment) friendsAndRelativesView).getActivity().registerReceiver(addDataProjectReceiver, addDataProjectReceiverFilter);

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
    public void addProjectFromDG(ProjectBean projectBean) {
        friendsAndRelativesInteractor.addProject(projectBean, this);
        SendBroadCastUtil.sendAddProjectBC((Fragment) friendsAndRelativesView, projectBean, "FriendsAndRelativesPresenter");
    }

    @Override
    public void addProjectFromBC(ProjectBean projectBean) {
        friendsAndRelativesInteractor.addProject(projectBean, this);
    }


    @Override
    public void initFragmentData(List dataList) {
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
        SendBroadCastUtil.sendListToFriendsItemPresenterBC((Fragment) friendsAndRelativesView, dataList);
    }

    @Override
    public void onNeedPositionNameError() {

    }

    @Override
    public void onNeedPositionNameSuccess(String name) {
        ((FriendsAndRelativesFragment) friendsAndRelativesView).jumpToTargetItemFragment(name);
    }
}
