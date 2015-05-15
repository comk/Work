package com.yiguo.daihai.work.network;

import android.app.Activity;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by daihai on 2015/5/14.
 *
 */
public class AsycnTaskManager {
    private static AsycnTaskManager mAsyncTaskManangerInstance;

    private AsycnTaskManager(){
    }

    public AsycnTaskManager getInstance(){
        return mAsyncTaskManangerInstance == null ? mAsyncTaskManangerInstance = new AsycnTaskManager() : mAsyncTaskManangerInstance;
    }



}
