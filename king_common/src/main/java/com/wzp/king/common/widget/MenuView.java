package com.wzp.king.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wzp.king.common.R;
import com.wzp.king.common.adapter.RecyclerAdapter;
import com.wzp.king.common.adapter.holder.RecyclerHolder;
import com.wzp.king.common.bean.constant.EmptyConstant;
import com.wzp.king.common.util.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 菜单
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/7
 */

public class MenuView extends FrameLayout {
    private RecyclerAdapter<MenuItem> mAdapter;
    private List<MenuItem> mItemMenuList;

    public static class MenuItem {
        private static final String ICON_NEXT = "{yit_next}";
        private String mLabel;
        private String mContent;
        private OnClickListener mOnClickListener;

        public MenuItem(@Nullable String label) {
            this(label, EmptyConstant.EMPTY_STRING);
        }

        public MenuItem(@Nullable String label, @Nullable String content) {
            this(label, content, null);
        }

        public MenuItem(@Nullable String label, @Nullable OnClickListener onClickListener) {
            this(label, ICON_NEXT, onClickListener);
        }

        public MenuItem(@Nullable String label, @Nullable String content,
                        @Nullable OnClickListener onClickListener) {
            mLabel = label;
            mContent = content;
            mOnClickListener = onClickListener;
        }

        @Nullable
        String getLabel() {
            return mLabel;
        }

        @Nullable
        String getContent() {
            return mContent;
        }

        void onItemClick(View view) {
            if (mOnClickListener == null) {
                return;
            }
            mOnClickListener.onClick(view);
        }
    }

    public MenuView(@NonNull Context context) {
        this(context, null);
    }

    public MenuView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    private void initView(@NonNull Context context) {
        inflate(getContext(), R.layout.wgt_menu, this);

        RecyclerView rvContent = findViewById(R.id.rv_menu_content);

        mItemMenuList = new ArrayList<>();
        mAdapter = new RecyclerAdapter<MenuItem>(context, mItemMenuList) {
            @Override
            public int getItemViewType(int position) {
                return 1;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
                final MenuItem menuItem = mItemDataList.get(position);
                holder.setText(R.id.tv_menu_item_label, menuItem.getLabel());
                holder.setText(R.id.tv_menu_item_content, menuItem.getContent());
                holder.setOnItemViewClickListener(new SingleClickListener() {
                    @Override
                    public void onSingleClick(@NonNull View v) {
                        menuItem.onItemClick(v);
                    }
                });
            }

            @NonNull
            @Override
            public View getLayoutByViewType(@NonNull ViewGroup parent, int viewType) {
                return mLayoutInflater.inflate(R.layout.wgt_menu_item, parent, false);
            }
        };
        rvContent.setLayoutManager(new LinearLayoutManager(context));
        rvContent.setAdapter(mAdapter);
    }

    public void setMenuItemList(@Nullable List<MenuItem> menuItemList) {
        mItemMenuList.clear();
        if (!EmptyUtil.isEmptyCollection(menuItemList)) {
            mItemMenuList.addAll(menuItemList);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void addMenuItem(@NonNull MenuItem menuItem) {
        mItemMenuList.add(menuItem);
        mAdapter.notifyDataSetChanged();
    }
}
