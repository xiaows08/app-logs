package cn.xiaows.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MonthBeginUDF extends UDF {

    public long evaluate() {
        return DateUtils.getMonthBeginTime(new Date()).getTime();
    }

    public long evaluate(int offset) {
        return DateUtils.getMonthBeginTime(new Date(), offset).getTime();
    }

    public long evaluate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = sdf.parse(dateStr);
        return DateUtils.getMonthBeginTime(date).getTime();
    }

    public long evaluate(String dateStr, int offset) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = sdf.parse(dateStr);
        return DateUtils.getMonthBeginTime(date, offset).getTime();
    }
}
