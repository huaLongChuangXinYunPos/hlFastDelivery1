package zhaoq.hl.fastdelivery.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.utils
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/18  11:53
 */
public final class StreamUtils {//读取输入流的工具类

    /**
     * 将输入流读取成String
     * @param inputStream
     * @return
     */
    public static byte[] readStream(InputStream inputStream) throws IOException{
        byte[] ret = null;

        if (inputStream != null){
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            int len =0;
            byte[] buffer = new byte[1024];
            while((len = inputStream.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
            buffer = null;
            ret = out.toByteArray();
            inputStream.close();
            out.close();
        }
        return ret;
    }

    /**
     * 关闭流的  方法：
     */
    public static void close(Object o){
        if(o!=null){
            try {
                if(o instanceof InputStream){
                    ((InputStream) o).close();
                }else if(o instanceof OutputStream){
                    ((OutputStream) o).close();
                }else if(o instanceof Reader){
                    ((Reader) o).close();
                }else if(o instanceof Writer){
                    ((Writer) o).close();
                }else if(o instanceof HttpURLConnection){
                    ((HttpURLConnection) o).disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
