package com.ks.hrms.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author QiuKai
 */
public class Utils {

    public final static String STR = "STR";
    public final static String BIGD = "BIGD";
    public final static String INTGER = "INTGER";
    public final static String DATE = "DATE";
    public final static String NULL = "NULL";

    public static final String CALENDAR_DAY = "DAY";
    public static final String CALENDAR_MONTH = "MONTH";
    public static final String CALENDAR_YEAR = "YEAR";
    public static final String CALENDAR_YEAR_FOR_MONTH = "YEAR4MONTH";
    public static final String CALENDAR_HOURS = "HOURS";
    public static final String CALENDAR_MINUTES = "MINUTES";
    public static final String CALENDAR_SECOUNDS = "SECOUNDS";

    @SuppressWarnings("unchecked")
    public static <T> T getValue(Class<T> t, Object o) {
        if (null != o) {
            if (o.getClass().isAssignableFrom(t)) {
                return (T) o;
            } else {
                try {
                    if (t.equals(String.class)) {
                        String s = new String(o.toString());
                        return (T) s;
                    } else if (t.equals(Integer.class)) {
                        Integer i = new Integer(o.toString());
                        return (T) i;
                    } else if (t.equals(BigDecimal.class)) {
                        BigDecimal bd = new BigDecimal(o.toString());
                        return (T) bd;
                    } else if (t.equals(Date.class)) {
                        Date date = DateUtil.converStrToDate(o.toString());
                        return (T) date;
                    }
                } catch (Exception e) {
                    // 转换类型出错
                    e.printStackTrace();
                    return null;
                }
            }
        }

        return null;
    }


    /**
     * String 参数中有一个为空 或者 "" 返回 true BigDecimal 参数中有一个为空 或者 BigDecimal.ZERO
     * 返回 true INTEGER 参数中有一个为空 或者 0 返回 true
     *
     * @param a
     *            参数
     *
     * @return true : 存在空字符串。 false : 不为空。
     */
    public static boolean isEmpty(Object... a) {
        boolean b = a == null ? true : false;
        if (b) {
            return b;
        }
        for (Object a_ : a) {
            String s = "";

            if (a_ instanceof String) {
                s = STR;
            } else if (a_ instanceof java.math.BigDecimal) {
                s = BIGD;
            }

            switch (s) {
                case STR:
                    String a_a = (String) a_;
                    if (a_a == null || a_a.equals("")) {
                        b = true;
                        return b;
                    }
                    break;
                case BIGD:
                    java.math.BigDecimal a_b = (java.math.BigDecimal) a_;
                    if (a_b == null || a_b.compareTo(java.math.BigDecimal.ZERO) == 0) {
                        b = true;
                        return b;
                    }
                    break;
                case INTGER:
                    Integer a_c = (Integer) a_;
                    if (a_c == null || a_c.equals(0)) {
                        b = true;
                        return b;
                    }
                    break;
                default:
                    b = a_ == null ? true : false;
                    if (b) {
                        return true;
                    }
            }
            continue;
        }
        return b;
    }

    /**
     *
     * 更新DateUtil 继承于tools的 DateUtil工具类 extends
     * {@link }
     *
     * @author QiuKai
     */
    public static class DateUtil {

        private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        private static Calendar calendar = null;
        static {
            setCalendar();
        }

        private static void setCalendar() {
            if (calendar == null) {
                calendar = Calendar.getInstance();
            }
        }

        private static java.util.Date addTime(java.util.Date date, int num, final String type) {
            calendar.setTime(date);
            int calendarType = 0;
            switch (type) {
                case CALENDAR_DAY:
                    calendarType = Calendar.DAY_OF_MONTH;
                    break;
                case CALENDAR_MONTH:
                    calendarType = Calendar.MONTH;
                    break;
                case CALENDAR_YEAR:
                    calendarType = Calendar.YEAR;
                    break;
                default:
                    break;
            }
            calendar.add(calendarType, num);

            java.util.Date returns = calendar.getTime();
            return returns;
        }

        /**
         * 返回日期的具体参数
         *
         * @param date
         *            日期
         * @param type
         *            要返回的类型:CALENDAR_DAY,CALENDAR_MONTH,CALENDAR_YEAR,CALENDAR_HOURS,CALENDAR_MINUTES,CALENDAR_SECOUNDS
         * @return
         */
        public static int rtDay(java.util.Date date, final String type) {
            return rtDays(date, type);
        }

