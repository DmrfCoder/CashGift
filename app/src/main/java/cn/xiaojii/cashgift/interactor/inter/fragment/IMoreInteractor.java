package cn.xiaojii.cashgift.interactor.inter.fragment;

import cn.xiaojii.cashgift.bean.global.PasswordBean;

/**
 * @author dmrfcoder
 * @date 2018/8/10
 */

public interface IMoreInteractor {

    interface UpdatePasswordBeanListener {
        void onUpdatePasswordBeanError();

        void onUpdatePasswordBeanSuccess(PasswordBean passwordBean);
    }

    void updatePasswordBean(PasswordBean passwordBean, UpdatePasswordBeanListener updatePasswordBeanListener);
}
