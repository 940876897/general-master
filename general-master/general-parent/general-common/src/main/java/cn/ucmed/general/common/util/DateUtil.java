package cn.ucmed.general.common.util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    private static final Logger LOG = Logger
            .getLogger(DateUtil.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy/MM/dd");
    private static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat(
            "yyyyMMdd");
    private static final SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat(
            "yyyy-MM-dd");
    private static final SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat(
            "yyyyMMddHHmm");

    private static final SimpleDateFormat sdf1 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");

    private static final SimpleDateFormat sdf2 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat sdf3 = new SimpleDateFormat(
            "HHmm");

    private static final SimpleDateFormat yyyyMMddhhmmssSSS = new SimpleDateFormat(
            "yyyyMMddhhmmssSSS");

    public String simpleDate(Date date) {
        if (date == null)
            return "";
        return sdf.format(date);
    }

    public static String simpleDate1(String date1) {
        if (date1 == null)
            return "";
        return sdf.format(date1);
    }

    public static String simpleDate2(Date date) {
        if (yyyyMMddhhmmssSSS == null)
            return null;
        return yyyyMMddhhmmssSSS.format(date);

    }


    /**
     * 取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {

        Calendar calendar = Calendar.getInstance();

        int i = calendar.get(1);
        int j = calendar.get(2) + 1;
        int k = calendar.get(5);
        return "" + i + (j >= 10 ? "" + j : "0" + j)
                + (k >= 10 ? "" + k : "0" + k);
    }

    public static Date calculateDate(Date startDay, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDay);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    public static Date calculateMonth(Date startDay, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDay);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * String
     * 获取当前时间yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentDateTime() {
        return dateToString4(getCurrentDate());
    }


    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * String
     * 获取今天日期yyyyMMdd
     *
     * @return
     */
    public static String getToday() {
        Calendar calendar = Calendar.getInstance();
        return DateUtil.getyyyyMMdd(calendar.getTime());
    }


    /**
     * String
     * 获取明天日期yyyyMMdd
     *
     * @return
     */
    public static String getTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return DateUtil.getyyyyMMdd(calendar.getTime());
    }


    /**
     * String
     * 获取几天后日期yyyyMMdd
     *
     * @return
     */
    public static String getDateByDays(Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return DateUtil.getyyyyMMdd(calendar.getTime());
    }

    /**
     * String
     * 获取几天后日期yyyy-MM-dd
     *
     * @return
     */
    public static String getDateByDays2(Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return DateUtil.getyyyy_MM_dd(calendar.getTime());
    }

    /**
     * 将日期格式化为字符串
     *
     * @param date
     * @return
     */
    @Deprecated
    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String getyyyyMMdd(Date date) {
        if (date == null)
            return "";
        return yyyyMMdd.format(date);
    }

    public static String getyyyy_MM_dd(Date date) {
        if (date == null)
            return "";
        return yyyy_MM_dd.format(date);
    }

    public static Date stringToDate(String date) {
        try {
            return yyyyMMdd.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    public static Date stringToDate6(String date) {
        try {
            return sdf2.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    /**
     * String
     * 获取时间 yyyy-MM-dd HH:mm
     *
     * @param date
     * @return
     */
    public static String format1(Date date) {
        if (date == null)
            date = new Date();
        return sdf1.format(date);
    }


    public static String dateToString1(Date date) {
        try {
            return sdf1.format(date);
        } catch (Exception e) {
            LOG.error("", e);
        }
        return null;
    }

    /**
     * Date
     * 获取时间 yyyy-MM-dd HH:mm
     *
     * @param date
     * @return Date
     */
    public static Date stringToDate1(String date) {
        try {
            return sdf1.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    /**
     * Date
     * 获取时间 yyyy/MM/dd
     *
     * @param date
     * @return Date
     */
    public static Date stringToDate5(String date) {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }


    /**
     * Date
     * 获取时间yyyyMMddHHmm
     *
     * @param date yyyyMMddHHmm
     * @return Date
     */
    public static Date stringToDate2(String date) {
        try {
            return yyyyMMddHHmm.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    /**
     * Date
     * 获取时间yyyyMMdd
     *
     * @param date yyyyMMdd
     * @return Date
     */
    public static Date stringToDate3(String date) {
        try {
            return yyyyMMdd.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    public static Date stringToDate4(String date) {
        try {
            return sdf2.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    public static String dateToString4(Date date) {
        try {
            return sdf2.format(date);
        } catch (Exception e) {
            LOG.error("", e);
        }
        return null;
    }

    public static String getHHmm(Date date) {
        try {
            return sdf3.format(date);
        } catch (Exception e) {
            LOG.error("", e);
        }
        return null;
    }


    public static Integer getWeekId(String date) {
        Date d = new Date();
        try {
            d = yyyyMMdd.parse(date);
        } catch (ParseException e) {
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }


    public static Integer getWeekId2(String date) {
        Date d = new Date();
        try {
            d = yyyy_MM_dd.parse(date);
        } catch (ParseException e) {
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }


    /**
     * 获取周几
     *
     * @param date yyyyMMdd
     * @return
     */
    public static String getWeek(String date) {
        String week = "";
        switch (getWeekId(date)) {
            case 1:
                week = "周一";
                break;
            case 2:
                week = "周二";
                break;
            case 3:
                week = "周三";
                break;
            case 4:
                week = "周四";
                break;
            case 5:
                week = "周五";
                break;
            case 6:
                week = "周六";
                break;
            case 0:
                week = "周日";
                break;

            default:
                break;
        }
        return week;
    }


    /**
     * 获取周几
     *
     * @param date yyyy_MM_dd
     * @return
     */
    public static String getWeek2(String date) {
        String week = "";
        switch (getWeekId2(date)) {
            case 1:
                week = "周一";
                break;
            case 2:
                week = "周二";
                break;
            case 3:
                week = "周三";
                break;
            case 4:
                week = "周四";
                break;
            case 5:
                week = "周五";
                break;
            case 6:
                week = "周六";
                break;
            case 0:
                week = "周日";
                break;

            default:
                break;
        }
        return week;
    }

    /**
     * 时间计算（日）
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getDate(Date date, int n) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.DAY_OF_MONTH, n);
        return gc.getTime();
    }

    public static Date getDate(Date date, int field, int n) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(field, n);
        return gc.getTime();
    }
}
