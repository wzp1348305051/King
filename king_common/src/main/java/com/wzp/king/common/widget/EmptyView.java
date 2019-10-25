package com.wzp.king.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzp.king.common.R;
import com.wzp.king.common.R2;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 带空状态视图
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/6
 */

public class EmptyView extends LinearLayout {
    @BindView(R2.id.tv_empty_text)
    TextView mTvText;
    private View mContentView;

    public EmptyView(@NonNull Context context) {
        this(context, null);
    }

    public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initContentView(context);
        bindContentView(context, attrs);
    }

    private void initContentView(@NonNull Context context) {
        View root = inflate(context, R.layout.wgt_empty, this);
        ButterKnife.bind(this, root);

        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);
    }

    private void bindContentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EmptyView);
        String emptyText = typedArray.getString(R.styleable.EmptyView_empty_text);
        typedArray.recycle();

        mTvText.setText(emptyText);
    }

    public TextView getEmptyView() {
        return mTvText;
    }

    public void setEmptyText(@Nullable String emptyText) {
        mTvText.setText(emptyText);
    }

    public View getContentView() {
        return mContentView;
    }

    public void setContentView(@Nullable View contentView) {
        mContentView = contentView;
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);

        if (visibility == VISIBLE) {
            if (mContentView != null) {
                mContentView.setVisibility(GONE);
            }
        } else {
            if (mContentView != null) {
                mContentView.setVisibility(VISIBLE);
            }
        }
    }
}
