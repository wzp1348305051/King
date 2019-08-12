package com.wzp.king.common.widget.selector;

import android.content.res.ColorStateList;
import android.graphics.Color;

import androidx.annotation.ColorInt;

/**
 * 颜色选择器
 *
 * @author wengzhipeng
 * @version v1.0, 2019-08-12
 */

public class ColorSelector {
    private int mColorNormal;
    private int mColorDisabled;
    private int mColorPressed;
    private int mColorSelected;
    private int mColorFocused;

    public ColorSelector() {
        mColorNormal = Color.parseColor("#FF333333");
        mColorDisabled = -1;
        mColorPressed = -1;
        mColorSelected = -1;
        mColorFocused = -1;
    }

    public ColorSelector setColorNormal(@ColorInt int color) {
        mColorNormal = color;
        return this;
    }

    public ColorSelector setColorDisabled(@ColorInt int color) {
        mColorDisabled = color;
        return this;
    }

    public ColorSelector setColorPressed(@ColorInt int color) {
        mColorPressed = color;
        return this;
    }

    public ColorSelector setColorSelected(@ColorInt int color) {
        mColorSelected = color;
        return this;
    }

    public ColorSelector setColorFocused(@ColorInt int color) {
        mColorFocused = color;
        return this;
    }

    public ColorStateList create() {
        int[] colors = new int[]{
                mColorDisabled > 0 ? mColorDisabled : mColorNormal,
                mColorPressed > 0 ? mColorPressed : mColorNormal,
                mColorSelected > 0 ? mColorSelected : mColorNormal,
                mColorFocused > 0 ? mColorFocused : mColorNormal,
                mColorNormal
        };
        int[][] states = new int[5][];
        states[0] = new int[]{-android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_pressed};
        states[2] = new int[]{android.R.attr.state_selected};
        states[3] = new int[]{android.R.attr.state_focused};
        states[4] = new int[]{};
        return new ColorStateList(states, colors);
    }
}
