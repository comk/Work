package com.yiguo.daihai.work.network.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by daihai on 2015/5/18.
 */
public class BounceScrollView extends ScrollView {

    public static final int MaxOverScrollY = 200;

    private int mScrollY = 0;

    private int mScrollRangeY = 0;

    private OnScrollListener onScrollListener;

    private TextView tv_top;

    private TextView tv_bottom;

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

    public TextView getTv_top() {
        return tv_top;
    }

    public void setTv_top(TextView tv_top) {
        this.tv_top = tv_top;
    }

    public TextView getTv_bottom() {
        return tv_bottom;
    }

    public void setTv_bottom(TextView tv_bottom) {
        this.tv_bottom = tv_bottom;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (onScrollListener != null && ev.getAction() == MotionEvent.ACTION_UP) {
            if(mScrollY < 0)
                onScrollListener.onOverTopScrollRelease(this, -mScrollY,tv_top);
            else if(mScrollY > mScrollRangeY)
                onScrollListener.onOverBottomScrollRelease(this, mScrollY - mScrollRangeY,tv_bottom);
            mScrollY = 0;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if(isTouchEvent){
            mScrollY = scrollY;
            mScrollRangeY = scrollRangeY;
            if(onScrollListener != null) {
                if (mScrollY < 0) {
                    onScrollListener.onOverTopScroll(this, -mScrollY, tv_top);
                } else if (mScrollY > mScrollRangeY) {
                    onScrollListener.onOverBottomScroll(this, mScrollY - mScrollRangeY, tv_bottom);
                }
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, MaxOverScrollY, isTouchEvent);
    }

    public interface OnScrollListener{
        public void onOverBottomScrollRelease(View v, int overscrolldistance, @Nullable View bottomView);
        public void onOverTopScrollRelease(View v, int overscrolldistance, @Nullable View topView);
        public void onOverTopScroll(View v, int overScrollDistance, View topView);
        public void onOverBottomScroll(View v, int overScrollDistance, View bottomView);
    }
}
