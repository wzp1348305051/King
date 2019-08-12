package com.wzp.king.common.util;

import android.content.Context;
import android.widget.Toast;

import com.wzp.king.common.bean.constant.ExceptionConstant;
import com.wzp.king.common.bean.constant.GlobalConstant;

import androidx.annotation.Nullable;

/**
 * Toast工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/18
 */

public class ToastUtil {
    private ToastUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    public static void showToast(@Nullable Context context, @Nullable String text) {
        if (EmptyUtil.isEmptyText(text)) {
            return;
        }

        if (context == null) {
            context = GlobalConstant.APP_CONTEXT;
        }

        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
