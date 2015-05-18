package com.yiguo.daihai.work.network.exception;

import android.app.Activity;
import android.os.*;
import android.os.Process;

/**
 * Created by daihai on 2015/5/18.
 */
public class CustomException implements Thread.UncaughtExceptionHandler {

    private Activity tag;

    public CustomException() {

    }

    public CustomException(Activity className) {
        tag = className;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if(tag != null) {
            System.out.println("=================================" + tag.getClass().getName());
            tag.finish();
            tag = null;
        }
        Process.killProcess(Process.myPid());
    }
}
