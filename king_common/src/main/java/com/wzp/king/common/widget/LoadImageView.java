package com.wzp.king.common.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.wzp.king.common.R;
import com.wzp.king.common.util.GlideUtil;

import androidx.annotation.AttrRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * 自动加载图片ImageView
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/1
 */

public class LoadImageView extends AppCompatImageView {
    public LoadImageView(@NonNull Context context) {
        this(context, null);
    }

    public LoadImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadImageView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void load(@DrawableRes int resId) {
        load(resId, R.drawable.image_holder, R.drawable.image_error);
    }

    public void load(@DrawableRes int resId, @DrawableRes int holder, @DrawableRes int error) {
        GlideUtil.loadImageBitmap(this, resId, holder, error);
    }

    public void load(@Nullable String uri) {
        load(uri, R.drawable.image_holder, R.drawable.image_error);
    }

    public void load(@Nullable String uri, @DrawableRes int holder, @DrawableRes int error) {
        GlideUtil.loadImageBitmap(this, uri, holder, error);
    }

    public void loadRound(@DrawableRes int resId) {
        loadRound(resId, R.drawable.image_holder, R.drawable.image_error);
    }

    public void loadRound(@DrawableRes int resId, @DrawableRes int holder, @DrawableRes int error) {
        GlideUtil.loadRoundImageBitmap(this, resId, holder, error);
    }

    public void loadRound(@Nullable String uri) {
        loadRound(uri, R.drawable.image_holder, R.drawable.image_error);
    }

    public void loadRound(@Nullable String uri, @DrawableRes int holder, @DrawableRes int error) {
        GlideUtil.loadRoundImageBitmap(this, uri, holder, error);
    }

}
