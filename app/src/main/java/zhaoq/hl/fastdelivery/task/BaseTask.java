package zhaoq.hl.fastdelivery.task;

import android.os.AsyncTask;

import zhaoq.hl.fastdelivery.callback.TaskCallback;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.task
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/21  11:00
 */
public abstract class BaseTask extends AsyncTask<String,Void,TaskResult> {

    private TaskCallback taskCallback;

    public BaseTask(TaskCallback taskCallback){
        this.taskCallback = taskCallback;
    }

    @Override
    protected void onPostExecute(TaskResult taskResult) {
        if(taskCallback!=null){
            taskCallback.onTaskFinished(taskResult);
        }
    }
}
