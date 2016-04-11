package zhaoq.hl.fastdelivery.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zhaoq.hl.fastdelivery.entity.FormDao;
import zhaoq.hl.fastdelivery.entity.FormDetail;
import zhaoq.hl.fastdelivery.entity.SaleFormInfoDao;
import zhaoq.hl.fastdelivery.entity.VersionEntity;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.utils
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/21  15:15
 */
public final class ParseUtils {

    /**
     * 将数据   解析为List
     * @param array
     * @return
     */
    public static ArrayList<SaleFormInfoDao> parseSaleFormToList(JSONArray array) throws JSONException {
        ArrayList<SaleFormInfoDao> list = new ArrayList<SaleFormInfoDao>();
        if (array != null){
            for (int i=0;i<array.length();i++){
                SaleFormInfoDao dao = new SaleFormInfoDao();
                JSONObject object = array.getJSONObject(i);
                dao.setCStoreNo(object.getString("cStoreNo"));
                dao.setCMall_No(object.getString("cMall_No"));
                dao.setCStoreName(object.getString("cStoreName"));
                dao.setDSaleDate(object.getString("dSaleDate"));
                dao.setCSaleSheetno(object.getString("cSaleSheetno"));
                dao.setUserNo(object.getString("userNo"));
                dao.setFMoney(object.getString("fMoney"));
                dao.setFLastMoney(object.getString("fLastMoney"));
                dao.setStat_Id(object.getString("Stat_Id"));
                dao.setStat_Name(object.getString("Stat_Name"));
                dao.setOverCut(object.getString("OverCut"));
                dao.setFirstSheet(object.getString("FirstSheet"));
                dao.setPeisongFee(object.getString("PeisongFee"));
                dao.setCouPonMoney(object.getString("CouPonMoney"));
                dao.setUserName(object.getString("UserName"));
                dao.setTel(object.getString("Tel"));
                list.add(dao);
            }
        }
        return list;
    }

        /**
         * 将表单详情解析为    数据对象
         */
    public static <T> T parseObject(String jsonString,Class<T> tClass){
        T t = null;
        if(jsonString!=null){
            Gson gson = new Gson();
            t = gson.fromJson(jsonString,tClass);
            return t;
        }
        return t;
    }

    public static ArrayList<VersionEntity> parseVersionList(JSONArray array)
    {
        ArrayList<VersionEntity> list = new ArrayList<VersionEntity>();
        try {
            if(array!=null){
                for(int i =0;i<array.length();i++){
                    VersionEntity version = new VersionEntity();
                    JSONObject object  = (JSONObject) array.get(i);
                    version.setCUrl(object.getString("cUrl"));
                    version.setCBeizhu(object.getString("cBeizhu"));
                    version.setCVersCode(object.getString("cVersCode"));
                    version.setDDatetime(object.getString("dDatetime"));
                    list.add(version);
                }
                return list;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
