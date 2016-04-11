package zhaoq.hl.fastdelivery.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import zhaoq.hl.fastdelivery.R;
import zhaoq.hl.fastdelivery.entity.SaleFormInfoDao;
import zhaoq.hl.fastdelivery.utils.NumForamtUtils;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.adapter
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/21  15:48
 */
public class SaleFormAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<SaleFormInfoDao> list;

    public SaleFormAdapter(FragmentActivity activity, ArrayList<SaleFormInfoDao> list) {
        this.context = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        int ret =0;
        if (list!=null){
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.salesheet_list_view_item,null);
            holder.tV1 = (TextView) convertView.findViewById(R.id.text_sale_no);
            holder.tV2 = (TextView) convertView.findViewById(R.id.text_sale_shop);
            holder.tV3 = (TextView) convertView.findViewById(R.id.text_money);
            holder.tV4 = (TextView) convertView.findViewById(R.id.text_exactly_money);
            holder.tV5 = (TextView) convertView.findViewById(R.id.text_user_name);
            holder.tV6 = (TextView) convertView.findViewById(R.id.text_sale_date);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //填充数据cSaleSheetno:销售单号
//        dSaleDate:销售时间
//        cStoreName：店铺名称
//        fMoney：单据金额
//        fLastMoney：实付金额
//        UserName;用户名
        holder.tV1.setText(list.get(position).getCSaleSheetno());//
        holder.tV2.setText(list.get(position).getCStoreName());
        holder.tV3.setText(NumForamtUtils.getFormatFloatNumber(list.get(position).getFMoney()));
        holder.tV4.setText(NumForamtUtils.getFormatFloatNumber(list.get(position).getFLastMoney()));
        holder.tV5.setText(list.get(position).getUserName());
        holder.tV6.setText(list.get(position).getDSaleDate());

        return convertView;
    }

    public static class ViewHolder{
        public TextView tV1,tV2,tV3,tV4,tV5,tV6;
    }

}
