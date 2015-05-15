package com.yiguo.daihai.work.network;

/**
 * Created by daihai on 2015/5/14.
 */
public interface AsyncTaskListener {
    public enum ErrorType{Error_NetWork_Not_Avaliable,Error_Time_Out,Error_Host_Not_Avaliable};
    public void onTaskStart(int taskType, int taskId);
    public void onTaskFinish(int taskType, int taskId);
    public void onTaskCancelled(int taskType, int taskId);
    public void onTaskInterruptted(int taskType, int taskId);
    public void onDoingBackground(int taskType, int taskId);
    public void onError(ErrorType errorType, String errorMsg, int taskType, int taskId);
}
