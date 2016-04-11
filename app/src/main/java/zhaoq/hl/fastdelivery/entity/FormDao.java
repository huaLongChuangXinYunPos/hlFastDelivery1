package zhaoq.hl.fastdelivery.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.entity
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/21  11:23
 */
public final class FormDao {

    //{"AppNo":"PS-PSOSS-PSDB",
    // "cSaleSheetno":"0509473150160-10003",
    // "dDate1":"2016-03-04 16:16:34.000",
    // "dDate2":"2016-03-06 16:16:34.000"}

    private String AppNo = "PS-PSOSS-PSDB";

    private String cSaleSheetNo;

    private String Date1;

    private String Date2;

    public String getcSaleSheetNo() {
        return cSaleSheetNo;
    }

    public void setcSaleSheetNo(String cSaleSheetNo) {
        this.cSaleSheetNo = cSaleSheetNo;
    }

    public String getDate1() {
        return Date1;
    }

    public void setDate1(String date1) {
        Date1 = date1;
    }

    public String getDate2() {
        return Date2;
    }

    public void setDate2(String date2) {
        Date2 = date2;
    }

    @Override
    public String toString() {
        return "FormDao{" +
                "AppNo='" + AppNo + '\'' +
                ", cSaleSheetNo='" + cSaleSheetNo + '\'' +
                ", Date1='" + Date1 + '\'' +
                ", Date2='" + Date2 + '\'' +
                '}';
    }
}
