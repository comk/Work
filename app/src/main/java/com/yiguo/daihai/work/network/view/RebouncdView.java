package com.yiguo.daihai.work.network.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;


/**
 * Created by daihai on 2015/5/18.
 */
public class RebouncdView extends ScrollView implements IPullable{

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

    public boolean canPullDown(){
        return getScrollY() == 0;
    }

    public boolean canPullUp(){
        View scrollViewChild = getChildAt(0);
        if (null != scrollViewChild) {
            return getScrollY() >= (scrollViewChild.getHeight() - getHeight());
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}
