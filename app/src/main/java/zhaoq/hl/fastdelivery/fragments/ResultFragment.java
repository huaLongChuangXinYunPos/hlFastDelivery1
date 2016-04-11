package zhaoq.hl.fastdelivery.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import hardware.print.printer;
import zhaoq.hl.fastdelivery.Configs;
import zhaoq.hl.fastdelivery.R;
import zhaoq.hl.fastdelivery.adapter.GoodsDetailAdapter;
import zhaoq.hl.fastdelivery.callback.TaskCallback;
import zhaoq.hl.fastdelivery.entity.DDateBean;
import zhaoq.hl.fastdelivery.entity.DetailPostEntity;
import zhaoq.hl.fastdelivery.entity.FormDetail;
import zhaoq.hl.fastdelivery.entity.SaleFormInfoDao;
import zhaoq.hl.fastdelivery.task.GetFormDetailInfoTask;
import zhaoq.hl.fastdelivery.task.GetSaleFormAsyncTask;
import zhaoq.hl.fastdelivery.task.TaskResult;
import zhaoq.hl.fastdelivery.utils.MyPrinter;
import zhaoq.hl.fastdelivery.utils.MyToast;
import zhaoq.hl.fastdelivery.utils.NumForamtUtils;
import zhaoq.hl.fastdelivery.utils.ParseUtils;
import zhaoq.hl.fastdelivery.view.MyListView;

/**
 * 用于显示    商品信息
 */
public class ResultFragment extends Fragment implements TaskCallback, View.OnClickListener {

    public ResultFragment() {
    }

    private View view;

    private TextView formNo;
    private TextView tvMoney;
    private TextView consumerInfo;
    private TextView address;
    private TextView saleTime;

    private Button printBtn;
    private MyListView listView;

    private ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_result, container, false);

        formNo = (TextView) view.findViewById(R.id.formNo);
        tvMoney = (TextView) view.findViewById(R.id.tvMoney);
        consumerInfo = (TextView) view.findViewById(R.id.consumerInfo);
        address = (TextView) view.findViewById(R.id.address);
        saleTime = (TextView) view.findViewById(R.id.saleTime);

        printBtn = (Button) view.findViewById(R.id.printBtn);
        listView = (MyListView) view.findViewById(R.id.detail_listView);

        scrollView = (ScrollView) view.findViewById(R.id.scroll_view);

        printBtn.setOnClickListener(this);
        return view;
    }

    private SaleFormInfoDao dao;

    public void update(SaleFormInfoDao dao,ImageView imageView) {
        this.dao = dao;
        scrollView.smoothScrollTo(0,0);
        //更新数据信息
        //根据单号  获取信息：
        ArrayList<String> list = new ArrayList<String>();
        DetailPostEntity entity = new DetailPostEntity();
        entity.setCSaleSheetno(dao.getCSaleSheetno());
        String json = new Gson().toJson(entity);
        list.add(json);
        new GetFormDetailInfoTask(this,imageView).execute(list.toString(), Configs.GET_DETAIL_AUTHORITY + "");
    }

    private static FormDetail detail;

    /**
     * 回调回来的   数据
     * @param result
     */
    @Override
    public void onTaskFinished(TaskResult result) {
        if(result.resultStatus == -1){
            //没连接上服务器
            Toast.makeText(getActivity(), "", Toast.LENGTH_LONG).show();
            MyToast.ToastIncenter(getActivity(), "请检查服务器").show();
        }

        if(result.resultStatus == 0){
            //未找到    匹配数据
            MyToast.ToastIncenter(getActivity(), "未查到该订单详细信息").show();
        }

        switch(result.task_id){
            case Configs.GET_DETAIL_AUTHORITY://获取   表单数据的详细表单信息
                if(result.resultStatus==1){
                    //找到了  匹配   数据  进行解析
                    JSONObject obj = (JSONObject) result.data;
                    detail  = ParseUtils.parseObject(obj.toString(),
                            FormDetail.class);

                    formNo.setText("销售单号：" + dao.getCSaleSheetno());
                    tvMoney.setText("商品金额："+ NumForamtUtils.getFormatFloatNumber(dao.getFMoney())
                            +"元，实付："+ NumForamtUtils.getFormatFloatNumber(dao.getFLastMoney()) + "元");
                    consumerInfo.setText("收货人：" + (detail.getDAddRess().size()==0 ? "无" : (detail.getDAddRess().get(0).getAddUser_Name()))
                            + "，电话：" + (detail.getDAddRess().size()==0 ? "无" : (detail.getDAddRess().get(0).getTel())));
                    address.setText("收货地址："+(detail.getDAddRess().size()==0 ? "无" : (detail.getDAddRess().get(0).getAddress_Name())));
                    saleTime.setText("下单时间："+detail.getDDate().get(0).getDSaleDate());

                    ArrayList<DDateBean> goodsList = (ArrayList<DDateBean>) detail.getDDate();
                    GoodsDetailAdapter adapter = new GoodsDetailAdapter(getActivity(),goodsList);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    MyToast.ToastIncenter(getActivity(),"获取单据成功").show();
                }
                break;
            default:
                break;
        }
    }

    private static boolean isPrint = false;  //判断当前  是否正在打印

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.printBtn:
                if(dao != null && detail.getDDate().size()!=0){
                    if(isPrint){ //当前   正在打印
                        MyToast.ToastIncenter(getActivity(),"当前正在打印").show();
                    }else{
                        isPrint = true;
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                MyPrinter pr = new MyPrinter();
                                pr.print(getContext(),dao,detail,"顾客联");
                                pr.print(getContext(), dao, detail, "配送联");

                                if(pr.m_printer.Open() == 0){  //关闭  打印机
                                    pr.m_printer.Close();
                                }
                                isPrint = false;
                            }
                        }).start();
                    }
                }else{
                    MyToast.ToastIncenter(getActivity(),"当前单数据为空,请检查该单").show();
                }
                break;

            default:
                break;
        }
    }
}
