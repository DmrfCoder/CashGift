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
    public static Fragment createForMain(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FriendsAndRelativesFragment();
                break;
            case 1:
                fragment = new RunningAccountFragment();
                break;
            case 2:
                fragment = new ProjectTableFragment();
                break;
            case 3:
                fragment = new DiscoverFragment();
                break;
            case 4:
                fragment = new MoreFragment();
                break;
            default:
                break;

        }
        return fragment;
    }


}
