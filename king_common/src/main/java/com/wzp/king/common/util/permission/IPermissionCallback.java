package com.wzp.king.common.util.permission;

import androidx.annotation.NonNull;

/**
 * 权限回调接口
 *
 * @author wengzhipeng
 * @since 2018/3/1
 */

public interface IPermissionCallback {
    void onPermissionCallback(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}
