package cn.xiaojii.cashgift.factory;

import android.content.Context;
import android.support.v4.app.Fragment;

import cn.xiaojii.cashgift.presenter.impl.MainPresenter;
import cn.xiaojii.cashgift.view.impl.DiscoverFragment;
import cn.xiaojii.cashgift.view.impl.FriendsAndRelativesFragment;
import cn.xiaojii.cashgift.view.impl.MoreFragment;
import cn.xiaojii.cashgift.view.impl.ProjectTableFragment;
import cn.xiaojii.cashgift.view.impl.RunningAccountFragment;


public class FragmentFactory {


    /**
     * main
     *
     * @param position
     * @return
     */
    public static Fragment createForMain(int position,MainPresenter mainPresenter) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FriendsAndRelativesFragment();
                break;
            case 1:
                fragment = new RunningAccountFragment();
                break;
            case 2:
                fragment = new ProjectTableFragment(mainPresenter);
                break;
            case 3:
                fragment = new DiscoverFragment(mainPresenter);
                break;
            case 4:
                fragment = new MoreFragment(mainPresenter);
                break;
            default:
                break;

        }
        return fragment;
    }


}
