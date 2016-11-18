package com.zzb.drawingtest.canvas.drawcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ZZB on 2016/11/17.
 */

public class CircleView extends View {
    private int mBorderWidth1, mBorderWidth2;
    private RectF mBorderRect = new RectF();
    private final Paint mBorderPaint1 = new Paint();
    private final Paint mBorderPaint2 = new Paint();
    private int mBorderColor1, mBorderColor2;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBorderRect.set(0, 0, getWidth(), getHeight());
        initBorderPaint(mBorderPaint1);
        initBorderPaint(mBorderPaint2);
    }

    private void initBorderPaint(Paint borderPaint) {
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(Color.BLACK);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        canvas.save();
        drawCircle1(canvas, centerX, centerY);
        drawCircle2(canvas, centerX, centerY);
        canvas.restore();
    }

    private void drawCircle2(Canvas canvas, int centerX, int centerY) {
        canvas.drawCircle(centerX, centerY, getBorderRadius((float) (mBorderWidth2 + mBorderWidth1*2)), mBorderPaint2);
    }

    private void drawCircle1(Canvas canvas, int centerX, int centerY) {
        canvas.drawCircle(centerX, centerY, getBorderRadius(mBorderWidth1), mBorderPaint1);
    }

    private float getBorderRadius(float borderWidth) {
        return Math.min((getHeight() - borderWidth) / 2, (getWidth() - borderWidth) / 2);
    }



    public void setBorder1(int width, int borderColor) {
        mBorderWidth1 = width;
        mBorderPaint1.setColor(borderColor);
        mBorderPaint1.setStrokeWidth(width);
    }
    public void setBorder2(int width, int borderColor) {
        mBorderWidth2 = width;
        mBorderPaint2.setColor(borderColor);
        mBorderPaint2.setStrokeWidth(width);
    }
    public void notifyUIChanged(){
        invalidate();
    }
}
