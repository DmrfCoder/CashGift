package cn.xiaojii.cashgift.interactor.inter.fragment;

import cn.xiaojii.cashgift.bean.global.PasswordBean;

/**
 * @author dmrfcoder
 * @date 2018/8/10
 */

public interface IMoreInteractor {


    void Destroy(DestroyListener destroyListener);
    interface DestroyListener{
        void onDestroyError();
        void onDestroySuccess(PasswordBean passwordBean);
    }

    interface UpdatePasswordBeanListener {
        void onUpdatePasswordBeanError();

        void onUpdatePasswordBeanSuccess(PasswordBean passwordBean);
    }

    void updatePasswordBean(PasswordBean passwordBean, UpdatePasswordBeanListener updatePasswordBeanListener);
}
