package com.yiguo.daihai.work.network.fix;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * 替换ViewPager中的mScroller以改变其滚动的速度
 * @author daihai
 *
 */
public class FixedSpeedScroller extends Scroller{

	private int mDuration = 400;

    public FixedSpeedScroller(Context context) {
        super(context);
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
	
}