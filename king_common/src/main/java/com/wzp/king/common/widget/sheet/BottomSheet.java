package com.wzp.king.common.widget.sheet;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.wzp.king.common.R;
import com.wzp.king.common.adapter.RecyclerAdapter;
import com.wzp.king.common.adapter.holder.RecyclerHolder;
import com.wzp.king.common.util.DisplayUtil;
import com.wzp.king.common.util.EmptyUtil;
import com.wzp.king.common.util.L;
import com.wzp.king.common.widget.LoadImageView;
import com.wzp.king.common.widget.SingleClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

/**
 * 通用底部列表
 *
 * @author wengzhipeng
 * @version v1.0, 2019/7/9
 */

public class BottomSheet extends Dialog {
    private static final String TAG = BottomSheet.class.getSimpleName();
    private final static int mAnimationDuration = 300;
    private View mContentView;
    private boolean mIsAnimating = false;

    BottomSheet(@NonNull Context context) {
        super(context, R.style.BottomSheetTheme);
    }

    BottomSheet(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initSheet();
    }

    @Override
    public void setContentView(int layoutResID) {
        mContentView = LayoutInflater.from(getContext()).inflate(layoutResID, null);
        super.setContentView(mContentView);
    }

    @Override
    public void setContentView(@NonNull View view) {
        mContentView = view;
        super.setContentView(view);
    }

    @Override
    public void setContentView(@NonNull View view, ViewGroup.LayoutParams params) {
        mContentView = view;
        super.setContentView(view, params);
    }

    @Override
    public void show() {
        super.show();

        animateUp();
    }

    @Override
    public void dismiss() {
        if (mIsAnimating) {
            return;
        }

        animateDown();
    }

    private void initSheet() {
        Window window = getWindow();
        if (window == null) {
            return;
        }

        window.getDecorView().setPadding(0, 0, 0, 0);

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER;
        int screenWidth = DisplayUtil.getScreenWidth(getContext());
        int screenHeight = DisplayUtil.getScreenHeight(getContext());
        layoutParams.width = screenWidth < screenHeight ? screenWidth : screenHeight;
        window.setAttributes(layoutParams);

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    /**
     * BottomSheet升起动画
     */
    private void animateUp() {
        if (mContentView == null) {
            return;
        }
        TranslateAnimation translate = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f
        );
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(translate);
        set.addAnimation(alpha);
        set.setInterpolator(new DecelerateInterpolator());
        set.setDuration(mAnimationDuration);
        set.setFillAfter(true);
        mContentView.startAnimation(set);
    }

