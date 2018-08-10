package cn.xiaojii.cashgift.view.impl.activity;

import android.app.Activity;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.util.Log;
import android.view.View;
import android.widget.Toast;



import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.util.gesture.Md5Util;
import cn.xiaojii.cashgift.util.io.SharedPreferencesUtil;
import cn.xiaojii.cashgift.view.inter.weight.IGesturePassWordView;
import cn.xiaojii.cashgift.view.inter.activity.IPassView;
import cn.xiaojii.cashgift.view.impl.weight.GesturePassWordView;

/**
 * @author dmrfcoder
 * @date 2018/8/10
 */

public class PassActivity extends Activity implements IPassView, View.OnClickListener {
    private GesturePassWordView gesturePassWordView;
    private Context context;
    private FingerprintManagerCompat fingerprintManagerCompat;
    //取消的对象
    private CancellationSignal cancellationSignal;
    private boolean fingerFlag = true;

    IGesturePassWordView.OnCompleteListener onCompleteListener=new IGesturePassWordView.OnCompleteListener() {
        @Override
        public void onComplete(String mPassword) {
            SharedPreferencesUtil sph = SharedPreferencesUtil.getInstance(getApplicationContext());
            String pwd = sph.getString("password", "");
            Md5Util md5 = new Md5Util();
            boolean passed = false;
            if (pwd.length() == 0) {
                sph.putString("password", md5.toMd5(mPassword, ""));
                Toast.makeText(context, context.getString(R.string.pwd_setted), Toast.LENGTH_LONG).show();
               startMainActivity();
                return;
            } else {
                String encodedPwd = md5.toMd5(mPassword, "");
                if (encodedPwd.equals(pwd)) {
                    passed = true;

                } else {
                    gesturePassWordView.markError();
                }
            }

            if (passed) {
                startMainActivity();
                Log.d("hcj", "password is correct!");
                Toast.makeText(context, context.getString(R.string.pwd_correct), Toast.LENGTH_LONG).show();
//					finish();
            }
        }
    };


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
        context = getApplicationContext();
        gesturePassWordView = (GesturePassWordView) this.findViewById(R.id.id_pass_gesture);
        gesturePassWordView.setOnCompleteListener(onCompleteListener);



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
