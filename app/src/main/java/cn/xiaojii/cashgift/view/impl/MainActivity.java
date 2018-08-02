package cn.xiaojii.cashgift.view.impl;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.view.IBaseFragment;
import cn.xiaojii.cashgift.view.IMainView;

/**
 * @author dmrfcoder
 * @date 2018/8/2
 */

@SuppressLint("Registered")
public class MainActivity extends FragmentActivity implements IMainView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void startfragment(IBaseFragment targetFragment) {

    }
}
