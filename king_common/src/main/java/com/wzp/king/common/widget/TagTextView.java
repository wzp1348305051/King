package com.wzp.king.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wzp.king.common.R;
import com.wzp.king.common.R2;
import com.wzp.king.common.util.DisplayUtil;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 带标签的TextView
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/6
 */

public class TagTextView extends FrameLayout {
    @BindView(R2.id.tv_tag_text_tag)
    TextView mTvTag;
    @BindView(R2.id.tv_tag_text_content)
    TextView mTvContent;
    private Drawable mTagDrawable;
    private String mTagText;
    private float mTagTextSize;
    private int mTagTextColor;
    private String mContentText;
    private float mContentTextSize;
    private int mContentTextColor;

    public TagTextView(@NonNull Context context) {
        this(context, null);
    }

    public TagTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagTextView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        handleTypedArray(context, attrs);
        initContentView(context);
        bindContentView();
    }

    private void handleTypedArray(@NonNull Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TagTextView);
        mTagDrawable = typedArray.getDrawable(R.styleable.TagTextView_tagBackground);
        mTagText = typedArray.getString(R.styleable.TagTextView_tagText);
        mTagTextSize = typedArray.getDimensionPixelSize(R.styleable.TagTextView_tagTextSize, DisplayUtil.dip2Px(context, 12f));
        mTagTextColor = typedArray.getColor(R.styleable.TagTextView_tagTextColor, Color.WHITE);
        mContentText = typedArray.getString(R.styleable.TagTextView_contextText);
        mContentTextSize = typedArray.getDimensionPixelSize(R.styleable.TagTextView_contextTextSize, DisplayUtil.dip2Px(context, 14f));
        mContentTextColor = typedArray.getColor(R.styleable.TagTextView_contextTextColor, Color.GRAY);

        typedArray.recycle();
    }

    private void initContentView(@NonNull Context context) {
        View root = inflate(context, R.layout.wgt_tag_text, this);

        ButterKnife.bind(this, root);
    }

    private void bindContentView() {
        mTvTag.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTagTextSize);
        mTvTag.setTextColor(mTagTextColor);
        mTvTag.setBackground(mTagDrawable);
        if (TextUtils.isEmpty(mTagText)) {
            mTvTag.setVisibility(GONE);
        } else {
            mTvTag.setText(mTagText);
            mTvTag.setVisibility(VISIBLE);
        }

        mTvContent.setText(mContentText);
        mTvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContentTextSize);
        mTvContent.setTextColor(mContentTextColor);
    }

    public void setTagDrawable(@Nullable Drawable tagDrawable) {
        mTvTag.setBackground(tagDrawable);
    }

    public void setTagText(@Nullable String tagText) {
        if (TextUtils.isEmpty(tagText)) {
            mTvTag.setVisibility(GONE);
        } else {
            mTvTag.setText(tagText);
            mTvTag.setVisibility(VISIBLE);
        }
    }

    public void setTagTextSize(float tagTextSize) {
        mTvTag.setTextSize(TypedValue.COMPLEX_UNIT_SP, tagTextSize);
    }

    public void setTagTextColor(@ColorInt int tagTextColor) {
        mTvTag.setTextColor(tagTextColor);
    }

    public void setContentText(@Nullable String contentText) {
        mTvContent.setText(contentText);
    }

    public void setContentTextSize(float contentTextSize) {
        mTvContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, contentTextSize);
    }

    public void setContentTextColor(@ColorInt int contentTextColor) {
        mTvContent.setTextColor(mContentTextColor);
    }
}
