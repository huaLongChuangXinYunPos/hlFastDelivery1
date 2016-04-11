package zhaoq.hl.fastdelivery.utils;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.utils
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/25  14:20
 */
public final class NumForamtUtils {

    /**
     * 格式化  带有小数点的数据   整数不进行去零
     * @param text
     * @return
     */
    public static String getFormatFloatNumber(String text){
        String str = text;
        if(str!=null&&!str.equals("")&&str.contains(".")){  //格式化  小数数据
            while(str.endsWith("0")){
                str = str.substring(0,str.length()-1);
            }
        }
        if(str.endsWith(".")){
            str = str.substring(0,str.length()-1);
        }
        return str;
    }

}
