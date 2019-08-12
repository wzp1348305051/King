package com.wzp.king.common.util;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;

import com.wzp.king.common.bean.constant.EmptyConstant;
import com.wzp.king.common.bean.constant.ExceptionConstant;
import com.wzp.king.common.bean.constant.GlobalConstant;

import java.io.InputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * 资源获取工具类
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/18
 */

public class ResUtil {
    public static final String ANIM = "anim";
    public static final String ANIMATOR = "animator";
    public static final String ARRAY = "array";
    public static final String ATTR = "attr";
    public static final String BOOL = "bool";
    public static final String COLOR = "color";
    public static final String DIMEN = "dimen";
    public static final String DRAWABLE = "drawable";
    public static final String ID = "id";
    public static final String INTEGER = "integer";
    public static final String LAYOUT = "layout";
    public static final String MENU = "menu";
    public static final String MIPMAP = "mipmap";
    public static final String STRING = "string";
    public static final String STYLE = "style";
    public static final String STYLEABLE = "styleable";

    private ResUtil() {
        throw new UnsupportedOperationException(ExceptionConstant.EXCEPTION_INVALID_INIT);
    }

    /**
     * 获取应用Resources对象
     *
     * @return 应用Resources对象
     */
    @NonNull
    public static Resources getResources() {
        return GlobalConstant.APP_CONTEXT.getResources();
    }

    /**
     * 根据资源名,资源类型,应用包名获取资源Id
     *
     * @param name        资源名
     * @param defType     资源类型
     * @param packageName 应用包名
     * @return 资源Id
     */
    public static int getResId(@Nullable String name, @Nullable String defType, @Nullable String packageName) {
        if (EmptyUtil.isEmptyText(name) || EmptyUtil.isEmptyText(defType) || EmptyUtil.isEmptyText(packageName)) {
            return EmptyConstant.EMPTY_INT;
        }

        return getResources().getIdentifier(name, defType, packageName);
    }

    /**
     * 根据资源名,资源类型获取本地资源Id
     *
     * @param name    资源名
     * @param defType 资源类型
     * @return 资源Id
     */
    public static int getLocalResId(@Nullable String name, @Nullable String defType) {
        return getResId(name, defType, AppUtil.getPackageName());
    }

    /**
     * 根据资源名,资源类型获取系统资源Id
     *
     * @param name    资源名
     * @param defType 资源类型
     * @return 资源Id
     */
    public static int getSystemResId(@Nullable String name, @Nullable String defType) {
        return getResId(name, defType, "android");
    }

    /**
     * 根据string资源Id获取对应string值
     *
     * @param resId 资源Id
     * @return 资源Id对应的资源值
     */
    @NonNull
    public static String getString(int resId) {
        try {
            return getResources().getString(resId);
        } catch (NotFoundException e) {
            return EmptyConstant.EMPTY_STRING;
        }
    }

    /**
     * 根据string资源名获取对应string值
     *
     * @param name 资源名
     * @return 资源名对应的资源值
     */
    @NonNull
    public static String getString(@Nullable String name) {
        return getString(getLocalResId(name, "string"));
    }

    /**
     * 根据string数组资源Id获取对应string数组值
     *
     * @param resId 资源Id
     * @return 资源Id对应的资源值
     */
    @NonNull
    public static String[] getStringArray(int resId) {
        try {
            return getResources().getStringArray(resId);
        } catch (NotFoundException e) {
            return EmptyConstant.EMPTY_STRING_ARRAY;
        }
    }

    /**
     * 根据string数组资源名获取对应string数组值
     *
     * @param name 资源名
     * @return 资源名对应的资源值
     */
    @NonNull
    public static String[] getStringArray(@Nullable String name) {
        return getStringArray(getLocalResId(name, "array"));
    }

    /**
     * 根据integer资源Id获取对应integer值
     *
     * @param resId 资源Id
     * @return 资源Id对应的资源值
     */
    public static int getInteger(int resId) {
        try {
            return getResources().getInteger(resId);
        } catch (NotFoundException e) {
            return EmptyConstant.EMPTY_INT;
        }
    }

    /**
     * 根据integer资源名获取对应integer值
     *
     * @param name 资源名
     * @return 资源名对应的资源值
     */
    public static int getInteger(@Nullable String name) {
        return getInteger(getLocalResId(name, "integer"));
    }

    /**
     * 根据integer数组资源Id获取对应integer数组值
     *
     * @param resId 资源Id
     * @return 资源Id对应的资源值
     */
    @NonNull
    public static int[] getIntegerArray(int resId) {
        try {
            return getResources().getIntArray(resId);
        } catch (NotFoundException e) {
            return EmptyConstant.EMPTY_INT_ARRAY;
        }
    }

    /**
     * 根据integer数组资源名获取对应integer数组值
     *
     * @param name 资源名
     * @return 资源名对应的资源值
     */
    @NonNull
    public static int[] getIntegerArray(@Nullable String name) {
        return getIntegerArray(getLocalResId(name, "array"));
    }

    /**
     * 根据boolean资源Id获取对应boolean值
     *
     * @param resId 资源Id
     * @return 资源Id对应的资源值
     */
    public static boolean getBoolean(int resId) {
        try {
            return getResources().getBoolean(resId);
        } catch (NotFoundException e) {
            return EmptyConstant.EMPTY_BOOLEAN;
        }
    }

