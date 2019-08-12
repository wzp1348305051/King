package com.wzp.king.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
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
        if (EmptyUtil.isEmptyText(uri)) {
            Drawable drawable = ResUtil.getDrawable(error);
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            }
            return;
        }

        BitmapTypeRequest<String> request = Glide.with(imageView.getContext()).load(uri).asBitmap();
        if (ResUtil.getDrawable(holder) != null) {
            request.placeholder(holder);
        }
        if (ResUtil.getDrawable(error) != null) {
            request.error(error);
        }
        request.dontAnimate().dontTransform().sizeMultiplier(0.8f).into(imageView);
    }

    public static void loadImageBitmap(@NonNull ImageView imageView, @DrawableRes int resId, int holder, int error) {
        BitmapTypeRequest<Integer> request = Glide.with(imageView.getContext()).load(resId).asBitmap();
        if (ResUtil.getDrawable(holder) != null) {
            request.placeholder(holder);
        }
        if (ResUtil.getDrawable(error) != null) {
            request.error(error);
        }
        request.dontAnimate().dontTransform().sizeMultiplier(0.8f).into(imageView);
    }

    public static void loadRoundImageBitmap(@NonNull ImageView imageView, @Nullable String uri, int holder, int error) {
        if (EmptyUtil.isEmptyText(uri)) {
            Drawable drawable = ResUtil.getDrawable(error);
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            }
            return;
        }

        BitmapTypeRequest<String> request = Glide.with(imageView.getContext()).load(uri).asBitmap();
        if (ResUtil.getDrawable(holder) != null) {
            request.placeholder(holder);
        }
        if (ResUtil.getDrawable(error) != null) {
            request.error(error);
        }
        request.dontAnimate().dontTransform().sizeMultiplier(0.8f).into(new BitmapImageViewTarget(imageView) {
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
        BitmapTypeRequest<Integer> request = Glide.with(imageView.getContext()).load(resId).asBitmap();
        if (ResUtil.getDrawable(holder) != null) {
            request.placeholder(holder);
        }
        if (ResUtil.getDrawable(error) != null) {
            request.error(error);
        }
        request.dontAnimate().dontTransform().sizeMultiplier(0.8f).into(new BitmapImageViewTarget(imageView) {
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
            return Glide.with(context).load(uri).asBitmap().into(width, height).get();
        } catch (Exception e) {
            return null;
        }
    }
}
