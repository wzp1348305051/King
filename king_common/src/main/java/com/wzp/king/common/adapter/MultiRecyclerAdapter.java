package com.wzp.king.common.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.wzp.king.common.adapter.bean.ItemData;
import com.wzp.king.common.adapter.holder.RecyclerHolder;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * 多布局RecycleView适配器
 *
 * @author wengzhipeng
 * @version v1.0, 2017/6/2
 */

public abstract class MultiRecyclerAdapter<T extends ItemData> extends RecyclerAdapter<T> {

    public MultiRecyclerAdapter(Context context) {
        super(context);
    }

    public MultiRecyclerAdapter(Context context, List<T> itemData) {
        super(context, itemData);
    }

    /**
     * 确定Item应使用何种布局
     *
     * @param position Item对应位置
     * @return 布局类型标识符，一般为枚举值
     */
    @Override
    public int getItemViewType(int position) {
        return mItemDataList == null ? 0 : mItemDataList.get(position).getViewType();
    }

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

