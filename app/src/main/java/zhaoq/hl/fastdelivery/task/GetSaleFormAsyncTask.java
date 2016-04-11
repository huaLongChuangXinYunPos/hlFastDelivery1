package zhaoq.hl.fastdelivery.task;

import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import zhaoq.hl.fastdelivery.Configs;
import zhaoq.hl.fastdelivery.callback.TaskCallback;
import zhaoq.hl.fastdelivery.client.ClientApi;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.task
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/21  11:08
 * 根据单号  获取销售单的
 */
public class GetSaleFormAsyncTask extends BaseTask{

    private static ImageView imageView;
    private static AnimationDrawable anim;

    public GetSaleFormAsyncTask(TaskCallback taskCallback,ImageView imageView) {
        super(taskCallback);
        this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        imageView.setVisibility(View.VISIBLE);
        anim = (AnimationDrawable) imageView.getBackground();
        anim.start();
        super.onPreExecute();
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult result = new TaskResult();
        result.task_id = Configs.GET_QUERY_AUTHORITY;
        if(params!=null){
            String data = params[0];
            JSONObject object = ClientApi.getSaleForm(data);
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
        imageView.setVisibility(View.GONE);
        anim.stop();
        super.onPostExecute(taskResult);
    }
}
