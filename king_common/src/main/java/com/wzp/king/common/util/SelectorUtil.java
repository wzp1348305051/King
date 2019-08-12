package com.wzp.king.common.util;

import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import com.wzp.king.common.R;
import com.wzp.king.common.widget.selector.ColorSelector;
import com.wzp.king.common.widget.selector.ShapeSelector;

/**
 * UI选择器工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2019-08-12
 */

public class SelectorUtil {

    public static StateListDrawable getButtonSolidBlue() {
        int colorBlue = ResUtil.getColor(R.color.color_blue);
        int colorBlueLight = ResUtil.getColor(R.color.color_blue_light);

        return new ShapeSelector()
                .setShape(GradientDrawable.RECTANGLE)
                .setCornerRadius(4f)
                .setSolidColorNormal(colorBlue)
                .setSolidColorDisabled(colorBlueLight)
                .setSolidColorPressed(colorBlueLight)
                .create();
    }

    public static StateListDrawable getButtonStrokeBlue() {
        int colorBlue = ResUtil.getColor(R.color.color_blue);
        int colorBlueLight = ResUtil.getColor(R.color.color_blue_light);

        return new ShapeSelector()
                .setShape(GradientDrawable.RECTANGLE)
                .setCornerRadius(4f)
                .setStrokeColorNormal(colorBlue)
                .setStrokeColorDisabled(colorBlueLight)
                .setStrokeColorPressed(colorBlueLight)
                .create();
    }

    public static ColorStateList getColorBlue() {
        int colorBlue = ResUtil.getColor(R.color.color_blue);
        int colorBlueLight = ResUtil.getColor(R.color.color_blue_light);

        return new ColorSelector()
                .setColorNormal(colorBlue)
                .setColorDisabled(colorBlueLight)
                .setColorPressed(colorBlueLight)
                .create();
    }
}