    /**
     * 根据boolean资源名获取对应boolean值
     *
     * @param name 资源名
     * @return 资源名对应的资源值
     */
    public static boolean getBoolean(@Nullable String name) {
        return getBoolean(getLocalResId(name, "bool"));
    }

    /**
     * 根据drawable资源Id获取对应drawable值
     *
     * @param resId 资源Id
     * @return 资源Id对应的资源值
     */
    @Nullable
    public static Drawable getDrawable(int resId) {
        try {
            return ContextCompat.getDrawable(GlobalConstant.APP_CONTEXT, resId);
        } catch (NotFoundException e) {
            return null;
        }
    }

    /**
     * 根据drawable资源名获取对应drawable值
     *
     * @param name 资源名
     * @return 资源名对应的资源值
     */
    @Nullable
    public static Drawable getDrawable(@Nullable String name) {
        return getDrawable(getLocalResId(name, "drawable"));
    }

    /**
     * 根据color资源Id获取对应color值
     *
     * @param resId 资源Id
     * @return 资源Id对应的资源值
     */
    public static int getColor(int resId) {
        try {
            return ContextCompat.getColor(GlobalConstant.APP_CONTEXT, resId);
        } catch (NotFoundException e) {
            return EmptyConstant.EMPTY_INT;
        }
    }

    /**
     * 根据color资源名获取对应color值
     *
     * @param name 资源名
     * @return 资源名对应的资源值
     */
    public static int getColor(@Nullable String name) {
        return getColor(getLocalResId(name, "color"));
    }

    /**
     * 根据xml资源Id获取对应xml文件
     *
     * @param resId 资源Id
     * @return 资源Id对应的资源值
     */
    @Nullable
    public static XmlResourceParser getXml(int resId) {
        try {
            return getResources().getXml(resId);
        } catch (NotFoundException e) {
            return null;
        }
    }

    /**
     * 根据xml资源名获取对应xml文件
     *
     * @param name 资源名
     * @return 资源名对应的资源值
     */
    @Nullable
    public static XmlResourceParser getXml(@Nullable String name) {
        return getXml(getLocalResId(name, "xml"));
    }

    /**
     * 根据dimen资源Id获取对应dimen值（绝对尺寸）
     *
     * @param resId 资源Id
     * @return 资源Id对应的资源值
     */
    public static float getDimension(int resId) {
        try {
            return getResources().getDimension(resId);
        } catch (NotFoundException e) {
            return EmptyConstant.EMPTY_FLOAT;
        }
    }

    /**
     * 根据dimen资源名获取对应dimen值（绝对尺寸）
     *
     * @param name 资源名
     * @return 资源名对应的资源值
     */
    public static float getDimension(@Nullable String name) {
        return getDimension(getLocalResId(name, "dimen"));
    }

    /**
     * 根据dimen资源Id获取对应dimen值（绝对尺寸,截断getDimension的小数位）
     *
     * @param resId 资源Id
     * @return 资源Id对应的资源值
     */
    public static int getDimensionPixelOffset(int resId) {
        try {
            return getResources().getDimensionPixelOffset(resId);
        } catch (NotFoundException e) {
            return EmptyConstant.EMPTY_INT;
        }
    }

    /**
     * 根据dimen资源名获取对应dimen值（绝对尺寸,截断getDimension的小数位）
     *
     * @param name 资源名
     * @return 资源名对应的资源值
     */
    public static int getDimensionPixelOffset(@Nullable String name) {
        return getDimensionPixelOffset(getLocalResId(name, "dimen"));
    }

    /**
     * 根据dimen资源Id获取对应dimen值（绝对尺寸）
     *
     * @param resId 资源Id
     * @return 资源Id对应的资源值
     */
    public static int getDimensionPixelSize(int resId) {
        try {
            return getResources().getDimensionPixelSize(resId);
        } catch (NotFoundException e) {
            return EmptyConstant.EMPTY_INT;
        }
    }

    /**
     * 根据dimen资源名获取对应dimen值（绝对尺寸）
     *
     * @param name 资源名
     * @return 资源名对应的资源值
     */
    public static int getDimensionPixelSize(@Nullable String name) {
        return getDimensionPixelSize(getLocalResId(name, "dimen"));
    }

    /**
     * 获取AssetManager读取asserts文件夹下资源（可以有目录结构,不会被映射到R文件中）
     */
    @NonNull
    public static AssetManager getAssets() {
        return getResources().getAssets();
    }

    /**
     * 根据raw资源Id获取对应文件输入流
     *
     * @param resId 资源Id
     * @return 资源Id对应的资源值
     */
    @Nullable
    public static InputStream openRawResource(int resId) {
        try {
            return getResources().openRawResource(resId);
        } catch (NotFoundException e) {
            return null;
        }
    }

    /**
     * 根据raw资源名获取对应文件输入流
     *
     * @param name 资源名
     * @return 资源名对应的资源值
     */
    @Nullable
    public static InputStream openRawResource(@Nullable String name) {
        return openRawResource(getLocalResId(name, "raw"));
    }

}
