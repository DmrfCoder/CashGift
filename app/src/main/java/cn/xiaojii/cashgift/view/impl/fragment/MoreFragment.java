package cn.xiaojii.cashgift.view.impl.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.interactor.impl.MoreInteractor;
import cn.xiaojii.cashgift.presenter.impl.fragment.MoreViewPresenter;
import cn.xiaojii.cashgift.util.io.SharedPreferencesUtil;
import cn.xiaojii.cashgift.view.impl.base.BaseFragment;
import cn.xiaojii.cashgift.view.inter.fragment.IMoreView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

@SuppressLint("ValidFragment")
public class MoreFragment extends BaseFragment implements IMoreView, CompoundButton.OnCheckedChangeListener {


    private Switch fingerSwitch;
    private Switch graphWitch;

    private MoreViewPresenter moreViewPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, null);
        initFragment(view);
        return view;
    }

    @Override
    public void initFragment(View view) {
        moreViewPresenter = new MoreViewPresenter(this, new MoreInteractor());

        fingerSwitch = view.findViewById(R.id.id_more_finger_switch);
        graphWitch = view.findViewById(R.id.id_more_graph_switch);
        fingerSwitch.setOnCheckedChangeListener(this);
        graphWitch.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.id_more_finger_switch:
                if (fingerSwitch.isChecked()) {
                    moreViewPresenter.fingerOnToOff();
                } else {
                    moreViewPresenter.fingerOffToOn();
                }
                break;
            case R.id.id_more_graph_switch:
                if (graphWitch.isChecked()) {
                    moreViewPresenter.gestureOffToOn();
                } else {
                    moreViewPresenter.gestureOnToOff();
                }
                break;
            default:
                break;
        }
    }


}
