package com.wzp.king.common.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.wzp.king.common.R;
import com.wzp.king.common.impl.IBaseFragment;
import com.wzp.king.common.util.ActivityUtil;
import com.wzp.king.common.util.KeyboardUtil;
import com.wzp.king.common.util.ToastUtil;
import com.wzp.king.common.widget.HeaderView;
import com.wzp.king.common.widget.LoadingView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * 带滑动返回的基 SupportFragment
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/25
 */
public abstract class BaseSwipeBackSupportFragment extends SwipeBackFragment implements IBaseFragment {
    protected LinearLayout mRootView;
    protected HeaderView mHeaderView;
    protected LoadingView mLoading;
    protected Activity _mActivity;
    private Unbinder mUnBinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = (LinearLayout) inflater.inflate(R.layout.fragment_base, container, false);
            initContentView(inflater);
            initMemberData();
            bindContentView(savedInstanceState);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    public void initContentView(@NonNull LayoutInflater inflater) {
        mHeaderView = mRootView.findViewById(R.id.layout_base_header);
        // 加载内容布局
        if (getContentView() > 0) {
            inflater.inflate(getContentView(), mRootView, true);
        }

        mUnBinder = ButterKnife.bind(this, mRootView);
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();

        KeyboardUtil.hideKeyboard(mRootView);
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
