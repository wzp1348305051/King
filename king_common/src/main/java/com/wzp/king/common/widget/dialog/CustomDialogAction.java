package com.wzp.king.common.widget.dialog;

/**
 * 通用Dialog 按钮行为
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/28
 */

public class CustomDialogAction {
    private CharSequence mLabel;
    private ActionClickListener mActionClickListener;

    public interface ActionClickListener<T extends CustomDialogBuilder> {
        void onActionClick(T builder);
    }

    CustomDialogAction(CharSequence label, ActionClickListener actionClickListener) {
        mLabel = label;
        mActionClickListener = actionClickListener;
    }

    CharSequence getLabel() {
        return mLabel;
    }

    ActionClickListener getActionClickListener() {
        return mActionClickListener;
    }
}
