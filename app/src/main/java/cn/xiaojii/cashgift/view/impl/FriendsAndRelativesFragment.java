package cn.xiaojii.cashgift.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.List;

import cn.xiaojii.cashgift.bean.FriendsAndRelativesBean;
import cn.xiaojii.cashgift.interactor.FriendsAndRelativesInteractor;
import cn.xiaojii.cashgift.presenter.impl.FriendsAndRelativesPresenter;
import cn.xiaojii.cashgift.view.IFriendsAndRelativesView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

public class FriendsAndRelativesFragment extends BaseFragment implements IFriendsAndRelativesView {
    private FriendsAndRelativesPresenter friendsAndRelativesPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        friendsAndRelativesPresenter = new FriendsAndRelativesPresenter(this, new FriendsAndRelativesInteractor());
    }

    @Override
    public void updateListView(List<FriendsAndRelativesBean> friendsAndRelativesBeanList) {

    }
}
