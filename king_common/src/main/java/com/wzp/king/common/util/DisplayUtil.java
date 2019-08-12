package com.wzp.king.common.util;

import android.content.Context;
import android.util.DisplayMetrics;

import com.wzp.king.common.bean.constant.ExceptionConstant;

import androidx.annotation.NonNull;

/**
 * 屏幕工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2017/7/25
 */

public class DisplayUtil {

    private DisplayUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    public static DisplayMetrics getDisplayMetrics(@NonNull Context context) {
        return context.getResources().getDisplayMetrics();
    }

    public static float getDensity(@NonNull Context context) {
        return getDisplayMetrics(context).density;
    }

    /**
     * 把以 dp 为单位的值，转化为以 px 为单位的值
     */
    public static int dip2Px(@NonNull Context context, float dpValue) {
        return (int) (dpValue * getDensity(context) + 0.5f);
    }

    /**
     * 把以 px 为单位的值，转化为以 dp 为单位的值
     */
    public static int px2Dip(@NonNull Context context, float pxValue) {
        return (int) (pxValue / getDensity(context) + 0.5f);
    }

    public static float getFontDensity(@NonNull Context context) {
        return getDisplayMetrics(context).scaledDensity;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(@NonNull Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight(@NonNull Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

}
