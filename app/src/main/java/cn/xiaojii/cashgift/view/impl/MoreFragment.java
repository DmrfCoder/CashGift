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


    private FingerprintManagerCompat fingerprintManagerCompat;
    //取消的对象
    private CancellationSignal cancellationSignal;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, null);
        initFragment(view);
        return view;
    }

    @Override
    public void initFragment(View view) {
        fingerprintManagerCompat = FingerprintManagerCompat.from(getContext());
        cancellationSignal = new CancellationSignal();

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


        fingerprintManagerCompat.authenticate(null, 0, cancellationSignal, new FingerDiscentListener(), null);


    }


    private class FingerDiscentListener extends FingerprintManagerCompat.AuthenticationCallback {
        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            super.onAuthenticationError(errMsgId, errString);
            if (errMsgId == 5) {//取消识别

            } else if (errMsgId == 7) {
                Toast.makeText(getActivity(), "操作过于频繁，请稍后重试", Toast.LENGTH_SHORT).show();
                if (cancellationSignal != null) {
                    cancellationSignal.cancel();
                    cancellationSignal = null;
                }
            }
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            Toast.makeText(getActivity(), "指纹识别成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            Toast.makeText(getActivity(), "指纹识别失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            super.onAuthenticationHelp(helpMsgId, helpString);
        }
    }
}
