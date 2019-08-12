package com.wzp.king.common.widget;

import android.view.View;

import com.wzp.king.common.util.L;

import androidx.annotation.NonNull;

/**
 * 600ms内防止重复点击
 *
 * @author wengzhipeng
 * @version v1.0, 2018/1/3
 */

public abstract class SingleClickListener implements View.OnClickListener {
    private static final String TAG = SingleClickListener.class.getSimpleName();
    private long mLastClickTime;

    public abstract void onSingleClick(@NonNull View v);

    @Override
    public void onClick(@NonNull View v) {
        long currentClickTime = System.currentTimeMillis();
        if (currentClickTime - mLastClickTime > 600) {
            mLastClickTime = currentClickTime;
            try {
                onSingleClick(v);
            } catch (Exception e) {
                L.eTag(TAG, e);
            }
        }
    }
}
