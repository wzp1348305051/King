package com.wzp.king.common.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.wzp.king.common.R;
import com.wzp.king.common.util.BitmapUtil;
import com.wzp.king.common.util.DisplayUtil;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 通用Loading框
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/1
 */

public class LoadingView extends View {
    private float mProgress;
    private float mOffset;
    private boolean mIsStop;
    private Paint mPaint;
    private ValueAnimator mAnimator;
    private Bitmap mBackground;
    private int mBackgroundSize;
    private RectF mRectBackground;
    private RectF mRectLoading;

    public LoadingView(@NonNull Context context) {
        this(context, null);
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initMemberData(context);
        initView();
    }

    private void initMemberData(@NonNull Context context) {
        mProgress = 0;
        mOffset = -90f;
        mIsStop = true;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(2);

        mAnimator = ValueAnimator.ofFloat(0 + mOffset, 720 + mOffset).setDuration(1200);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setRepeatCount(Animation.INFINITE);
        mAnimator.setRepeatMode(ValueAnimator.RESTART);
        mAnimator.addUpdateListener((ValueAnimator animation) -> {
            mProgress = (float) animation.getAnimatedValue();
            invalidate();
        });

        mBackground = BitmapUtil.getBitmapByResId(R.drawable.loading_bg);
        mBackgroundSize = DisplayUtil.dip2Px(context, 105);
        mRectBackground = new RectF(0, 0, mBackgroundSize, mBackgroundSize);

        int start = DisplayUtil.dip2Px(context, 26);
        int end = mBackgroundSize - start;
        mRectLoading = new RectF(start, start, end, end);
    }

    private void initView() {
        setFocusable(false);
        setEnabled(false);
        setFocusableInTouchMode(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mBackgroundSize, mBackgroundSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 计算开始度数和扫过的度数
        float startDegree;
        float swipeDegree;
        if (mProgress <= 360f + mOffset) {
            startDegree = 0 + mOffset;
            swipeDegree = mProgress - startDegree;
        } else {
            startDegree = mProgress - 360;
            swipeDegree = 360 + mOffset - startDegree;
        }
        // 画背景
        canvas.drawBitmap(mBackground, null, mRectBackground, null);
        // 画圆弧
        canvas.drawArc(mRectLoading, startDegree, swipeDegree, false, mPaint);
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (mIsStop) {
            return;
        }

        if (visibility == View.VISIBLE) {
            mAnimator.start();
        } else {
            mAnimator.end();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        stopLoading();
    }

    public void startLoading() {
        if (!mIsStop) {
            return;
        }

        mIsStop = false;
        mAnimator.start();
    }

    public void stopLoading() {
        mIsStop = true;
        mAnimator.end();
        mAnimator.cancel();
    }
}
