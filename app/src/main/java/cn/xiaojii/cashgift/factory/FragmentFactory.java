package cn.xiaojii.cashgift.factory;

import android.support.v4.app.Fragment;

import cn.xiaojii.cashgift.view.impl.fragment.DiscoverFragment;
import cn.xiaojii.cashgift.view.impl.fragment.FriendsAndRelativesFragment;
import cn.xiaojii.cashgift.view.impl.fragment.MoreFragment;
import cn.xiaojii.cashgift.view.impl.fragment.ProjectTableFragment;
import cn.xiaojii.cashgift.view.impl.fragment.RunningAccountFragment;


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
