package cn.xiaojii.cashgift.view;

import android.graphics.Canvas;
import android.graphics.Paint;


import cn.xiaojii.cashgift.bean.PointBean;

/**
 * Created by Wjyyy on 2018/8/10.
 */

public interface IGesturePassWordView {

    void drawToCanvas(Canvas canvas);
    void drawLine(PointBean start, PointBean end, Canvas canvas, Paint paint);
    void drawArrow(Canvas canvas, Paint paint, PointBean start, PointBean end, float arrowHeight, int angle);
    void initCache();
    void initPaints();

    /**
     * @param index
     * @return
     */
    int[] getArrayIndex(int index);
    PointBean checkSelectPoint(float x, float y);
    void reset();
    int crossPoint(PointBean p);
    void addPointBean(PointBean point);
    String toPointString();
    void error();
    void enableTouch();

    void disableTouch();

    void clearPassword();

    void clearPassword(final long time);
    void setOnCompleteListener(OnCompleteListener mCompleteListener);
    public interface OnCompleteListener {

        public void onComplete(String password);
    }

    }
