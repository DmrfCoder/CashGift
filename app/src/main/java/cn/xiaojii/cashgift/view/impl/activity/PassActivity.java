package cn.xiaojii.cashgift.view.impl.activity;

import android.app.Activity;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.bean.global.ConstantsBean;
import cn.xiaojii.cashgift.util.gesture.Md5Util;
import cn.xiaojii.cashgift.util.io.SharedPreferencesUtil;
import cn.xiaojii.cashgift.view.impl.fragment.GestureFragment;
import cn.xiaojii.cashgift.view.inter.weight.IGesturePassWordView;
import cn.xiaojii.cashgift.view.inter.activity.IPassView;
import cn.xiaojii.cashgift.view.impl.weight.GesturePassWordView;

/**
 * @author dmrfcoder
 * @date 2018/8/10
 */

public class PassActivity extends FragmentActivity implements GestureFragment.GestureListener,IPassView, View.OnClickListener {
    private GesturePassWordView gesturePassWordView;
    private Context context;
    private FingerprintManagerCompat fingerprintManagerCompat;
    //取消的对象
    private CancellationSignal cancellationSignal;
    private boolean fingerFlag = true;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        initActivity();
    }


    @Override
    public void initActivity() {


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = GestureFragment.getInstance(ConstantsBean.GESTURE_TYPE.GESTURE_SET);
        fragmentTransaction.replace(R.id.id_pass_gesture_fragment, fragment, fragment.getClass().getName());

        fragmentTransaction.commit();


        fingerprintManagerCompat = FingerprintManagerCompat.from(this);
        cancellationSignal = new CancellationSignal();
        context = getApplicationContext();


        if (fingerFlag) {
            fingerprintManagerCompat.authenticate(null, 0, cancellationSignal, new FingerDiscentListener(), null);
        } else {
            startMainActivity();
        }


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            default:
                break;
        }
    }

    @Override
    public void onSetError() {

    }

    @Override
    public void onSetSuccess(String password) {

    }

    @Override
    public void onVerifyError() {

    }

    @Override
    public void onVerifySuccess() {

    }


    private class FingerDiscentListener extends FingerprintManagerCompat.AuthenticationCallback {
        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            super.onAuthenticationError(errMsgId, errString);
            if (errMsgId == 5) {
                //取消识别

            } else if (errMsgId == 7) {
                //tvHint.setText("操作过于频繁，请稍后重试");
                if (cancellationSignal != null) {
                    cancellationSignal.cancel();
                    cancellationSignal = null;
                }
            }
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);

            startMainActivity();
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            //tvHint.setText("指纹识别失败");
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            super.onAuthenticationHelp(helpMsgId, helpString);
        }
    }


    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
