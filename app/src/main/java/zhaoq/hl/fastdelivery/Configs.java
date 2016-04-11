package zhaoq.hl.fastdelivery;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/18  10:47
 */
public class Configs {//配置    url  和版本信息


    public static final String SP_FILE_NAME = "congfigs"; // 共享文件的   文件名称

    public static final String SERVER_DOWNLOAD_NEWVERSION = "downLoadNewVersionUrl";//共享参数中  版本的  下载地址是

    public static final String GET_SERVER_VERSION_INFO_URL = "http://192.168.3.199:8888/APPWebService.asmx/GetVerSion?";

    public static final String BASE_IMAGE_URL ="http://192.168.3.199:1235/O2OPIC"; //服务器地址  图片地址

    public static final String GET_FORMNO_INFO_URL = "http://192.168.3.199:1235/OrderPeisong/AppOrderPeisong.asmx/GetSelectOrder?";

    public static final String GET_FORMDETAIL_INFO_URL = "http://192.168.3.199:1235/OrderPeisong/AppOrderPeisong.asmx/OrderPeisong?";

    /**
     * 获取  软件版本的   标识
     */
    public static final int GET_VERSION_INFO_AUTHORITY = 8;

    /**
     * 获取   表单详情的   标识
     */
    public static final int GET_DETAIL_AUTHORITY = 4;

    /**
     * 查询  表单相似数据的异步任务标识
     */
    public static final int GET_QUERY_AUTHORITY = 6;
}
