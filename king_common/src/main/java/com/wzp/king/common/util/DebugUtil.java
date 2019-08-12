package com.wzp.king.common.util;

import com.wzp.king.common.BuildConfig;
import com.wzp.king.common.bean.constant.ExceptionConstant;

/**
 * 调试工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/20
 */

public class DebugUtil {
    private static boolean sIsDebug = BuildConfig.DEBUG;

    public static boolean isDebug() {
        return sIsDebug;
    }

    public static void switchDebug(boolean openDebug) {
        sIsDebug = openDebug;
    }

    public static void openDebug() {
        switchDebug(true);
    }

    private DebugUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

}
