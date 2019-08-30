package com.wzp.king.common.util;

import com.wzp.king.common.bean.constant.EmptyConstant;
import com.wzp.king.common.bean.constant.ExceptionConstant;

import java.util.regex.Pattern;

import androidx.annotation.Nullable;

/**
 * 字符串工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/27
 */

public class StringUtil {

    /**
     * 限制为“6-20位字母+数字”
     */
    @SuppressWarnings("all")
    public static boolean isPasswordValid(@Nullable String text) {
        if (EmptyUtil.isEmptyText(text)) {
            return false;
        }

        String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,20})$";
        return text.matches(regex);
    }

    public static boolean isNumber(@Nullable String text) {
        return !EmptyUtil.isEmptyText(text) && Pattern.compile("[-+]?[0-9]*").matcher(text).matches();
    }

    public static boolean isNumberDecimal(@Nullable String text) {
        if (EmptyUtil.isEmptyText(text)) {
            return false;
        }

        Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
        return pattern.matcher(text).matches();
    }

    /**
     * 判断手机号是否合法
     */
    @SuppressWarnings("all")
    public static boolean isPhoneNumber(@Nullable String text) {
        return isNumber(text) && text.length() == 11;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     */
    public static boolean isEmail(@Nullable String text) {
        if (EmptyUtil.isEmptyText(text)) {
            return false;
        }

        Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        return pattern.matcher(text).matches();
    }

    public static int parseInt(@Nullable String text) {
        return parseInt(text, EmptyConstant.EMPTY_INT);
    }

    @SuppressWarnings("all")
    public static int parseInt(@Nullable String text, int defaultValue) {
        if (EmptyUtil.isEmptyText(text)) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static double parseDouble(@Nullable String text) {
        return parseDouble(text, EmptyConstant.EMPTY_DOUBLE);
    }

    @SuppressWarnings("all")
    public static double parseDouble(@Nullable String text, double defaultValue) {
        if (EmptyUtil.isEmptyText(text)) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(text);
        } catch (Exception e) {
            return defaultValue;
        }
    }

}
