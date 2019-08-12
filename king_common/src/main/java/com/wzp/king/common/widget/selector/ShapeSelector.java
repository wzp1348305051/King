package com.wzp.king.common.widget.selector;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.TypedValue;

import com.wzp.king.common.bean.constant.GlobalConstant;
import com.wzp.king.common.util.DisplayUtil;

import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.IntDef;

/**
 * 图形选择器
 *
 * @author wengzhipeng
 * @version v1.0, 2019-08-12
 */

public class ShapeSelector {
    @IntDef({GradientDrawable.RECTANGLE, GradientDrawable.OVAL, GradientDrawable.LINE, GradientDrawable.RING})
    private @interface Shape {
    }

    private int mShape;
    private float mCornerRadius;
    private int mStrokeWidth;
    private int mSolidColorNormal;
    private int mSolidColorDisabled;
    private int mSolidColorPressed;
    private int mSolidColorSelected;
    private int mSolidColorFocused;
    private int mStrokeColorNormal;
    private int mStrokeColorDisabled;
    private int mStrokeColorPressed;
    private int mStrokeColorSelected;
    private int mStrokeColorFocused;

    public ShapeSelector() {
        mShape = GradientDrawable.RECTANGLE;
        mCornerRadius = 0f;
        mStrokeWidth = 0;
        mSolidColorNormal = Color.TRANSPARENT;
        mSolidColorDisabled = Color.TRANSPARENT;
        mSolidColorPressed = Color.TRANSPARENT;
        mSolidColorSelected = Color.TRANSPARENT;
        mSolidColorFocused = Color.TRANSPARENT;
        mStrokeColorNormal = Color.TRANSPARENT;
        mStrokeColorDisabled = Color.TRANSPARENT;
        mStrokeColorPressed = Color.TRANSPARENT;
        mStrokeColorSelected = Color.TRANSPARENT;
        mStrokeColorFocused = Color.TRANSPARENT;
    }

    public ShapeSelector setShape(@Shape int shape) {
        mShape = shape;
        return this;
    }

    public ShapeSelector setCornerRadius(float radius) {
        mCornerRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radius,
                DisplayUtil.getDisplayMetrics(GlobalConstant.APP_CONTEXT));
        return this;
    }

    public ShapeSelector setStrokeWidth(@Dimension int width) {
        mStrokeWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width,
                DisplayUtil.getDisplayMetrics(GlobalConstant.APP_CONTEXT));
        return this;
    }

    public ShapeSelector setSolidColorNormal(@ColorInt int color) {
        mSolidColorNormal = color;
        if (mSolidColorDisabled == Color.TRANSPARENT) {
            mSolidColorDisabled = color;
        }
        if (mSolidColorPressed == Color.TRANSPARENT) {
            mSolidColorPressed = color;
        }
        if (mSolidColorSelected == Color.TRANSPARENT) {
            mSolidColorSelected = color;
        }
        if (mSolidColorFocused == Color.TRANSPARENT) {
            mSolidColorFocused = color;
        }
        return this;
    }

    public ShapeSelector setSolidColorDisabled(@ColorInt int color) {
        mSolidColorDisabled = color;
        return this;
    }

    public ShapeSelector setSolidColorPressed(@ColorInt int color) {
        mSolidColorPressed = color;
        return this;
    }

    public ShapeSelector setSolidColorSelected(@ColorInt int color) {
        mSolidColorSelected = color;
        return this;
    }

    public ShapeSelector setSolidColorFocused(@ColorInt int color) {
        mSolidColorFocused = color;
        return this;
    }

    public ShapeSelector setStrokeColorNormal(@ColorInt int color) {
        mStrokeColorNormal = color;
        if (mStrokeColorDisabled == Color.TRANSPARENT) {
            mStrokeColorDisabled = color;
        }
        if (mStrokeColorPressed == Color.TRANSPARENT) {
            mStrokeColorPressed = color;
        }
        if (mStrokeColorSelected == Color.TRANSPARENT) {
            mStrokeColorSelected = color;
        }
        if (mStrokeColorFocused == Color.TRANSPARENT) {
            mStrokeColorFocused = color;
        }
        return this;
    }

    public ShapeSelector setStrokeColorDisabled(@ColorInt int color) {
        mStrokeColorDisabled = color;
        return this;
    }

    public ShapeSelector setStrokeColorPressed(@ColorInt int color) {
        mStrokeColorPressed = color;
        return this;
    }

    public ShapeSelector setStrokeColorSelected(@ColorInt int color) {
        mStrokeColorSelected = color;
        return this;
    }

    public ShapeSelector setStrokeColorFocused(@ColorInt int color) {
        mStrokeColorFocused = color;
        return this;
    }

    public StateListDrawable create() {
        StateListDrawable selector = new StateListDrawable();

        // android:state_enabled="false"
        if (mSolidColorDisabled != Color.TRANSPARENT || mStrokeColorDisabled != Color.TRANSPARENT) {
            GradientDrawable drawable = getItemShape(mShape, mCornerRadius, mSolidColorDisabled, mStrokeWidth,
                    mStrokeColorDisabled);
            selector.addState(new int[]{-android.R.attr.state_enabled}, drawable);
        }

        // android:state_pressed="true"
        if (mSolidColorPressed != Color.TRANSPARENT || mStrokeColorPressed != Color.TRANSPARENT) {
            GradientDrawable drawable = getItemShape(mShape, mCornerRadius, mSolidColorPressed, mStrokeWidth,
                    mStrokeColorPressed);
            selector.addState(new int[]{android.R.attr.state_pressed}, drawable);
        }

        // android:state_selected="true"
        if (mSolidColorSelected != Color.TRANSPARENT || mStrokeColorSelected != Color.TRANSPARENT) {
            GradientDrawable selectedShape = getItemShape(mShape, mCornerRadius, mSolidColorSelected, mStrokeWidth,
                    mStrokeColorSelected);
            selector.addState(new int[]{android.R.attr.state_selected}, selectedShape);
        }

        // android:state_focused="true"
        if (mSolidColorFocused != Color.TRANSPARENT || mStrokeColorFocused != Color.TRANSPARENT) {
            GradientDrawable focusedShape = getItemShape(mShape, mCornerRadius, mSolidColorFocused, mStrokeWidth,
                    mStrokeColorFocused);
            selector.addState(new int[]{android.R.attr.state_focused}, focusedShape);
        }

        // normal
        GradientDrawable drawable = getItemShape(mShape, mCornerRadius, mSolidColorNormal, mStrokeWidth,
                mStrokeColorNormal);
        selector.addState(new int[]{}, drawable);

        return selector;
    }

    private GradientDrawable getItemShape(int shape, float cornerRadius, int solidColor, int strokeWidth,
                                          int strokeColor) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(shape);
        drawable.setStroke(strokeWidth, strokeColor);
        drawable.setCornerRadius(cornerRadius);
        drawable.setColor(solidColor);
        return drawable;
    }
}
