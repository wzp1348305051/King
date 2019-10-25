package com.wzp.king.common.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.joanzapata.iconify.widget.IconTextView;

/**
 * 方形IconTextView
 *
 * @author wengzhipeng
 * @version v1.0, 2019-10-24
 */
public class SquareIconTextView extends IconTextView {
    public SquareIconTextView(Context context) {
        super(context);
    }

    public SquareIconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareIconTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int measuredSize;
        if (widthSize < heightSize) {
            measuredSize = widthSize;
        } else {
            measuredSize = heightSize;
        }
        setMeasuredDimension(measuredSize, measuredSize);
    }
}
