package cn.xiaows.hive.udf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 得到指定 date 的零时刻
     *
     * @param date
     * @return
     */
    public static Date getDayBeginTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 00:00:00");
        try {
            return sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 得到指定 date 的偏移量零时刻
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date getDayBeginTime(Date date, int offset) {
        Date beginDate = getDayBeginTime(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        calendar.add(Calendar.DAY_OF_MONTH, offset);
        return calendar.getTime();
    }

    private static Calendar getWeekBeginCalendar(Date date) {
        Date beginDate = getDayBeginTime(date);
        Calendar c = Calendar.getInstance();
        c.setTime(beginDate);
        int n = c.get(Calendar.DAY_OF_WEEK);
        c.add(Calendar.DAY_OF_MONTH, -(n - 1));
        return c;
    }

    public static Date getWeekBeginTime(Date date) {
        Calendar weekBeginCalendar = getWeekBeginCalendar(date);
        return weekBeginCalendar.getTime();
    }

    public static Date getWeekBeginTime(Date date, int offset) {
        Calendar weekBeginCalendar = getWeekBeginCalendar(date);
        weekBeginCalendar.add(Calendar.DAY_OF_MONTH, offset * 7);
        return weekBeginCalendar.getTime();
    }

    private static Calendar getMonthBeginCalendar(Date date) {
        Date dayBeginTime = getDayBeginTime(date);
        Calendar c = Calendar.getInstance();
        c.setTime(dayBeginTime);
        int n = c.get(Calendar.DAY_OF_MONTH);
        c.add(Calendar.DAY_OF_YEAR, -(n - 1));
        return c;
    }

    public static Date getMonthBeginTime(Date date) {
        Calendar monthBeginCalendar = getMonthBeginCalendar(date);
        return monthBeginCalendar.getTime();
    }

    public static Date getMonthBeginTime(Date date, int offset) {
        Calendar monthBeginCalendar = getMonthBeginCalendar(date);
        monthBeginCalendar.add(Calendar.MONTH, offset);
        return monthBeginCalendar.getTime();
    }

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dayBeginTime = DateUtils.getDayBeginTime(date);
        System.out.println(sdf.format(dayBeginTime));

        Date dayBeginTime1 = DateUtils.getDayBeginTime(date, 2);
        System.out.println(sdf.format(dayBeginTime1));

        Date weekBeginTime = DateUtils.getWeekBeginTime(date);
        System.out.println(sdf.format(weekBeginTime));

        Date weekBeginTime1 = DateUtils.getWeekBeginTime(date, 2);
        System.out.println(sdf.format(weekBeginTime1));

        Date monthBeginTime = DateUtils.getMonthBeginTime(date);
        System.out.println(sdf.format(monthBeginTime));

        Date monthBeginTime1 = DateUtils.getMonthBeginTime(date, 2);
        System.out.println(sdf.format(monthBeginTime1));
    }
}
