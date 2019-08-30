package com.wzp.king.common.util;

import android.content.Context;

import com.wzp.king.common.bean.constant.ExceptionConstant;
import com.wzp.king.common.widget.dialog.CustomDialog;
import com.wzp.king.common.widget.dialog.CustomDialogAction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 对话框工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/26
 */

public class DialogUtil {

    private DialogUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    public static void showMessageDialog(@NonNull Context context, @Nullable String title, @Nullable String msg) {
        showMessageDialog(context, title, msg, null);
    }

    public static void showMessageDialog(@NonNull Context context, @Nullable String title, @Nullable String msg,
                                         @Nullable CustomDialogAction.ActionClickListener<CustomDialog.MessageBuilder> confirmListener) {
        showMessageDialog(context, title, msg, null, confirmListener);
    }

    public static void showMessageDialog(@NonNull Context context, @Nullable String title, @Nullable String msg,
                                         @Nullable String confirmText,
                                         @Nullable CustomDialogAction.ActionClickListener<CustomDialog.MessageBuilder> confirmListener) {
        if (EmptyUtil.isEmptyText(confirmText)) {
            confirmText = "我知道了";
        }

        new CustomDialog.MessageBuilder(context)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addAction(confirmText, confirmListener)
                .create()
                .show();
    }


    public static void showConfirmDialog(@NonNull Context context, @Nullable String title, @Nullable String msg,
                                         @Nullable CustomDialogAction.ActionClickListener<CustomDialog.MessageBuilder> confirmListener) {
        showConfirmDialog(context, title, msg, null, null, confirmListener);
    }

    public static void showConfirmDialog(@NonNull Context context, @Nullable String title, @Nullable String msg,
                                         @Nullable String cancelText, @Nullable String confirmText,
                                         @Nullable CustomDialogAction.ActionClickListener<CustomDialog.MessageBuilder> confirmListener) {
        showConfirmDialog(context, title, msg, cancelText, confirmText, null, confirmListener);
    }

    public static void showConfirmDialog(@NonNull Context context, @Nullable String title, @Nullable String msg,
                                         @Nullable String cancelText, @Nullable String confirmText,
                                         @Nullable CustomDialogAction.ActionClickListener<CustomDialog.MessageBuilder> cancelListener,
                                         @Nullable CustomDialogAction.ActionClickListener<CustomDialog.MessageBuilder> confirmListener) {
        if (EmptyUtil.isEmptyText(cancelText)) {
            cancelText = "取消";
        }
        if (EmptyUtil.isEmptyText(confirmText)) {
            confirmText = "确定";
        }
        new CustomDialog.MessageBuilder(context)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addAction(cancelText, cancelListener)
                .addAction(confirmText, confirmListener)
                .create()
                .show();
    }

    public static void showInputDialog(@NonNull Context context, @Nullable String title, @Nullable String hint,
                                       @Nullable CustomDialogAction.ActionClickListener<CustomDialog.InputBuilder> confirmListener) {
        new CustomDialog.InputBuilder(context)
                .setTitle(title)
                .setHint(hint)
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addAction("取消", null)
                .addAction("确定", confirmListener)
                .create()
                .show();
    }

    public static void showMenuDialog(@NonNull Context context, @Nullable String title,
                                      @NonNull CharSequence[] menuArray,
                                      @Nullable CustomDialog.MenuBuilder.MenuSelectListener listener) {
        showMenuDialog(context, title, menuArray, -1, listener);
    }

    public static void showMenuDialog(@NonNull Context context, @Nullable String title,
                                      @NonNull CharSequence[] menuArray, int checkedIndex,
                                      @Nullable CustomDialog.MenuBuilder.MenuSelectListener listener) {
        new CustomDialog.MenuBuilder(context)
                .setTitle(title)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .setMenuArray(menuArray, listener)
                .setCheckedIndex(checkedIndex)
                .create()
                .show();
    }

    public static void showSingleChoiceDialog(@NonNull Context context, @Nullable String title,
                                              @NonNull CharSequence[] choiceArray, int checkedIndex,
                                              @Nullable CustomDialog.ChoiceBuilder.ConfirmListener confirmListener) {
        if (checkedIndex < 0 || checkedIndex >= choiceArray.length) {
            checkedIndex = 0;
        }
        showChoiceDialog(context, true, title, choiceArray, new int[]{checkedIndex}, confirmListener);
    }

    public static void showMultipleChoiceDialog(@NonNull Context context, @Nullable String title,
                                                @NonNull CharSequence[] choiceArray,
                                                @Nullable int[] checkedIndexArray,
                                                @Nullable CustomDialog.ChoiceBuilder.ConfirmListener confirmListener) {
        showChoiceDialog(context, false, title, choiceArray, checkedIndexArray, confirmListener);
    }

    private static void showChoiceDialog(@NonNull Context context, boolean singleMode, @Nullable String title,
                                         @NonNull CharSequence[] choiceArray, @Nullable int[] checkedIndexArray,
                                         @Nullable CustomDialog.ChoiceBuilder.ConfirmListener confirmListener) {
        new CustomDialog.ChoiceBuilder(context)
                .setTitle(title)
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .setChoiceArray(choiceArray)
                .setChoiceMode(singleMode, checkedIndexArray)
                .addAction("取消", null)
                .addAction("确定", (CustomDialog.ChoiceBuilder builder) -> {
                    if (confirmListener != null) {
                        confirmListener.onConfirm(builder.getCheckedIndexArray());
                    }
                })
                .create()
                .show();
    }

}
