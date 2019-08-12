package com.wzp.king.common.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.widget.FrameLayout;

import com.wzp.king.common.bean.constant.ExceptionConstant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Activity工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/5
 */

public class ActivityUtil {

    private ActivityUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    /**
     * Activity是否死亡
     */
    public static boolean isActivityDied(@Nullable Activity activity) {
        return activity == null || activity.isFinishing() || activity.isDestroyed();
    }

    /**
     * 获取DecorView
     */
    @Nullable
    public static FrameLayout getDecorView(@Nullable Activity activity) {
        if (isActivityDied(activity)) {
            return null;
        }
        return activity.getWindow().getDecorView().findViewById(android.R.id.content);
    }

    /**
     * 打开系统设置页
     */
    public static void openSystemSetting(@NonNull Activity activity, int requestCode) {
        Uri uri = Uri.fromParts("package", AppUtil.getPackageName(), null);
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(uri);
        activity.startActivityForResult(intent, requestCode);
    }
}
