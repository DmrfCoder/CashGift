package cn.xiaojii.cashgift.bean.gesture;

/**
 * Created by Wjyyy on 2018/8/9.
 */


public class PointBean {
    public static int STATE_NORMAL = 0;
    public static int STATE_CHECK = 1;
    public static int STATE_CHECK_ERROR = 2;

    public float x;
    public float y;
    public int state = 0;
    public int index = 0;

    public PointBean(float x, float y, int value) {
        this.x = x;
        this.y = y;
        index = value;
    }

    public int getColNum() {
        return (index - 1) % 3;
    }

    public int getRowNum() {
        return (index - 1) / 3;
    }

}