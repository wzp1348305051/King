package com.wzp.king.common.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzp.king.common.adapter.bean.ItemData;
import com.wzp.king.common.adapter.holder.RecyclerHolder;
import com.wzp.king.common.util.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 带下拉刷新功能的组合子适配器，ItemData的ViewType值必须大于0且小于10000，负数和10000以上号已被占用
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/1
 */

public abstract class MergeRecyclerChildAdapter<T extends ItemData> {
    private List<T> mItemDataList;

    public MergeRecyclerChildAdapter() {
        mItemDataList = new ArrayList<>();
    }

    @Nullable
    public List<T> getItemDataList() {
        return mItemDataList;
    }

    public void addItemData(@Nullable T itemData) {
        if (itemData == null) {
            return;
        }

        if (mItemDataList == null) {
            mItemDataList = new ArrayList<>();
        }
        mItemDataList.add(itemData);
    }

    public void addItemDataList(@Nullable List<T> itemDataList) {
        if (EmptyUtil.isEmptyCollection(itemDataList)) {
            return;
        }

        for (T itemData : itemDataList) {
            addItemData(itemData);
        }
    }

    /**
     * 确定包含多少Item项
     *
     * @return Item项数目
     */
    public int getItemCount() {
        return EmptyUtil.isEmptyCollection(mItemDataList) ? 0 : mItemDataList.size();
    }

    /**
     * 确定Item应使用何种布局
     *
     * @param position Item对应位置
     * @return 布局类型标识符，一般为枚举值
     */
    public int getItemViewType(int position) {
        return EmptyUtil.isEmptyCollection(mItemDataList) ? -1 : mItemDataList.get(position).getViewType();
    }

    /**
     * 创建ViewHolder
     */
    RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RecyclerHolder.getViewHolder(getLayoutByViewType(parent, LayoutInflater.from(parent.getContext()), viewType));
    }

    /**
     * 绑定ViewHolder
     */
    void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        if (position < 0 || EmptyUtil.isEmptyCollection(mItemDataList)) {
            return;
        }
        T itemData = mItemDataList.get(position);
        if (itemData == null) {
            return;
        }
        bindViewHolder(holder, itemData, position);
    }

    /**
     * 根据ViewType类型获取ViewHolder对应布局，一般通过LayoutInflater生成相应布局
     *
     * @param parent         Item对应父布局
     * @param layoutInflater XML布局解析器
     * @param viewType       Item对应布局类型标识符，一般为枚举值
     * @return Item对应布局
     */
    @NonNull
    protected abstract View getLayoutByViewType(@NonNull ViewGroup parent, @NonNull LayoutInflater layoutInflater, int viewType);

    /**
     * 填充ViewHolder数据
     *
     * @param holder   Item对应Holder
     * @param itemData 数据
     * @param position Item对应位置
     */
    protected abstract void bindViewHolder(@NonNull RecyclerHolder holder, @NonNull T itemData, int position);


}

