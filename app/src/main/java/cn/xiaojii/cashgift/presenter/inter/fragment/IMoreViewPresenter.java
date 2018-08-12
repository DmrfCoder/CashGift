package cn.xiaojii.cashgift.presenter.inter.fragment;

/**
 * @author dmrfcoder
 * @date 2018/8/10
 */

public interface IMoreViewPresenter {

    /**
     * 更新PasswordBean
     */
    void updatePasswordBean();

    /**
     * 设置finger是否使用
     *
     * @param fingerFlag
     */
    void setFingerFlag(boolean fingerFlag);

    /**
     * 设置图案是否使用
     *
     * @param gestureFlag
     * @param pass
     */
    void setGestureFlag(boolean gestureFlag, String pass);

    /**
     * 手势开关从开启到关闭
     */
    void gestureOnToOff();

    /**
     * 手势开关从关闭到开启
     */
    void gestureOffToOn();

    /**
     * 指纹开关从开启到关闭
     */
    void fingerOnToOff();

    /**
     * 指纹开关从关闭到开启
     */
    void fingerOffToOn();


}
