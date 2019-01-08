package cn.xiaojii.cashgift.presenter.impl.fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.xiaojii.cashgift.bean.global.ConstantsBean;
import cn.xiaojii.cashgift.bean.fragment.ProjectBean;
import cn.xiaojii.cashgift.bean.message.ProjectListMessageBean;
import cn.xiaojii.cashgift.interactor.inter.fragment.IFriendsAndRelativesItemInteractor;
import cn.xiaojii.cashgift.interactor.impl.FriendsAndRelativesItemInteractor;
import cn.xiaojii.cashgift.presenter.inter.base.IBaseFragmentPresenter;
import cn.xiaojii.cashgift.presenter.inter.fragment.IFriendsAndRelativesItemPresenter;
import cn.xiaojii.cashgift.view.inter.fragment.IFriendsAndRelativesItemView;

/**
 * @author dmrfcoder
 * @date 2018/8/8
 */

public class FriendsAndRelativesItemFragmentPresenter implements IFriendsAndRelativesItemPresenter,
        IBaseFragmentPresenter, IFriendsAndRelativesItemInteractor.UpdateFariViewListener {
    private IFriendsAndRelativesItemView iFriendsAndRelativesItemView;
    private FriendsAndRelativesItemInteractor friendsAndRelativesItemInteractor;

    public FriendsAndRelativesItemFragmentPresenter(IFriendsAndRelativesItemView iFriendsAndRelativesItemView, FriendsAndRelativesItemInteractor friendsAndRelativesItemInteractor) {
        this.iFriendsAndRelativesItemView = iFriendsAndRelativesItemView;
        this.friendsAndRelativesItemInteractor = friendsAndRelativesItemInteractor;
        EventBus.getDefault().register(this);
    }



    @Override
    public void addProjectFromDialog(ProjectBean projectBean) {

    }


    @Override
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void initDataFromMainInteractor(ProjectListMessageBean projectListMessageBean) {
        if (projectListMessageBean.getTag().equals(ConstantsBean.TAG_MAINPRESENTER)){
            friendsAndRelativesItemInteractor.initData(projectListMessageBean.getProjectBeans(),this);
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
