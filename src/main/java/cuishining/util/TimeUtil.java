package cuishining.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Date;

/**
 * Created by shining.cui on 2016/7/25.
 */
public class TimeUtil {
    private static final String timeFormatStr = "yyyy-MM-dd HH-mm-ss";
    private static final String timeFormatStr1 = "yyyy-MM-dd HH:mm:ss";

    public static String parseDateFromSystemDate(Date date) {
       return new DateTime(date).toString(timeFormatStr1);
    }

    public static String parseDateFromJpgFileDate(Date date) {
        return new DateTime(date, DateTimeZone.UTC).toString(timeFormatStr);
    }
}
