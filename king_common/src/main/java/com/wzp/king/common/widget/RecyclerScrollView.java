package com.wzp.king.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 解决ScrollView嵌套RecyclerView滑动卡顿问题
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/28
 */

public class RecyclerScrollView extends ScrollView {
    private int mSlop;
    private int mTouch;

    public RecyclerScrollView(@NonNull Context context) {
        super(context);
        setSlop(context);
    }

    public RecyclerScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setSlop(context);
    }

    public RecyclerScrollView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setSlop(context);
    }

    /**
     * 是否intercept当前的触摸事件
     *
     * @param ev 触摸事件
     * @return true：调用onMotionEvent()方法，并完成滑动操作
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // 保存当前touch的纵坐标值
                    mTouch = (int) ev.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    // 滑动距离大于slop值时，返回true
                    if (Math.abs((int) ev.getRawY() - mTouch) > mSlop) return true;
                    break;
            }
            return super.onInterceptTouchEvent(ev);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取相应context的touch slop值（即在用户滑动之前，能够滑动的以像素为单位的距离）
     *
     * @param context ScrollView对应的context
     */
    private void setSlop(@NonNull Context context) {
        mSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }
}

