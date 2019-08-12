package com.wzp.king.common.util;

import com.wzp.king.common.bean.constant.EmptyConstant;
import com.wzp.king.common.bean.constant.ExceptionConstant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 日期工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/18
 */

public class DateUtil {
    public static final DateFormat FMT_DATE = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    public static final DateFormat FMT_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    private static long sTimeCalibrator = 0L;// 本地时间和服务时间校准时间差(serverTime - localTime)

    private DateUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    public static void setTimeCalibrator(long timeCalibrator) {
        sTimeCalibrator = timeCalibrator;
    }

    /**
     * 校准后时间
     */
    public static long currentTimeMillis() {
        return correctTime(System.currentTimeMillis());
    }

    /***
     * 根据时间戳转换时间
     */
    @NonNull
    public static String format(@Nullable Date date, @Nullable DateFormat format) {
        if (date == null) {
            return EmptyConstant.EMPTY_STRING;
        }

        if (format == null) {
            format = FMT_DATE_TIME;
        }
        return format.format(date);
    }

    /***
     * 根据时间戳转换时间
     */
    @NonNull
    public static String format(long timeMillis, @Nullable DateFormat format) {
        if (timeMillis <= 0) {
            return EmptyConstant.EMPTY_STRING;
        }

        if (format == null) {
            format = FMT_DATE_TIME;
        }
        return format.format(new Date(timeMillis));
    }

    /**
     * 校验后的时间
     */
    @NonNull
    public static String currentTime() {
        return FMT_DATE_TIME.format(new Date(currentTimeMillis()));
    }

    /**
     * 校验后的时间
     */
    @NonNull
    public static String currentDate() {
        return FMT_DATE.format(new Date(currentTimeMillis()));
    }

    /**
     * 判断2个日期毫秒是否是同一天
     */
    public static boolean isSameDay(@Nullable Date firstDate, @Nullable Date secondDate) {
        if (firstDate == null || secondDate == null) {
            return false;
        }

        return FMT_DATE.format(firstDate).equals(FMT_DATE.format(secondDate));
    }

    /**
     * 判断2个日期毫秒是否是同一天
     */
    public static boolean isSameDay(long firstTimeMillis, long secondTimeMillis) {
        if (firstTimeMillis <= 0 || secondTimeMillis <= 0) {
            return false;
        }

        return isSameDay(new Date(firstTimeMillis), new Date(secondTimeMillis));
    }

    /**
     * 校准时间
     */
    private static long correctTime(long time) {
        return time + sTimeCalibrator;
    }
}
