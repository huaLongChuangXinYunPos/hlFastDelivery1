package zhaoq.hl.fastdelivery.entity;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.entity
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/21  15:17
 */
public final class SaleFormInfoDao {
    /**
     * cStoreNo : 10003
     * cMall_No : 1
     * cStoreName : 商店名
     * dSaleDate : 2016-03-05
     * cSaleSheetno : 0509473150160-10003
     * userNo : 201603031418082723
     * fMoney : 2.8000
     * fLastMoney : 12.8000
     * Stat_Id : 11
     * Stat_Name : 宸蹭粯娆�,鍙楃悊涓�
     * OverCut : 0.0000
     * FirstSheet : 0.0000
     * PeisongFee : 10.0000
     * CouPonMoney : 0.0000
     * UserName : 用户名
     * Tel : 18500150287
     */

    private String cStoreNo;
    private String cMall_No;
    private String cStoreName;
    private String dSaleDate;
    private String cSaleSheetno;
    private String userNo;
    private String fMoney;
    private String fLastMoney;
    private String Stat_Id;
    private String Stat_Name;
    private String OverCut;
    private String FirstSheet;
    private String PeisongFee;
    private String CouPonMoney;
    private String UserName;
    private String Tel;

    public String getCStoreNo() {
        return cStoreNo;
    }

    public void setCStoreNo(String cStoreNo) {
        this.cStoreNo = cStoreNo;
    }

    public String getCMall_No() {
        return cMall_No;
    }

    public void setCMall_No(String cMall_No) {
        this.cMall_No = cMall_No;
    }

    public String getCStoreName() {
        return cStoreName;
    }

    public void setCStoreName(String cStoreName) {
        this.cStoreName = cStoreName;
    }

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

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getFMoney() {
        return fMoney;
    }

    public void setFMoney(String fMoney) {
        this.fMoney = fMoney;
    }

    public String getFLastMoney() {
        return fLastMoney;
    }

    public void setFLastMoney(String fLastMoney) {
        this.fLastMoney = fLastMoney;
    }

    public String getStat_Id() {
        return Stat_Id;
    }

    public void setStat_Id(String Stat_Id) {
        this.Stat_Id = Stat_Id;
    }

    public String getStat_Name() {
        return Stat_Name;
    }

    public void setStat_Name(String Stat_Name) {
        this.Stat_Name = Stat_Name;
    }

    public String getOverCut() {
        return OverCut;
    }

    public void setOverCut(String OverCut) {
        this.OverCut = OverCut;
    }

    public String getFirstSheet() {
        return FirstSheet;
    }

    public void setFirstSheet(String FirstSheet) {
        this.FirstSheet = FirstSheet;
    }

    public String getPeisongFee() {
        return PeisongFee;
    }

    public void setPeisongFee(String PeisongFee) {
        this.PeisongFee = PeisongFee;
    }

    public String getCouPonMoney() {
        return CouPonMoney;
    }

    public void setCouPonMoney(String CouPonMoney) {
        this.CouPonMoney = CouPonMoney;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    @Override
    public String toString() {
        return "SaleFormInfoDao{" +
                "cStoreNo='" + cStoreNo + '\'' +
                ", cMall_No='" + cMall_No + '\'' +
                ", cStoreName='" + cStoreName + '\'' +
                ", dSaleDate='" + dSaleDate + '\'' +
                ", cSaleSheetno='" + cSaleSheetno + '\'' +
                ", userNo='" + userNo + '\'' +
                ", fMoney='" + fMoney + '\'' +
                ", fLastMoney='" + fLastMoney + '\'' +
                ", Stat_Id='" + Stat_Id + '\'' +
                ", Stat_Name='" + Stat_Name + '\'' +
                ", OverCut='" + OverCut + '\'' +
                ", FirstSheet='" + FirstSheet + '\'' +
                ", PeisongFee='" + PeisongFee + '\'' +
                ", CouPonMoney='" + CouPonMoney + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Tel='" + Tel + '\'' +
                '}';
    }
}
