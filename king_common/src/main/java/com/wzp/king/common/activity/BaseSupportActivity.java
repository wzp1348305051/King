package com.wzp.king.common.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wzp.king.common.impl.IBaseActivity;
import com.wzp.king.common.util.ActivityUtil;
import com.wzp.king.common.util.KeyboardUtil;
import com.wzp.king.common.util.ToastUtil;
import com.wzp.king.common.util.permission.PermissionHelper;
import com.wzp.king.common.widget.LoadingView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 基 Fragment Activity (https://github.com/YoKeyword/Fragmentation/wiki)
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/24
 */

public abstract class BaseSupportActivity extends SupportActivity implements IBaseActivity {
    private LoadingView mLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());
        initContentView();
        initMemberData();
        bindContentView(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();

        KeyboardUtil.hideKeyboard(this);
    }

    @Override
    public void initContentView() {
        ButterKnife.bind(this);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    /**
     * 用户权限申请回调方法
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionHelper.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void showToast(@Nullable String msg) {
        if (ActivityUtil.isActivityDied(this)) {
            return;
        }
        ToastUtil.showToast(this, msg);
    }

    public void showToast(@StringRes int resId) {
        showToast(getString(resId));
    }

    /**
     * 展示Loading框
     */
    public void showLoading() {
        if (ActivityUtil.isActivityDied(this)) {
            return;
        }

        if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
            return;
        }

        if (mLoading == null) {
            mLoading = new LoadingView(this);
            FrameLayout decorView = ActivityUtil.getDecorView(this);
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
