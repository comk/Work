package com.yiguo.daihai.work.network.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by daihai on 2015/5/19.
 */
public class BounceListView extends ListView {
    public static final int MaxOverScrollDistance = 200;

    public BounceListView(Context context) {
        super(context);
    }

    public BounceListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BounceListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, MaxOverScrollDistance, isTouchEvent);
    }
}
