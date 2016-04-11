package zhaoq.hl.fastdelivery.task;

import org.json.JSONException;
import org.json.JSONObject;

import zhaoq.hl.fastdelivery.Configs;
import zhaoq.hl.fastdelivery.callback.TaskCallback;
import zhaoq.hl.fastdelivery.client.ClientApi;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.task
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/28  10:07
 */
public class GetVersionTask extends BaseTask {

    public GetVersionTask(TaskCallback taskCallback) {
        super(taskCallback);
    }
    private static long startTime;
    @Override
    protected void onPreExecute() {
        startTime = System.currentTimeMillis();//获取系统当前  毫秒数
        super.onPreExecute();
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult result = new TaskResult();
        result.task_id = Configs.GET_VERSION_INFO_AUTHORITY;
        if(params!=null){
            JSONObject object = ClientApi.getVersionInfo();
            if (object!=null){
                try {
                    result.resultStatus = object.getInt("resultStatus");
                    result.data = object;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(TaskResult taskResult) {
        long endTime = System.currentTimeMillis();//获取执行完的  结束时间
        long timeUsed = endTime - startTime;
        /**
         * 强制休眠  以保证   当前 splash的背景动画的执行完毕
         */
        if (timeUsed < 2500){
            try {
                Thread.sleep(2500-timeUsed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        super.onPostExecute(taskResult);
    }
}
