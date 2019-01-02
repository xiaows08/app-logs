package cn.xiaows.hive.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.text.SimpleDateFormat;
import java.util.Date;

@Description
public class FormatTimeUDF extends UDF {

    public String evaluate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date);
    }

    public String evaluate(long ms) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(new Date(ms));
    }

    public String evaluate(long ms, String fmt) {
        return new SimpleDateFormat(fmt).format(new Date(ms));
    }
}
