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

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
