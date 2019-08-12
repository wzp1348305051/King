package com.wzp.king.common.exception;

import com.wzp.king.common.util.AppUtil;
import com.wzp.king.common.util.L;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 自定义未捕捉异常处理
 *
 * @author wengzhipeng
 * @version v1.0, 2019/5/16
 */

public class ExceptionHandler implements UncaughtExceptionHandler {
    private static final String TAG = ExceptionHandler.class.getSimpleName();
    private UncaughtExceptionHandler mDefaultHandler;// 系统默认UncaughtException处理类

    private static class Holder {
        private static final ExceptionHandler INSTANCE = new ExceptionHandler();
    }

    private ExceptionHandler() {

    }

    public void init() {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static ExceptionHandler getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if (!handleException(throwable) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认异常处理器来处理
            mDefaultHandler.uncaughtException(thread, throwable);
        } else {
            // 自定义异常处理完成后续处理
            // 退出程序
            AppUtil.exitApp();
        }
    }

    /**
     * 自定义异常处理, 收集, 保存异常信息等操作
     *
     * @param throwable 异常信息
     * @return true（用户处理了异常信息）, false（用户未处理异常信息）
     */
    private boolean handleException(Throwable throwable) {
        if (throwable == null) {
            return false;
        }

        L.eTag(TAG, throwable);
        return true;
    }

}
