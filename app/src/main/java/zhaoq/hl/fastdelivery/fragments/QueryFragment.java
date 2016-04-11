package zhaoq.hl.fastdelivery.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import zhaoq.hl.fastdelivery.Configs;
import zhaoq.hl.fastdelivery.R;
import zhaoq.hl.fastdelivery.adapter.SaleFormAdapter;
import zhaoq.hl.fastdelivery.entity.SaleFormInfoDao;
import zhaoq.hl.fastdelivery.task.GetSaleFormAsyncTask;
import zhaoq.hl.fastdelivery.callback.TaskCallback;
import zhaoq.hl.fastdelivery.task.TaskResult;
import zhaoq.hl.fastdelivery.utils.MyToast;
import zhaoq.hl.fastdelivery.utils.ParseUtils;

/**
 *
 */
public class QueryFragment extends Fragment implements TaskCallback, AdapterView.OnItemClickListener {

    public QueryFragment() {
    }

    private View view;

    private PullToRefreshListView listView;

    private ArrayList<SaleFormInfoDao> list;

    private SaleFormAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_query, container, false);
        listView = (PullToRefreshListView) view.findViewById(R.id.fragment_pull_to_refresh);
        return view;
    }

    public void adapterNotify(ArrayList<String> alist,ImageView imageView){
        new GetSaleFormAsyncTask(this,imageView).execute(alist.toString(),Configs.GET_QUERY_AUTHORITY + "");
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        switch(result.task_id){
            case Configs.GET_QUERY_AUTHORITY://获取所有相似  表单数据的表单信息

                if(result.resultStatus==1){
                    try {
                        //找到了  匹配  数据  进行解析
                        JSONObject obj = (JSONObject) result.data;
                        JSONArray array =obj.getJSONArray("dDate");
                        list = ParseUtils.parseSaleFormToList(array);

                        adapter = new SaleFormAdapter(getActivity(),list);
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        listView.setOnItemClickListener(this);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if(result.resultStatus == -1){
                    //没连接上服务器
                    MyToast.ToastIncenter(getActivity(), "请检查服务器").show();
                }

                if(result.resultStatus == 0){
                    //未找到    匹配数据
                    MyToast.ToastIncenter(getActivity(), "未查到匹配数据").show();
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
         callback = (QFCallback) getActivity();
        super.onCreate(savedInstanceState);
    }

    /**
     * 设置  item的监听事件：
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //获取id对应的位置的数据
        //回调给主线程
        callback.qfCallback(list.get(position - 1));
    }

    private QFCallback callback;

    public interface QFCallback{
        void qfCallback(SaleFormInfoDao dao);
    }
}
