package com.wzp.king.common.widget.dialog;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzp.king.common.R;
import com.wzp.king.common.adapter.RecyclerAdapter;
import com.wzp.king.common.adapter.holder.RecyclerHolder;
import com.wzp.king.common.bean.constant.EmptyConstant;
import com.wzp.king.common.util.EmptyUtil;
import com.wzp.king.common.widget.SingleClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 通用Dialog
 *
 * @author wengzhipeng
 * @version v1.0, 2018/6/20
 */

public class CustomDialog extends Dialog {

    CustomDialog(@NonNull Context context) {
        super(context, R.style.DialogTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDialog();
    }

    private void initDialog() {
        Window window = getWindow();
        if (window == null) {
            return;
        }

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.CENTER;
        window.setAttributes(layoutParams);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public static class MessageBuilder extends CustomDialogBuilder<MessageBuilder> {
        private String mMessage;

        public MessageBuilder(@NonNull Context context) {
            super(context);
        }

        public MessageBuilder setMessage(@Nullable String message) {
            mMessage = message;
            return this;
        }

        @Override
        public void createContentView(LinearLayout contentView) {
            if (EmptyUtil.isEmptyText(mMessage)) {
                return;
            }

            TextView textView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.dialog_custom_message,
                    contentView, false);
            textView.setText(mMessage);
            contentView.addView(textView);
        }
    }

    public static class InputBuilder extends CustomDialogBuilder<InputBuilder> {
        private String mHint;
        private EditText mEditText;

        public InputBuilder(@NonNull Context context) {
            super(context);
        }

        @NonNull
        public InputBuilder setHint(@Nullable String hint) {
            mHint = hint;
            return this;
        }

        @NonNull
        public String getInput() {
            if (mEditText == null) {
                return EmptyConstant.EMPTY_STRING;
            }

            return mEditText.getText().toString().trim();
        }

        @Override
        public void createContentView(LinearLayout contentView) {
            mEditText = (EditText) LayoutInflater.from(mContext).inflate(R.layout.dialog_custom_input,
                    contentView, false);
            mEditText.setHint(mHint);
            contentView.addView(mEditText);
        }
    }

    public static class MenuBuilder extends CustomDialogBuilder<MenuBuilder> {
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
        public void createContentView(LinearLayout contentView) {
            if (EmptyUtil.isEmptyCollection(mMenuList)) {
                return;
            }

            RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(mContext).inflate(R.layout.dialog_custom_menu,
                    contentView, false);
            MenuAdapter adapter = new MenuAdapter(mContext, mMenuList, mCheckedIndex, mListener, mDialog);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(adapter);
            contentView.addView(recyclerView);
        }

        static class MenuAdapter extends RecyclerAdapter<CharSequence> {
            private int mCheckedIndex;
            private MenuSelectListener mListener;
            private CustomDialog mDialog;

            MenuAdapter(@NonNull Context context, @NonNull List<CharSequence> itemData, int checkedIndex,
                        @Nullable MenuSelectListener listener, @NonNull CustomDialog dialog) {
                super(context, itemData);

                mCheckedIndex = checkedIndex;
                mListener = listener;
                mDialog = dialog;
            }

            @Override
            public int getItemViewType(int position) {
                return 1;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
                CharSequence label = mItemDataList.get(position);
                holder.setText(R.id.tv_dialog_menu_label, label);
                if (mCheckedIndex == position) {
                    holder.setVisibility(R.id.iv_dialog_menu_selected, View.VISIBLE);
                } else {
                    holder.setVisibility(R.id.iv_dialog_menu_selected, View.INVISIBLE);
                }
                holder.setOnItemViewClickListener(new SingleClickListener() {
                    @Override
                    public void onSingleClick(@NonNull View v) {
                        if (mListener != null) {
                            mListener.onSelect(position);
                        }
                        mDialog.dismiss();
                        mDialog = null;
                    }
                });
            }

            @NonNull
            @Override
            public View getLayoutByViewType(@NonNull ViewGroup parent, int viewType) {
                return LayoutInflater.from(mContext).inflate(R.layout.dialog_custom_menu_item, parent, false);
            }
        }
    }

    public static class ChoiceBuilder extends CustomDialogBuilder<ChoiceBuilder> {
        private LinkedHashMap<CharSequence, Boolean> mChoiceMap;
        private boolean mSingleMode;// 是否为单选模式，默认多选模式

        public interface ConfirmListener {
            void onConfirm(@NonNull int[] checkedIndexArray);
        }

        public ChoiceBuilder(@NonNull Context context) {
            super(context);

            mChoiceMap = new LinkedHashMap<>();
            mSingleMode = false;
        }

        @NonNull
        public ChoiceBuilder setChoiceList(@NonNull List<CharSequence> choiceList) {
            mChoiceMap.clear();
            for (CharSequence charSequence : choiceList) {
                if (EmptyUtil.isEmptyText(charSequence)) {
                    continue;
                }
                mChoiceMap.put(charSequence, false);
            }
            return this;
        }

        @NonNull
        public ChoiceBuilder setChoiceArray(@NonNull CharSequence[] choiceArray) {
            return setChoiceList(Arrays.asList(choiceArray));
        }

        @NonNull
        public ChoiceBuilder setChoiceMode(boolean singleMode, @Nullable int[] checkedIndexArray) {
            if (singleMode && checkedIndexArray != null && checkedIndexArray.length > 1) {
                throw new UnsupportedOperationException("单选模式中最多只能有一个默认选中项");
            }

            mSingleMode = singleMode;
            if (!EmptyUtil.isEmptyMap(mChoiceMap) && checkedIndexArray != null && checkedIndexArray.length > 0) {
                int minIndex = 0;
                int maxIndex = mChoiceMap.size() - 1;
                ArrayList<CharSequence> keys = new ArrayList<>(mChoiceMap.keySet());
                for (int index : checkedIndexArray) {
                    if (index < minIndex || index > maxIndex) {
                        continue;
                    }
                    mChoiceMap.replace(keys.get(index), true);
                }
            }
            return this;
        }

        @NonNull
        public int[] getCheckedIndexArray() {
            if (EmptyUtil.isEmptyMap(mChoiceMap)) {
                return EmptyConstant.EMPTY_INT_ARRAY;
            }

            ArrayList<Integer> indexList = new ArrayList<>();
            ArrayList<Boolean> valueList = new ArrayList<>(mChoiceMap.values());
            for (int i = 0, size = valueList.size(); i < size; i++) {
                if (valueList.get(i)) {
                    indexList.add(i);
                }
            }
            return indexList.stream().mapToInt(Integer::valueOf).toArray();
        }

        @Override
        public void createContentView(LinearLayout contentView) {
            if (EmptyUtil.isEmptyMap(mChoiceMap)) {
                return;
            }

            RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(mContext).inflate(R.layout.dialog_custom_choice, contentView, false);
            ChoiceAdapter adapter = new ChoiceAdapter(mContext, mChoiceMap, mSingleMode);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(adapter);
            contentView.addView(recyclerView);
        }

        static class ChoiceAdapter extends RecyclerAdapter<CharSequence> {
            private LinkedHashMap<CharSequence, Boolean> mChoiceMap;
            private boolean mSingleMode;// 是否为单选模式，默认多选模式
            private CharSequence mSingleModeKey;// 单选模式选中的Map Item Key

            ChoiceAdapter(@NonNull Context context, @NonNull LinkedHashMap<CharSequence, Boolean> choiceMap,
                          boolean singleMode) {
                super(context, new ArrayList<>(choiceMap.keySet()));

                mChoiceMap = choiceMap;
                mSingleMode = singleMode;
                mSingleModeKey = EmptyConstant.EMPTY_STRING;

                if (singleMode) {
                    Set<Map.Entry<CharSequence, Boolean>> set = choiceMap.entrySet();
                    for (Map.Entry<CharSequence, Boolean> entry : set) {
                        if (entry.getValue()) {
                            mSingleModeKey = entry.getKey();
                            break;
                        }
                    }
                }
            }

            @Override
            public int getItemViewType(int position) {
                return 1;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
                CheckBox checkBox = (CheckBox) holder.getItemView();
                CharSequence label = mItemDataList.get(position);

                checkBox.setText(label);
                Boolean checked = mChoiceMap.get(label);
                if (checked != null && checked) {
                    checkBox.setChecked(true);
                } else {
                    checkBox.setChecked(false);
                }
                checkBox.setOnClickListener(new SingleClickListener() {
                    @Override
                    public void onSingleClick(@NonNull View v) {
                        if (mSingleMode) {
                            if (!EmptyUtil.isEmptyText(mSingleModeKey) && !mSingleModeKey.equals(checkBox.getText())) {
                                mChoiceMap.replace(mSingleModeKey, false);
                            }
                            mChoiceMap.replace(checkBox.getText(), true);
                            mSingleModeKey = checkBox.getText();
                            notifyDataSetChanged();
                        } else {
                            mChoiceMap.replace(checkBox.getText(), checkBox.isChecked());
                            notifyDataSetChanged();
                        }
                    }
                });
            }

            @NonNull
            @Override
            public View getLayoutByViewType(@NonNull ViewGroup parent, int viewType) {
                return LayoutInflater.from(mContext).inflate(R.layout.dialog_custom_choice_item, parent, false);
            }
        }
    }
}
