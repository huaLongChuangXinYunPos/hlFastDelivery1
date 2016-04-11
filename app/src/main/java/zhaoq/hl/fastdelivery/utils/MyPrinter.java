package zhaoq.hl.fastdelivery.utils;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.zxing.WriterException;

import java.io.IOException;

import hardware.print.printer;
import zhaoq.hl.fastdelivery.Configs;
import zhaoq.hl.fastdelivery.entity.FormDetail;
import zhaoq.hl.fastdelivery.entity.SaleFormInfoDao;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.utils
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/25  10:49
 * 用于打印的  工具类
 */
public final class MyPrinter{

    public static printer m_printer = null;

    static{
        m_printer = new printer();
    }

    public MyPrinter() {
        if (m_printer!=null){
            m_printer.Open();
        }
    }

    /**
     * 打印  方法
     * @param context  上下文
     * @param dao  表单数据
     * @param detail  表单  详情数据
     */
    public static void print(Context context, SaleFormInfoDao dao, FormDetail detail,String theme) {

        m_printer.PrintLineInit(45);
        m_printer.PrintLineString(theme, 45, printer.PrintType.Centering, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(24);
        m_printer.PrintLineString(theme, 24, printer.PrintType.Centering, false);
        m_printer.PrintLineEnd();

        Bitmap bm1 = null;
        try {
            bm1 = BitmapUtils.getTwoCode(context.getApplicationContext().getSharedPreferences(
                            Configs.SP_FILE_NAME, Context.MODE_PRIVATE).getString(
                            Configs.SERVER_DOWNLOAD_NEWVERSION,
                            "http://www.baidu.com"),
                    130);
            ImageUtils.saveImg("downUrl", bm1, context);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(ImageUtils.getImg(context,"downUrl")!=null) {
            m_printer.PrintBitmap(ImageUtils.getImg(context,"downUrl"));
        }

        m_printer.PrintLineInit(24);
        m_printer.PrintLineString("扫描下载APP精彩不断！", 24, printer.PrintType.Centering, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(22);
        m_printer.PrintLineString("-----------------------------------------", 22, printer.PrintType.Centering, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(24);
        m_printer.PrintLineString("买家:" + dao.getUserName(), 24, printer.PrintType.Left, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(24);
        m_printer.PrintLineString("电话:" + dao.getTel(), 24, printer.PrintType.Left, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(24);
        m_printer.PrintLineString("下单时间:" + dao.getDSaleDate(), 24, printer.PrintType.Left, false);
        m_printer.PrintLineEnd();

        if(detail.getDAddRess().size()!=0){
            if(detail.getDAddRess().get(0).getAddress_Name().length()>10){
                m_printer.PrintLineInit(24);
                m_printer.PrintLineString("送货地址:" + detail.getDAddRess().get(0).getAddress_Name().substring(0, 10), 24, printer.PrintType.Left, false);
                m_printer.PrintLineEnd();

                m_printer.PrintLineInit(24);
                m_printer.PrintLineString(detail.getDAddRess().get(0).getAddress_Name().substring(10, detail.getDAddRess().get(0).getAddress_Name().length()), 24, printer.PrintType.Left, false);
                m_printer.PrintLineEnd();
            }else{
                m_printer.PrintLineInit(24);
                m_printer.PrintLineString("送货地址:" + detail.getDAddRess().get(0).getAddress_Name(), 24, printer.PrintType.Left, false);
                m_printer.PrintLineEnd();
            }
        }else{
            m_printer.PrintLineInit(24);
            m_printer.PrintLineString("送货地址:" + "无", 24, printer.PrintType.Left, false);
            m_printer.PrintLineEnd();
        }


        m_printer.PrintLineInit(22);
        m_printer.PrintLineString("-----------------------------------------", 22, printer.PrintType.Centering, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(25);
        m_printer.PrintLineString("商品名称", 25, printer.PrintType.Left, false);
        m_printer.PrintLineString("数量    单价", 25, printer.PrintType.Centering, false);
        m_printer.PrintLineString("金额      ", 25, printer.PrintType.Right, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(22);
        m_printer.PrintLineString("-----------------------------------------", 22, printer.PrintType.Centering, false);
        m_printer.PrintLineEnd();

        for(int i=0;i<detail.getDDate().size();i++){
            m_printer.PrintLineInit(24);
            m_printer.PrintLineString((i + 1) + "," + detail.getDDate().get(i).getCBarcode(), 24, printer.PrintType.Left, false);
            m_printer.PrintLineEnd();

            m_printer.PrintLineInit(24);
            m_printer.PrintLineString("  " + detail.getDDate().get(i).getCGoodsName(), 24, printer.PrintType.Left, false);
            m_printer.PrintLineEnd();

            m_printer.PrintLineInit(24);
            m_printer.PrintLineString(NumForamtUtils.getFormatFloatNumber(detail.getDDate().get(i).getFQuantity())+"  *  ￥"+
                    NumForamtUtils.getFormatFloatNumber(detail.getDDate().get(i).getFPrice()) + " =￥" +
                    NumForamtUtils.getFormatFloatNumber(detail.getDDate().get(i).getFLastMoney()) + "     .", 24, printer.PrintType.Right, false);
            m_printer.PrintLineEnd();
        }

        m_printer.PrintLineInit(22);
        m_printer.PrintLineString("-----------------------------------------", 22, printer.PrintType.Centering, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(24);
        m_printer.PrintLineString("配送费：    +" + NumForamtUtils.getFormatFloatNumber(dao.getPeisongFee()) + "元", 24, printer.PrintType.Left, false);
        m_printer.PrintLineEnd();
        m_printer.PrintLineInit(24);
        m_printer.PrintLineString("首单减：     -" + NumForamtUtils.getFormatFloatNumber(dao.getFirstSheet()) + "元", 24, printer.PrintType.Left, false);
        m_printer.PrintLineEnd();
        m_printer.PrintLineInit(24);
        m_printer.PrintLineString("满减活动： -" + NumForamtUtils.getFormatFloatNumber(dao.getOverCut()) + "元", 24, printer.PrintType.Left, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(22);
        m_printer.PrintLineString("-----------------------------------------", 22, printer.PrintType.Centering, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(28);
        m_printer.PrintLineString("合计:￥ " + NumForamtUtils.getFormatFloatNumber(detail.getDPay().get(0).getFPayMoney()), 28, printer.PrintType.Left, false);
        m_printer.PrintLineString("        数量：" + detail.getDDate().size(), 28, printer.PrintType.Centering, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(22);
        m_printer.PrintLineString("打印时间:" + TimeUtils.getSystemNowTime("yyyy-MM-dd hh:mm:ss"), 22, printer.PrintType.Left, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(22);
        m_printer.PrintLineString("-----------------------------------------", 22, printer.PrintType.Centering, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(24);
        m_printer.PrintLineString("扫描条码或二维码确认订单", 24, printer.PrintType.Centering, false);
        m_printer.PrintLineEnd();

        Bitmap bm2 = BitmapUtils.getOneCode(detail.getDPay().get(0).getCSaleSheetNo(), 480, 130);
        try {
            ImageUtils.saveImg("sheetNo", bm2, context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(ImageUtils.getImg(context,"sheetNo")!=null) {
            m_printer.PrintBitmap(ImageUtils.getImg(context,"sheetNo"));
        }

        m_printer.PrintLineInit(24);
        m_printer.PrintLineString(detail.getDPay().get(0).getCSaleSheetNo(), 24, printer.PrintType.Centering, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(22);
        m_printer.PrintLineString("-----------------------------------------", 22, printer.PrintType.Centering, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(24);
        m_printer.PrintLineString("服务电话:18513667437", 24, printer.PrintType.Left, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(24);
        m_printer.PrintLineString("商家地址:"+"北京市海淀区", 24, printer.PrintType.Left, false);
        m_printer.PrintLineEnd();

        m_printer.PrintLineInit(100);
        m_printer.PrintLineString("", 100, printer.PrintType.Left, false);
        m_printer.PrintLineEnd();
    }
}
