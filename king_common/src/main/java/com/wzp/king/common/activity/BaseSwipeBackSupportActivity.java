package com.wzp.king.common.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.wzp.king.common.R;
import com.wzp.king.common.bean.constant.RequestConstant;
import com.wzp.king.common.impl.IBaseActivity;
import com.wzp.king.common.util.ActivityUtil;
import com.wzp.king.common.util.KeyboardUtil;
import com.wzp.king.common.util.SysSrvUtil;
import com.wzp.king.common.util.ToastUtil;
import com.wzp.king.common.util.permission.PermissionHelper;
import com.wzp.king.common.widget.HeaderView;
import com.wzp.king.common.widget.LoadingView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * 带滑动返回的基 Fragment Activity (https://github.com/YoKeyword/Fragmentation/blob/master/fragmentation_swipeback/README.md)
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/25
 */

public abstract class BaseSwipeBackSupportActivity extends SwipeBackActivity implements IBaseActivity {
    protected LinearLayout mRootView;
    protected HeaderView mHeaderView;
    protected LoadingView mLoading;
    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
        setContentView(R.layout.activity_base);
        initContentView();
        initMemberData();
        bindContentView(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();

        KeyboardUtil.hideKeyboard(mActivity);
    }

    @Override
    public void initContentView() {
        FrameLayout decorView = ActivityUtil.getDecorView(mActivity);
        if (decorView != null) {
            mRootView = decorView.findViewById(R.id.ll_base_root);
            mHeaderView = decorView.findViewById(R.id.layout_base_header);
            // 加载内容布局
            if (getContentView() > 0) {
                SysSrvUtil.getLayoutInflater(mActivity).inflate(getContentView(), mRootView, true);
            }
        }

        ButterKnife.bind(mActivity);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestConstant.REQUEST_OVERLAY) {
            PermissionHelper.getInstance().onRequestOverlayResult(Settings.canDrawOverlays(this));
        }
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
