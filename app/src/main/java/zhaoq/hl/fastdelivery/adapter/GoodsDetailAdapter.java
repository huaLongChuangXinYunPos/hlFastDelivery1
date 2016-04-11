package zhaoq.hl.fastdelivery.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import zhaoq.hl.fastdelivery.Configs;
import zhaoq.hl.fastdelivery.R;
import zhaoq.hl.fastdelivery.entity.DDateBean;
import zhaoq.hl.fastdelivery.utils.NumForamtUtils;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.adapter
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/22  15:49
 */
public class GoodsDetailAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DDateBean> goodsList;

    public GoodsDetailAdapter(Context activity, ArrayList<DDateBean> goodsList) {
        this.context = activity;
        this.goodsList = goodsList;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(goodsList!=null){
            ret = goodsList.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return goodsList.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.goods_detail_list_item,null);

            holder.imageView = (ImageView) convertView.findViewById(R.id.detail_list_image);
            holder.name = (TextView) convertView.findViewById(R.id.detail_goodsname);
            holder.cbarcode = (TextView) convertView.findViewById(R.id.detail_list_cbarcodeprice);
            holder.amount = (TextView) convertView.findViewById(R.id.detail_list_amount);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(Configs.BASE_IMAGE_URL+goodsList.get(position).getMinIMG0()).memoryPolicy(MemoryPolicy.NO_CACHE,
                        MemoryPolicy.NO_STORE).into(holder.imageView);
        //添加数据
        holder.name.setText("品名:"+goodsList.get(position).getCGoodsName());
        holder.cbarcode.setText("条码:"+goodsList.get(position).getCBarcode()+
                ",单价:"+NumForamtUtils.getFormatFloatNumber(goodsList.get(position).getFPrice())+"元");
        holder.amount.setText("规格:"+ NumForamtUtils.getFormatFloatNumber(goodsList.get(position).getCSpec()));

        return convertView;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView name,cbarcode,amount;
    }
}
