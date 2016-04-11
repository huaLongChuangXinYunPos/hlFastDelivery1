package zhaoq.hl.fastdelivery.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 操作   时间的  工具类
 */
public final class TimeUtils {
	
	public static String getSystemNowTime(String formatPattern) {
		SimpleDateFormat df = new SimpleDateFormat(formatPattern);
		String time = df.format(new Date());
		return time;
	}
	

}
