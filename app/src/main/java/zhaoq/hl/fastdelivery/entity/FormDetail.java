package zhaoq.hl.fastdelivery.entity;

import java.util.List;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.entity
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/22  11:13
 */
public final class FormDetail {

    /**
     * resultStatus : 1
     * dPay : [{"cSaleSheetNo":"0509473150160-10003","fPayMoney":"12.8000","cPayStyleNo":"01","cPayStyleName":"閽卞寘鏀粯"},{"cSaleSheetNo":"0509473150160-10003","fPayMoney":"12.8000","cPayStyleNo":"01","cPayStyleName":"閽卞寘鏀粯"}]
     * dDate : [{"dSaleDate":"2016-03-05","cSaleSheetno":"0509473150160-10003","iSeed":"0","cGoodsNo":"112160","cGoodsName":"500ml缇庡勾杈鹃潚鑻规灉鍛虫苯姘�","cMall_No":"1","cBarcode":"6906907909054","cUnit":"鐡�","cSpec":"500ml","fPrice":"0.0000","fVipPrice":"0.0000","fQuantity":"1.0000","fLastMoney":"2.8000","MinIMG0":"/DOGOServer/pic/19.jpg"}]
     * dAddRess : [{"AddUser_Name":"澶忔槑","Address_Name":"鍖椾含","Tel":"33333"}]
     */

    private int resultStatus;
    /**
     * cSaleSheetNo : 0509473150160-10003
     * fPayMoney : 12.8000
     * cPayStyleNo : 01
     * cPayStyleName : 閽卞寘鏀粯
     */

    private List<DPayBean> dPay;
    /**
     * dSaleDate : 2016-03-05
     * cSaleSheetno : 0509473150160-10003
     * iSeed : 0
     * cGoodsNo : 112160
     * cGoodsName : 500ml缇庡勾杈鹃潚鑻规灉鍛虫苯姘�
     * cMall_No : 1
     * cBarcode : 6906907909054
     * cUnit : 鐡�
     * cSpec : 500ml
     * fPrice : 0.0000
     * fVipPrice : 0.0000
     * fQuantity : 1.0000
     * fLastMoney : 2.8000
     * MinIMG0 : /DOGOServer/pic/19.jpg
     */

    private List<DDateBean> dDate;
    /**
     * AddUser_Name : 澶忔槑
     * Address_Name : 鍖椾含
     * Tel : 33333
     */

    private List<DAddRessBean> dAddRess;

    public int getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(int resultStatus) {
        this.resultStatus = resultStatus;
    }

    public List<DPayBean> getDPay() {
        return dPay;
    }

    public void setDPay(List<DPayBean> dPay) {
        this.dPay = dPay;
    }

    public List<DDateBean> getDDate() {
        return dDate;
    }

    public void setDDate(List<DDateBean> dDate) {
        this.dDate = dDate;
    }

    public List<DAddRessBean> getDAddRess() {
        return dAddRess;
    }

    public void setDAddRess(List<DAddRessBean> dAddRess) {
        this.dAddRess = dAddRess;
    }

    @Override
    public String toString() {
        return "FormDetail{" +
                "resultStatus=" + resultStatus +
                ", dPay=" + dPay +
                ", dDate=" + dDate +
                ", dAddRess=" + dAddRess +
                '}';
    }
}
