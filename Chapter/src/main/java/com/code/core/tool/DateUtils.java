package com.code.core.tool;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class DateUtils {
    private static final long TIME_DEVIATION = 600000; // 10min unit:ms
    public final static String DATE_FORMAT_DEFAULT = "yyyy-MM-dd";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_CONTACT = "yyyyMMdd";
    public static final String DATE_FORMAT_CHINESE = "yyyy年MM月dd日";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String JIFEN_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME_FORMAT_MS = "yyyyMMddHHmmssSSS";
    public static final String DATE_TIME_FORMAT_DB = "yyyy-MM-dd.HH.mm. ss. ms";
    public static final String CMS_DRAW_SEQUENCE_FORMAT = "yyyyMMddHHmmss";
    public static final String CMS_DRAW_SEQUENCE_TIME = "HHmmss";
    public static final String LOAN_REQUEST_CODE = "yyyyMMddHHmmss";
    public static final String LOAN_CODE = "yyyyMMddHHmmss";
    public static final String YEAR_TO_SECOND = "yyyyMMddHHmmss";
    public static final String POINT_TIME = "yyyy-MM-dd HH:mm:ss" + ".0";
    public static final String p2pUser = "dd-M月-yy年";
    public static final String DOT_DIGIT_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

    public static final String XINBAO_DATE_FORMAT = "yyyy/MM/dd";
    public static final String MEMBER_DATE_FORMAT = "yyyy/M/dd";
    public static final String MEMBER_SYSTEM_DATE_FORMAT = "yyyy-MM-dd";
    public static final String MATCH_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_STRING_FORMAT = "yyyyMMdd";
    public static final String XINBAO_MOBILENO_SEQUENCE_FORMATE = "yyyyMMddHHmmss";

    private static Map<String, SimpleDateFormat> dateFormatMap = new HashMap<String, SimpleDateFormat>();
    public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DateUtils.class);


    /**
     * 获取当天的最后一毫秒的日期
     *
     * @return
     */
    public static Date endOfToday() {
        return endOfDay(new Date());
    }

    /**
     * 获取当天的最早一毫秒的日期
     *
     * @return
     */
    public static Date startOfToday() {
        return startOfDay(new Date());
    }

    /**
     * 获取某天的最后一毫秒的日期
     *
     * @param date
     * @return
     */
    public static Date endOfDay(Date date) {
        DateTime startDateTime = new DateTime(date).dayOfYear().roundFloorCopy();
        return startDateTime.plusDays(1).minusMillis(1).toDate();
    }

    /**
     * 获取某天的最早一毫秒的日期
     *
     * @param date
     * @return
     */
    public static Date startOfDay(Date date) {
        return new DateTime(date).dayOfYear().roundFloorCopy().toDate();
    }

    /**
     * 获取某天以后几天的日期字符串形式
     *
     * @param format 日期字符串格式
     * @param date   输入日期
     * @param num    增加天数
     * @return
     */
    public static String getDateAfterNowByNum(String format, Date date, int num) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, num);
        Date newDate = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(newDate);
    }

    /**
     * 获取某天前后几天
     *
     * @param date
     * @param num  为负表示前几天，为正表示后继天
     * @return
     */
    public static Date getDateAfterByNum(Date date, int num) {
        DateTime dt = new DateTime(date);
        return dt.plusDays(num).toDate();
    }

    /**
     * 获取当前时间后后特定小时的时间
     *
     * @param format
     * @param hour
     * @return
     */
    public static String getDateTimeAfterNowByHour(String format, int hour) {
        Date as = new Date(System.currentTimeMillis() + hour * 60 * 60 * 1000);
        SimpleDateFormat matter1 = new SimpleDateFormat(format);
        String time = matter1.format(as);

        return time;
    }


    /**
     * 得到某一天是这一年的第几周
     *
     * @param date
     * @return
     */
    public static int getWeek(String date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        return week;
    }

    /**
     * 得到某一天是这一年的第几周
     *
     * @param date
     * @return
     */
    public static String getWeekName(String date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        return "第" + String.valueOf(week) + "周";
    }

    /**
     * 判断特定时间是否早于今天最早时刻
     *
     * @param date
     * @return
     */
    public static boolean beforeToday(Date date) {
        return date.compareTo(DateUtils.startOfToday()) < 0;
    }

    /**
     * 判断特定时间是否晚于今天最晚时刻
     *
     * @param date
     * @return
     */
    public static boolean afterToday(Date date) {
        return date.compareTo(DateUtils.endOfToday()) > 0;
    }

    /**
     * 判断两个日期是否为一个月中相同的天
     * 如datetime = 2.14, other = 7.14,则返回true
     *
     * @param datetime
     * @param other
     * @return
     */
    public static boolean isOnSameDayOfMonth(DateTime datetime, DateTime other) {
        return datetime.getDayOfMonth() == other.getDayOfMonth();
    }

    /**
     * 将特定日期转化成"yyyy-MM-dd"形式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, DATE_FORMAT);
    }

    /**
     * 获取指定日期所在月的最后一天
     *
     * @param date
     * @return
     */
    public static String getLastOfMonth(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = DateUtils.formatDate(ca.getTime());

        return last;
    }

    /**
     * 获取当前月的第一天
     */
    public static String getfirstOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String first = format.format(c.getTime());
        return first;
    }


    /**
     * 获取当前月的第一天
     */
    public static String getfirstOfMonthPre(Date date, int prevmonth) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, prevmonth);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String first = format.format(c.getTime());
        return first;
    }


    /**
     * 获取当前月上月的第n天
     */

    //获取前月的第一天
    public static String getfirstOfLastMonth(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, n);//设置为1号,当前日期既为本月第一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstDay = format.format(c.getTime());
        return firstDay;
    }


    /**
     * 将特定日期转化成"yyyyMMdd"形式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDateContact(Date date) {
        return formatDate(date, DATE_FORMAT_CONTACT);
    }

    /**
     * 将特定日期转化成"yyyy年MM月dd日"形式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDateToChinese(Date date) {
        return formatDate(date, DATE_FORMAT_CHINESE);
    }

    /**
     * 获取两个日期间相差的天数
     *
     * @param dateA
     * @param dateB
     * @return
     */
    public static long diffDateDays(Date dateA, Date dateB) {
        long nd = 1000 * 24 * 60 * 60;
        long diff = Math.abs(dateA.getTime() - dateB.getTime());
        return diff / nd;
    }

    /**
     * 将特定日期转化成"dd-M月-yy年"形式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDateToChinese2(Date date) {
        return formatDate(date, p2pUser);
    }

    /**
     * 将特定日期转化成"yyyy/M/dd"形式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDateToMember(Date date) {
        return formatDate(date, MEMBER_DATE_FORMAT);
    }

    /**
     * 将特定日期转化成"yyyy-MM-dd HH:mm:ss"形式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, DATE_TIME_FORMAT);
    }

    /**
     * 将特定日期转化成"yyyy-MM-dd HH:mm"形式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDateTimeJiFen(Date date) {
        return formatDate(date, JIFEN_DATE_TIME_FORMAT);
    }

    /**
     * 将特定日期转化成"yyyy-MM-dd.HH.mm. ss. ms"形式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDateTimeDB(Date date) {
        return formatDate(date, DATE_TIME_FORMAT_DB);
    }

    /**
     * 将特定日期转化成"yyyy-MM-dd HH:mm:ss"+".0"形式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDateTimePoint(Date date) {
        return formatDate(date, POINT_TIME);
    }

    /**
     * 将"yyyy-MM-dd"格式的字符串转化为Date
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        return parseDate(date, DATE_FORMAT);
    }

    /**
     * 将"yyyy-MM-dd HH:mm:ss"格式的字符串转化为Date
     *
     * @param date
     * @return
     */
    public static Date parseDateTime(String date) {
        return parseDate(date, DATE_TIME_FORMAT);
    }

    /**
     * 将特定日期转化成"yyyyMMddHHmmss"形式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDateAsCmsDrawSequence(Date date) {
        return formatDate(date, CMS_DRAW_SEQUENCE_FORMAT);
    }

    /**
     * 将特定日期转化成"yyyyMMddHHmmssSSS"形式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDateAsMs(Date date) {
        return formatDate(date, DATE_TIME_FORMAT_MS);
    }

    /**
     * 将"yyyy/MM/dd"格式的字符串转化为Date
     *
     * @param date
     * @return
     */
    public static Date parseXinbaoDateFormat(String date) throws ParseException {
        return parseDate(date, XINBAO_DATE_FORMAT);
    }

    /**
     * 将特定日期转化成"yyyyMMdd"形式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDateAsString(Date date) {
        return formatDate(date, DATE_STRING_FORMAT);
    }

    /**
     * 将特定日期转化成指定形式的字符串
     * 如果date为null,则返回null
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static String formatDate(Date date, String dateFormat) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(dateFormat).format(date);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 将指定格式的字符串转化为Date
     * 如果date为null,则返回null
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static Date parseDate(String date, String dateFormat) {
        try {
            if (date == null) {
                return null;
            }
            return new SimpleDateFormat(dateFormat).parse(date, new ParsePosition(0));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将特定日期转化成指定形式的字符串
     *
     * @param date
     * @return
     */
    public static String format(String formatString, Date date) {
        return new SimpleDateFormat(formatString).format(date);
    }

    /**
     * 将特定日期转化成"yyyyMMddHHmmss"形式的字符串
     *
     * @param date
     * @return
     */
    public static String formatDateAsYearToSecond(Date date) {
        return formatDate(date, YEAR_TO_SECOND);
    }

    /**
     * 验证实际时间在预期时间的误差范围内
     *
     * @param expectedDate
     * @param actualDate
     */
    public static void isDateTimeInDeviation(Date expectedDate, Date actualDate) {

        if (Math.abs(expectedDate.getTime() - actualDate.getTime()) < TIME_DEVIATION) {
            return;
        }
        throw new RuntimeException(String.format("DateTime overpass deviation!Expected:%s,Actual:%s", expectedDate, actualDate));
    }

    /**
     * 获取两个日期的间隔天数
     *
     * @param dateFrom
     * @param dateTo
     * @return
     */
    public static int calculateIntervalDays(Date dateFrom, Date dateTo) {
        return Days.daysBetween(new DateTime(DateUtils.endOfDay(dateFrom)), new DateTime(DateUtils.endOfDay(dateTo))).getDays();
    }

    /**
     * 将原格式为oldFormat的字符串日期转化为newFormat的字符串日期
     *
     * @param oldFormat
     * @param newFormat
     * @param timeStr
     * @return
     * @throws ParseException
     */
    public static String changeDateFormat(String oldFormat, String newFormat, String timeStr) throws ParseException {
        Date date1 = new SimpleDateFormat(oldFormat).parse(timeStr);
        String date = new SimpleDateFormat(newFormat).format(new Date(date1.getTime()));
        return date;
    }

    /**
     * 将日期转化为指定格式，若日期为null,则返回null
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDateMayNull(Date date, String format) {
        String ret = null;
        if (date == null) {
            return ret;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 一个月第一天
     *
     * @param date
     * @return
     */
    public static String firstDateOfMonth(Date date) {
        DateTime dt = new DateTime(date.getTime()).withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
        return formatDate(dt.toDate(), DATE_TIME_FORMAT);
    }

    /**
     * 一个月最后一天
     *
     * @param date
     * @return
     */
    public static String lastDateOfMonth(Date date) {
        DateTime dt = new DateTime(date.getTime()).withDayOfMonth(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).plusMonths(1).minusMillis(1);
        return formatDate(dt.toDate(), DATE_TIME_FORMAT);
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    /*
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {WeekDays.SUNDAY.getValue(), WeekDays.MONDAY.getValue(), WeekDays.TUESDAY.getValue(),
                WeekDays.WEDNESDAY.getValue(), WeekDays.THURSDAY.getValue(), WeekDays.FRIDAY.getValue(), WeekDays.SATURDAY.getValue()};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }
    */
    /**
     * 将特定时间减去一定秒数
     *
     * @param date
     * @param seconds
     * @return
     */
    public static String minusSeconds(Date date, int seconds) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.set(Calendar.SECOND, startDate.get(Calendar.SECOND) - seconds);
        return df.format(startDate.getTime());
    }

    /**
     * 获取当天最早时间字符串，格式为"yyyy-MM-dd"
     *
     * @return
     */
    public static String today() {
        return formatDate(startOfToday());
    }

    public static Date parseAsDateDB(String date) {
        return parseDate(date, DATE_TIME_FORMAT_DB);
    }

    public static Date parseDateTest(String src) {
        if (src.isEmpty()) {
            //EmptyChecker.isEmpty(src)
            return null;
        }

        return parseDateTest(src, DATE_FORMAT_DEFAULT);
    }

    public static Date parseDateTest(String src, String dateTemplate) {     //  synchronized
        if (src.isEmpty()) {
            //EmptyChecker.isEmpty(src)
            return null;
        }

        try {
            return getSimpleDateFormat(dateTemplate).parse(src);
        } catch (ParseException e) {
            throw new RuntimeException(String.format("unsupported date template:%s", src), e);
        }
    }

    public static SimpleDateFormat getSimpleDateFormat(String dateTemplate) {
        synchronized (dateFormatMap) {
            if (!dateFormatMap.containsKey(dateTemplate)) {
                dateFormatMap.put(dateTemplate, new SimpleDateFormat(dateTemplate));
            }

            return dateFormatMap.get(dateTemplate);
        }
    }

    public static String getOffsetDateyyyyyMMdd(int dayoffset, String formate) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, dayoffset);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat(formate);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 计算规则为：Floor((今天日期-生日) / 365.25)
     * 31557600000 = 365.25 * 24 * 60 * 60 * 1000
     *
     * @param birthday
     * @return
     */
    public static int calculateAgeAccordingBirthday(Date birthday) {
        if (birthday == null) {
            throw new IllegalArgumentException("Birthday cannot be null");
        }
        return (int) Math.floor((System.currentTimeMillis() - birthday.getTime()) / 31557600000L);
    }

    public static Date convertStr2UsuallyDate(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }

        Date resultDate = null;

        resultDate = parseDate(dateStr);
        if (resultDate != null) {
            return resultDate;
        }

        resultDate = parseDateTime(dateStr);
        return resultDate;
    }

    /**
     * 基于日期的计算
     *
     * @param date
     * @param year  增加的年数,可以为正数、0、负数
     * @param month 增加的月数,可以为正数、0、负数
     * @param day   增加的天数,可以为正数、0、负数
     * @param hour  增加的小时数,可以为正数、0、负数
     * @param min   增加的分钟数,可以为正数、0、负数
     * @param sec   增加的秒数,可以为正数、0、负数
     * @return
     */
    public static Date calculateDateTime(Date date, int year, int month, int day, int hour, int min, int sec) {
        if (date == null) {
            logger.warn("use null date to calculate date time.Return null");
            return null;
        }

        DateTime dt = new DateTime(date);
        dt = dt.plusYears(year).plusMonths(month).plusDays(day).plusHours(hour).plusMinutes(min).plusSeconds(sec);
        return dt.toDate();
    }

    /**
     * 获取精确到秒的时间戳
     *
     * @param date
     * @return
     */
    public static long getSecondTimestamp(Date date, Boolean isMec) {
        if (null == date) {
            return 0;
        }
        String timestamp = "";
        if (isMec) {
            timestamp = String.valueOf(date.getTime());
        } else {
            timestamp = String.valueOf(date.getTime() / 1000);
        }


        return Long.valueOf(timestamp);
    }

    /**
     * 获取当前时间相隔一段时间的时间戳(秒级别)
     * @param spacingTime 与当前时间的时间间隔  例如：5分钟后，传300；5分钟前传-300，当前时间则传0
     * @return 时间戳（秒级别） 例如1596790915
     */
    public static long getRecentTimestamp(int spacingTime) {
        String timestamp = "";
        timestamp = String.valueOf(new Date().getTime() / 1000 + spacingTime);
        return Long.valueOf(timestamp);
    }

    /**
     * 获取当前时间相隔一段时间的标准时间(yyyy-MM-dd HH:mm:ss形式)
     * @param spacingTime 与当前时间的时间间隔  例如：5分钟后，传300；5分钟前传-300，当前时间则传0
     * @return 标准时间，例如 2020-08-18 09:51:43
     */
    public static String getRecentDateTime(int spacingTime) {
        long timestamp;
        timestamp = System.currentTimeMillis()  + spacingTime*1000;
        String dateTime = "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateTime = sdf.format(timestamp);
        return dateTime;
    }

    /**
     * 获取当前格式化的日期时间
     *
     * @param format 时间日期格式 例如yyyy-MM-dd HH:mm:ss
     * @return 格式化的日期时间 例如2020-08-07 17:00:44
     */
    public static String getFormatDateTime(String format) {
        String dataTime = "";
        if (format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        dataTime = DateTimeFormatter.ofPattern(format).format(LocalDateTime.now());
        return dataTime;
    }

    //ms时间戳格式化
    public static String timeStamp2Date(Long mSeconds,String format) {

        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(mSeconds));
    }

    public static void main(String[] args) {
//        System.out.println(getfirstOfMonthPre(new Date(), 0));
//        System.out.println(getfirstOfMonthPre(new Date(), -1));
//        System.out.println(getfirstOfMonthPre(new Date(), -2));
//        System.out.println(calculateDateTime(new Date(),0,0,0,0,30,0));
//        System.out.println(getSecondTimestamp(calculateDateTime(new Date(),0,0,0,0,30,0),Boolean.FALSE));
//        System.out.println(getFormatDateTime("yyyy-MM-dd HH:mm:ss"));
        System.out.println(getRecentDateTime(1800));
        System.out.println(getRecentTimestamp(0));

        System.out.println(getRecentTimestamp(-300));
        System.out.println(getSecondTimestamp(parseDateTime("2020-11-14 17:35:00"),false));

    }
}
