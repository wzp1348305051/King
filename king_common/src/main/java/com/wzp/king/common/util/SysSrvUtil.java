package com.wzp.king.common.util;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.wzp.king.common.bean.constant.ExceptionConstant;
import com.wzp.king.common.bean.constant.GlobalConstant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 获取各种系统服务管理类
 *
 * @author wengzhipeng
 * @since 2016-01-26
 */

public class SysSrvUtil {

    private SysSrvUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    /**
     * 获取窗口管理器
     *
     * @param context 应用程序上下文
     * @return The top-level window manager in which you can place custom windows. The
     * returned object is a android.view.WindowManager.
     */
    @NonNull
    public static WindowManager getWindowManager(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * 获取xml布局解析器
     *
     * @param context 应用程序上下文
     * @return A android.view.LayoutInflater for inflating layout resources in this
     * context.
     */
    @NonNull
    public static LayoutInflater getLayoutInflater(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 获取活动管理器
     *
     * @param context 应用程序上下文
     * @return A android.app.ActivityManager for interacting with the global activity
     * state of the system.
     */
    @NonNull
    public static ActivityManager getActivityManager(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    }

    /**
     * 获取电源服务管理器
     *
     * @param context 应用程序上下文
     * @return A android.os.PowerManager for controlling power management.
     */
    @NonNull
    public static PowerManager getPowerManger(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (PowerManager) context.getSystemService(Context.POWER_SERVICE);
    }

    /**
     * 获取闹钟服务管理器
     *
     * @param context 应用程序上下文
     * @return A android.app.AlarmManager for receiving intents at the time of your
     * choosing.
     */
    @NonNull
    public static AlarmManager getAlarmManager(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    /**
     * 获取状态栏服务管理器
     *
     * @param context 应用程序上下文
     * @return A android.app.NotificationManager for informing the user of background
     * events.
     */
    @NonNull
    public static NotificationManager getNotificationManager(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /**
     * 获取键盘锁服务管理器
     *
     * @param context 应用程序上下文
     * @return A android.app.KeyguardManager for controlling keyguard.
     */
    @NonNull
    public static KeyguardManager getKeyguardManager(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
    }

    /**
     * 获取位置服务管理器
     *
     * @param context 应用程序上下文
     * @return A android.location.LocationManager for controlling location (e.g., GPS)
     * updates.
     */
    @NonNull
    public static LocationManager getLocationManager(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    /**
     * 获取搜索服务管理器
     *
     * @param context 应用程序上下文
     * @return A android.app.SearchManager for handling search.
     */
    @NonNull
    public static SearchManager getSearchManager(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
    }

    /**
     * 获取感应器服务管理器
     *
     * @param context 应用程序上下文
     * @return A android.hardware.SensorManager for controlling sensors.
     */
    @NonNull
    public static SensorManager getSensorManager(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    /**
     * 获取存储服务管理器
     *
     * @param context 应用程序上下文
     * @return A android.os.storage.StorageManager for controlling different storage.
     */
    @NonNull
    public static StorageManager getStorageManager(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
    }

    /**
     * 获取震动服务管理器
     *
     * @param context 应用程序上下文
     * @return A android.os.Vibrator for interacting with the vibrator hardware.
     */
    @NonNull
    public static Vibrator getVibrator(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    /**
     * 获取网络连接服务管理器
     *
     * @param context 应用程序上下文
     * @return A ConnectivityManager for handling management of network
     * connections.
     */
    @NonNull
    public static ConnectivityManager getConnectivityManager(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * 获取Wifi服务管理器
     *
     * @param context 应用程序上下文
     * @return A WifiManager for management of Wifi connectivity.
     */
    @NonNull
    public static WifiManager getWifiManager(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    /**
     * 获取音频服务管理器
     *
     * @param context 应用程序上下文
     * @return A android.media.AudioManager for controlling audio services.
     */
    @NonNull
    public static AudioManager getAudioManager(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    /**
     * 获取媒体路由服务管理器
     *
     * @param context 应用程序上下文
     * @return A android.media.MediaRouter for controlling media router.
     */
    @NonNull
    public static MediaRouter getMediaRouter(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (MediaRouter) context.getSystemService(Context.MEDIA_ROUTER_SERVICE);
    }

    /**
     * 获取电话服务管理器
     *
     * @param context 应用程序上下文
     * @return A android.telephony.TelephonyManager for controlling cell phone.
     */
    @NonNull
    public static TelephonyManager getTelephonyManager(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    /**
     * 获取键盘管理器
     *
     * @param context 应用程序上下文
     * @return A android.view.inputmethod.InputMethodManager for controlling keyboard.
     */
    @NonNull
    public static InputMethodManager getInputMethodManager(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    /**
     * 获取UI模式（夜间模式和行车模式）管理器
     *
     * @param context 应用程序上下文
     * @return A android.view.inputmethod.InputMethodManager for controlling keyboard.
     */
    @NonNull
    public static UiModeManager getUiModeManager(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (UiModeManager) context.getSystemService(Context.UI_MODE_SERVICE);
    }

    /**
     * 获取下载管理器
     *
     * @param context 应用程序上下文
     * @return A android.app.DownloadManager for controlling download operation.
     */
    @NonNull
    public static DownloadManager getDownloadManager(@Nullable Context context) {
        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        return (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
    }

}
