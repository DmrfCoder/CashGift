package cn.xiaojii.cashgift.view.impl.weight;

/**
 * Created by Wjyyy on 2018/8/9.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.xiaojii.cashgift.bean.gesture.PointBean;
import cn.xiaojii.cashgift.util.gesture.MathUtil;
import cn.xiaojii.cashgift.view.inter.weight.IGesturePassWordView;

/**
 * @author dmrfcoder
 *
 */
public class GesturePassWordView extends View implements IGesturePassWordView {

    private boolean isCache = false;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private PointBean[][] mpoints = new PointBean[3][3];
    private float dotRadius = 0;
    private List<PointBean> sPointBeans = new ArrayList<PointBean>();
    private boolean checking = false;
    private long clearTime = 1000;
    private int pwdMaxLen = 9;
    private int pwdMinLen = 4;
    private boolean isTouch = true;

    private Paint arrowPaint;
    private Paint linePaint;
    private Paint selectedPaint;
    private Paint errorPaint;
    private Paint normalPaint;
    private int errorColor = 0xffea0945;
    private int selectedColor = 0xff0596f6;
    private int outterSelectedColor = 0xff8cbad8;
    private int outterErrorColor = 0xff901032;
    private int dotColor = 0xffd9d9d9;
    private int outterDotColor = 0xff929292;
    private boolean movingNoPoint = false;
    private float moveingX, moveingY;
    private Timer timer = new Timer();

    private TimerTask task = null;
    private OnCompleteListener mCompleteListener;

    public GesturePassWordView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GesturePassWordView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GesturePassWordView(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (!isCache) {
            initCache();
        }
        drawToCanvas(canvas);
    }

    @Override
    public void drawToCanvas(Canvas canvas) {
        boolean inErrorState = false;
        for (PointBean[] pointBeans : mpoints) {
            for (PointBean pointBean : pointBeans) {
                if (pointBean.state == PointBean.STATE_CHECK) {
                    selectedPaint.setColor(outterSelectedColor);
                    canvas.drawCircle(pointBean.x, pointBean.y, dotRadius, selectedPaint);
                    selectedPaint.setColor(selectedColor);
                    canvas.drawCircle(pointBean.x, pointBean.y, dotRadius / 4, selectedPaint);
                } else if (pointBean.state == PointBean.STATE_CHECK_ERROR) {
                    inErrorState = true;
                    errorPaint.setColor(outterErrorColor);
                    canvas.drawCircle(pointBean.x, pointBean.y, dotRadius, errorPaint);
                    errorPaint.setColor(errorColor);
                    canvas.drawCircle(pointBean.x, pointBean.y, dotRadius / 4, errorPaint);
                } else {
                    normalPaint.setColor(dotColor);
                    canvas.drawCircle(pointBean.x, pointBean.y, dotRadius, normalPaint);
                    normalPaint.setColor(outterDotColor);
                    canvas.drawCircle(pointBean.x, pointBean.y, dotRadius / 4, normalPaint);
                }
            }
        }

        if (inErrorState) {
            arrowPaint.setColor(errorColor);
            linePaint.setColor(errorColor);
        } else {
            arrowPaint.setColor(selectedColor);
            linePaint.setColor(selectedColor);
        }

        if (sPointBeans.size() > 0) {
            int tmpAlpha = mPaint.getAlpha();
            PointBean tp = sPointBeans.get(0);
            for (int i = 1; i < sPointBeans.size(); i++) {
                PointBean p = sPointBeans.get(i);
                drawLine(tp, p, canvas, linePaint);
                drawArrow(canvas, arrowPaint, tp, p, dotRadius / 4, 38);
                tp = p;
            }
            if (this.movingNoPoint) {
                drawLine(tp, new PointBean(moveingX, moveingY, -1), canvas, linePaint);
            }
            mPaint.setAlpha(tmpAlpha);
        }

    }

    @Override
    public void drawLine(PointBean start, PointBean end, Canvas canvas, Paint paint) {
        double d = MathUtil.distance(start.x, start.y, end.x, end.y);
        float rx = (float) ((end.x - start.x) * dotRadius / 4 / d);
        float ry = (float) ((end.y - start.y) * dotRadius / 4 / d);
        canvas.drawLine(start.x + rx, start.y + ry, end.x - rx, end.y - ry, paint);
    }