        /**
         *
         * @param date
         *            日期
         * @param type
         *            可用的type:YYYY-MM-DD,YYYYMMDD,YYYYMM,YYYY,MM,DD,HH,mm,MMDDYYYY,YYYYMMDDHHmmss,HHmmss,YYYY-MM-DD
         *            HH:mm:ss,ss
         * @return DATE parseStr
         */
        public static String rtYMD(java.util.Date date, final String type) {
            switch (type) {
                case "YYYY-MM-DD":
                    return rtYMDs(date, type);
                case "YYYYMMDD":
                    return rtYMDs(date, type);
                case "YYYYMM":
                    return rtYMDs(date, type);
                case "YYYY":
                    return rtYMDs(date, type);
                case "MM":
                    return rtYMDs(date, type);
                case "DD":
                    return rtYMDs(date, type);
                case "HH":
                    return rtYMDs(date, type);
                case "mm":
                    return rtYMDs(date, type);
                case "ss":
                    return rtYMDs(date, type);
                case "MMDDYYYY":
                    String rts = rtYMDs(date, "MM") + rtYMDs(date, "DD") + rtYMDs(date, "YYYY");
                    return rts;
                case "YYYYMMDDHHmmss":
                    rts = rtYMDs(date, "YYYYMMDD") + rtYMDs(date, "HH") + rtYMDs(date, "mm") + rtYMDs(date, "ss");
                    return rts;
                case "HHmmss":
                    rts = rtYMDs(date, "HH") + rtYMDs(date, "mm") + rtYMDs(date, "ss");
                    return rts;
                case "YYYY-MM-DD HH:mm:ss":
                    rts = rtYMDs(date, "YYYY-MM-DD") + " " + rtYMDs(date, "HH") + ":" + rtYMDs(date, "mm") + ":" + rtYMDs(date, "ss");
                    return rts;
                default:
                    break;
            }
            return "";
        }


        private static String rtYMDs(java.util.Date date, final String type) {
            StringBuilder rts = new StringBuilder();
            String pilt = "";
            Pattern patt = Pattern.compile("[^a-zA-Z]");
            Matcher m = patt.matcher(type);
            while (m.find()) {
                if (m.group().matches("[^a-zA-Z]")) {
                    pilt = m.group();
                }
            }
            int year, month, day, hours, minutes, secounds;
            {
                year = rtDays(date, CALENDAR_YEAR);
                month = rtDays(date, CALENDAR_MONTH);
                day = rtDays(date, CALENDAR_DAY);
                hours = rtDay(date, CALENDAR_HOURS);
                minutes = rtDay(date, CALENDAR_MINUTES);
                secounds = rtDay(date, CALENDAR_SECOUNDS);

            }
            switch (type) {

                case "YYYY-MM-DD":
                    rts.append(year);
                    rts.append(pilt);
                    rts.append(String.format("%02d", (month)));
                    rts.append(pilt);
                    rts.append(String.format("%02d", day));
                    break;
                case "YYYYMMDD":
                    rts.append(year);
                    rts.append(String.format("%02d", (month)));
                    rts.append(String.format("%02d", day));
                    break;
                case "YYYYMM":
                    rts.append(year);
                    rts.append(String.format("%02d", (month)));
                    break;
                case "YYYY":
                    rts.append(year);
                    break;
                case "MM":
                    rts.append(String.format("%02d", (month)));
                    break;
                case "DD":
                    rts.append(String.format("%02d", day));
                    break;
                case "HH":
                    rts.append(String.format("%02d", hours));
                    break;
                case "mm":
                    rts.append(String.format("%02d", minutes));
                    break;
                case "ss":
                    rts.append(String.format("%02d", secounds));
                    break;
                default:
                    break;
            }
            return rts.toString();
        }

