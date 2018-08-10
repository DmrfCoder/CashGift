package cn.xiaojii.cashgift.view.impl;

import android.annotation.SuppressLint;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.inter.IBaseFragmentView;
import cn.xiaojii.cashgift.presenter.IMainPresenter;
import cn.xiaojii.cashgift.view.IMoreView;

/**
 * @author dmrfcoder
 * @date 2018/8/3
 */

@SuppressLint("ValidFragment")
public class MoreFragment extends Fragment implements IMoreView, IBaseFragmentView, View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    private Switch fingerSwitch;
    private Switch graphWitch;
    private boolean isShowGesturePwd;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, null);
        initFragment(view);
        return view;
    }

    @Override
    public void initFragment(View view) {

        view.findViewById(R.id.id_more_finger).setOnClickListener(this);
        view.findViewById(R.id.id_more_pass).setOnClickListener(this);

        fingerSwitch = view.findViewById(R.id.id_more_finger_switch);
        graphWitch = view.findViewById(R.id.id_more_graph_switch);
        fingerSwitch.setOnCheckedChangeListener(this);
        graphWitch.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_more_finger:
                finger();
                break;
            case R.id.id_more_pass:
                break;
            default:
                break;
        }
    }

    private void finger() {


    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.id_more_finger:
                if (fingerSwitch.isChecked()) {

                } else {

                }
                break;
            case R.id.id_more_graph_switch:
                if (graphWitch.isChecked()) {

                } else {

                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        graphWitch.setOnCheckedChangeListener(null);
        if (isShowGesturePwd) {
            graphWitch.setChecked(true);
        } else {
            graphWitch.setChecked(false);
        }

        //注册回调
        graphWitch.setOnCheckedChangeListener(this);
    }
}
