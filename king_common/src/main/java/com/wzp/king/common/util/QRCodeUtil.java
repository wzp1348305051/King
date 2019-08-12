package com.wzp.king.common.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 二维码工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/20
 */

public class QRCodeUtil {

    /**
     * 生成二维码Bitmap
     *
     * @param content    内容
     * @param width      图片宽度，单位像素
     * @param height     图片高度，单位像素
     * @param bitmapLogo 二维码中心Logo图标，可为null
     */
    @Nullable
    public static Bitmap createQRBitmap(@Nullable String content, int width, int height, @Nullable Bitmap bitmapLogo) {
        if (EmptyUtil.isEmptyText(content)) {
            return null;
        }

        try {
            // 配置参数
            HashMap<EncodeHintType, Object> params = new HashMap<>();
            params.put(EncodeHintType.CHARACTER_SET, "utf-8");
            params.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 设置容错级别
            params.put(EncodeHintType.MARGIN, 2); // 设置空白边距宽度
            // 图像数据转换，使用矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE,
                    width, height, params);
            int[] pixels = new int[width * height];
            // 按照二维码算法，逐个生成二维码图片，两个for循环是图片横列扫描结果
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = 0xFF000000;
                    } else {
                        pixels[y * width + x] = 0xFFFFFFFF;
                    }
                }
            }
            // 生成二维码图片格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            if (bitmapLogo == null) {
                return bitmap;
            } else {
                return addLogo(bitmap, bitmapLogo);
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 在二维码中间添加Logo图案
     */
    private static Bitmap addLogo(@NonNull Bitmap bitmapOrigin, @NonNull Bitmap bitmapLogo) {
        int originWidth = bitmapOrigin.getWidth();
        int originHeight = bitmapOrigin.getHeight();
        int logoWidth = bitmapLogo.getWidth();
        int logoHeight = bitmapLogo.getHeight();

        if (originWidth == 0 || originHeight == 0 || logoWidth == 0 || logoHeight == 0) {
            return bitmapOrigin;
        }

        // logo大小为二维码整体大小的2/13
        float scale = originWidth / 6.5f / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(originWidth, originHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(bitmapOrigin, 0, 0, null);
            canvas.scale(scale, scale, originWidth * 1.0f / 2, originHeight * 1.0f / 2);
            canvas.drawBitmap(bitmapLogo, (originWidth - logoWidth) * 1.0f / 2,
                    (originHeight - logoHeight) * 1.0f / 2, null);
            canvas.save();
            canvas.restore();
            return bitmap;
        } catch (Exception e) {
            return bitmapOrigin;
        }
    }
}