    @Override
    public void drawArrow(Canvas canvas, Paint paint, PointBean start, PointBean end, float arrowHeight, int angle) {
        double d = MathUtil.distance(start.x, start.y, end.x, end.y);
        float sinB = (float) ((end.x - start.x) / d);
        float cosB = (float) ((end.y - start.y) / d);
        float tanA = (float) Math.tan(Math.toRadians(angle));
        float h = (float) (d - arrowHeight - dotRadius * 1.1);
        float l = arrowHeight * tanA;
        float a = l * sinB;
        float b = l * cosB;
        float x0 = h * sinB;
        float y0 = h * cosB;
        float x1 = start.x + (h + arrowHeight) * sinB;
        float y1 = start.y + (h + arrowHeight) * cosB;
        float x2 = start.x + x0 - b;
        float y2 = start.y + y0 + a;
        float x3 = start.x + x0 + b;
        float y3 = start.y + y0 - a;
        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.close();
        canvas.drawPath(path, paint);
    }

    @Override
    public void initCache() {
        float width = this.getWidth();
        float height = this.getHeight();
        float x = 0;
        float y = 0;

        if (width > height) {
            x = (width - height) / 2;
            width = height;
        } else {
            y = (height - width) / 2;
            height = width;
        }

        int leftPadding = 15;
        float dotPadding = width / 3 - leftPadding;
        float middleX = width / 2;
        float middleY = height / 2;

        mpoints[0][0] = new PointBean(x + middleX - dotPadding, y + middleY - dotPadding, 1);
        mpoints[0][1] = new PointBean(x + middleX, y + middleY - dotPadding, 2);
        mpoints[0][2] = new PointBean(x + middleX + dotPadding, y + middleY - dotPadding, 3);
        mpoints[1][0] = new PointBean(x + middleX - dotPadding, y + middleY, 4);
        mpoints[1][1] = new PointBean(x + middleX, y + middleY, 5);
        mpoints[1][2] = new PointBean(x + middleX + dotPadding, y + middleY, 6);
        mpoints[2][0] = new PointBean(x + middleX - dotPadding, y + middleY + dotPadding, 7);
        mpoints[2][1] = new PointBean(x + middleX, y + middleY + dotPadding, 8);
        mpoints[2][2] = new PointBean(x + middleX + dotPadding, y + middleY + dotPadding, 9);

        dotRadius = width / 10;
        isCache = true;

        initPaintBeans();
    }

    @Override
    public void initPaintBeans() {
        arrowPaint = new Paint();
        arrowPaint.setColor(selectedColor);
        arrowPaint.setStyle(Style.FILL);
        arrowPaint.setAntiAlias(true);

        linePaint = new Paint();
        linePaint.setColor(selectedColor);
        linePaint.setStyle(Style.STROKE);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(dotRadius / 9);

        selectedPaint = new Paint();
        selectedPaint.setStyle(Style.STROKE);
        selectedPaint.setAntiAlias(true);
        selectedPaint.setStrokeWidth(dotRadius / 6);

        errorPaint = new Paint();
        errorPaint.setStyle(Style.STROKE);
        errorPaint.setAntiAlias(true);
        errorPaint.setStrokeWidth(dotRadius / 6);

        normalPaint = new Paint();
        normalPaint.setStyle(Style.STROKE);
        normalPaint.setAntiAlias(true);
        normalPaint.setStrokeWidth(dotRadius / 9);
    }

    @Override
    public int[] getArrayIndex(int index) {
        int[] ai = new int[2];
        ai[0] = index / 3;
        ai[1] = index % 3;
        return ai;
    }


    @Override
    public PointBean checkSelectPoint(float x, float y) {
        for (PointBean[] mpoint : mpoints) {
            for (PointBean p : mpoint) {
                if (MathUtil.checkInRound(p.x, p.y, dotRadius, (int) x, (int) y)) {
                    return p;
                }
            }
        }
        return null;
    }


    @Override
    public void reset() {
        for (PointBean p : sPointBeans) {
            p.state = PointBean.STATE_NORMAL;
        }
        sPointBeans.clear();
        this.enableTouch();
    }


