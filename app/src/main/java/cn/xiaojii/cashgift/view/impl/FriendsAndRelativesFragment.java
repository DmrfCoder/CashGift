package cn.xiaojii.cashgift.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.xiaojii.cashgift.R;
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



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friendsandrelatives, null);
        friendsAndRelativesPresenter = new FriendsAndRelativesPresenter(this, new FriendsAndRelativesInteractor());
        return view;
    }



    @Override
    public void updateListView(List<FriendsAndRelativesBean> friendsAndRelativesBeanList) {

    }
}
