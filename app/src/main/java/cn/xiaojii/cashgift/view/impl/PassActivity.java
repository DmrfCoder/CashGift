package cn.xiaojii.cashgift.view.impl;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


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


        findViewById(R.id.id_set).setOnClickListener(this);
        findViewById(R.id.id_yanzheng).setOnClickListener(this);

        tvHint = findViewById(R.id.id_txt);
        fingerprintManagerCompat.authenticate(null, 0, cancellationSignal, new FingerDiscentListener(), null);


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
}
