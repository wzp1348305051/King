package com.wzp.king.common.widget.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzp.king.common.R;
import com.wzp.king.common.bean.constant.EmptyConstant;
import com.wzp.king.common.util.EmptyUtil;
import com.wzp.king.common.widget.SingleClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 通用Dialog Builder
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/28
 */
public abstract class CustomDialogBuilder<T extends CustomDialogBuilder> {
    protected Context mContext;
    CustomDialog mDialog;
    private CharSequence mTitle;
    private List<CustomDialogAction> mActionList;
    private boolean mCancelable;
    private boolean mCanceledOnTouchOutside;

    public CustomDialogBuilder(@NonNull Context context) {
        mContext = context;
        mDialog = null;
        mTitle = EmptyConstant.EMPTY_STRING;
        mActionList = new ArrayList<>();
        mCancelable = false;
        mCanceledOnTouchOutside = false;
    }

    @SuppressWarnings("unchecked")
    public T setTitle(@Nullable CharSequence title) {
        mTitle = title;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T addAction(@Nullable CustomDialogAction action) {
        if (action != null) {
            mActionList.add(action);
        }
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T addAction(CharSequence str, CustomDialogAction.ActionClickListener<T> listener) {
        return addAction(new CustomDialogAction(str, listener));
    }

    @SuppressWarnings("unchecked")
    public T setCancelable(boolean cancelable) {
        mCancelable = cancelable;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mCanceledOnTouchOutside = canceledOnTouchOutside;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public CustomDialog create() {
        mDialog = new CustomDialog(mContext);

        View root = LayoutInflater.from(mContext).inflate(R.layout.dialog_custom, null);
        // Title
        TextView tvTitle = root.findViewById(R.id.tv_dialog_title);
        if (EmptyUtil.isEmptyText(mTitle)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(mTitle);
            tvTitle.setVisibility(View.VISIBLE);
        }
        // Content
        createContentView(root.findViewById(R.id.ll_dialog_content));
        // Action
        LinearLayout llAction = root.findViewById(R.id.ll_dialog_action);
        if (EmptyUtil.isEmptyCollection(mActionList)) {
            llAction.setVisibility(View.GONE);
        } else {
            T builder = (T) this;
            for (CustomDialogAction action : mActionList) {
                TextView textView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.dialog_custom_action, llAction, false);
                textView.setText(action.getLabel());
                textView.setOnClickListener(new SingleClickListener() {
                    @Override
                    public void onSingleClick(@NonNull View v) {
                        CustomDialogAction.ActionClickListener listener = action.getActionClickListener();
                        if (listener != null) {
                            listener.onActionClick(builder);
                        }
                        mDialog.dismiss();
                        mDialog = null;
                    }
                });
                llAction.addView(textView);
            }
            llAction.setVisibility(View.VISIBLE);
        }

        mDialog.addContentView(root, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mDialog.setCancelable(mCancelable);
        mDialog.setCanceledOnTouchOutside(mCanceledOnTouchOutside);
        return mDialog;
    }

    public abstract void createContentView(LinearLayout contentView);

}
