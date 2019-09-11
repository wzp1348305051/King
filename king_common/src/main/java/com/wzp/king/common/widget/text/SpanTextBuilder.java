package com.wzp.king.common.widget.text;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.wzp.king.common.bean.constant.ExceptionConstant;
import com.wzp.king.common.util.EmptyUtil;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Span标记文本
 *
 * @author wengzhipeng
 * @version v1.0, 2019-08-30
 */

public class SpanTextBuilder {
    private TextView mTextView;
    private SpannableStringBuilder mContentBuilder;

    public SpanTextBuilder(@NonNull TextView textView) {
        mTextView = textView;
        mContentBuilder = new SpannableStringBuilder();
    }

    @NonNull
    public SpannableStringBuilder getContentBuilder() {
        return mContentBuilder;
    }

    @SuppressWarnings("all")
    public SpanTextBuilder appendSpanText(@Nullable String text, Object... spans) {
        if (EmptyUtil.isEmptyText(text)) {
            return this;
        }

        int start = mContentBuilder.length();
        int end = start + text.length();
        mContentBuilder.append(text);

        if (spans != null && spans.length > 0) {
            for (Object span : spans) {
                mContentBuilder.setSpan(span, start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }
        return this;
    }

    /**
     * 字体颜色文本
     */
    public SpanTextBuilder appendForeColorText(@Nullable String text, @ColorInt int textColor) {
        return appendSpanText(text, new ForegroundColorSpan(textColor));
    }

    /**
     * 背景颜色文本
     */
    public SpanTextBuilder appendBackColorText(@Nullable String text, @ColorInt int textColor) {
        return appendSpanText(text, new BackgroundColorSpan(textColor));
    }

    /**
     * 字体大小文本
     */
    public SpanTextBuilder appendSizeText(@Nullable String text, int textSize) {
        return appendSizeText(text, textSize, true);
    }

    /**
     * 字体大小文本
     */
    public SpanTextBuilder appendSizeText(@Nullable String text, int textSize, boolean sp) {
        if (textSize <= 0) {
            throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_ACTION + ": text size must be" +
                    " greater than zero");
        }

        return appendSpanText(text, new AbsoluteSizeSpan(textSize, sp));
    }

    /**
     * 粗体文本
     */
    public SpanTextBuilder appendBoldText(@Nullable String text) {
        return appendSpanText(text, new StyleSpan(Typeface.BOLD));
    }

    /**
     * 斜体文本
     */
    public SpanTextBuilder appendItalicText(@Nullable String text) {
        return appendSpanText(text, new StyleSpan(Typeface.ITALIC));
    }

    /**
     * 粗体斜体文本
     */
    public SpanTextBuilder appendBoldItalicText(@Nullable String text) {
        return appendSpanText(text, new StyleSpan(Typeface.BOLD_ITALIC));
    }

    /**
     * 删除线文本
     */
    public SpanTextBuilder appendStrikethroughText(@Nullable String text) {
        return appendSpanText(text, new StrikethroughSpan());
    }

    /**
     * 下划线文本
     */
    public SpanTextBuilder appendUnderlineText(@Nullable String text) {
        return appendSpanText(text, new UnderlineSpan());
    }

    /**
     * 图标文本
     */
    public SpanTextBuilder appendDrawableText(@NonNull Context context, @DrawableRes int resId) {
        return appendDrawableText(context, resId, -1);
    }

    /**
     * 图标文本
     */
    public SpanTextBuilder appendDrawableText(@NonNull Context context, @DrawableRes int resId, int verticalAlignment) {
        if (verticalAlignment == ImageSpan.ALIGN_BASELINE || verticalAlignment == ImageSpan.ALIGN_BOTTOM) {
            return appendSpanText("ImageSpan", new ImageSpan(context, resId, verticalAlignment));
        } else {
            return appendSpanText("ImageSpan", new CenterImageSpan(context, resId));
        }
    }

    /**
     * 标签文本
     */
    public SpanTextBuilder appendLabelText(@Nullable String text, int radius, @ColorInt int textColor,
                                           @ColorInt int backgroundColor) {
        return appendSpanText(text, new LabelSpan(radius, textColor, backgroundColor)).appendSpanText(" ");
    }

    public SpanTextBuilder appendUrlText(@Nullable String text, @Nullable String url) {
        if (EmptyUtil.isEmptyText(url)) {
            return appendSpanText(text);
        }

        return appendSpanText(text, new URLSpan(url));
    }

    public void create() {
        mTextView.setText(mContentBuilder);
    }
}
