package cn.xiaojii.cashgift.view.impl;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.bean.GlobalBean;
import cn.xiaojii.cashgift.inter.IBaseFragmentView;
import cn.xiaojii.cashgift.presenter.IMainPresenter;
import cn.xiaojii.cashgift.view.IDiscoverView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

@SuppressLint("ValidFragment")
public class DiscoverFragment extends Fragment implements IDiscoverView, IBaseFragmentView, View.OnClickListener {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, null);
        initFragment(view);
        return view;
    }

    @Override
    public void initFragment(View view) {
        view.findViewById(R.id.id_discover_excel).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_discover_excel:
                EventBus.getDefault().post(GlobalBean.EXPORTEXCEL);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
