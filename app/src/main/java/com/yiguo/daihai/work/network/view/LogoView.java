package com.yiguo.daihai.work.network.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.View;

public class LogoView extends View implements Runnable {

    private Paint paint;

    private boolean isRunning = false;

    private float radius = 50;

    private int moveDistance = 30;

    int percent = 0;

    private static final float TOTAL_TIME = 60f;

    private int colorRed = Color.parseColor("#E8340C");

    private int colorYellow = Color.parseColor("#FFE100");

    private int colorGreen = Color.parseColor("#C3D600");

    private int colorOrange = Color.parseColor("#F18D00");

    private PointF point = new PointF();

    private PointF pointF = new PointF();

    private RectF rectLeafLeft = new RectF();

    private RectF rectLeafRight = new RectF();

    public LogoView(Context context) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        moveDistance = (int) (TOTAL_TIME / 2 - 2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        radius = Math.min(getWidth(), getHeight()) / 20;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (percent > moveDistance * 2 + TOTAL_TIME / 2) {
            float angle = (percent - (moveDistance * 2 + TOTAL_TIME / 2)) / (TOTAL_TIME * 1.5f);
            canvas.rotate(475 * angle + 90, getWidth() / 2, getHeight() / 2);
            canvas.scale(Math.min(Math.abs(angle - 1), 1), Math.min(Math.abs(angle - 1), 1), getWidth() / 2, getHeight() / 2);
        } else {
            canvas.rotate(Math.min(90 * Math.max(percent - moveDistance, 0) / TOTAL_TIME, 90), getWidth() / 2, getHeight() / 2);
        }

        getCirclePath();

        //左侧画一个圆
        drawCircle(canvas, point.x - radius, point.y, radius, colorRed, paint);

        //右侧画一个圆
        drawCircle(canvas, getWidth() - point.x + radius, point.y, radius, colorYellow, paint);

        if (percent > TOTAL_TIME / 2 + moveDistance) {
            canvas.save();
            canvas.rotate(-Math.min(90 * Math.max(percent - moveDistance, 0) / TOTAL_TIME, 90), getWidth() / 2, getHeight() / 2);
            getLeafPath();
            rectLeafLeft.set(pointF.x - radius, pointF.y - radius, pointF.x, pointF.y);
            rectLeafRight.set(getWidth() - pointF.x, pointF.y, getWidth() - pointF.x + radius, pointF.y + radius);
            drawLeaf(canvas, pointF.x - radius, pointF.y, radius, rectLeafLeft, colorGreen, paint);
            drawLeaf(canvas, getWidth() - pointF.x + radius, pointF.y, radius, rectLeafRight, colorOrange, paint);
            canvas.restore();
        }


        super.onDraw(canvas);
    }

    private void getLeafPath() {
        int p = percent - (int) TOTAL_TIME / 2 - moveDistance;
        if (p <= moveDistance) {
            pointF.x = getWidth() * p / TOTAL_TIME;
            pointF.y = getHeight() / 2;
        } else {
            pointF.x = getWidth() * moveDistance / TOTAL_TIME;
            pointF.y = getHeight() / 2;
        }
    }

    private void getCirclePath() {
        if (percent <= moveDistance) {
            point.x = getWidth() * percent / TOTAL_TIME;
            point.y = getHeight() / 2;
        } else {
            point.x = getWidth() * moveDistance / TOTAL_TIME;
            point.y = getHeight() / 2;
        }
    }

    public void start() {
        if (percent == 2 * (moveDistance + TOTAL_TIME)) {
            percent = 0;
        }
        isRunning = true;
        new Thread(this).start();
    }

    public void stop() {
        isRunning = false;
    }

    public boolean toggle() {
        if (isRunning) {
            stop();
            return true;
        } else {
            start();
            return false;
        }
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (View.VISIBLE != visibility) {
            stop();
        }
    }

    private void drawCircle(Canvas canvas, float x, float y, float radius, int color, Paint paint) {
        paint.setColor(color);
        canvas.save();
        canvas.rotate(60 * Math.min((float) percent / moveDistance, 1f), getWidth() / 2, getHeight() / 2);
        canvas.drawCircle(x, y, radius, paint);
        canvas.restore();
    }

    private void drawLeaf(Canvas canvas, float x, float y, float radius, RectF r, int color, Paint paint) {
        paint.setColor(color);
        canvas.save();
        canvas.rotate(60 * Math.min((float) (percent - (int) TOTAL_TIME / 2 - moveDistance) / moveDistance, 1f), getWidth() / 2, getHeight() / 2);
        canvas.drawCircle(x, y, radius, paint);
        canvas.save();
        canvas.rotate(45, x, y);
        canvas.drawRect(r, paint);
        canvas.restore();
        canvas.restore();
    }

    @Override
    public void run() {
        while (isRunning) {
            percent++;
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            postInvalidate();
//			if(percent ==  2 * (moveDistance + TOTAL_TIME)){
//				percent = 0;
//				isRunning = false;
//			}
        }
    }


}
