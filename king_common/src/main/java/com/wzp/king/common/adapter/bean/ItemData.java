package com.wzp.king.common.adapter.bean;

import androidx.annotation.IntRange;

/**
 * 适配器通用数据，用于绑定数据与布局类型
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/1
 */

public class ItemData {
    private int mViewType;

    public ItemData(@IntRange(from = 0) int viewType) {
        mViewType = viewType;
    }

    public int getViewType() {
        return mViewType;
    }
}
