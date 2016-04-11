package zhaoq.hl.fastdelivery.entity;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.entity
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/26  16:02
 */
public final class VersionEntity {

    /**
     * cUrl : http://114.112.64.110:7080/O2O/hlcxdg.apk
     * cVersCode : 2.0
     * cBeizhu :
     * dDatetime : 2016-03-20
     */

    private String cUrl;
    private String cVersCode;
    private String cBeizhu;
    private String dDatetime;

    public String getCUrl() {
        return cUrl;
    }

    public void setCUrl(String cUrl) {
        this.cUrl = cUrl;
    }

    public String getCVersCode() {
        return cVersCode;
    }

    public void setCVersCode(String cVersCode) {
        this.cVersCode = cVersCode;
    }

    public String getCBeizhu() {
        return cBeizhu;
    }

    public void setCBeizhu(String cBeizhu) {
        this.cBeizhu = cBeizhu;
    }

    public String getDDatetime() {
        return dDatetime;
    }

    public void setDDatetime(String dDatetime) {
        this.dDatetime = dDatetime;
    }
    @Override
    public String toString() {
        return "VersionEntity{" +
                "cUrl='" + cUrl + '\'' +
                ", cVersCode='" + cVersCode + '\'' +
                ", cBeizhu='" + cBeizhu + '\'' +
                ", dDatetime='" + dDatetime + '\'' +
                '}';
    }
}
