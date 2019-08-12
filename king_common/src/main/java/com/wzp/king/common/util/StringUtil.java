package com.wzp.king.common.util;

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

    private StringUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    /**
     * 限制为“6-20位字母+数字”
     */
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

    /**
     * 判断手机号是否合法
     */
    public static boolean isPhoneNumber(@Nullable String text) {
        return !EmptyUtil.isEmptyText(text) && isNumber(text) && text.length() == 11;
    }

    public static int parseInt(@Nullable String text) {
        return parseInt(text, 0);
    }

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

}
