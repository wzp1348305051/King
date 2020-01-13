package com.wzp.king.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.wzp.king.common.bean.constant.ExceptionConstant;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

/**
 * Glide工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/18
 */

public class GlideUtil {

    private GlideUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    public static void loadImageBitmap(@NonNull ImageView imageView, @Nullable String uri, int holder, int error) {
        RequestOptions options = new RequestOptions();
        if (ResUtil.getDrawable(holder) != null) {
            options.placeholder(holder);
        }
        if (ResUtil.getDrawable(error) != null) {
            options.error(error);
        }
        Glide.with(imageView).asBitmap().load(uri).apply(options).dontAnimate().dontTransform().sizeMultiplier(0.8f).into(imageView);
    }

    public static void loadImageBitmap(@NonNull ImageView imageView, @DrawableRes int resId, int holder, int error) {
        RequestOptions options = new RequestOptions();
        if (ResUtil.getDrawable(holder) != null) {
            options.placeholder(holder);
        }
        if (ResUtil.getDrawable(error) != null) {
            options.error(error);
        }
        Glide.with(imageView).asBitmap().load(resId).apply(options).dontAnimate().dontTransform().sizeMultiplier(0.8f).into(imageView);
    }

    public static void loadRoundImageBitmap(@NonNull ImageView imageView, @Nullable String uri, int holder, int error) {
        RequestOptions options = new RequestOptions();
        if (ResUtil.getDrawable(holder) != null) {
            options.placeholder(holder);
        }
        if (ResUtil.getDrawable(error) != null) {
            options.error(error);
        }
        Glide.with(imageView).asBitmap().load(uri).apply(options).dontAnimate().dontTransform().sizeMultiplier(0.8f).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(imageView.getResources(),
                        resource);
                drawable.setCircular(true);
                imageView.setImageDrawable(drawable);
            }
        });
    }

    public static void loadRoundImageBitmap(@NonNull ImageView imageView, @DrawableRes int resId, int holder, int error) {
        RequestOptions options = new RequestOptions();
        if (ResUtil.getDrawable(holder) != null) {
            options.placeholder(holder);
        }
        if (ResUtil.getDrawable(error) != null) {
            options.error(error);
        }
        Glide.with(imageView).asBitmap().load(resId).apply(options).dontAnimate().dontTransform().sizeMultiplier(0.8f).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(imageView.getResources(),
                        resource);
                drawable.setCircular(true);
                imageView.setImageDrawable(drawable);
            }
        });
    }

    @Nullable
    public static Bitmap getImageBitmap(@NonNull Context context, @NonNull String uri, int width, int height) {
        try {
            return Glide.with(context).asBitmap().load(uri).submit(width, height).get();
        } catch (Exception e) {
            return null;
        }
    }
}
