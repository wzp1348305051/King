package com.wzp.king.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.wzp.king.common.R;
import com.wzp.king.common.adapter.RecyclerAdapter;
import com.wzp.king.common.adapter.holder.RecyclerHolder;
import com.wzp.king.common.bean.constant.EmptyConstant;
import com.wzp.king.common.util.EmptyUtil;
import com.wzp.king.common.util.ResUtil;

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

public class MenuView extends RecyclerView {
    private RecyclerAdapter<Menu> mAdapter;
    private List<Menu> mMenuList;

    public static class Menu {
        private static final String ICON_NEXT = "{yit_next}";
        private CharSequence mTitle;
        private CharSequence mSubtitle;
        private String mIconLeft;
        private CharSequence mIconRight;
        private OnClickListener mOnClickListener;

        public Menu(@Nullable String title) {
            this(title, EmptyConstant.EMPTY_STRING);
        }

        public Menu(@Nullable CharSequence title, @Nullable CharSequence subtitle) {
            this(title, subtitle, null);
        }

        public Menu(@Nullable CharSequence title, @Nullable OnClickListener listener) {
            this(title, EmptyConstant.EMPTY_STRING, listener);
        }

        public Menu(@Nullable CharSequence title, @Nullable CharSequence subtitle, @Nullable OnClickListener listener) {
            this(title, subtitle, listener, EmptyConstant.EMPTY_STRING, EmptyConstant.EMPTY_STRING);
        }

        public Menu(@Nullable CharSequence title, @Nullable CharSequence subtitle, @Nullable OnClickListener listener
                , @Nullable String iconLeft, @Nullable CharSequence iconRight) {
            mTitle = title;
            mSubtitle = subtitle;
            mIconLeft = iconLeft;
            mIconRight = iconRight;
            mOnClickListener = listener;
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

        bindContentView(context);
    }

    private void bindContentView(@NonNull Context context) {
        mMenuList = new ArrayList<>();
        mAdapter = new RecyclerAdapter<Menu>(context, mMenuList) {
            @Override
            public int getItemViewType(int position) {
                return 1;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
                Menu menu = mItemDataList.get(position);

                holder.setText(R.id.tv_menu_title, menu.mTitle);
                if (EmptyUtil.isEmptyText(menu.mSubtitle)) {
                    holder.setVisibility(R.id.tv_menu_subtitle, GONE);
                } else {
                    holder.setVisibility(R.id.tv_menu_subtitle, VISIBLE);
                    holder.setText(R.id.tv_menu_subtitle, menu.mSubtitle);
                }
                if (EmptyUtil.isEmptyText(menu.mIconLeft)) {
                    holder.setVisibility(R.id.iv_menu_icon_left, GONE);
                } else {
                    holder.setVisibility(R.id.iv_menu_icon_left, VISIBLE);
                    if (menu.mIconLeft.contains("R.drawable.")) {
                        String name = menu.mIconLeft.substring(menu.mIconLeft.lastIndexOf(".") + 1);
                        holder.setImage(R.id.iv_menu_icon_left, ResUtil.getLocalResId(name, "drawable"));
                    } else {
                        holder.setImage(R.id.iv_menu_icon_left, menu.mIconLeft);
                    }
                }
                if (EmptyUtil.isEmptyText(menu.mIconRight) && menu.mOnClickListener != null) {
                    menu.mIconRight = Menu.ICON_NEXT;
                }
                if (EmptyUtil.isEmptyText(menu.mIconRight)) {
                    holder.setVisibility(R.id.tv_menu_icon_right, GONE);
                } else {
                    holder.setVisibility(R.id.tv_menu_icon_right, VISIBLE);
                    holder.setText(R.id.tv_menu_icon_right, menu.mIconRight);
                }
                holder.setOnItemViewClickListener(menu.mOnClickListener);
            }

            @NonNull
            @Override
            public View getLayoutByViewType(@NonNull ViewGroup parent, int viewType) {
                return mLayoutInflater.inflate(R.layout.wgt_menu, parent, false);
            }
        };
        setLayoutManager(new LinearLayoutManager(context, VERTICAL, false));
        setAdapter(mAdapter);
    }

    public void setMenuList(@Nullable List<Menu> menuList) {
        mMenuList.clear();
        if (!EmptyUtil.isEmptyCollection(menuList)) {
            mMenuList.addAll(menuList);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void addMenu(@NonNull Menu menu) {
        addMenu(menu, false);
    }

    public void addMenu(@NonNull Menu menu, boolean invalidateCache) {
        mMenuList.add(menu);
        if (invalidateCache) {
            mAdapter.notifyDataSetChanged();
        }
    }
}
