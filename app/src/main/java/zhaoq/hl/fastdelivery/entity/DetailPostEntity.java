package zhaoq.hl.fastdelivery.entity;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.entity
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/22  14:14
 */
public final class DetailPostEntity {

    /**
     * AppNo : PS-PSOSS-PSDB
     * cSaleSheetno : 0509473150160-10003
     */

    private String AppNo = "PS-PSOSS-PSDB";
    private String cSaleSheetno;

    public String getCSaleSheetno() {
        return cSaleSheetno;
    }

    public void setCSaleSheetno(String cSaleSheetno) {
        this.cSaleSheetno = cSaleSheetno;
    }

    @Override
    public String toString() {
        return "DetailPostEntity{" +
                "AppNo='" + AppNo + '\'' +
                ", cSaleSheetno='" + cSaleSheetno + '\'' +
                '}';
    }
}

