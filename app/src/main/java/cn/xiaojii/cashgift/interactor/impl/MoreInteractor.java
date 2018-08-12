package cn.xiaojii.cashgift.interactor.impl;

import cn.xiaojii.cashgift.bean.global.PasswordBean;
import cn.xiaojii.cashgift.interactor.inter.fragment.IMoreInteractor;
import cn.xiaojii.cashgift.util.io.SharedPreferencesUtil;

/**
 * @author dmrfcoder
 * @date 2018/8/10
 */

public class MoreInteractor implements IMoreInteractor {
    private PasswordBean passwordBean;


    @Override
    public void Destroy(DestroyListener destroyListener) {
        if (passwordBean==null){
            destroyListener.onDestroyError();
        }else {
            destroyListener.onDestroySuccess(passwordBean);
        }

    }

    @Override
    public void updatePasswordBean(PasswordBean passwordBean, UpdatePasswordBeanListener updatePasswordBeanListener) {
        if (passwordBean==null){
            updatePasswordBeanListener.onUpdatePasswordBeanError();
        }else {
            this.passwordBean=passwordBean;
            updatePasswordBeanListener.onUpdatePasswordBeanSuccess(passwordBean);
        }
    }
}
