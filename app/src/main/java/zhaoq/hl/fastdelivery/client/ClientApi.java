package zhaoq.hl.fastdelivery.client;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import zhaoq.hl.fastdelivery.Configs;
import zhaoq.hl.fastdelivery.utils.HttpTools;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.client
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/21  13:07
 */
public final class ClientApi {


    /**
     * 从服务器  获取版本信息
     * @return
     */
    public static JSONObject getVersionInfo() {
        JSONObject ret = null;
        HashMap<String,String> namedata = new HashMap<String,String>();
        namedata.put("name", "PeiSong");
        byte[] bytes = HttpTools.doPost(Configs.GET_SERVER_VERSION_INFO_URL, namedata);
        if(bytes!=null){
            try {
                ret = new JSONObject(new String(bytes,"utf-8"));
                return ret;
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 获取   相似的查询  所有销售单
     * @param data
     * @return
     */
    public static JSONObject getSaleForm(String data) {
        JSONObject ret = null;
        if (data!=null){
            HashMap<String,String> namedata = new HashMap<String,String>();
            namedata.put("name", data);
            byte[] bytes = HttpTools.doPost(Configs.GET_FORMNO_INFO_URL, namedata);
            if(bytes!=null){
                try {
                    ret = new JSONObject(new String(bytes,"utf-8"));
                    return ret;
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    /**
     * 获取订单详情信息：
     * @param data
     * @return
     */
    public static JSONObject getFormDetail(String data) {
        JSONObject ret = null;
        if (data!=null){
            HashMap<String,String> namedata = new HashMap<String,String>();
            namedata.put("name", data);
            byte[] bytes = HttpTools.doPost(Configs.GET_FORMDETAIL_INFO_URL, namedata);
            if(bytes!=null){
                try {
                    ret = new JSONObject(new String(bytes,"utf-8"));
                    return ret;
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

}
