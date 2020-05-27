package yangfuwei.xhB17121910.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataTimeUtils {

    public static String dateToStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DEFAULT_TIME_FORMAT, Locale.CHINA);
        return sdf.format(date);
    }

    public static Date str2Date(String src) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DEFAULT_TIME_FORMAT, Locale.CHINA);
        try {
            return sdf.parse(src);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
