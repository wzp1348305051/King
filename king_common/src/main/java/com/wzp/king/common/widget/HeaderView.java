package com.wzp.king.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.wzp.king.common.R;
import com.wzp.king.common.R2;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 页面头部标题栏
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/6
 */

public class HeaderView extends FrameLayout {
    @BindView(R2.id.tv_header_left)
    IconTextView mTvLeft;
    @BindView(R2.id.tv_header_middle)
    IconTextView mTvMiddle;
    @BindView(R2.id.tv_header_right)
    IconTextView mTvRight;
    private String mLeftText;
    private float mLeftTextSize;
    private int mLeftTextColor;
    private OnClickListener mOnLeftClickListener;
    private String mMiddleText;
    private float mMiddleTextSize;
    private int mMiddleTextColor;
    private OnClickListener mOnMiddleClickListener;
    private String mRightText;
    private float mRightTextSize;
    private int mRightTextColor;
    private OnClickListener mOnRightClickListener;

    public HeaderView(@NonNull Context context) {
        this(context, null);
    }

    public HeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        handleTypedArray(context, attrs);
        initContentView(context);
        bindContentView();
    }

    private void handleTypedArray(@NonNull Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderView);
        mLeftText = typedArray.getString(R.styleable.HeaderView_leftText);
        mLeftTextSize = typedArray.getDimension(R.styleable.HeaderView_leftTextSize, 16f);
        mLeftTextColor = typedArray.getColor(R.styleable.HeaderView_leftTextColor, Color.parseColor("#FF666666"));
        mMiddleText = typedArray.getString(R.styleable.HeaderView_middleText);
        mMiddleTextSize = typedArray.getDimension(R.styleable.HeaderView_middleTextSize, 16f);
        mMiddleTextColor = typedArray.getColor(R.styleable.HeaderView_middleTextColor, Color.parseColor("#FF333333"));
        mRightText = typedArray.getString(R.styleable.HeaderView_rightText);
        mRightTextSize = typedArray.getDimension(R.styleable.HeaderView_rightTextSize, 16f);
        mRightTextColor = typedArray.getColor(R.styleable.HeaderView_rightTextColor, Color.parseColor("#FF666666"));
        typedArray.recycle();
    }

    private void initContentView(@NonNull Context context) {
        View root = inflate(context, R.layout.wgt_header, this);

        ButterKnife.bind(this, root);
    }

    private void bindContentView() {
        mTvLeft.setText(mLeftText);
        mTvLeft.setTextSize(mLeftTextSize);
        mTvLeft.setTextColor(mLeftTextColor);
        mTvLeft.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(@NonNull View v) {
                if (mOnLeftClickListener == null) {
                    return;
                }
                mOnLeftClickListener.onClick(v);
            }
        });
        mTvMiddle.setText(mMiddleText);
        mTvMiddle.setTextSize(mMiddleTextSize);
        mTvMiddle.setTextColor(mMiddleTextColor);
        mTvMiddle.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(@NonNull View v) {
                if (mOnMiddleClickListener == null) {
                    return;
                }
                mOnMiddleClickListener.onClick(v);
            }
        });
        mTvRight.setText(mRightText);
        mTvRight.setTextSize(mRightTextSize);
        mTvRight.setTextColor(mRightTextColor);
        mTvRight.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(@NonNull View v) {
                if (mOnRightClickListener == null) {
                    return;
                }
                mOnRightClickListener.onClick(v);
            }
        });
    }

    public void setLeftText(@Nullable CharSequence leftText) {
        mTvLeft.setText(leftText);
    }

    public void setLeftTextSize(float leftTextSize) {
        mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_SP, leftTextSize);
    }

    public void setLeftTextColor(@ColorInt int leftTextColor) {
        mTvLeft.setTextColor(leftTextColor);
    }

    public void setOnLeftClickListener(@Nullable OnClickListener onLeftClickListener) {
        mOnLeftClickListener = onLeftClickListener;
    }

    public String getMiddleText() {
        return mTvMiddle.getText().toString();
    }

    public void setMiddleText(@Nullable CharSequence middleText) {
        mTvMiddle.setText(middleText);
    }

    public void setMiddleTextSize(float middleTextSize) {
        mTvMiddle.setTextSize(TypedValue.COMPLEX_UNIT_SP, middleTextSize);
    }

    public void setMiddleTextColor(@ColorInt int middleTextColor) {
        mTvMiddle.setTextColor(middleTextColor);
    }

    public void setOnMiddleClickListener(@Nullable OnClickListener onMiddleClickListener) {
        mOnMiddleClickListener = onMiddleClickListener;
    }

    public void setRightText(@Nullable CharSequence rightText) {
        mTvRight.setText(rightText);
    }

    public void setRightTextSize(float rightTextSize) {
        mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_SP, rightTextSize);
    }

    public void setRightTextColor(@ColorInt int rightTextColor) {
        mTvRight.setTextColor(rightTextColor);
    }

    public void setOnRightClickListener(@Nullable OnClickListener onRightClickListener) {
        mOnRightClickListener = onRightClickListener;
    }
}
