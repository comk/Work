package com.yiguo.daihai.work.network.view;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;


/**
 * Created by daihai on 2015/5/18.
 */
public class RebouncdView extends ScrollView {

    private boolean isOverScroll = true;

    public RebouncdView(Context context) {
        this(context, null);
    }

    public RebouncdView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RebouncdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
//        isOverScroll = scrollY >=0 && scrollY <= scrollRangeY;
        System.out.println("deltaX = " + deltaX+
                ", deltaY = " + deltaY +
                ", scrollX = "+scrollX+
                ", scrollY = " + scrollY+
                ", scrollRangeX = " + scrollRangeX+
                ", scrollRangeY = " + scrollRangeY+
                ", maxOverScrollX = " + maxOverScrollX+
                ", maxOverScrollY = " +  maxOverScrollY+
                ", isTouchEvent = " + isTouchEvent);
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    public boolean isOverScroll() {
        return isOverScroll;
    }

    public void setIsOverScroll(boolean isOverScroll) {
        this.isOverScroll = isOverScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            isOverScroll = false;
//        }
        return false;
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        System.out.println("scrollX = " + scrollX +
                ", scrollY = "+scrollY +
                ", clampedX = "+clampedX +
                ", clampedY = "+clampedY);
        isOverScroll = clampedY;
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }
}
