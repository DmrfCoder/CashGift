package cn.xiaojii.cashgift.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import cn.xiaojii.cashgift.factory.FragmentFactory;
import cn.xiaojii.cashgift.presenter.impl.MainPresenter;

/**
 * Created by Carson_Ho on 16/5/23.
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {


    private MainPresenter mainPresenter;

    public FragmentAdapter(FragmentManager fm, MainPresenter mainPresenter) {
        super(fm);
        this.mainPresenter = mainPresenter;
    }

    @Override
    public Fragment getItem(int arg0) {
        return FragmentFactory.createForMain(arg0, mainPresenter);
    }//根据Item的位置返回对应位置的Fragment，绑定item和Fragment

    @Override
    public int getCount() {
        return 5;
    }//设置Item的数量

}


