package com.yiguo.daihai.work.network.view;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.yiguo.daihai.work.R;

/**
 * Created by daihai on 2015/5/19.
 */
public class DragView extends FrameLayout {

    public static final int MAX_DRAG_DISTANCE = 200;

    View mContentView;

    ViewDragHelper mHelper;

    private GestureDetectorCompat mGestureDetector;

    public DragView(Context context) {
        this(context, null);
    }

    public DragView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mGestureDetector = new GestureDetectorCompat(context,
                new YScrollDetector());
        mHelper = ViewDragHelper.create(this, 1.0f,
                mViewDragHelperCallback);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //get mContentView from xml layout file
        mContentView = findViewById(R.id.content);
    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            return Math.abs(distanceY) > Math.abs(distanceX);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        mHelper.shouldInterceptTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_DOWN
                || ev.getAction() == MotionEvent.ACTION_UP) {
            mHelper.processTouchEvent(ev);
        }
        mContentView.onTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev) && ((RebouncdView)mContentView).isOverScroll();
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            mHelper.processTouchEvent(ev);
        } catch (Exception ex) {
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mHelper.continueSettling(true))
            ViewCompat.postInvalidateOnAnimation(this);
    }

    private void smoothSlideTo() {
        if (mHelper.smoothSlideViewTo(mContentView, 0, 0))
            ViewCompat.postInvalidateOnAnimation(this);
    }

    private ViewDragHelper.Callback mViewDragHelperCallback = new ViewDragHelper.Callback() {

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        @Override
        public boolean tryCaptureView(View arg0, int arg1) {
            return arg0 == mContentView && ((RebouncdView)mContentView).isOverScroll();
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top,
                                          int dx, int dy) {

        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            if(Math.abs(top) <= MAX_DRAG_DISTANCE) {
                return top;
            }
            return MAX_DRAG_DISTANCE * (top/Math.abs(top));
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            smoothSlideTo();
        }
    };


}
