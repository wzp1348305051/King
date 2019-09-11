package com.wzp.king.common.widget.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

import com.wzp.king.common.bean.constant.GlobalConstant;
import com.wzp.king.common.util.DisplayUtil;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 圆角背景Span
 *
 * @author wengzhipeng
 * @version v1.0, 2019-08-30
 */

public class LabelSpan extends ReplacementSpan {
    private int mRadius;
    private int mTextColor;
    private int mBackgroundColor;

    public LabelSpan(int radius, @ColorInt int textColor, @ColorInt int backgroundColor) {
        super();

        mRadius = radius > 0 ? DisplayUtil.dip2Px(GlobalConstant.APP_CONTEXT, radius) : 0;
        mTextColor = textColor;
        mBackgroundColor = backgroundColor;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x,
                     int top, int y, int bottom, @NonNull Paint paint) {
        float padding = getPadding(paint);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        RectF rect = new RectF(x, top, x + paint.measureText(text, start, end) + padding * 2,
                fontMetrics.bottom - fontMetrics.top);
        paint.setColor(mBackgroundColor);
        canvas.drawRoundRect(rect, mRadius, mRadius, paint);
        paint.setColor(mTextColor);
        canvas.drawText(text, start, end, x + padding, y- (padding / 6f), paint);
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end,
                       @Nullable Paint.FontMetricsInt fm) {
        return Math.round(paint.measureText(text, start, end)) + (int) (getPadding(paint) * 2);
    }

    private float getPadding(@NonNull Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (fontMetrics.bottom - fontMetrics.top) / 8f;
    }
}