    @Override
    public int crossPointBean(PointBean p) {
        // reset
        if (sPointBeans.contains(p)) {
            if (sPointBeans.size() > 2) {
                if (sPointBeans.get(sPointBeans.size() - 1).index != p.index) {
                    return 2;
                }
            }
            return 1;
        } else {
            return 0;
        }
    }


    @Override
    public void addPointBean(PointBean point) {
        if (sPointBeans.size() > 0) {
            PointBean lastPoint = sPointBeans.get(sPointBeans.size() - 1);
            int dx = Math.abs(lastPoint.getColNum() - point.getColNum());
            int dy = Math.abs(lastPoint.getRowNum() - point.getRowNum());
            boolean ifFlag = (dx > 1 || dy > 1) && (dx == 0 || dy == 0 || dx == dy);
            if (ifFlag) {
                int middleIndex = (point.index + lastPoint.index) / 2 - 1;
                PointBean middlePoint = mpoints[middleIndex / 3][middleIndex % 3];
                if (middlePoint.state != PointBean.STATE_CHECK) {
                    middlePoint.state = PointBean.STATE_CHECK;
                    sPointBeans.add(middlePoint);
                }
            }
        }
        this.sPointBeans.add(point);
    }


    @Override
    public String toPointString() {
        if (sPointBeans.size() >= pwdMinLen && sPointBeans.size() <= pwdMaxLen) {
            StringBuffer sf = new StringBuffer();
            for (PointBean p : sPointBeans) {
                sf.append(p.index);
            }
            return sf.toString();
        } else {
            return "";
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isTouch) {
            return false;
        }

        movingNoPoint = false;

        float ex = event.getX();
        float ey = event.getY();
        boolean isFinish = false;
        PointBean pointBean = null;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (task != null) {
                    task.cancel();
                    task = null;
                }
                reset();
                pointBean = checkSelectPoint(ex, ey);
                if (pointBean != null) {
                    checking = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (checking) {
                    pointBean = checkSelectPoint(ex, ey);
                    if (pointBean == null) {
                        movingNoPoint = true;
                        moveingX = ex;
                        moveingY = ey;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                pointBean = checkSelectPoint(ex, ey);
                checking = false;
                isFinish = true;
                break;
            default:
                break;
        }
        if (!isFinish && checking && pointBean != null) {

            int rk = crossPointBean(pointBean);
            if (rk == 2) {
                movingNoPoint = true;
                moveingX = ex;
                moveingY = ey;
            } else if (rk == 0) {
                pointBean.state = PointBean.STATE_CHECK;
                addPointBean(pointBean);
            }
        }


        if (isFinish) {
            if (this.sPointBeans.size() == 1) {
                this.reset();
            } else if (sPointBeans.size() < pwdMinLen || sPointBeans.size() > pwdMaxLen) {
                error();
                clearPassword();
                Toast.makeText(this.getContext(), "password too short or too long, cannot be saved!",
                        Toast.LENGTH_SHORT).show();
            } else if (mCompleteListener != null) {
                this.disableTouch();
                mCompleteListener.onComplete(toPointString());
            }
        }
        this.postInvalidate();
        return true;
    }


    @Override
    public void error() {
        for (PointBean p : sPointBeans) {
            p.state = PointBean.STATE_CHECK_ERROR;
        }
    }

    public void markError() {
        markError(clearTime);
    }

    public void markError(final long time) {
        for (PointBean p : sPointBeans) {
            p.state = PointBean.STATE_CHECK_ERROR;
        }
        this.clearPassword(time);
    }

    @Override
    public void enableTouch() {
        isTouch = true;
    }

    @Override
    public void disableTouch() {
        isTouch = false;
    }

    @Override
    public void clearPassword() {
        clearPassword(clearTime);
    }

    @Override
    public void clearPassword(final long time) {
        if (time > 1) {
            if (task != null) {
                task.cancel();
            }
            postInvalidate();
            task = new TimerTask() {
                @Override
                public void run() {
                    reset();
                    postInvalidate();
                }
            };
            timer.schedule(task, time);
        } else {
            reset();
            postInvalidate();
        }
    }


    @Override
    public void setOnCompleteListener(OnCompleteListener mCompleteListener) {
        this.mCompleteListener = mCompleteListener;

    }


}