    /**
     * BottomSheet降下动画
     */
    private void animateDown() {
        if (mContentView == null) {
            return;
        }
        TranslateAnimation translate = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f
        );
        AlphaAnimation alpha = new AlphaAnimation(1, 0);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(translate);
        set.addAnimation(alpha);
        set.setInterpolator(new DecelerateInterpolator());
        set.setDuration(mAnimationDuration);
        set.setFillAfter(true);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mIsAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIsAnimating = false;
                mContentView.post(() -> {
                    // 在dismiss的时候可能已经detach了，简单try-catch一下
                    try {
                        BottomSheet.super.dismiss();
                    } catch (Exception e) {
                        L.e(TAG, e);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mContentView.startAnimation(set);
    }

    public static class MenuBuilder extends BottomSheetBuilder<MenuBuilder, CharSequence> {
        private List<CharSequence> mMenuList;
        private int mCheckedIndex;
        private MenuSelectListener mListener;

        public interface MenuSelectListener {
            void onSelect(int selectIndex);
        }

        public MenuBuilder(@NonNull Context context) {
            super(context);

            mMenuList = new ArrayList<>();
            mCheckedIndex = -1;
        }

        @NonNull
        public MenuBuilder setMenuList(@NonNull List<CharSequence> menuList, @Nullable MenuSelectListener listener) {
            mMenuList.clear();
            for (CharSequence charSequence : menuList) {
                if (EmptyUtil.isEmptyText(charSequence)) {
                    continue;
                }
                mMenuList.add(charSequence);
            }
            mListener = listener;
            return this;
        }

        @NonNull
        public MenuBuilder setMenuArray(@NonNull CharSequence[] menuArray, @Nullable MenuSelectListener listener) {
            return setMenuList(Arrays.asList(menuArray), listener);
        }

        @NonNull
        public MenuBuilder setCheckedIndex(int checkedIndex) {
            mCheckedIndex = checkedIndex;
            return this;
        }

        @Override
        public RecyclerAdapter<CharSequence> getContentAdapter() {
            return new MenuAdapter(mContext, mMenuList, mCheckedIndex, mListener, mBottomSheet);
        }

        @Override
        public int getContentHeight() {
            if (EmptyUtil.isEmptyCollection(mMenuList)) {
                return ViewGroup.LayoutParams.WRAP_CONTENT;
            }

            int itemHeight = mMenuList.size() * DisplayUtil.dip2Px(mContext, 48.5f);
            int maxHeight = contentMaxHeight();
            return itemHeight < maxHeight ? ViewGroup.LayoutParams.WRAP_CONTENT : maxHeight;
        }

        static class MenuAdapter extends RecyclerAdapter<CharSequence> {
            private int mCheckedIndex;
            private MenuSelectListener mListener;
            private BottomSheet mBottomSheet;

            MenuAdapter(@NonNull Context context, @Nullable List<CharSequence> itemData, int checkedIndex,
                        @Nullable MenuSelectListener listener, @NonNull BottomSheet bottomSheet) {
                super(context, itemData);

                mCheckedIndex = checkedIndex;
                mListener = listener;
                mBottomSheet = bottomSheet;
            }

            @Override
            public int getItemViewType(int position) {
                return 1;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
                CharSequence label = mItemDataList.get(position);
                holder.setText(R.id.tv_sheet_menu_label, label);
                if (mCheckedIndex == position) {
                    holder.setVisibility(R.id.iv_sheet_menu_selected, View.VISIBLE);
                } else {
                    holder.setVisibility(R.id.iv_sheet_menu_selected, View.INVISIBLE);
                }
                holder.setOnItemViewClickListener(new SingleClickListener() {
                    @Override
                    public void onSingleClick(@NonNull View v) {
                        if (mListener != null) {
                            mListener.onSelect(position);
                        }
                        mBottomSheet.dismiss();
                        mBottomSheet = null;
                    }
                });
            }

            @NonNull
            @Override
            public View getLayoutByViewType(@NonNull ViewGroup parent, int viewType) {
                return LayoutInflater.from(mContext).inflate(R.layout.sheet_bottom_menu_item, parent, false);
            }
        }
    }

    public static class ChoiceItem {
        private int mIconResId;
        private String mIconUrl;
        private CharSequence mLabelText;

        public ChoiceItem(@DrawableRes int iconResId, @Nullable CharSequence labelText) {
            mIconResId = iconResId;
            mLabelText = labelText;
        }

        public ChoiceItem(@NonNull String iconUrl, @Nullable CharSequence labelText) {
            mIconUrl = iconUrl;
            mLabelText = labelText;
        }
    }

    public static class ChoiceBuilder extends BottomSheetBuilder<ChoiceBuilder, ChoiceItem> {
        private List<ChoiceItem> mChoiceList;
        private ChoiceSelectListener mListener;

        public interface ChoiceSelectListener {
            void onSelect(int selectIndex);
        }

        public ChoiceBuilder(@NonNull Context context) {
            super(context);

            mChoiceList = new ArrayList<>();
        }

        @NonNull
        public ChoiceBuilder setChoiceList(@NonNull List<ChoiceItem> choiceList, @Nullable ChoiceSelectListener listener) {
            mChoiceList.clear();
            mChoiceList.addAll(choiceList);
            mListener = listener;
            return this;
        }

        @NonNull
        public ChoiceBuilder setChoiceArray(@NonNull ChoiceItem[] choiceArray, @Nullable ChoiceSelectListener listener) {
            return setChoiceList(Arrays.asList(choiceArray), listener);
        }

        @Override
        public RecyclerAdapter<ChoiceItem> getContentAdapter() {
            return new ChoiceAdapter(mContext, mChoiceList, mListener, mBottomSheet);
        }

        @Override
        public int getContentHeight() {
            if (EmptyUtil.isEmptyCollection(mChoiceList)) {
                return ViewGroup.LayoutParams.WRAP_CONTENT;
            }

            int itemHeight = (int) Math.ceil(mChoiceList.size() * 1.0f / 4) * DisplayUtil.dip2Px(mContext, 106f);
            int maxHeight = contentMaxHeight();
            return itemHeight < maxHeight ? ViewGroup.LayoutParams.WRAP_CONTENT : maxHeight;
        }

        static class ChoiceAdapter extends RecyclerAdapter<ChoiceItem> {
            private ChoiceSelectListener mListener;
            private BottomSheet mBottomSheet;

            ChoiceAdapter(@NonNull Context context, @Nullable List<ChoiceItem> itemData,
                          @Nullable ChoiceSelectListener listener, @NonNull BottomSheet bottomSheet) {
                super(context, itemData);

                mListener = listener;
                mBottomSheet = bottomSheet;
            }

            @Override
            public int getItemViewType(int position) {
                return 1;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
                ChoiceItem choiceItem = mItemDataList.get(position);

                LoadImageView ivIcon = (LoadImageView) holder.getView(R.id.iv_sheet_choice_icon);
                TextView tvLabel = (TextView) holder.getView(R.id.tv_sheet_choice_label);

                if (choiceItem.mIconResId > 0) {
                    ivIcon.load(choiceItem.mIconResId);
                } else {
                    ivIcon.load(choiceItem.mIconUrl);
                }
                tvLabel.setText(choiceItem.mLabelText);

                holder.setOnItemViewClickListener(new SingleClickListener() {
                    @Override
                    public void onSingleClick(@NonNull View v) {
                        if (mListener != null) {
                            mListener.onSelect(position);
                        }
                        mBottomSheet.dismiss();
                        mBottomSheet = null;
                    }
                });
            }

            @NonNull
            @Override
            public View getLayoutByViewType(@NonNull ViewGroup parent, int viewType) {
                return LayoutInflater.from(mContext).inflate(R.layout.sheet_bottom_choice_item, parent, false);
            }
        }
    }
}
