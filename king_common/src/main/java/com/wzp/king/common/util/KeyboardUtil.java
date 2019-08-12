package com.wzp.king.common.util;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.wzp.king.common.bean.constant.ExceptionConstant;

import androidx.annotation.Nullable;

/**
 * 键盘工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/26
 */

public class KeyboardUtil {
    private static final String TAG = KeyboardUtil.class.getSimpleName();

    private KeyboardUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }


    public static void showKeyboard(@Nullable EditText editText, boolean delay) {
        showKeyboard(editText, delay ? 200 : 0);
    }

    /**
     * 针对给定的editText显示软键盘（editText会先获得焦点）. 可以和{@link #hideKeyboard(View)}
     * 搭配使用，进行键盘的显示隐藏控制。
     */
    public static void showKeyboard(@Nullable EditText editText, int delay) {
        if (editText == null) {
            return;
        }

        if (!editText.requestFocus()) {
            L.dTag(TAG, "showKeyboard can not get focus");
            return;
        }

        if (delay > 0) {
            editText.postDelayed(() -> {
                InputMethodManager manager =
                        SysSrvUtil.getInputMethodManager(editText.getContext().getApplicationContext());
                if (manager != null) {
                    manager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                }
            }, delay);
        } else {
            InputMethodManager manager =
                    SysSrvUtil.getInputMethodManager(editText.getContext().getApplicationContext());
            if (manager != null) {
                manager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        }
    }

    /**
     * 隐藏软键盘
     */
    public static boolean hideKeyboard(@Nullable Activity activity) {
        if (ActivityUtil.isActivityDied(activity)) {
            return false;
        }

        return hideKeyboard(ActivityUtil.getDecorView(activity));
    }

    /**
     * 隐藏软键盘 可以和{@link #showKeyboard(EditText, boolean)}搭配使用，进行键盘的显示隐藏控制。
     *
     * @param view 当前页面上任意一个可用的view
     */
    public static boolean hideKeyboard(@Nullable View view) {
        if (view == null) {
            return false;
        }

        InputMethodManager manager = SysSrvUtil.getInputMethodManager(view.getContext().getApplicationContext());
        if (manager != null) {
            return manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        return false;
    }
}
