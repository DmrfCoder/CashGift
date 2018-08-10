package cn.xiaojii.cashgift.view.inter.weight;

import android.graphics.Canvas;
import android.graphics.Paint;


import cn.xiaojii.cashgift.bean.gesture.PointBean;

/**
 * Created by Wjyyy on 2018/8/10.
 */

public interface IGesturePassWordView {

    /**
     * 绘制ToCanvas
     *
     * @param canvas
     */
    void drawToCanvas(Canvas canvas);

    /**
     * 绘制直线
     *
     * @param start
     * @param end
     * @param canvas
     * @param paint
     */
    void drawLine(PointBean start, PointBean end, Canvas canvas, Paint paint);

    /**
     * 绘制箭头
     *
     * @param canvas
     * @param paint
     * @param start
     * @param end
     * @param arrowHeight
     * @param angle
     */
    void drawArrow(Canvas canvas, Paint paint, PointBean start, PointBean end, float arrowHeight, int angle);

    /**
     * 初始化Cache
     */
    void initCache();

    /**
     * 初始化PaintBeans
     */
    void initPaintBeans();

    /**
     * 获取ArrayIndex
     *
     * @param index
     * @return
     */
    int[] getArrayIndex(int index);

    /**
     * SelectPoint
     *
     * @param x
     * @param y
     * @return
     */
    PointBean checkSelectPoint(float x, float y);

    /**
     * 复位
     */
    void reset();

    /**
     * crossPointBean
     *
     * @param p
     * @return
     */
    int crossPointBean(PointBean p);

    /**
     * 添加点
     *
     * @param point
     */
    void addPointBean(PointBean point);

    /**
     * toPointString
     *
     * @return
     */
    String toPointString();

    /**
     * 发生了错误
     */
    void error();

    /**
     * enableTouch
     */
    void enableTouch();

    /**
     * disableTouch
     */
    void disableTouch();

    /**
     * 清除密码
     */
    void clearPassword();

    /**
     * 清除妈妈
     *
     * @param time
     */
    void clearPassword(final long time);

    /**
     * 设置监听器
     *
     * @param mCompleteListener
     */
    void setOnCompleteListener(OnCompleteListener mCompleteListener);

    interface OnCompleteListener {

        /**
         * 绘制完成，返回密码
         *
         * @param password
         */
        public void onComplete(String password);
    }

}
