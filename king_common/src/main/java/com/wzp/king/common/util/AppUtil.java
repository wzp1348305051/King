package com.wzp.king.common.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.wzp.king.common.bean.constant.EmptyConstant;
import com.wzp.king.common.bean.constant.ExceptionConstant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * App工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/1
 */

public class AppUtil {
    private static final String TAG = AppUtil.class.getSimpleName();
    @SuppressLint("StaticFieldLeak")
    private static Context sAppContext;

    private AppUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    public static void init(@NonNull Context context) {
        sAppContext = context.getApplicationContext();
    }

    /**
     * 获取全局ApplicationContext
     *
     * @return ApplicationContext
     */
    @NonNull
    public static Context getAppContext() {
        if (sAppContext == null) {
            throw new NullPointerException(ExceptionConstant.EXCEPTION_NO_INIT);
        }
        return sAppContext;
    }

    /**
     * 获取本应用包名
     *
     * @return 本应用包名
     */
    @NonNull
    public static String getPackageName() {
        return EmptyUtil.emptyIfNull(getAppContext().getPackageName());
    }

    /**
     * 获取PackageManager
     */
    @NonNull
    public static PackageManager getPackageManager() {
        return getAppContext().getPackageManager();
    }

    /**
     * 获取PackageInfo
     */
    @Nullable
    public static PackageInfo getPackageInfo() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            L.eTag(TAG, e);
        }
        return null;
    }

    /**
     * 获取AndroidManifest中meta-data信息
     */
    @NonNull
    public static String getMetaData(@Nullable String key) {
        if (EmptyUtil.isEmptyText(key)) {
            return EmptyConstant.EMPTY_STRING;
        }

        try {
            return getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA).metaData.getString(key, EmptyConstant.EMPTY_STRING);
        } catch (PackageManager.NameNotFoundException e) {
            L.eTag(TAG, e);
        }
        return EmptyConstant.EMPTY_STRING;
    }

    /**
     * 获取版本名称
     */
    @NonNull
    public static String getVersionName() {
        PackageInfo packageInfo = getPackageInfo();
        return packageInfo == null ? EmptyConstant.EMPTY_STRING : EmptyUtil.emptyIfNull(packageInfo.versionName);
    }

    /**
     * 读取 App 的主版本号，例如 3.1.2，主版本号是 3
     */
    private static int getMajorVersion() {
        String versionName = getVersionName();
        if (EmptyUtil.isEmptyText(versionName)) {
            return -1;
        }

        String[] parts = versionName.split("\\.");
        if (parts.length > 0) {
            return Integer.parseInt(parts[0]);
        }
        return -1;
    }

    /**
     * 读取 App 的次版本号，例如 3.1.2，次版本号是 1
     */
    private static int getMinorVersion() {
        String versionName = getVersionName();
        if (EmptyUtil.isEmptyText(versionName)) {
            return -1;
        }

        String[] parts = versionName.split("\\.");
        if (parts.length > 1) {
            return Integer.parseInt(parts[1]);
        }
        return -1;
    }

    /**
     * 读取 App 的修正版本号，例如 3.1.2，修正版本号是 2
     */
    public static int getFixVersion() {
        String versionName = getVersionName();
        if (EmptyUtil.isEmptyText(versionName)) {
            return -1;
        }

        String[] parts = versionName.split("\\.");
        if (parts.length > 2) {
            return Integer.parseInt(parts[2]);
        }
        return -1;
    }

    /**
     * 获取版本号
     */
    public static int getVersionCode() {
        PackageInfo packageInfo = getPackageInfo();
        return packageInfo == null ? EmptyConstant.EMPTY_INT : packageInfo.versionCode;
    }

    /**
     * 获取安卓版本号
     */
    @NonNull
    public static String getAndroidVersionName() {
        return "Android " + Build.VERSION.RELEASE;
    }

    public static void exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

}
