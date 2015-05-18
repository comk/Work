package com.yiguo.daihai.work.network.view;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.yiguo.daihai.work.R;

/**
 * Created by daihai on 2015/5/18.
 */
public class RebouncdView extends ScrollView {

    ViewDragHelper mHelper;

    View mContentView;

    private GestureDetectorCompat mGestureDetector;

    public RebouncdView(Context context) {
        this(context, null);
    }

    public RebouncdView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RebouncdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mGestureDetector = new GestureDetectorCompat(context,
                new YScrollDetector());
        mHelper = ViewDragHelper.create(this, 1.0f,
                mViewDragHelperCallback);
    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            return Math.abs(distanceY) > Math.abs(distanceX);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = getChildAt(0);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//
//        if(mContentView.getBottom() == this.getBottom()){
//            System.out.println("to the bottom =====================");
//        }else if(mContentView.getTop() == this.getTop()){
//            System.out.println("to the Top =====================");
//        }
//
//        mHelper.shouldInterceptTouchEvent(ev);
//        if (ev.getAction() == MotionEvent.ACTION_DOWN
//                || ev.getAction() == MotionEvent.ACTION_UP) {
//            mHelper.processTouchEvent(ev);
//        }
//        return mGestureDetector.onTouchEvent(ev);
//    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
//        if(mContentView.getBottom() == this.getBottom()){
//            System.out.println("to the bottom =====================");
//        }else if(mContentView.getTop() == this.getTop()){
//            System.out.println("to the Top =====================");
//        }
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, 1200, isTouchEvent);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    //    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        try {
//            mHelper.processTouchEvent(ev);
//        } catch (Exception ex) {
//        }
//        return true;
//    }

    private ViewDragHelper.Callback mViewDragHelperCallback = new ViewDragHelper.Callback() {
        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        @Override
        public boolean tryCaptureView(View arg0, int arg1) {
            return arg0 == mContentView;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top,
                                          int dx, int dy) {
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            if(top > 0)
                return super.clampViewPositionVertical(child, top, dy);
            return top;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            smoothSlideTo(releasedChild);
        }
    };

    private void smoothSlideTo(View releasedView) {
        if(releasedView.getTop() == 0)
            return;
//        if (releasedView.getTop() < 0 && releasedView.getTop() > -height/3) {
//            if (mHelper.smoothSlideViewTo(releasedView, 0, 0))
//                ViewCompat.postInvalidateOnAnimation(this);
//        } else {
//            if (mHelper.smoothSlideViewTo(releasedView, 0, -height))
//                ViewCompat.postInvalidateOnAnimation(this);
//        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed,l,t,r,b);
    }
}