        private static int rtDays(java.util.Date date, final String type) {
            int i = 0;
            calendar.setTime(date);

            switch (type) {
                case CALENDAR_DAY:
                    i = calendar.get(Calendar.DAY_OF_MONTH);
                    break;
                case CALENDAR_MONTH:
                    i = calendar.get(Calendar.MONTH) + 1;
                    break;
                case CALENDAR_YEAR:
                    i = calendar.get(Calendar.YEAR);
                    break;
                case CALENDAR_HOURS:
                    i = calendar.get(Calendar.HOUR_OF_DAY);
                    break;
                case CALENDAR_MINUTES:
                    i = calendar.get(Calendar.MINUTE);
                    break;
                case CALENDAR_SECOUNDS:
                    i = calendar.get(Calendar.SECOND);
                    break;
                default:
                    break;
            }
            return i;
        }

        /**
         * 对日期加天 eg:(20170310+5=20170315)
         *
         * @param date
         *            日期
         * @param days
         *            调整天数
         * @return 调整之后的日期
         */
        public static java.util.Date addDays(java.util.Date date, int days) {

            return addTime(date, days, CALENDAR_DAY);
        }

        /**
         * 对日期减天 eg:(20170310-5=20170305)
         *
         * @param date
         *            日期
         * @param days
         *            调整天数
         * @return 调整之后的日期
         */
        public static java.util.Date deleteDays(java.util.Date date, int days) {

            return addTime(date, -days, CALENDAR_DAY);
        }

        /**
         * 获得dateto-date的天数 eg:(20170310-20170305=5)
         *
         * @param date
         *            开始日期
         * @param dateto
         *            结束日期
         * @param type
         *            返回的类型
         *            ：CALENDAR_SECOUNDS,CALENDAR_MINUTES,CALENDAR_HOURS,CALENDAR_YEAR,CALENDAR_DAY
         *
         * @return
         */
        private static int getDayPoor(java.util.Date date, java.util.Date dateto, String type) {

            Long dateTime = date != null ? date.getTime() : System.currentTimeMillis();
            Long dateTimeTo = dateto != null ? dateto.getTime() : System.currentTimeMillis();
            Long time = (dateTimeTo - dateTime);

            switch (type) {
                case CALENDAR_SECOUNDS:
                    return (int) (time / 1000);
                case CALENDAR_MINUTES:
                    return (int) Math.round((time / 1000 / 60));
                case CALENDAR_HOURS:
                    return (int) (time / 1000 / 60 / 60);
                case CALENDAR_YEAR:
                    if (isEmpty(date, dateto)) {
                        return 0;
                    }
                    calendar.setTime(date);
                    int year1 = calendar.get(Calendar.YEAR);
                    calendar.setTime(dateto);
                    int year2 = calendar.get(Calendar.YEAR);
                    return year2 - year1;
                case CALENDAR_DAY:
                default:
                    return (int) (time / 1000 / 60 / 60 / 24);
            }
        }

        /**
         * 获得dateto-date的天数 eg:(20170310-20170305=5)
         *
         * @param date
         *            开始日期
         * @param dateto
         *            结束日期
         * @return
         */
        public static int getDay(java.util.Date date, java.util.Date dateto) {

            return getDayPoor(date, dateto, "default");

        }

        /**
         * 获得dateto-date的天数 eg:(20170310-20170305=5)
         *
         * @param date
         * @param dateto
         *            dateto||date 为空 取 System.currentTimeMillis()
         * @param type
         *            type =
         *            [CALENDAR_SECOUNDS,CALENDAR_MINUTES,CALENDAR_HOURS...]
         *            REUTN SECOUNDS MINUTES 。。。
         * @return
         */
        public static int getDay(java.util.Date date, java.util.Date dateto, final String type) {
            return getDayPoor(date, dateto, type);
        }

        /**
         * 返回当前日期的 yyyy-MM-dd 00:00:00格式
         *
         * @return
         */
        public static Date toDay() {
            String dateStr = "";
            try {
                Date d = new Date();
                dateStr = String.format("%tF 00:00:00", d);
                return dateFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 返回默认的日期格式化类
         *
         * @return
         */
        public static synchronized SimpleDateFormat getDateFormat() {
            return dateFormat;
        }

        /**
         * 根据参数重置dateFormat
         *
         * @param formatStr
         *            格式化字符串
         */
        public static void newDateFormat(String formatStr) {
            dateFormat = new SimpleDateFormat(formatStr);
        }

        public static Date converStrToDate(String dateStr){
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return dateFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
