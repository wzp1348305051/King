package com.wzp.king.common.util;

import android.content.Context;

import com.wzp.king.common.bean.constant.ExceptionConstant;
import com.wzp.king.common.widget.sheet.BottomSheet;
import com.wzp.king.common.widget.sheet.BottomSheetBuilder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Sheet工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2019/7/9
 */

public class SheetUtil {

    private SheetUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    public static void showMenuSheet(@NonNull Context context, @Nullable String title,
                                     @NonNull CharSequence[] menuArray,
                                     @Nullable BottomSheet.MenuBuilder.MenuSelectListener listener) {
        showMenuSheet(context, title, menuArray, -1, listener);
    }

    public static void showMenuSheet(@NonNull Context context, @Nullable String title,
                                     @NonNull CharSequence[] menuArray, int checkedIndex,
                                     @Nullable BottomSheet.MenuBuilder.MenuSelectListener listener) {
        new BottomSheet.MenuBuilder(context)
                .setType(BottomSheetBuilder.TYPE_LIST)
                .setTitle(title)
                .setMenuArray(menuArray, listener)
                .setCheckedIndex(checkedIndex)
                .create()
                .show();
    }

    public static void showChoiceSheet(@NonNull Context context, @Nullable String title,
                                       @NonNull List<BottomSheet.ChoiceItem> choiceList,
                                       @Nullable BottomSheet.ChoiceBuilder.ChoiceSelectListener listener) {
        new BottomSheet.ChoiceBuilder(context)
                .setType(BottomSheetBuilder.TYPE_GRID)
                .setTitle(title)
                .setChoiceList(choiceList, listener)
                .create()
                .show();
    }

}
