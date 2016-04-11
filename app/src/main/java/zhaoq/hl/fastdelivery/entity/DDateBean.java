package zhaoq.hl.fastdelivery.entity;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.entity
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/22  11:22
 */
public final class DDateBean {
    private String dSaleDate;
    private String cSaleSheetno;
    private String iSeed;
    private String cGoodsNo;
    private String cGoodsName;
    private String cMall_No;
    private String cBarcode;
    private String cUnit;
    private String cSpec;
    private String fPrice;
    private String fVipPrice;
    private String fQuantity;
    private String fLastMoney;
    private String MinIMG0;

    public String getDSaleDate() {
        return dSaleDate;
    }

    public void setDSaleDate(String dSaleDate) {
        this.dSaleDate = dSaleDate;
    }

    public String getCSaleSheetno() {
        return cSaleSheetno;
    }

    public void setCSaleSheetno(String cSaleSheetno) {
        this.cSaleSheetno = cSaleSheetno;
    }

    public String getISeed() {
        return iSeed;
    }

    public void setISeed(String iSeed) {
        this.iSeed = iSeed;
    }

    public String getCGoodsNo() {
        return cGoodsNo;
    }

    public void setCGoodsNo(String cGoodsNo) {
        this.cGoodsNo = cGoodsNo;
    }

    public String getCGoodsName() {
        return cGoodsName;
    }

    public void setCGoodsName(String cGoodsName) {
        this.cGoodsName = cGoodsName;
    }

    public String getCMall_No() {
        return cMall_No;
    }

    public void setCMall_No(String cMall_No) {
        this.cMall_No = cMall_No;
    }

    public String getCBarcode() {
        return cBarcode;
    }

    public void setCBarcode(String cBarcode) {
        this.cBarcode = cBarcode;
    }

    public String getCUnit() {
        return cUnit;
    }

    public void setCUnit(String cUnit) {
        this.cUnit = cUnit;
    }

    public String getCSpec() {
        return cSpec;
    }

    public void setCSpec(String cSpec) {
        this.cSpec = cSpec;
    }

    public String getFPrice() {
        return fPrice;
    }

    public void setFPrice(String fPrice) {
        this.fPrice = fPrice;
    }

    public String getFVipPrice() {
        return fVipPrice;
    }

    public void setFVipPrice(String fVipPrice) {
        this.fVipPrice = fVipPrice;
    }

    public String getFQuantity() {
        return fQuantity;
    }

    public void setFQuantity(String fQuantity) {
        this.fQuantity = fQuantity;
    }

    public String getFLastMoney() {
        return fLastMoney;
    }

    public void setFLastMoney(String fLastMoney) {
        this.fLastMoney = fLastMoney;
    }

    public String getMinIMG0() {
        return MinIMG0;
    }

    public void setMinIMG0(String MinIMG0) {
        this.MinIMG0 = MinIMG0;
    }

    @Override
    public String toString() {
        return "{" +
                "dSaleDate='" + dSaleDate + '\'' +
                ", cSaleSheetno='" + cSaleSheetno + '\'' +
                ", iSeed='" + iSeed + '\'' +
                ", cGoodsNo='" + cGoodsNo + '\'' +
                ", cGoodsName='" + cGoodsName + '\'' +
                ", cMall_No='" + cMall_No + '\'' +
                ", cBarcode='" + cBarcode + '\'' +
                ", cUnit='" + cUnit + '\'' +
                ", cSpec='" + cSpec + '\'' +
                ", fPrice='" + fPrice + '\'' +
                ", fVipPrice='" + fVipPrice + '\'' +
                ", fQuantity='" + fQuantity + '\'' +
                ", fLastMoney='" + fLastMoney + '\'' +
                ", MinIMG0='" + MinIMG0 + '\'' +
                '}';
    }
}