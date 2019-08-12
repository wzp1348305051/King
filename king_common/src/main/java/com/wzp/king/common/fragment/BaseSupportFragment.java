package com.wzp.king.common.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wzp.king.common.impl.IBaseFragment;
import com.wzp.king.common.util.ActivityUtil;
import com.wzp.king.common.util.KeyboardUtil;
import com.wzp.king.common.util.ToastUtil;
import com.wzp.king.common.widget.LoadingView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 基 SupportFragment
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/25
 */
public abstract class BaseSupportFragment extends SupportFragment implements IBaseFragment {
    protected View mContentView;
    private LoadingView mLoading;
    private Unbinder mUnBinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(getContentView(), null);
        initContentView(mContentView);
        initMemberData();
        bindContentView();
        return mContentView;
    }

    @Override
    public void initContentView(View view) {
        mUnBinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();

        KeyboardUtil.hideKeyboard(mContentView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    public void showToast(@Nullable String msg) {
        if (ActivityUtil.isActivityDied(_mActivity)) {
            return;
        }

        ToastUtil.showToast(_mActivity, msg);
    }

    public void showToast(@StringRes int resId) {
        showToast(getString(resId));
    }

    /**
     * 展示Loading框
     */
    public void showLoading() {
        if (ActivityUtil.isActivityDied(_mActivity)) {
            return;
        }

        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            return;
        }

        if (mLoading == null) {
            mLoading = new LoadingView(_mActivity);
            FrameLayout decorView = ActivityUtil.getDecorView(_mActivity);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER;
            if (decorView != null) {
                decorView.addView(mLoading, layoutParams);
            }
        }

        mLoading.startLoading();
        mLoading.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏Loading框
     */
    public void hideLoading() {
        if (mLoading == null) {
            return;
        }

        mLoading.stopLoading();
        mLoading.setVisibility(View.INVISIBLE);
    }
}
