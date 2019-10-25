package com.wzp.king.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.wzp.king.common.R;
import com.wzp.king.common.R2;
import com.wzp.king.common.util.EmptyUtil;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 输入栏
 *
 * @author wengzhipeng
 * @version v1.0, 2019/10/22
 */

public class InputView extends RelativeLayout {
    @BindView(R2.id.tv_input_icon)
    IconTextView mTvIcon;
    @BindView(R2.id.et_input_edit)
    EditText mEtEdit;

    public InputView(@NonNull Context context) {
        this(context, null);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initContentView(context);
        bindContentView(context, attrs);
    }

    private void initContentView(@NonNull Context context) {
        View root = inflate(context, R.layout.wgt_input, this);
        ButterKnife.bind(this, root);

        setBackgroundResource(R.drawable.edit_stroke_grey);
    }

    private void bindContentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputView);
        String icon = typedArray.getString(R.styleable.InputView_input_icon);
        String hint = typedArray.getString(R.styleable.InputView_input_hint);
        typedArray.recycle();

        if (EmptyUtil.isEmptyText(icon)) {
            mTvIcon.setVisibility(GONE);
        } else {
            mTvIcon.setVisibility(VISIBLE);
            mTvIcon.setText(icon);
        }

        if (!EmptyUtil.isEmptyText(hint)) {
            mEtEdit.setHint(hint);
        }
    }

    public IconTextView getIconView() {
        return mTvIcon;
    }

    public void setIcon(@Nullable CharSequence text) {
        mTvIcon.setText(text);
    }

    public EditText getEditView() {
        return mEtEdit;
    }

    public void setHint(@Nullable CharSequence hint) {
        mEtEdit.setHint(hint);
    }
}
