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
public class MoreFragment extends Fragment implements IMoreView, IBaseFragmentView, View.OnClickListener {




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



}
