package com.wzp.king.common.widget.sheet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wzp.king.common.R;
import com.wzp.king.common.adapter.RecyclerAdapter;
import com.wzp.king.common.bean.constant.EmptyConstant;
import com.wzp.king.common.util.DisplayUtil;
import com.wzp.king.common.util.EmptyUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * BottomSheet Builder
 *
 * @author wengzhipeng
 * @version v1.0, 2019/7/9
 */

public abstract class BottomSheetBuilder<T extends BottomSheetBuilder, D> {
    public static final int TYPE_LIST = 1000;
    public static final int TYPE_GRID = 1001;
    Context mContext;
    BottomSheet mBottomSheet;
    private int mType;
    private CharSequence mTitle;

    BottomSheetBuilder(@NonNull Context context) {
        mContext = context;
        mBottomSheet = null;
        mType = TYPE_LIST;
        mTitle = EmptyConstant.EMPTY_STRING;
    }

    int contentMaxHeight() {
        return (int) (DisplayUtil.getScreenHeight(mContext) * 0.5);
    }

    @SuppressWarnings("unchecked")
    public T setType(int type) {
        mType = type;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTitle(@Nullable CharSequence title) {
        mTitle = title;
        return (T) this;
    }

    public BottomSheet create() {
        mBottomSheet = new BottomSheet(mContext);

        View root = LayoutInflater.from(mContext).inflate(R.layout.sheet_bottom, null);
        // Title
        TextView tvTitle = root.findViewById(R.id.tv_sheet_title);
        if (EmptyUtil.isEmptyText(mTitle)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(mTitle);
            tvTitle.setVisibility(View.VISIBLE);
        }
        // Content
        RecyclerView recyclerView = root.findViewById(R.id.rv_sheet_content);
        if (mType == TYPE_LIST) {
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        }
        if (getContentMaxHeight() != ViewGroup.LayoutParams.WRAP_CONTENT) {
            recyclerView.getLayoutParams().height = getContentMaxHeight();
        }
        recyclerView.setAdapter(getContentAdapter());

        mBottomSheet.setContentView(root, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        mBottomSheet.setCancelable(true);
        mBottomSheet.setCanceledOnTouchOutside(true);
        return mBottomSheet;
    }

    public abstract RecyclerAdapter<D> getContentAdapter();

    public abstract int getContentMaxHeight();
}
