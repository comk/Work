package com.yiguo.daihai.work.network.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by daihai on 2015/5/18.
 */
public class BounceScrollView extends ScrollView {

    private static final int MaxOverScrollY = 300;

    private int mScrollY;

    private int mScrollRangeY;

    private OnScrollListener onScrollListener;

    public BounceScrollView(Context context) {
        super(context);
    }

    public BounceScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BounceScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public OnScrollListener getOnScrollListener() {
        return onScrollListener;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if(isTouchEvent && onScrollListener != null){
            if(scrollY < 0 || scrollY > scrollRangeY) {
                mScrollY = scrollY;
                mScrollRangeY = scrollRangeY;
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, MaxOverScrollY, isTouchEvent);
    }

    public interface OnScrollListener{
        public void onOverBottomScrollRelease(int overscrolldistance);
        public void onOverTopScrollRelease(int overscrolldistance);
    }

}
