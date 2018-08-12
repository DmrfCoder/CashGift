package cn.xiaojii.cashgift.view.impl.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Objects;

import cn.xiaojii.cashgift.R;
import cn.xiaojii.cashgift.bean.global.ConstantsBean;
import cn.xiaojii.cashgift.util.gesture.Md5Util;
import cn.xiaojii.cashgift.util.io.SharedPreferencesUtil;
import cn.xiaojii.cashgift.view.impl.base.BaseFragment;
import cn.xiaojii.cashgift.view.impl.weight.GesturePassWordView;
import cn.xiaojii.cashgift.view.inter.weight.IGesturePassWordView;

/**
 * @author dmrfcoder
 * @date 2018/8/11
 */

public class GestureFragment extends BaseFragment {


    public interface GestureListener{
        void onSetError();
        void onSetSuccess(String password);
        void onVerifyError();
        void onVerifySuccess();
    }

    private GestureListener gestureListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context!=null){
            gestureListener= (GestureListener) context;
        }
    }

    private GesturePassWordView gesturePassWordView;
    private Context context;
    private ConstantsBean.GESTURE_TYPE gestureTpye;
    private boolean isFirstSet = true;
    private String firstSetPwd = "";


    IGesturePassWordView.OnCompleteListener onCompleteListener = new IGesturePassWordView.OnCompleteListener() {
        @Override
        public void onComplete(String password) {
            Md5Util md5 = new Md5Util();
            String encodedPwd = md5.toMd5(password, "");
            SharedPreferencesUtil sph = SharedPreferencesUtil.getInstance(context);
            String pwd = sph.getString(ConstantsBean.GESTURE_KEY, "");

            boolean passed = false;


            switch (gestureTpye) {
                case GESTURE_RESET:
                case GESTURE_SET:
                    if (isFirstSet) {
                        isFirstSet = false;
                        firstSetPwd = encodedPwd;

                    } else if (firstSetPwd.equals(encodedPwd)) {
                        gestureListener.onSetSuccess(encodedPwd);
                        Toast.makeText(context, "手势设置成功", Toast.LENGTH_LONG).show();
                    } else {
                        gestureListener.onSetError();
                        Toast.makeText(context, "两次手势不一致，请重新设置", Toast.LENGTH_LONG).show();
                    }

                    break;

                case GESTURE_VERIFICATION:
                    if (pwd.equals(encodedPwd)) {
                        Toast.makeText(context, "验证成功", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }
            gesturePassWordView.reset();

        }
    };

    public static Fragment getInstance(ConstantsBean.GESTURE_TYPE gestureTYPE) {
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantsBean.GESTURE_KEY, gestureTYPE.getIndex());
        GestureFragment gestureFragment = new GestureFragment();
        gestureFragment.setArguments(bundle);
        return gestureFragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gesture, null);
        initFragment(view);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void initFragment(View view) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            gestureTpye = ConstantsBean.GESTURE_TYPE.getItemByIndex(bundle.getInt(ConstantsBean.GESTURE_KEY));
        }

        context = Objects.requireNonNull(getActivity()).getApplicationContext();
        gesturePassWordView = view.findViewById(R.id.id_gesture_gesturepasswordview);
        gesturePassWordView.setOnCompleteListener(onCompleteListener);


    }
}
