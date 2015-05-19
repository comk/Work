package com.yiguo.daihai.work.network.animation;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by daihai on 2015/5/19.
 */
public class PulseAnimator extends BaseViewAnimator {
    public PulseAnimator() {
    }

    @Override
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(
            ObjectAnimator.ofFloat(target, "scaleY", 1,1.05f, 1.1f,1.15f, 1.2f,1.25f, 1.3f,1.25f,1.2f,1.15f,1.1f,1.05f,1),
                ObjectAnimator.ofFloat(target, "scaleX", 1,1.05f, 1.1f,1.15f, 1.2f,1.25f, 1.3f,1.25f,1.2f,1.15f,1.1f,1.05f,1)
        );
    }
}
