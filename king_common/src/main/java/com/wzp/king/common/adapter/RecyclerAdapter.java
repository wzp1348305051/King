package com.wzp.king.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzp.king.common.adapter.holder.RecyclerHolder;
import com.wzp.king.common.util.SysSrvUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 通用RecycleView适配器
 *
 * @author wengzhipeng
 * @version v1.0, 2017/6/15
 */

public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerHolder> {
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    protected List<T> mItemDataList;

    public RecyclerAdapter(@NonNull Context context) {
        this(context, null);
    }

    public RecyclerAdapter(@NonNull Context context, @Nullable List<T> itemDataList) {
        mContext = context;
        mLayoutInflater = SysSrvUtil.getLayoutInflater(context);
        mItemDataList = itemDataList;
    }

    @NonNull
    public Context getContext() {
        return mContext;
    }

    @Nullable
    public List<T> getItemDataList() {
        return mItemDataList;
    }

    public void setItemDataList(@Nullable List<T> itemData) {
        mItemDataList = itemData;
    }

    public void clearItemDataList() {
        if (mItemDataList == null) {
            return;
        }
        mItemDataList.clear();
    }

    @NonNull
    public LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }

    @Override
    public int getItemCount() {
        return mItemDataList == null ? 0 : mItemDataList.size();
    }

    @Override
    @NonNull
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RecyclerHolder.getViewHolder(getLayoutByViewType(parent, viewType));
    }

    /**
     * 确定Item应使用何种布局
     *
     * @param position Item对应位置
     * @return 布局类型标识符，一般为枚举值
     */
    @Override
    public abstract int getItemViewType(int position);

    /**
     * 填充ViewHolder数据
     *
     * @param holder   Item对应Holder
     * @param position Item对应位置
     */
    @Override
    public abstract void onBindViewHolder(@NonNull RecyclerHolder holder, int position);

    /**
     * 根据ViewType类型获取ViewHolder对应布局，一般通过LayoutInflater生成相应布局
     *
     * @param parent   Item对应父布局
     * @param viewType Item对应布局类型标识符，一般为枚举值
     * @return Item对应布局
     */
    @NonNull
    public abstract View getLayoutByViewType(@NonNull ViewGroup parent, int viewType);
}
