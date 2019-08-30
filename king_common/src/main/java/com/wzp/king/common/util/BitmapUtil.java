package com.wzp.king.common.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Base64;
import android.util.TypedValue;

import com.wzp.king.common.bean.constant.EmptyConstant;
import com.wzp.king.common.bean.constant.ExceptionConstant;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Bitmap工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2017/7/25
 */

public class BitmapUtil {
    private static final String TAG = BitmapUtil.class.getSimpleName();

    private BitmapUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    /**
     * 缩放图片
     */
    @Nullable
    public static Bitmap zoomBitmap(@Nullable Bitmap bitmap, int newWidth, int newHeight) {
        if (bitmap == null) {
            return null;
        }

        if (newWidth <= 0 || newHeight <= 0) {
            return bitmap;
        }

        // 获得图片宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            return bitmap;
        }

        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新图片
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    /**
     * 拼接图片
     *
     * @param bitmapOrigin 原始图片
     * @param bitmapFloat  浮层图片
     * @param left         浮层图片左坐标
     * @param top          浮层图片上坐标
     */
    @Nullable
    public static Bitmap composeBitmap(@Nullable Bitmap bitmapOrigin, @Nullable Bitmap bitmapFloat, int left, int top) {
        if (bitmapOrigin == null) {
            return null;
        }

        int originWidth = bitmapOrigin.getWidth();
        int originHeight = bitmapOrigin.getHeight();
        Rect originRect = new Rect(0, 0, originWidth, originHeight);
        if (originWidth <= 0 || originHeight <= 0 || bitmapFloat == null) {
            return bitmapOrigin;
        }

        try {
            Bitmap bitmap = Bitmap.createBitmap(originWidth, originHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(bitmapOrigin, originRect, originRect, null);
            canvas.drawBitmap(bitmapFloat, left, top, null);
            canvas.save();
            canvas.restore();
            return bitmap;
        } catch (Exception e) {
            return bitmapOrigin;
        }
    }

    /**
     * 保存图片到文件
     *
     * @param filePath 文件路径
     * @param bitmap   图片
     */
    public static boolean saveBitmapToFile(@Nullable String filePath, @Nullable Bitmap bitmap) {
        if (EmptyUtil.isEmptyText(filePath) || bitmap == null) {
            return false;
        }

        boolean success = false;
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(filePath));
            success = bitmap.compress(CompressFormat.JPEG, 100, outputStream);
        } catch (Exception e) {
            L.eTag(TAG, e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (Exception e) {
                    L.eTag(TAG, e);
                }
            }
        }
        return success;
    }

    @NonNull
    public static byte[] Bitmap2Bytes(@Nullable Bitmap bitmap) {
        if (bitmap == null) {
            return EmptyConstant.EMPTY_BYTE_ARRAY;
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, outputStream);
        return outputStream.toByteArray();
    }

    @Nullable
    public static Bitmap Bytes2Bimap(@Nullable byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * 将字符串转换成Bitmap类型
     */
    @Nullable
    public static Bitmap string2Bitmap(@Nullable String string) {
        if (EmptyUtil.isEmptyText(string)) {
            return null;
        }

        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            L.eTag(TAG, e);
        }
        return bitmap;
    }

    /**
     * 将Bitmap转换成字符串
     */
    @NonNull
    public static String bitmap2String(@Nullable Bitmap bitmap) {
        if (bitmap == null) {
            return EmptyConstant.EMPTY_STRING;
        }

        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    /**
     * 根据图片Id获取图片的Bitmap
     */
    @Nullable
    public static Bitmap getBitmapByResId(int resId) {
        Resources resources = ResUtil.getResources();
        TypedValue typedValue = new TypedValue();
        resources.openRawResource(resId, typedValue);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inTargetDensity = typedValue.density;
        return BitmapFactory.decodeResource(resources, resId, options);
    }

}
