package cn.xiaojii.cashgift.presenter.impl;

import java.util.List;

import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.bean.ProjectBean;
import cn.xiaojii.cashgift.interactor.IBaseInteractor;
import cn.xiaojii.cashgift.interactor.impl.FriendsAndRelativesInteractor;
import cn.xiaojii.cashgift.presenter.IBasePresenter;
import cn.xiaojii.cashgift.presenter.IFriendsAndRelativesPresenter;
import cn.xiaojii.cashgift.util.ConvertBeanUtil;
import cn.xiaojii.cashgift.view.IFriendsAndRelativesView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class FriendsAndRelativesPresenter implements IFriendsAndRelativesPresenter,
        IBasePresenter, FriendsAndRelativesInteractor.OnInquireFinishedListener,
        IBaseInteractor.InitDataListener, IBaseInteractor.AddProjectListener,
        IBaseInteractor.UpdateViewListener {
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
    public void onInquireError() {

    }

    @Override
    public void onInquireSuccess(List<FriendsAndRelativesBean> friendsAndRelativesBeanList) {
        friendsAndRelativesView.updateListView(friendsAndRelativesBeanList);
    }


    @Override
    public void addProject(ProjectBean projectBean) {
        friendsAndRelativesInteractor.addProject(projectBean, this);
    }

    @Override
    public void initData(List dataList) {
        friendsAndRelativesInteractor.initData(dataList, this);
    }

    @Override
    public void updateView() {
        friendsAndRelativesInteractor.updateView(this);
    }

    @Override
    public void onInitDataError() {

    }

    @Override
    public void onInitDataSuccess(List dataList) {
        //friendsAndRelativesView.updateListView(dataList);
    }

    @Override
    public void onAddProjectError() {

    }

    @Override
    public void onAddProjectSuccess(List beanList) {

    }

    @Override
    public void onUpdateViewError() {

    }

    @Override
    public void onUpdateViewSuccess(List dataList) {
        friendsAndRelativesView.updateListView(ConvertBeanUtil.convertProjectBeansToFriendsAndRelativesBeans(dataList));
    }
}
