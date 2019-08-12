package com.wzp.king.common.widget.selector;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import com.wzp.king.common.util.ResUtil;

import androidx.annotation.DrawableRes;

/**
 * 图片选择器
 *
 * @author wengzhipeng
 * @version v1.0, 2019-08-12
 */

public class DrawableSelector {
    private Drawable mDrawableNormal;
    private Drawable mDrawableDisabled;
    private Drawable mDrawablePressed;
    private Drawable mDrawableSelected;
    private Drawable mDrawableFocused;

    public DrawableSelector() {
        mDrawableNormal = new ColorDrawable(Color.TRANSPARENT);
    }

    public DrawableSelector setDrawableNormal(Drawable drawable) {
        mDrawableNormal = drawable;
        if (mDrawableDisabled == null) {
            mDrawableDisabled = drawable;
        }
        if (mDrawablePressed == null) {
            mDrawablePressed = drawable;
        }
        if (mDrawableSelected == null) {
            mDrawableSelected = drawable;
        }
        if (mDrawableFocused == null) {
            mDrawableFocused = drawable;
        }
        return this;
    }

    public DrawableSelector setDrawableNormal(@DrawableRes int resId) {
        return setDrawableNormal(ResUtil.getDrawable(resId));
    }

    public DrawableSelector setDrawableDisabled(Drawable drawable) {
        mDrawableDisabled = drawable;
        return this;
    }

    public DrawableSelector setDrawableDisabled(@DrawableRes int resId) {
        return setDrawableDisabled(ResUtil.getDrawable(resId));
    }

    public DrawableSelector setDrawablePressed(Drawable drawable) {
        mDrawablePressed = drawable;
        return this;
    }

    public DrawableSelector setDrawablePressed(@DrawableRes int resId) {
        return setDrawablePressed(ResUtil.getDrawable(resId));
    }

    public DrawableSelector setDrawableSelected(Drawable drawable) {
        mDrawableSelected = drawable;
        return this;
    }

    public DrawableSelector setDrawableSelected(@DrawableRes int resId) {
        return setDrawableSelected(ResUtil.getDrawable(resId));
    }

    public DrawableSelector setDrawableFocused(Drawable drawable) {
        mDrawableFocused = drawable;
        return this;
    }

    public DrawableSelector setDrawableFocused(@DrawableRes int resId) {
        return setDrawableFocused(ResUtil.getDrawable(resId));
    }

    public StateListDrawable create() {
        StateListDrawable selector = new StateListDrawable();
        if (mDrawableDisabled != null) {
            selector.addState(new int[]{-android.R.attr.state_enabled}, mDrawableDisabled);
        }
        if (mDrawablePressed != null) {
            selector.addState(new int[]{android.R.attr.state_pressed}, mDrawablePressed);
        }
        if (mDrawableSelected != null) {
            selector.addState(new int[]{android.R.attr.state_selected}, mDrawableSelected);
        }
        if (mDrawableFocused != null) {
            selector.addState(new int[]{android.R.attr.state_focused}, mDrawableFocused);
        }
        selector.addState(new int[]{}, mDrawableNormal);
        return selector;
    }
}
