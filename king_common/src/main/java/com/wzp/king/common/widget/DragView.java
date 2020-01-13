package com.wzp.king.common.widget;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.wzp.king.common.util.DisplayUtil;

import androidx.annotation.Nullable;

/**
 * 可拖拽容器
 *
 * @author wengzhipeng
 * @version v1.0, 2019-12-17
 */
public class DragView extends FrameLayout {
    private int mStatusBarHeight;
    private int mScreenWidth;
    private int mScreenHeight;
    private int mDownX;
    private int mDownY;
    private int mLastX;
    private int mLastY;
    private int mBounceX;// X轴回弹距离

    public DragView(Context context) {
        this(context, null);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mStatusBarHeight = DisplayUtil.getStatusBarHeight();
        mScreenWidth = DisplayUtil.getScreenWidth(context);
        mScreenHeight = DisplayUtil.getScreenHeight(context) - mStatusBarHeight;
    }

    public void setBounceX(int bounceX) {
        mBounceX = bounceX;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int action = event.getAction();
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        if (action == MotionEvent.ACTION_DOWN) {
            mDownX = rawX;
            mDownY = rawY;
            mLastX = rawX;
            mLastY = rawY;
        } else if (action == MotionEvent.ACTION_MOVE) {
            int width = getWidth();
            int height = getHeight();
            int dx = rawX - mLastX;
            int dy = rawY - mLastY;
            int left = getLeft() + dx;
            int top = getTop() + dy;

            if (left < 0) {
                left = 0;
            } else if (left + width > mScreenWidth) {
                left = mScreenWidth - width;
            }
            if (top < mStatusBarHeight) {
                top = mStatusBarHeight;
            } else if (top + height > mScreenHeight) {
                top = mScreenHeight - height;
            }

            MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
            layoutParams.leftMargin = left;
            layoutParams.topMargin = top;
            setLayoutParams(layoutParams);

            mLastX = rawX;
            mLastY = rawY;
        } else if (action == MotionEvent.ACTION_UP) {
            int left = getLeft();
            int width = getWidth();
            if (Math.abs(mDownX - rawX) < 10 && Math.abs(mDownY - rawY) < 10) {
                performClick();
            } else if (left * 2 + width < mScreenWidth) {// 置左
                startScroll(left, true);
            } else {// 置右
                startScroll(mScreenWidth - left - width, false);
            }
        }
        return true;
    }

    private void startScroll(int dx, boolean isLeft) {
        long duration = dx * 1000 / mScreenWidth;
        ValueAnimator animatorMove = ValueAnimator.ofInt(dx, 0).setDuration(duration);
        animatorMove.addUpdateListener((ValueAnimator animation) -> {
            MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
            int value = (int) animation.getAnimatedValue();
            if (isLeft) {
                layoutParams.leftMargin = value;
            } else {
                layoutParams.leftMargin = mScreenWidth - value - getWidth();
            }
            setLayoutParams(layoutParams);
        });

        if (mBounceX > 0) {
            ValueAnimator animatorBack = ValueAnimator.ofInt(0, mBounceX).setDuration(80);
            animatorBack.addUpdateListener((ValueAnimator animation) -> {
                MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
                int value = (int) animation.getAnimatedValue();
                if (isLeft) {
                    layoutParams.leftMargin = value;
                } else {
                    layoutParams.leftMargin = mScreenWidth - value - getWidth();
                }
                setLayoutParams(layoutParams);
            });
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(animatorMove).before(animatorBack);
            animatorSet.start();
        } else {
            animatorMove.start();
        }
    }

}
