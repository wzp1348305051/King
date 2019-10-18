package com.wzp.king.common.util.permission;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.SparseArray;

import com.wzp.king.common.bean.constant.GlobalConstant;
import com.wzp.king.common.bean.constant.RequestConstant;
import com.wzp.king.common.util.DialogUtil;
import com.wzp.king.common.widget.dialog.CustomDialog;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

/**
 * 权限请求工具类
 *
 * @author wengzhipeng
 * @since 2018/3/5
 */

public class PermissionHelper {
    private SparseArray<IPermissionCallback> mCallbacks;

    private static class Holder {
        private static final PermissionHelper INSTANCE = new PermissionHelper();
    }

    private PermissionHelper() {
        mCallbacks = new SparseArray<>();
    }

    /**
     * 获取权限管理帮助类实例
     *
     * @return PermissionHelper实例
     */
    @NonNull
    public static PermissionHelper getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 判断Activity是否已经获取相应权限
     *
     * @param activity   上下文
     * @param permission 权限名称，由PermissionRequest获取
     * @return true or false
     */
    public boolean hasSelfPermission(@NonNull Activity activity, @NonNull String permission) {
        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(activity, permission);
    }

    /**
     * 判断Activity是否已经获取相应一些权限
     *
     * @param activity    上下文
     * @param permissions 权限名称列表，每个权限由PermissionRequest获取
     * @return true or false
     */
    public boolean hasSelfPermissions(@NonNull Activity activity, @NonNull String... permissions) {
        for (String permission : permissions) {
            if (!hasSelfPermission(activity, permission)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否应该展示权限获取提示框
     *
     * @param activity   上下文
     * @param permission 权限名称，由PermissionRequest获取
     * @return true or false
     */
    public boolean shouldShowRequestPermissionRationale(@NonNull Activity activity, @NonNull String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    /**
     * 是否应该展示权限获取提示框
     *
     * @param activity    上下文
     * @param permissions 权限名称列表，每个权限由PermissionRequest获取
     * @return true or false
     */
    public boolean shouldShowRequestPermissionRationales(@NonNull Activity activity, @NonNull String... permissions) {
        for (String permission : permissions) {
            if (shouldShowRequestPermissionRationale(activity, permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 请求权限
     *
     * @param activity    上下文
     * @param callback    回调
     * @param requestCode 请求码
     * @param permissions 权限名称列表，每个权限由PermissionRequest获取
     */
    public void requestPermissions(@NonNull Activity activity, @Nullable IPermissionCallback callback,
                                   @IntRange(from = 0) int requestCode, @NonNull String... permissions) {
        if (callback != null) {
            mCallbacks.put(requestCode, callback);
        }
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    /**
     * 申请悬浮窗权限
     */
    public void requestOverlay(@NonNull Activity activity, @Nullable IPermissionCallback callback) {
        if (callback != null) {
            mCallbacks.put(RequestConstant.REQUEST_OVERLAY, callback);
        }
        DialogUtil.showMessageDialog(activity, "提示", "当前应用需要您开启悬浮窗权限", "去开启",
                (CustomDialog.MessageBuilder messageBuilder) -> {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + GlobalConstant.PACKAGE_NAME));
            activity.startActivityForResult(intent, RequestConstant.REQUEST_OVERLAY);
        });
    }

    /**
     * 检查权限是否被允许
     *
     * @param grantResults 权限结果列表
     * @return true or false
     */
    public boolean verifyPermissions(@NonNull int... grantResults) {
        if (grantResults.length == 0) {
            return false;
        }
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 权限请求回调，此方法须由Activity的onRequestPermissionsResult方法调用
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 权限请求结果
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        IPermissionCallback callback = mCallbacks.get(requestCode);
        if (callback != null) {
            callback.onPermissionCallback(requestCode, permissions, grantResults);
            mCallbacks.remove(requestCode);
        }
    }

    /**
     * 悬浮窗请求回调，此方法须由Activity的onActivityResult方法调用
     */
    public void onRequestOverlayResult(boolean grantResult) {
        IPermissionCallback callback = mCallbacks.get(RequestConstant.REQUEST_OVERLAY);
        if (callback != null) {
            callback.onPermissionCallback(RequestConstant.REQUEST_OVERLAY, new String[]{}, grantResult ?
                    new int[]{PackageManager.PERMISSION_GRANTED} : new int[]{PackageManager.PERMISSION_DENIED});
            mCallbacks.remove(RequestConstant.REQUEST_OVERLAY);
        }
    }

}
