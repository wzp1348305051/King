package com.wzp.king.common.util;

import android.content.SharedPreferences;

import com.tencent.mmkv.MMKV;
import com.wzp.king.common.bean.constant.EmptyConstant;
import com.wzp.king.common.bean.constant.ExceptionConstant;
import com.wzp.king.common.bean.constant.GlobalConstant;

import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Key, Value存储工具类(默认使用MMKV进行存取)
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/11
 */

public class KV {
    static {
        MMKV.initialize(GlobalConstant.APP_CONTEXT);
    }

    @NonNull
    private static SharedPreferences sSp = MMKV.defaultMMKV();

    private KV() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    /**
     * 替换默认实现类
     */
    public static void sp(@NonNull SharedPreferences sp) {
        sSp = sp;
    }

    public static boolean putString(@NonNull String key, @Nullable String value) {
        return putString(key, value, true);
    }

    /**
     * @param ignoreResult 是否忽略写入成功与否结果，忽略结果，加速并发写入，return值为true
     */
    public static boolean putString(@NonNull String key, @Nullable String value, boolean ignoreResult) {
        if (EmptyUtil.isEmptyText(key)) {
            return false;
        }

        if (ignoreResult) {
            sSp.edit().putString(key, value).apply();
            return true;
        } else {
            return sSp.edit().putString(key, value).commit();
        }
    }

    @NonNull
    public static String getString(@NonNull String key) {
        return getString(key, EmptyConstant.EMPTY_STRING);
    }

    @NonNull
    public static String getString(@NonNull String key, @Nullable String defaultValue) {
        return EmptyUtil.emptyIfNull(sSp.getString(key, defaultValue));
    }

    public static boolean putStringSet(@NonNull String key, @Nullable Set<String> value) {
        return putStringSet(key, value, true);
    }

    /**
     * @param ignoreResult 是否忽略写入成功与否结果，忽略结果，加速并发写入，return值为true
     */
    public static boolean putStringSet(@NonNull String key, @Nullable Set<String> value, boolean ignoreResult) {
        if (EmptyUtil.isEmptyText(key)) {
            return false;
        }

        if (ignoreResult) {
            sSp.edit().putStringSet(key, value).apply();
            return true;
        } else {
            return sSp.edit().putStringSet(key, value).commit();
        }
    }

    @NonNull
    public static Set<String> getStringSet(@NonNull String key) {
        return getStringSet(key, EmptyConstant.EMPTY_STRING_SET);
    }

    @NonNull
    public static Set<String> getStringSet(@NonNull String key, @Nullable Set<String> defaultValue) {
        Set<String> set = sSp.getStringSet(key, defaultValue);
        return set == null ? EmptyConstant.EMPTY_STRING_SET : set;
    }

    public static boolean putInt(@NonNull String key, int value) {
        return putInt(key, value, true);
    }

    /**
     * @param ignoreResult 是否忽略写入成功与否结果，忽略结果，加速并发写入，return值为true
     */
    public static boolean putInt(@NonNull String key, int value, boolean ignoreResult) {
        if (EmptyUtil.isEmptyText(key)) {
            return false;
        }

        if (ignoreResult) {
            sSp.edit().putInt(key, value).apply();
            return true;
        } else {
            return sSp.edit().putInt(key, value).commit();
        }
    }

    public static int getInt(@NonNull String key) {
        return getInt(key, EmptyConstant.EMPTY_INT);
    }

    public static int getInt(@NonNull String key, int defaultValue) {
        return sSp.getInt(key, defaultValue);
    }

    public static boolean putLong(@NonNull String key, long value) {
        return putLong(key, value, true);
    }

    /**
     * @param ignoreResult 是否忽略写入成功与否结果，忽略结果，加速并发写入，return值为true
     */
    public static boolean putLong(@NonNull String key, long value, boolean ignoreResult) {
        if (EmptyUtil.isEmptyText(key)) {
            return false;
        }

        if (ignoreResult) {
            sSp.edit().putLong(key, value).apply();
            return true;
        } else {
            return sSp.edit().putLong(key, value).commit();
        }
    }

    public static long getLong(@NonNull String key) {
        return getLong(key, EmptyConstant.EMPTY_LONG);
    }

    public static long getLong(@NonNull String key, long defaultValue) {
        return sSp.getLong(key, defaultValue);
    }

    public static boolean putFloat(@NonNull String key, float value) {
        return putFloat(key, value, true);
    }

    /**
     * @param ignoreResult 是否忽略写入成功与否结果，忽略结果，加速并发写入，return值为true
     */
    public static boolean putFloat(@NonNull String key, float value, boolean ignoreResult) {
        if (EmptyUtil.isEmptyText(key)) {
            return false;
        }

        if (ignoreResult) {
            sSp.edit().putFloat(key, value).apply();
            return true;
        } else {
            return sSp.edit().putFloat(key, value).commit();
        }
    }

    public static float getFloat(@NonNull String key) {
        return getFloat(key, EmptyConstant.EMPTY_FLOAT);
    }

    public static float getFloat(@NonNull String key, float defaultValue) {
        return sSp.getFloat(key, defaultValue);
    }

    public static boolean putBoolean(@NonNull String key, boolean value) {
        return putBoolean(key, value, true);
    }

    /**
     * @param ignoreResult 是否忽略写入成功与否结果，忽略结果，加速并发写入，return值为true
     */
    public static boolean putBoolean(@NonNull String key, boolean value, boolean ignoreResult) {
        if (EmptyUtil.isEmptyText(key)) {
            return false;
        }

        if (ignoreResult) {
            sSp.edit().putBoolean(key, value).apply();
            return true;
        } else {
            return sSp.edit().putBoolean(key, value).commit();
        }
    }

    public static boolean getBoolean(@NonNull String key) {
        return getBoolean(key, EmptyConstant.EMPTY_BOOLEAN);
    }

    public static boolean getBoolean(@NonNull String key, boolean defaultValue) {
        return sSp.getBoolean(key, defaultValue);
    }

    public static boolean remove(@NonNull String key) {
        return remove(key, true);
    }

    public static boolean remove(@NonNull String key, boolean ignoreResult) {
        if (EmptyUtil.isEmptyText(key)) {
            return false;
        }

        if (ignoreResult) {
            sSp.edit().remove(key).apply();
            return true;
        } else {
            return sSp.edit().remove(key).commit();
        }
    }

    public static boolean clear() {
        return clear(true);
    }

    public static boolean clear(boolean ignoreResult) {
        if (ignoreResult) {
            sSp.edit().clear().apply();
            return true;
        } else {
            return sSp.edit().clear().commit();
        }
    }

}
