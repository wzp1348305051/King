package com.wzp.king.common.bean.constant;

import android.annotation.SuppressLint;
import android.content.Context;

import com.wzp.king.common.util.AppUtil;

/**
 * 全局常量集合
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/1
 */

public class GlobalConstant {
    @SuppressLint("StaticFieldLeak")
    public static final Context APP_CONTEXT = AppUtil.getAppContext();
    public static final String PACKAGE_NAME = AppUtil.getPackageName();
    public static final String SEPARATOR_FILE = System.getProperty("file.separator");// 系统文件分隔符
    public static final String SEPARATOR_PATH = System.getProperty("path.separator");// 系统路径分隔符
    public static final String SEPARATOR_LINE = System.getProperty("line.separator");// 系统行分隔符
}
