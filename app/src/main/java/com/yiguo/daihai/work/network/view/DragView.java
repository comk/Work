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

import com.yiguo.daihai.work.R;

/**
 * Created by daihai on 2015/5/19.
 */
public class DragView extends FrameLayout {

    public static final int MAX_DRAG_DISTANCE = 200;

    RebouncdView mContentView;

    OnPullListener onPullListener;

    boolean blockTouchEvent = false;

    View headerView;

    View footerView;

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

    public OnPullListener getOnPullListener() {
        return onPullListener;
    }

    public void setOnPullListener(OnPullListener onPullListener) {
        this.onPullListener = onPullListener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //get mContentView from xml layout file
        mContentView = (RebouncdView) findViewById(R.id.content);
        headerView = findViewById(R.id.header);
        footerView = findViewById(R.id.footer);
    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            return Math.abs(distanceY) > Math.abs(distanceX);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mHelper.processTouchEvent(ev);
        if(!blockTouchEvent)
            mContentView.onTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
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
            return arg0 == mContentView;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top,
                                          int dx, int dy) {
            headerView.layout(0, mContentView.getTop() - headerView.getHeight(), headerView.getWidth(), mContentView.getTop());
            footerView.layout(0, mContentView.getBottom(), footerView.getWidth(), mContentView.getBottom() + footerView.getHeight());
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            if(top <= 0 && mContentView.canPullUp()){
                int distance = Math.min(Math.abs(top), MAX_DRAG_DISTANCE);
                if(onPullListener != null && footerView != null)
                    onPullListener.onPullUp(distance, distance/MAX_DRAG_DISTANCE, footerView);
                if(Math.abs(top) <= MAX_DRAG_DISTANCE) {
                    return top;
                }
                return MAX_DRAG_DISTANCE * (top/Math.abs(top));
            }else if(top >= 0 && mContentView.canPullDown()){
                int distance = Math.min(Math.abs(top), MAX_DRAG_DISTANCE);
                if(onPullListener != null && headerView != null)
                    onPullListener.onPullDown(distance, distance / MAX_DRAG_DISTANCE, headerView);
                if(Math.abs(top) <= MAX_DRAG_DISTANCE) {
                    return top;
                }
                return MAX_DRAG_DISTANCE * (top/Math.abs(top));
            }else{

                if(top > 0 && mContentView.canPullUp()){
                    return 0;
                }else if(top < 0 && mContentView.canPullDown()){
                    return 0;
                }else {
                    if (top != 0) {
                        blockTouchEvent = true;
                        if (Math.abs(top) <= MAX_DRAG_DISTANCE) {
                            return top;
                        }
                        return MAX_DRAG_DISTANCE * (top / Math.abs(top));
                    } else {
                        blockTouchEvent = false;
                        return 0;
                    }
                }


//                if(!mContentView.canPullUp() && top < 0){
//                    blockTouchEvent = true;
//                    if(Math.abs(top) <= MAX_DRAG_DISTANCE) {
//                        return top;
//                    }
//                    return MAX_DRAG_DISTANCE * (top/Math.abs(top));
//                }else if(!mContentView.canPullDown() && top > 0){
//                    blockTouchEvent = true;
//                    if(Math.abs(top) <= MAX_DRAG_DISTANCE) {
//                        return top;
//                    }
//                    return MAX_DRAG_DISTANCE * (top/Math.abs(top));
//                }
//                blockTouchEvent = false;
//                return 0;
            }
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            smoothSlideTo();
        }
    };

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        headerView.layout(0, mContentView.getTop() - headerView.getHeight(), headerView.getWidth(), mContentView.getTop());
        footerView.layout(0, mContentView.getBottom(), footerView.getWidth(), mContentView.getBottom() + footerView.getHeight());
    }

    public interface OnPullListener{
        /**
         * pull down action
         * @param distance the distance of pull down
         * @param percent the rate of pull down
         */
        public void onPullDown(int distance, float percent, View header);

        /***
         * pull up action
         * @param distance the distance of pull up
         * @param percent the rate of pull up
         */
        public void onPullUp(int distance, float percent, View footerView);
    }

}
