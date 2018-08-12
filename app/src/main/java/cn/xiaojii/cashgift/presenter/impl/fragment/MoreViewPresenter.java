package cn.xiaojii.cashgift.presenter.impl.fragment;

import android.support.v4.app.Fragment;

import cn.xiaojii.cashgift.bean.global.ConstantsBean;
import cn.xiaojii.cashgift.bean.global.PasswordBean;
import cn.xiaojii.cashgift.interactor.inter.fragment.IMoreInteractor;
import cn.xiaojii.cashgift.presenter.inter.fragment.IMoreViewPresenter;
import cn.xiaojii.cashgift.util.io.SharedPreferencesUtil;
import cn.xiaojii.cashgift.view.inter.fragment.IMoreView;

/**
 * @author dmrfcoder
 * @date 2018/8/10
 */

public class MoreViewPresenter implements IMoreViewPresenter, IMoreInteractor.UpdatePasswordBeanListener {
    private IMoreView iMoreView;
    private IMoreInteractor iMoreInteractor;

    public MoreViewPresenter(IMoreView iMoreView, IMoreInteractor iMoreInteractor) {
        this.iMoreView = iMoreView;
        this.iMoreInteractor = iMoreInteractor;
        updatePasswordBean();
    }

    @Override
    public void updatePasswordBean() {
        SharedPreferencesUtil sph = SharedPreferencesUtil.getInstance(((Fragment) iMoreView).getActivity().getApplicationContext());
        PasswordBean passwordBean = sph.getPasswordBean(ConstantsBean.APP_CONFIG_GESTURE_SP_KEY);
        iMoreInteractor.updatePasswordBean(passwordBean, this);
    }

    @Override
    public void setFingerFlag(boolean fingerFlag) {

    }

    @Override
    public void setGestureFlag(boolean gestureFlag, String pass) {

    }

    @Override
    public void gestureOnToOff() {

    }

    @Override
    public void gestureOffToOn() {

    }

    @Override
    public void fingerOnToOff() {

    }

    @Override
    public void fingerOffToOn() {

    }

    @Override
    public void onUpdatePasswordBeanError() {
        //没有从SP中读出可用的配置信息
    }

    @Override
    public void onUpdatePasswordBeanSuccess(PasswordBean passwordBean) {
        //配置记录
    }
}
