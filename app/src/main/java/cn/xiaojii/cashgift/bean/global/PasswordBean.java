package cn.xiaojii.cashgift.bean.global;

/**
 * @author dmrfcoder
 * @date 2018/8/10
 */

public class PasswordBean {
    private String gesturePassword;
    private boolean flagGesture;
    private boolean flagFinger;

    public String getGesturePassword() {
        return gesturePassword;
    }

    public void setGesturePassword(String gesturePassword) {
        this.gesturePassword = gesturePassword;
    }

    public boolean isFlagGesture() {
        return flagGesture;
    }

    public void setFlagGesture(boolean flagGesture) {
        this.flagGesture = flagGesture;
    }

    public boolean isFlagFinger() {
        return flagFinger;
    }

    public void setFlagFinger(boolean flagFinger) {
        this.flagFinger = flagFinger;
    }
}
