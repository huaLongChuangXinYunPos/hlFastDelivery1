package zhaoq.hl.fastdelivery.utils;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.utils
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/18  11:37
 */
public final class HttpTools {

    private HttpTools(){}

    private static final int CONNECT_TIMEOUT = 5000;
    private static final int READTIMEOUT = 5000;

    public static byte[] doGet(String string){
        byte[] ret = null;
        if (string!=null){
            HttpURLConnection conn = null;
            try{
                URL url = new URL(string);
                conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
                conn.setConnectTimeout(CONNECT_TIMEOUT);
                conn.setReadTimeout(READTIMEOUT);

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.connect();
                InputStream in = null;
                if (conn.getResponseCode() == 200){
                    in = conn.getInputStream();
                    byte[] result = StreamUtils.readStream(in);
                    return result;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                StreamUtils.close(conn);
            }
        }
        return ret;
    }

    /**
     * dopost 方法请求数据
     * @return
     */
    public static byte[] doPost(String string,HashMap<String,String> map) {
        byte[] bytes = null;
        if(string!=null){
            StringBuilder sb = new StringBuilder();
            try {
                for (Map.Entry<String,String> en:map.entrySet()) {
                    sb.append(en.getKey())
                            .append("=")
                            .append(URLEncoder.encode(en.getValue(),"utf-8"));
                }
                HttpURLConnection conn = null;
                URL url = new URL(string);
                conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setConnectTimeout(CONNECT_TIMEOUT);
                conn.setReadTimeout(READTIMEOUT);

                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.connect();

                byte[] b = sb.toString().getBytes();//设置提交的数据信息
                //提交数据
                OutputStream out = conn.getOutputStream();
                out.write(b);
                out.close();
                if(conn.getResponseCode()==200){
                    InputStream in = conn.getInputStream();
                    bytes = StreamUtils.readStream(in);
                    in.close();
                    return bytes;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
}
