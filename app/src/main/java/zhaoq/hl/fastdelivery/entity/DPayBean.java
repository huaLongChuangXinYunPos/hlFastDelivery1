package zhaoq.hl.fastdelivery.entity;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.entity
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/22  11:22
 */
public final class DPayBean {
    private String cSaleSheetNo;
    private String fPayMoney;
    private String cPayStyleNo;
    private String cPayStyleName;

    public String getCSaleSheetNo() {
        return cSaleSheetNo;
    }

    public void setCSaleSheetNo(String cSaleSheetNo) {
        this.cSaleSheetNo = cSaleSheetNo;
    }

    public String getFPayMoney() {
        return fPayMoney;
    }

    public void setFPayMoney(String fPayMoney) {
        this.fPayMoney = fPayMoney;
    }

    public String getCPayStyleNo() {
        return cPayStyleNo;
    }

    public void setCPayStyleNo(String cPayStyleNo) {
        this.cPayStyleNo = cPayStyleNo;
    }

    public String getCPayStyleName() {
        return cPayStyleName;
    }

    public void setCPayStyleName(String cPayStyleName) {
        this.cPayStyleName = cPayStyleName;
    }
}
