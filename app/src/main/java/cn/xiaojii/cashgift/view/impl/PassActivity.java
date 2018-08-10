package cn.xiaojii.cashgift.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import org.apache.log4j.chainsaw.Main;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.view.IPassView;

/**
 * @author dmrfcoder
 * @date 2018/8/10
 */

public class PassActivity extends Activity implements IPassView, View.OnClickListener {

    private TextView tvHint;
    private FingerprintManagerCompat fingerprintManagerCompat;
    //取消的对象
    private CancellationSignal cancellationSignal;
    private boolean fingerFlag = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        initActivity();
    }


    @Override
    public void initActivity() {

        fingerprintManagerCompat = FingerprintManagerCompat.from(this);
        cancellationSignal = new CancellationSignal();



        tvHint = findViewById(R.id.id_txt);
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


    private class FingerDiscentListener extends FingerprintManagerCompat.AuthenticationCallback {
        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            super.onAuthenticationError(errMsgId, errString);
            if (errMsgId == 5) {
                //取消识别

            } else if (errMsgId == 7) {
                tvHint.setText("操作过于频繁，请稍后重试");
                if (cancellationSignal != null) {
                    cancellationSignal.cancel();
                    cancellationSignal = null;
                }
            }
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            tvHint.setText("指纹识别成功");
            startMainActivity();
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            tvHint.setText("指纹识别失败");
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
