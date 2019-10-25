package com.wzp.king.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.wzp.king.common.R;
import com.wzp.king.common.R2;
import com.wzp.king.common.util.DisplayUtil;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 页面头部标题栏
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/6
 */

public class HeaderView extends RelativeLayout {
    @BindView(R2.id.tv_header_left)
    IconTextView mTvLeft;
    @BindView(R2.id.tv_header_middle)
    IconTextView mTvMiddle;
    @BindView(R2.id.tv_header_right)
    IconTextView mTvRight;

    public HeaderView(@NonNull Context context) {
        this(context, null);
    }

    public HeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initContentView(context);
        bindContentView(context, attrs);
    }

    private void initContentView(@NonNull Context context) {
        View root = inflate(context, R.layout.wgt_header, this);
        ButterKnife.bind(this, root);
    }

    private void bindContentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        int textSize = DisplayUtil.dip2Px(context, 16f);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderView);
        String leftText = typedArray.getString(R.styleable.HeaderView_header_leftText);
        float leftSize = typedArray.getDimensionPixelSize(R.styleable.HeaderView_header_leftSize, textSize);
        int leftColor = typedArray.getColor(R.styleable.HeaderView_header_leftColor, Color.parseColor("#FF666666"));
        String middleText = typedArray.getString(R.styleable.HeaderView_header_middleText);
        float middleSize = typedArray.getDimensionPixelSize(R.styleable.HeaderView_header_middleSize, textSize);
        int middleColor = typedArray.getColor(R.styleable.HeaderView_header_middleColor, Color.parseColor("#FF333333"));
        String rightText = typedArray.getString(R.styleable.HeaderView_header_rightText);
        float rightSize = typedArray.getDimensionPixelSize(R.styleable.HeaderView_header_rightSize, textSize);
        int rightColor = typedArray.getColor(R.styleable.HeaderView_header_rightColor, Color.parseColor("#FF666666"));
        typedArray.recycle();

        mTvLeft.setText(leftText);
        mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftSize);
        mTvLeft.setTextColor(leftColor);
        mTvMiddle.setText(middleText);
        mTvMiddle.setTextSize(TypedValue.COMPLEX_UNIT_PX, middleSize);
        mTvMiddle.setTextColor(middleColor);
        mTvRight.setText(rightText);
        mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightSize);
        mTvRight.setTextColor(rightColor);
    }

    public IconTextView getLeftView() {
        return mTvLeft;
    }

    public void setLeftText(@StringRes int resId) {
        mTvLeft.setText(resId);
    }

    public void setLeftText(@Nullable CharSequence text) {
        mTvLeft.setText(text);
    }

    public void setLeftSize(float size) {
        mTvLeft.setTextSize(size);
    }

    public void setLeftColor(@ColorInt int color) {
        mTvLeft.setTextColor(color);
    }

    public IconTextView getMiddleView() {
        return mTvMiddle;
    }

    public void setMiddleText(@StringRes int resId) {
        mTvMiddle.setText(resId);
    }

    public void setMiddleText(@Nullable CharSequence text) {
        mTvMiddle.setText(text);
    }

    public void setMiddleSize(float size) {
        mTvMiddle.setTextSize(size);
    }

    public void setMiddleColor(@ColorInt int color) {
        mTvMiddle.setTextColor(color);
    }

    public IconTextView getRightView() {
        return mTvRight;
    }

    public void setRightText(@StringRes int resId) {
        mTvRight.setText(resId);
    }

    public void setRightText(@Nullable CharSequence text) {
        mTvRight.setText(text);
    }

    public void setRightSize(float size) {
        mTvRight.setTextSize(size);
    }

    public void setRightColor(@ColorInt int color) {
        mTvRight.setTextColor(color);
    }

}
