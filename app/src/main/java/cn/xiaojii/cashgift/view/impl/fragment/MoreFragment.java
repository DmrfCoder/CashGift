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


        fingerSwitch = view.findViewById(R.id.id_more_finger_switch);
        graphWitch = view.findViewById(R.id.id_more_graph_switch);
        fingerSwitch.setOnCheckedChangeListener(this);
        graphWitch.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.id_more_finger_switch:
                SharedPreferencesUtil sph = SharedPreferencesUtil.getInstance(getActivity().getApplicationContext());

                if (fingerSwitch.isChecked()) {
                    //从未开启到开启，需要验证指纹
                    int a = 0;
                } else {
                    //从开启到未开启，也需验证指纹
                    int bs = 0;
                }
                break;
            case R.id.id_more_graph_switch:
                if (graphWitch.isChecked()) {
//从未开启到开启，如果之前未设置手势图像，需要引导用户设置手势，若之前已设置，则直接打开开关
                } else {
//从开启到未开启，需验证手势
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
