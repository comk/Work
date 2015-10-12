package com.yiguo.daihai.work.network.view;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.yiguo.daihai.work.R;

/**
 * Created by daihai on 2015/7/2.
 */
public class RefreshView  extends FrameLayout {
    /**
     * Max Drag Distance
     */
    public static final int MAX_DRAG_DISTANCE = 200;

    ViewDragHelper mHelper;

    View mContentView;

    private GestureDetectorCompat mGestureDetector;

    public RefreshView(Context context) {
        this(context, null);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mGestureDetector = new GestureDetectorCompat(context,
                new YScrollDetector());
        mHelper = ViewDragHelper.create(this, 1.0f,
                mViewDragHelperCallback);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = findViewById(R.id.content);
    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            return Math.abs(distanceY) >= Math.abs(distanceX);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("getScrollY() == ",""+mContentView.getScrollY());
        if (ev.getAction() == MotionEvent.ACTION_DOWN
        				|| ev.getAction() == MotionEvent.ACTION_UP) {
        			mHelper.processTouchEvent(ev);
        		}
        switch (ev.getAction() & ev.getActionMasked()){
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_DOWN:
                if(mContentView.getScrollY() == 0){

                }else if(mContentView.getScrollY() > 0){

                }else if(mContentView.getScrollY() < 0){

                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        if(mContentView.getScrollY() == 0){
            mContentView.dispatchTouchEvent(ev);
            mHelper.processTouchEvent(ev);
        }else if(mContentView.getScrollY() > 0) {
            mContentView.dispatchTouchEvent(ev);
            return false;
        }else if(mContentView.getScrollY() < 0){
            try{
                mHelper.processTouchEvent(ev);
            }catch (Exception ex){}
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        mHelper.shouldInterceptTouchEvent(ev);
//        return mGestureDetector.onTouchEvent(ev);// && mContentView.getScrollY() <= 0;
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        try{
//            mHelper.processTouchEvent(ev);
//        }catch (Exception ex){}
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        if (mHelper.continueSettling(true))
            ViewCompat.postInvalidateOnAnimation(this);
    }

    private void smoothSlideTo(View slideView, int left, int top) {
        if (mHelper.smoothSlideViewTo(slideView, left, top))
            ViewCompat.postInvalidateOnAnimation(this);
    }


    private ViewDragHelper.Callback mViewDragHelperCallback = new ViewDragHelper.Callback() {

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        @Override
        public boolean tryCaptureView(View arg0, int arg1) {
            Log.e("captureView ",arg0.toString());
            return mContentView == arg0;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top,
                                          int dx, int dy) {

        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            if(top > 0)
                return Math.min(top, MAX_DRAG_DISTANCE);
            else
                return 0;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            smoothSlideTo(releasedChild,0,0);
        }
    };


}
