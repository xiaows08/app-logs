package cn.xiaows.hive.udf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DayBeginUDF {

    public long evaluate() {
        return DateUtils.getDayBeginTime(new Date()).getTime();
    }

    public long evaluate(int offset) {
        return DateUtils.getDayBeginTime(new Date(), offset).getTime();
    }

    public long evaluate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = sdf.parse(dateStr);
        return DateUtils.getDayBeginTime(date).getTime();
    }

    public long evaluate(String dateStr, int offset) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = sdf.parse(dateStr);
        return DateUtils.getDayBeginTime(date, offset).getTime();
    }
}
