package com.wzp.king.common.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzp.king.common.adapter.bean.ItemData;
import com.wzp.king.common.adapter.holder.RecyclerHolder;
import com.wzp.king.common.util.EmptyUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 组合适配器
 *
 * @author wengzhipeng
 * @version v1.0, 2017/10/10
 */

public class MergeRecyclerAdapter<T extends ItemData> extends RecyclerView.Adapter<RecyclerHolder> {
    protected Activity mActivity;
    protected List<MergeRecyclerChildAdapter<T>> mChildAdapterList;
    protected LinkedList<Integer> mChildAdapterIndex;// 存储子Adapter最后一位索引加一，用于根据位置寻找子Adapter

    public MergeRecyclerAdapter(@NonNull Activity activity) {
        mActivity = activity;
        mChildAdapterList = new ArrayList<>();
        mChildAdapterIndex = new LinkedList<>();
    }

    public void addChildAdapter(@Nullable MergeRecyclerChildAdapter<T> childAdapter) {
        if (childAdapter == null) {
            return;
        }
        mChildAdapterList.add(childAdapter);
        updateChildAdapterIndex(childAdapter);
    }

    public void addChildAdapters(@Nullable List<? extends MergeRecyclerChildAdapter<T>> childAdapterList) {
        if (EmptyUtil.isEmptyCollection(childAdapterList)) {
            return;
        }
        for (MergeRecyclerChildAdapter<T> childAdapter : childAdapterList) {
            addChildAdapter(childAdapter);
        }
    }

    public void cleanChildAdapterList() {
        mChildAdapterList.clear();
        notifyAdapterItemChanged();
    }

    public void resetChildAdapterList(@Nullable List<? extends MergeRecyclerChildAdapter<T>> childAdapterList) {
        mChildAdapterList.clear();
        addChildAdapters(childAdapterList);
    }

    public void addChildAdapterView(@Nullable View view) {
        if (view == null) {
            return;
        }

        addChildAdapter(new ChildViewAdapter<>(view));
    }

    @Nullable
    public List<MergeRecyclerChildAdapter<T>> getChildAdapterList() {
        return mChildAdapterList;
    }

    public void notifyAdapterItemChanged() {
        refreshChildAdapterIndex();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        Integer itemCount = mChildAdapterIndex.peekLast();
        return itemCount == null ? 0 : itemCount;
    }

    /**
     * 确定Item应使用何种布局
     *
     * @param position Item对应位置
     * @return 布局类型标识符，一般为枚举值
     */
    @Override
    public int getItemViewType(int position) {
        int childAdapterIndex = getChildAdapterIndexByPosition(position);
        if (childAdapterIndex != -1) {
            MergeRecyclerChildAdapter<T> childAdapter = mChildAdapterList.get(childAdapterIndex);
            if (childAdapter != null) {
                if (childAdapterIndex == 0) {
                    return childAdapter.getItemViewType(position);
                } else {
                    return childAdapter.getItemViewType(position - mChildAdapterIndex.get(childAdapterIndex - 1));
                }
            }
        }
        return -1;
    }

    @Override
    @NonNull
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerHolder holder = null;
        for (MergeRecyclerChildAdapter<T> adapter : mChildAdapterList) {
            if (adapter != null) {
                holder = adapter.onCreateViewHolder(parent, viewType);
            }
            if (holder != null) {
                return holder;
            }
        }
        throw new UnsupportedOperationException("Not Support Current View Type");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        int childAdapterIndex = getChildAdapterIndexByPosition(position);
        if (childAdapterIndex != -1) {
            MergeRecyclerChildAdapter<T> childAdapter = mChildAdapterList.get(childAdapterIndex);
            if (childAdapter != null) {
                if (childAdapterIndex == 0) {
                    childAdapter.onBindViewHolder(holder, position);
                } else {
                    childAdapter.onBindViewHolder(holder, position - mChildAdapterIndex.get(childAdapterIndex - 1));
                }
            }
        }
    }

    protected int getChildAdapterIndexByPosition(int position) {
        int size = mChildAdapterIndex.size();
        for (int i = 0; i < size; i++) {
            if (position < mChildAdapterIndex.get(i)) {
                return i;
            }
        }
        return -1;
    }

    protected void refreshChildAdapterIndex() {
        mChildAdapterIndex.clear();
        if (!EmptyUtil.isEmptyCollection(mChildAdapterList)) {
            for (MergeRecyclerChildAdapter<T> childAdapter : mChildAdapterList) {
                updateChildAdapterIndex(childAdapter);
            }
        }
    }

    protected void updateChildAdapterIndex(@NonNull MergeRecyclerChildAdapter<T> childAdapter) {
        Integer lastIndex = mChildAdapterIndex.peekLast();
        int itemCount = childAdapter.getItemCount();
        if (lastIndex == null) {
            mChildAdapterIndex.offerLast(itemCount);
        } else {
            mChildAdapterIndex.offerLast(lastIndex + itemCount);
        }
    }

    private static class ChildViewAdapter<T extends ItemData> extends MergeRecyclerChildAdapter<T> {
        private static int sItemViewType = 10000;
        private int mItemViewType;
        private View mItemView;

        ChildViewAdapter(@NonNull View view) {
            super();
            mItemView = view;
            synchronized (ChildViewAdapter.class) {
                mItemViewType = sItemViewType;
                sItemViewType++;
            }
        }

        @Override
        @NonNull
        protected View getLayoutByViewType(@NonNull ViewGroup parent, @NonNull LayoutInflater layoutInflater,
                                           int viewType) {
            if (viewType == mItemViewType) {
                return mItemView;
            }
            throw new RuntimeException("Invalid Item View Type");
        }

        @Override
        protected void bindViewHolder(@NonNull RecyclerHolder holder, @NonNull ItemData itemData, int position) {

        }

        @Override
        public int getItemCount() {
            return 1;
        }

        @Override
        public int getItemViewType(int position) {
            return mItemViewType;
        }
    }
}
