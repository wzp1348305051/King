package com.wzp.king.common.util;

import android.text.TextUtils;

import com.wzp.king.common.bean.constant.EmptyConstant;
import com.wzp.king.common.bean.constant.ExceptionConstant;

import java.util.Collection;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 空值工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/1
 */

public class EmptyUtil {

    private EmptyUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    @Nullable
    public static String nullIfEmpty(@Nullable String str) {
        return isEmptyText(str) ? null : str;
    }

    @NonNull
    public static String emptyIfNull(@Nullable String str) {
        return str == null ? EmptyConstant.EMPTY_STRING : str;
    }

    /**
     * 判断文本是否为空
     */
    public static boolean isEmptyText(@Nullable CharSequence str) {
        return TextUtils.isEmpty(str);
    }

    /**
     * 判断集合是否为空
     */
    public static boolean isEmptyCollection(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断字典是否为空
     */
    public static boolean isEmptyMap(Map map) {
        return map == null || map.isEmpty();
    }
}
