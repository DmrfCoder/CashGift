package cn.xiaojii.cashgift.presenter.impl.fragment;

import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.xiaojii.cashgift.bean.fragment.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.bean.global.ConstantsBean;
import cn.xiaojii.cashgift.bean.fragment.ProjectBean;
import cn.xiaojii.cashgift.bean.message.ProjectListMessageBean;
import cn.xiaojii.cashgift.interactor.inter.base.IBaseFragmentInteractor;
import cn.xiaojii.cashgift.interactor.inter.fragment.IFriendsAndRelativesInteractor;
import cn.xiaojii.cashgift.interactor.impl.FriendsAndRelativesFragmentInteractor;
import cn.xiaojii.cashgift.presenter.inter.base.IBaseFragmentPresenter;
import cn.xiaojii.cashgift.presenter.inter.fragment.IFriendsAndRelativesPresenter;
import cn.xiaojii.cashgift.view.inter.fragment.IFriendsAndRelativesView;
import cn.xiaojii.cashgift.view.impl.fragment.FriendsAndRelativesItemFragment;
import cn.xiaojii.cashgift.view.impl.activity.MainActivity;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class FriendsAndRelativesFragmentPresenter implements IFriendsAndRelativesPresenter,
        IBaseFragmentPresenter, FriendsAndRelativesFragmentInteractor.OnInquireFinishedListener,
        IBaseFragmentInteractor.AddProjectListener,
        IFriendsAndRelativesInteractor.UpdateFarViewListener,
        FriendsAndRelativesFragmentInteractor.NeedPositionNameListener {
    private IFriendsAndRelativesView friendsAndRelativesView;
    private FriendsAndRelativesFragmentInteractor friendsAndRelativesInteractor;


    public FriendsAndRelativesFragmentPresenter(IFriendsAndRelativesView friendsAndRelativesView, final FriendsAndRelativesFragmentInteractor friendsAndRelativesInteractor) {
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
    public void initDataFromMainInteractor(ProjectListMessageBean projectListMessageBean) {
        if (projectListMessageBean.getTag().equals(ConstantsBean.TAG_MAINPRESENTER)) {
            List dataList = projectListMessageBean.getProjectBeans();
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
