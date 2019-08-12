package com.wzp.king.common.adapter.holder;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzp.king.common.R;
import com.wzp.king.common.util.GlideUtil;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 通用RecycleViewHolder
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/1
 */

public class RecyclerHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViewArray;
    private View mItemView;

    private RecyclerHolder(@NonNull View itemView) {
        super(itemView);
        mViewArray = new SparseArray<>();
        mItemView = itemView;
    }

    /**
     * 根据布局获取相应ViewHolder
     *
     * @param rootView ViewHolder对应根布局
     */
    @NonNull
    public static RecyclerHolder getViewHolder(@NonNull View rootView) {
        return new RecyclerHolder(rootView);
    }

    /**
     * 获取Holder对应根布局
     */
    @NonNull
    public View getItemView() {
        return mItemView;
    }

    /**
     * 获取布局子控件
     *
     * @param viewId 控件id
     */
    @NonNull
    public View getView(@IdRes int viewId) {
        View view = mViewArray.get(viewId);
        if (view == null) {
            view = mItemView.findViewById(viewId);
            mViewArray.put(viewId, view);
        }
        if (view == null) {
            throw new RuntimeException("未找到对应控件");
        }
        return view;
    }

    /**
     * 设置控件可见性
     *
     * @param viewId     TextView控件id
     * @param visibility 可见性
     */
    public void setVisibility(@IdRes int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
    }

    /**
     * 加载TextView文本内容
     *
     * @param viewId TextView控件id
     * @param text   文本内容
     */
    public void setText(@IdRes int viewId, @Nullable CharSequence text) {
        View view = getView(viewId);
        if (view instanceof TextView) {
            ((TextView) view).setText(text);
        }
    }

    /**
     * 加载ImageView图片
     *
     * @param viewId   ImageView控件id
     * @param imageUrl 图片资源地址
     */
    public void setImage(@IdRes int viewId, @Nullable String imageUrl) {
        View view = getView(viewId);
        if (view instanceof ImageView) {
            GlideUtil.loadImageBitmap((ImageView) view, imageUrl, R.drawable.image_holder, R.drawable.image_error);
        }
    }

    /**
     * 加载ImageView图片
     *
     * @param viewId ImageView控件id
     * @param resId  本地图片资源ID
     */
    public void setImage(@IdRes int viewId, @DrawableRes int resId) {
        View view = getView(viewId);
        if (view instanceof ImageView) {
            GlideUtil.loadImageBitmap((ImageView) view, resId, R.drawable.image_holder, R.drawable.image_error);
        }
    }

    /**
     * 设置控件点击事件
     *
     * @param viewId   控件id
     * @param listener 点击事件
     */
    public void setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
    }

    /**
     * 设置Item根布局点击事件
     *
     * @param listener 点击事件
     */
    public void setOnItemViewClickListener(View.OnClickListener listener) {
        if (mItemView == null) {
            return;
        }
        mItemView.setOnClickListener(listener);
    }

}
