package com.wzp.king.common;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wzp.king.common.exception.ExceptionHandler;
import com.wzp.king.common.util.ActivityLifecycleManager;
import com.wzp.king.common.util.AppUtil;
import com.wzp.king.common.util.L;
import com.wzp.king.common.util.iconfont.IconFontUtil;

import androidx.multidex.MultiDexApplication;
import butterknife.ButterKnife;
import io.reactivex.plugins.RxJavaPlugins;
import me.yokeyword.fragmentation.Fragmentation;

/**
 * 自定义Application
 *
 * @author wengzhipeng
 * @version v1.0, 2018/3/13
 */

public class AppApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initLibrary();
        initThirdLibrary();
        initActivityLifecycle();
    }

    private void initLibrary() {
        AppUtil.init(this);
        ExceptionHandler.getInstance().init();
    }

    /**
     * 初始化三方库
     */
    private void initThirdLibrary() {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
        ButterKnife.setDebug(BuildConfig.DEBUG);
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .handleException(L::e)
                .install();
        IconFontUtil.init();
        RxJavaPlugins.setErrorHandler(L::e);
    }

    private void initActivityLifecycle() {
        ActivityLifecycleManager.getInstance().init(this);
    }
}
