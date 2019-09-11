package com.wzp.king.common.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.wzp.king.common.bean.constant.ExceptionConstant;

import androidx.annotation.NonNull;

/**
 * 轮询服务工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2019-09-09
 */

public class PollingUtil {

    private PollingUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    /**
     * 开启轮询服务
     */
    public static void startPollingService(@NonNull Context context, int intervalMillis,
                                           @NonNull Class<?> clazz, @NonNull String action) {
        AlarmManager manager = SysSrvUtil.getAlarmManager(context);

        //包装需要执行Service的Intent
        Intent intent = new Intent(context, clazz);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //触发服务的起始时间
        long triggerAtTime = SystemClock.elapsedRealtime();

        //使用AlarmManger的setRepeating方法设置定期执行的时间间隔和需要执行的Service
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime, intervalMillis,
                pendingIntent);
    }

    /**
     * 停止轮询服务
     */
    public static void stopPollingService(@NonNull Context context, @NonNull Class<?> clazz,
                                          @NonNull String action) {
        AlarmManager manager = SysSrvUtil.getAlarmManager(context);
        Intent intent = new Intent(context, clazz);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        manager.cancel(pendingIntent);
    }

}
